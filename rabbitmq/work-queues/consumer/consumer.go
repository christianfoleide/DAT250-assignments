package main

import (
	"bytes"
	"log"
	"time"

	"github.com/streadway/amqp"
)

func main() {
	conn, err := amqp.Dial("amqp:guest:guest@localhost:5672/")
	if err != nil {
		log.Fatalf("failed to connect to broker: %+v", err)
	}
	defer conn.Close()

	ch, err := conn.Channel()
	if err != nil {
		log.Fatalf("failed to acquire channel: %+v", err)
	}
	defer ch.Close()

	msgs, err := ch.Consume(
		"task_queue", // queue
		"",           // consumer
		false,        // auto-ack
		false,        // exclusive
		false,        // no-local
		false,        // no-wait
		nil,          // args
	)
	if err != nil {
		log.Fatalf("failed to consume on queue: %+v", err)
	}

	forever := make(chan bool, 1)
	go func() {
		for msg := range msgs {
			log.Printf("Received msg: %s\n", msg.Body)
			t := time.Duration(dotCount(msg.Body))
			time.Sleep(time.Second * t)
			log.Printf("Done processing msg")
			msg.Ack(false)
		}
	}()
	<-forever
}

func dotCount(msg []byte) int {
	return bytes.Count(msg, []byte("."))
}
