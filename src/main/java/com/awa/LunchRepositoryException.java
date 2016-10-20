package com.awa;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016-10-20.
 */
public class LunchRepositoryException extends RuntimeException {
    public LunchRepositoryException() {
        super();
    }

    public LunchRepositoryException(String message) {
        super(message);
    }

    public LunchRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public LunchRepositoryException(Throwable cause) {
        super(cause);
    }

    protected LunchRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
