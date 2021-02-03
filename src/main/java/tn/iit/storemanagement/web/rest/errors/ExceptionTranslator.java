package tn.iit.storemanagement.web.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorVM processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ErrorVM dto = new ErrorVM("VALIDATION ERROR");
        for (FieldError fieldError : fieldErrors) {
            dto.add(fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
        }
        return dto;
    }

    @ExceptionHandler(MyResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorVM> processRessourceNotFound(MyResourceNotFoundException exception) {
        BodyBuilder builder;
        builder = ResponseEntity.status(HttpStatus.NOT_FOUND);
        return builder.body(new ErrorVM("NOT FOUND", exception.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorVM> processMethodNotSupportedException(IllegalArgumentException exception) {
        BodyBuilder builder;
        builder = ResponseEntity.status(HttpStatus.CONFLICT);
        return builder.body(new ErrorVM("CONFLICT", exception.getMessage()));
    }

    @ExceptionHandler(IllegalBusinessLogicException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorVM> processIllegalBusinessLogic(IllegalBusinessLogicException exception) {
        BodyBuilder builder;
        builder = ResponseEntity.status(HttpStatus.CONFLICT);
        return builder.body(new ErrorVM("CONFLICT", exception.getMessage()));
    }
}
