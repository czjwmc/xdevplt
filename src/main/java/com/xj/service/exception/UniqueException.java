package com.xj.service.exception;


/**
 * An exception that is thrown by classes wanting to insert a record with unique.
 * This is used to wrap Spring's DataIntegrityViolationException, 
 * so it's checked in the web layer.
 *
 * @author <a href="mailto:wx@xujis.com">wx</a>
 */
public class UniqueException extends Exception {
    private static final long serialVersionUID = -1L;

    /**
     * Constructor for UniqueException.
     *
     * @param message exception message
     */
    public UniqueException(final String message) {
        super(message);
    }
}
