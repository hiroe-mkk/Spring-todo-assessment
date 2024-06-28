package com.example.todo.common.message;

import java.io.Serializable;

public record ResultMessage(com.example.todo.common.message.ResultMessage.ResultMessageType type, String message) implements Serializable {

    public static ResultMessage success(String message) {
        return new ResultMessage(ResultMessageType.SUCCESS, message);
    }

    public static ResultMessage info(String message) {
        return new ResultMessage(ResultMessageType.INFO, message);
    }

    public static ResultMessage warning(String message) {
        return new ResultMessage(ResultMessageType.WARNING, message);
    }

    public static ResultMessage error(String message) {
        return new ResultMessage(ResultMessageType.ERROR, message);
    }

    public static ResultMessage danger(String message) {
        return new ResultMessage(ResultMessageType.DANGER, message);
    }

    public String toString() {
        return "ResultMessages [type=" + this.type + ", message=" + this.message + "]";
    }

    enum ResultMessageType {
        SUCCESS("success"),
        INFO("info"),
        WARNING("warning"),
        ERROR("error"),
        DANGER("danger");

        private final String type;

        ResultMessageType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

        public String toString() {
            return this.type;
        }
    }
}
