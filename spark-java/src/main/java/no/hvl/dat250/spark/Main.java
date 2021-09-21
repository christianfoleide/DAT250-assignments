package no.hvl.dat250.spark;

import no.hvl.dat250.spark.controller.TodoController;

public class Main {
    public static void main(String[] args) {
        TodoController tc = new TodoController();
        tc.listenAndServe(8080);
    }
}
