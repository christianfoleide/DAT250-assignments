package no.hvl.dat250.spark.controller;

import com.google.gson.Gson;
import no.hvl.dat250.spark.entities.Todo;
import no.hvl.dat250.spark.service.TodoService;
import org.eclipse.jetty.http.HttpStatus;

import static spark.Spark.*;

public class TodoController {

    private final TodoService ts;

    public TodoController() {
        ts = new TodoService();
    }

    public void listenAndServe(int port) {
        port(port);

        // Set content-type header on every response
        before((req, res) -> {
           res.header("Content-Type", "application/json");
        });

        get("/todos", (req, res) -> {
            res.status(HttpStatus.OK_200);
           return ts.getTodosJSON();
        });

        get("/todos/:id", (req, res) -> {
            Long id = Long.valueOf(req.params(":id"));
            String response = ts.getTodoByIdJSON(id);
            if (response.equals("")) {
                res.status(HttpStatus.NOT_FOUND_404);
                return "404 Not found";
            }
            return response;
        });

        post("/todos", (req, res) -> {
           Todo t = new Gson().fromJson(req.body(), Todo.class);
           if (t == null) {
               res.status(HttpStatus.UNPROCESSABLE_ENTITY_422);
               return "422 Unprocessable entity";
           }
           ts.insertTodo(t);
           res.status(HttpStatus.CREATED_201);
           return "201 Created";
        });

        put("/todos/:id", (req, res) -> {
            Long id = Long.valueOf(req.params(":id"));
           Todo newTodo = new Gson().fromJson(req.body(), Todo.class);
            if (ts.updateTodo(id, newTodo)) {
                res.status(HttpStatus.OK_200);
                return "200 OK";
            }
            res.status(HttpStatus.NOT_FOUND_404);
            return "404 Not found";
        });

        delete("/todos/:id", (req, res) -> {
            Long id = Long.valueOf(req.params(":id"));
            if (ts.deleteTodo(id)) {
                res.status(HttpStatus.NO_CONTENT_204);
                return "204 Deleted";
            }
            res.status(HttpStatus.BAD_REQUEST_400);
            return "404 Not found";
        });

    }
}
