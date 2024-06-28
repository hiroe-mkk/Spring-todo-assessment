package com.example.todo.common.exception;

import com.example.todo.common.message.ResultMessage;

public class ResourceNotFoundException extends ResultMessagesNotificationException {
    public ResourceNotFoundException(ResultMessage resultMessage) {
        super(resultMessage);
    }
}
