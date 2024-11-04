package com.afs.stock.resource.advise;

import com.afs.stock.model.ErrorResponseModel;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GeneralControllerAdvise {

    final private Tracer tracer;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        StringBuilder errorValid =new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName ;
            if(error instanceof FieldError){
                fieldName = ((FieldError) error).getField();
            }else{
                fieldName = error.getObjectName();
            }

            String errorMessage = error.getDefaultMessage();
            if(!errorValid.isEmpty()) {
                errorValid.append(" , ");
            }
            errorValid.append(fieldName).append(" : ").append(errorMessage);


        });

        ErrorResponseModel err = new ErrorResponseModel();
        err.setRejectionReason(errorValid.toString());
        err.setStatusCode("400");
        err.setTracerId(tracer.currentSpan().context().traceId());
        log.error(ex.getClass().getName(), ex);

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseModel> handleException(Exception ex, WebRequest request) {

        log.error(ex.getClass().getName(), ex);

        return new ResponseEntity<>(ErrorResponseModel.builder().statusCode("Exception")
                .rejectionReason(ex.getMessage()).tracerId(tracer.currentSpan().context().traceId()).build(),HttpStatus.BAD_REQUEST);
    }

}
