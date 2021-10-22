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

	q, err := ch.QueueDeclare("helloworld", false, false, false, false, nil)
	if err != nil {
		log.Fatalf("failed to declare queue")
	}

	log.Printf("%+v\n", q)

	if err := ch.Publish("", "helloworld", false, false, amqp.Publishing{
		ContentType: "text/plain",
		Body:        []byte("Hello, world!"),
	}); err != nil {
		log.Fatalf("failed to publish: %+v", err)
	}
}
