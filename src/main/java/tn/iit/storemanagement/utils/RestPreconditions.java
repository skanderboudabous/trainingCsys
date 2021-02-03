package tn.iit.storemanagement.utils;

import tn.iit.storemanagement.web.rest.errors.IllegalBusinessLogicException;
import tn.iit.storemanagement.web.rest.errors.MyResourceNotFoundException;

public final class RestPreconditions {

    private RestPreconditions() {
        throw new AssertionError();
    }

    // API

    /**
     * Check if some value was found, otherwise throw exception.
     *
     * @param expression
     *            has value true if found, otherwise false
     * @throws MyResourceNotFoundException
     *             if expression is false, means value not found.
     */
    public static void checkFound(final boolean expression,String message) {
        if (!expression) {
            throw new MyResourceNotFoundException("message");
        }
    }

    /**
     * Check if some value was found, otherwise throw exception.
     *
     * @param expression
     *            has value true if found, otherwise false
     * @throws MyResourceNotFoundException
     *             if expression is false, means value not found.
     */
    public static <T> T checkFound(final T resource) {
        if (resource == null) {
            throw new MyResourceNotFoundException();
        }

        return resource;
    }

    public static <T> T checkFound(final T resource,String message) {
        if (resource == null) {
            throw new MyResourceNotFoundException(message);
        }

        return resource;
    }
    public static void checkBusinessLogic(final boolean expression,String message) {
        if (!expression) {
            throw new IllegalBusinessLogicException(message);
        }
    }

}
