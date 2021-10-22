### Work queues

  - Send tasks to a receiver which uses a few seconds to process the task.
  - To start - **durable** was set to **false**:
    - messages currently on the broker will be lost upon node shutdown.
- Fix:
  - set **durable** to **true** when declaring queue
  - consumer: set **auto-ack** to **false** (and ack later yourself)
  - producer: set deliverymode to **persistent**.

Broker didn't care which consumer it sent messages to:
  - bad if a consumer is given many messages while still processing previous ones.
  - fix:
    - set **pre-fetch count** to 1. 