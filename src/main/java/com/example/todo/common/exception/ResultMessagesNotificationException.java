package com.example.todo.common.exception;

import com.example.todo.common.message.ResultMessage;

public class ResultMessagesNotificationException extends RuntimeException {
    private final ResultMessage resultMessage;

    protected ResultMessagesNotificationException(ResultMessage resultMessage) {
        super(resultMessage.toString());
        this.resultMessage = resultMessage;
    }

    public String getMessage() {
        return this.resultMessage.message();
    }

    public ResultMessage getResultMessage() {
        return this.resultMessage;
    }
}
