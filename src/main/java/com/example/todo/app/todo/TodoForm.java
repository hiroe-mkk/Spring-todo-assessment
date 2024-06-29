package com.example.todo.app.todo;

import com.example.todo.domain.model.todo.Todo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class TodoForm implements Serializable {

    public static interface TodoCreate {
    }

    public static interface TodoFinish {
    }

    public static interface TodoDelete {
    }

    private static final long serialVersionUID = 1L;

    @NotNull(groups = {TodoFinish.class, TodoDelete.class})
    private String todoId;

    @NotNull(groups = {TodoCreate.class})
    @Size(min = 1, max = 30, groups = {TodoCreate.class})
    private String todoTitle;

    public String getTodoId() {
        return todoId;
    }

    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public Todo convertToTodo() {
        Todo todo = new Todo();
        todo.setTodoId(this.todoId);
        todo.setTodoTitle(this.todoTitle);
        return todo;
    }
}
