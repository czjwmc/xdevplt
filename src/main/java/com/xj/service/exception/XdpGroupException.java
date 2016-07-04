package com.xj.service.exception;


/**
 * An exception that is thrown by classes wanting to get a xrpPattern record with error.
 */
public class XdpGroupException extends Exception {
    private static final long serialVersionUID = -1L;

    /**
     * Constructor.
     *
     * @param message exception message
     */
    public XdpGroupException(final String message) {
        super(message);
    }
}
