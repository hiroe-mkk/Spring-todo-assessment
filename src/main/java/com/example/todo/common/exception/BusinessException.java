package com.example.todo.common.exception;

import com.example.todo.common.message.ResultMessage;

public class BusinessException extends ResultMessagesNotificationException {
    public BusinessException(ResultMessage resultMessage) {
        super(resultMessage);
    }
}
