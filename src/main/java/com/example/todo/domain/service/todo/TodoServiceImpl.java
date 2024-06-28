package com.example.todo.domain.service.todo;

import com.example.todo.common.exception.BusinessException;
import com.example.todo.common.exception.ResourceNotFoundException;
import com.example.todo.common.message.ResultMessage;
import com.example.todo.domain.model.todo.Todo;
import com.example.todo.domain.repository.todo.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    private static final long MAX_UNFINISHED_COUNT = 5;

    @Autowired
    TodoRepository todoRepository;

    @Override
    @Transactional(readOnly = true)
    public Collection<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo create(Todo todo) {
        long unfinishedCount = todoRepository.countByFinished(false);
        if (unfinishedCount >= MAX_UNFINISHED_COUNT) {
            ResultMessage resultMessage = ResultMessage.error("[E001] The count of un-finished Todo must not be over " + MAX_UNFINISHED_COUNT + ".");
            throw new BusinessException(resultMessage);
        }

        String todoId = UUID.randomUUID().toString();
        Date createdAt = new Date();

        todo.setTodoId(todoId);
        todo.setCreatedAt(createdAt);
        todo.setFinished(false);

        todoRepository.create(todo);

        return todo;
    }

    @Override
    public Todo finish(String todoId) {
        Todo todo = findOne(todoId);
        if (todo.isFinished()) {
            ResultMessage resultMessage = ResultMessage.error("[E002] The requested Todo is already finished. (id=" + todoId + ")");
            throw new BusinessException(resultMessage);
        }

        todo.setFinished(true);
        todoRepository.update(todo);
        return todo;
    }

    @Override
    public void delete(String todoId) {
        Todo todo = findOne(todoId);
        todoRepository.delete(todo);
    }


    private Todo findOne(String todoId) {
        Todo todo = todoRepository.findById(todoId);
        if (todo == null) {
            ResultMessage resultMessage = ResultMessage.error("[E404] The requested Todo is not found. (id=" + todoId + ")");
            throw new ResourceNotFoundException(resultMessage);
        }

        return todo;
    }
}
