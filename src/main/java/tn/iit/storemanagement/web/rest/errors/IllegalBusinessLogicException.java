package tn.iit.storemanagement.web.rest.errors;

public class IllegalBusinessLogicException extends RuntimeException {
    public IllegalBusinessLogicException(String message) {
        super(message);
    }
}
