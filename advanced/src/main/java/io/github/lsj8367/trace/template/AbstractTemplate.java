package io.github.lsj8367.trace.template;

import io.github.lsj8367.trace.TraceStatus;
import io.github.lsj8367.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> {
    private final LogTrace trace;

    public AbstractTemplate(final LogTrace trace) {
        this.trace = trace;
    }

    public T execute(final String message) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);

            //로직 호출
            T result = call();

            trace.end(status);
            return result;

        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();
}