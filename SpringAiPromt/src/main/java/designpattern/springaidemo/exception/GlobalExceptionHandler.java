package designpattern.springaidemo.exception;

import designpattern.springaidemo.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.retry.NonTransientAiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static  final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final HttpServletResponse httpServletResponse;

    public GlobalExceptionHandler(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        String errorMessage= ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        ErrorResponse errorResponse= new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setMessage(errorMessage);
        errorResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        errorResponse.setError("Validation failed");
        errorResponse.setPath(request.getRequestURI());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NonTransientAiException.class)
    public ResponseEntity<ErrorResponse> handleAiException(
            NonTransientAiException ex, HttpServletRequest request) {
        logger.error("AI Exception: {}", ex.getMessage());

        ErrorResponse errorResponse= new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        errorResponse.setError("Ai service is not available");
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(
            Exception ex, HttpServletRequest request) {

        ErrorResponse errorResponse= new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        errorResponse.setError("Internal server error");
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
