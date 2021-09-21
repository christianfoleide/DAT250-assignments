package no.hvl.dat250.spark.service;

import com.google.gson.Gson;
import no.hvl.dat250.spark.entities.Todo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

public class TodoService {

    private static final String PERSISTENCE_UNIT_NAME = "todos"; // "people"
    private final EntityManagerFactory emf;

    public TodoService() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public boolean insertTodo(Todo t) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public String getTodosJSON() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("select t from Todo t");
        List<Todo> todos = q.getResultList();
        em.close();
        return new Gson().toJson(todos);
    }

    public String getTodoByIdJSON(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Todo t = em.find(Todo.class, id);
        if (Objects.isNull(t)) {
            em.close();
            return "";
        }
        em.close();
        return new Gson().toJson(t);
    }

    public boolean updateTodo(Long id, Todo newTodo) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Todo oldTodo = em.find(Todo.class, id);
        if (Objects.isNull(oldTodo)) {
            return false;
        }
        oldTodo.setDescription(newTodo.getDescription());
        oldTodo.setSummary(newTodo.getSummary());
        em.persist(oldTodo);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean deleteTodo(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Todo oldTodo = em.find(Todo.class, id);
        if (Objects.isNull(oldTodo)) {
            return false;
        }
        em.remove(oldTodo);
        em.getTransaction().commit();
        em.close();
        return true;
    }
}
