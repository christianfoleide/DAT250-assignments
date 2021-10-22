package main

import (
	"log"

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
		"helloworld",
		"",
		true,
		false,
		false,
		false,
		nil,
	)
	if err != nil {
		log.Fatalf("failed to consume: %+v", err)
	}

	for msg := range msgs {
		log.Println(string(msg.Body))
	}
}
