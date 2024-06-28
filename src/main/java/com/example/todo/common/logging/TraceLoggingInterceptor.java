package com.example.todo.common.logging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TraceLoggingInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(TraceLoggingInterceptor.class);
    private static final String START_ATTR = TraceLoggingInterceptor.class.getName() + ".startTime";
    private static final String HANDLING_ATTR = TraceLoggingInterceptor.class.getName() + ".handlingTime";
    private static final long DEFAULT_WARN_NANOS = TimeUnit.SECONDS.toNanos(3L);
    private long warnHandlingNanos = DEFAULT_WARN_NANOS;

    public void setWarnHandlingNanos(long warnHandlingNanos) {
        this.warnHandlingNanos = warnHandlingNanos;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod handlerMethod) {
            if (logger.isTraceEnabled()) {
                logger.trace("[START CONTROLLER] {}.{}({})",
                        className(handlerMethod), methodName(handlerMethod), buildMethodParams(handlerMethod));
            }

            request.setAttribute(START_ATTR, System.nanoTime());
        }

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (handler instanceof HandlerMethod handlerMethod) {
            long startTime = 0L;
            if (request.getAttribute(START_ATTR) != null) {
                startTime = (Long) request.getAttribute(START_ATTR);
                request.removeAttribute(START_ATTR);
            }
            long handlingTime = System.nanoTime() - startTime;
            request.setAttribute(HANDLING_ATTR, handlingTime);

            boolean isWarnHandling = handlingTime > this.warnHandlingNanos;
            if (!this.isEnabledLogLevel(isWarnHandling)) return;

            Object view = null;
            Map<String, Object> model = null;
            if (modelAndView != null) {
                view = modelAndView.getView();
                if (view == null) {
                    view = modelAndView.getViewName();
                }
                model = modelAndView.getModel();
            }
            logger.trace("[END CONTROLLER] {}.{}({})-> view={}, model={}",
                    className(handlerMethod), methodName(handlerMethod), buildMethodParams(handlerMethod),
                    view, model);

            String handlingTimeMessage = "[HANDLING TIME] {}.{}({})-> {} ns";
            if (isWarnHandling) {
                logger.warn(handlingTimeMessage + " > {}",
                        className(handlerMethod), methodName(handlerMethod), buildMethodParams(handlerMethod),
                        String.format("%1$,3d", handlingTime),
                        this.warnHandlingNanos);
            } else {
                logger.trace(handlingTimeMessage,
                        className(handlerMethod), methodName(handlerMethod), buildMethodParams(handlerMethod),
                        String.format("%1$,3d", handlingTime));
            }
        }
    }

    private boolean isEnabledLogLevel(boolean isWarnHandling) {
        return isWarnHandling ? logger.isWarnEnabled() : logger.isTraceEnabled();
    }

    private static String className(HandlerMethod handlerMethod) {
        return handlerMethod.getMethod().getDeclaringClass().getSimpleName();
    }

    private static String methodName(HandlerMethod handlerMethod) {
        return handlerMethod.getMethod().getName();
    }

    private static String buildMethodParams(HandlerMethod handlerMethod) {
        return Arrays.stream(handlerMethod.getMethodParameters())
                .map(param -> param.getParameterType().getSimpleName())
                .collect(Collectors.joining(", "));
    }
}
