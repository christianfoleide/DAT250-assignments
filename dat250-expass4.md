## Hand-in report DAT250 assignment 4

### Task 2 - implementing REST Api for TODO-items

Code is found inside the `./spark-java` folder.

Usage

#### Creating TODO-items:

```
POST /todos
body:

{
      "summary":     "Some summary",
      "description": "Some description",
}
```

#### Getting all TODO-items:

```terminal
GET /todos

Response:

[{...}, {...}]
```

#### Getting a TOOD-item with id 1:

```terminal
GET /todos/1

Response:
{
      "id": 1,
      "summary": "...",
      "description": "..."
}
```

#### Deleting a TODO-item with id 1:
```terminal
DELETE /todos/1
```

#### Updating a TODO-item with id 1:
```terminal
PUT /todos/1

Body:
{
      "summary": "some new summary",
      "description": "some new description"
}
```

#### Issues
I had no issues using the Persistence API with Derby, or Spark.
