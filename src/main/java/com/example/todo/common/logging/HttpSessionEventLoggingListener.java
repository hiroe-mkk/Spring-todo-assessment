package com.example.todo.common.logging;


import jakarta.servlet.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HttpSessionEventLoggingListener implements HttpSessionListener, HttpSessionAttributeListener, HttpSessionActivationListener {
    private static final Logger logger = LoggerFactory.getLogger(HttpSessionEventLoggingListener.class);

    public void sessionWillPassivate(HttpSessionEvent se) {
        if (logger.isDebugEnabled()) {
            HttpSession session = se.getSession();
            logger.debug("SESSIONID#{} sessionWillPassivate : {}", session.getId(), se.getSource());
        }

    }

    public void sessionDidActivate(HttpSessionEvent se) {
        if (logger.isDebugEnabled()) {
            HttpSession session = se.getSession();
            logger.debug("SESSIONID#{} sessionDidActivate : {}", session.getId(), se.getSource());
        }

    }

    public void attributeAdded(HttpSessionBindingEvent se) {
        if (logger.isDebugEnabled()) {
            HttpSession session = se.getSession();
            logger.debug("SESSIONID#{} attributeAdded : {}={}", new Object[]{session.getId(), se.getName(), se.getValue()});
        }

    }

    public void attributeRemoved(HttpSessionBindingEvent se) {
        if (logger.isDebugEnabled()) {
            HttpSession session = se.getSession();
            logger.debug("SESSIONID#{} attributeRemoved : {}={}", new Object[]{session.getId(), se.getName(), se.getValue()});
        }

    }

    public void attributeReplaced(HttpSessionBindingEvent se) {
        if (logger.isTraceEnabled()) {
            HttpSession session = se.getSession();
            logger.trace("SESSIONID#{} attributeReplaced : {}={}", new Object[]{session.getId(), se.getName(), se.getValue()});
        }

    }

    public void sessionCreated(HttpSessionEvent se) {
        if (logger.isDebugEnabled()) {
            HttpSession session = se.getSession();
            logger.debug("SESSIONID#{} sessionCreated : {}", session.getId(), se.getSource());
        }

    }

    public void sessionDestroyed(HttpSessionEvent se) {
        if (logger.isDebugEnabled()) {
            HttpSession session = se.getSession();
            logger.debug("SESSIONID#{} sessionDestroyed : {}", session.getId(), se.getSource());
        }

    }
}
