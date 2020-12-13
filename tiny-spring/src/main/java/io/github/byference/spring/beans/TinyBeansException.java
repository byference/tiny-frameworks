package io.github.byference.spring.beans;

/**
 * TinyBeansException
 *
 * @author byference
 * @since 2020-12-13
 */
public class TinyBeansException extends RuntimeException {

    private static final long serialVersionUID = 6088288107109597657L;

    public TinyBeansException() {
        super();
    }

    public TinyBeansException(String message) {
        super(message);
    }

    public TinyBeansException(String message, Throwable cause) {
        super(message, cause);
    }

    public TinyBeansException(Throwable cause) {
        super(cause);
    }
}
