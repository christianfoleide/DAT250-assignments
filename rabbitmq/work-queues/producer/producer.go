package main

import (
	"log"

	"github.com/streadway/amqp"
)

var msgs = []string{"task1..", "task2.", "task3...", "task4..", "task5..."}

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

	q, err := ch.QueueDeclare(
		"task_queue", // queue name
		true,         // durable
		false,        // delete when unused
		false,        // exclusive
		false,        // no-wait
		nil,          // arguments
	)
	if err != nil {
		log.Fatalf("failed to declare queue")
	}
	err = ch.Qos(
		1,     // prefetch count
		0,     // prefetch size
		false, // global
	)
	log.Printf("%+v\n", q)
	for _, msg := range msgs {
		if err := ch.Publish(
			"",           // exchange
			"task_queue", // routing key
			false,        // mandatory
			false,
			amqp.Publishing{
				DeliveryMode: amqp.Persistent, // persist if/when node dies
				ContentType:  "text/plain",
				Body:         []byte(msg),
			},
		); err != nil {
			log.Fatalf("failed to publish: %+v", err)
		}
	}
}
