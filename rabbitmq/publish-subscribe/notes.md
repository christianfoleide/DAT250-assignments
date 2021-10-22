### RabbitMQ exchanges

- Pushes messages to different queues.
- A producer can only send to an exchange, ***not to a queue directly***.
- Q: Which queue to route the message to? A: **exchange type** and **binding** :arrow_down:

##### Exchange type

- Direct, topic, headers, **fanout**.

##### Fanout

- Broadcasts messages to all the queues it knows.
- Must **bind** an exchange to queues in order to `fanout` (broadcast) the messages from the exchange to each bound queue.

##### Bindings

A binding is a relationship between an exchange and a queue.

Implementation must contain:
      - Declaring a **custom exchange** (contrary to default from before)
      - ***Binding*** said exchange to relevant queues


