package com.example.todo.domain.repository.todo;

import com.example.todo.domain.model.todo.Todo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

@Mapper
public interface TodoRepository {
    Todo findById(String todoId);

    Collection<Todo> findAll();

    void create(Todo todo);

    boolean update(Todo todo);

    void delete(Todo todo);

    long countByFinished(boolean finished);
}
