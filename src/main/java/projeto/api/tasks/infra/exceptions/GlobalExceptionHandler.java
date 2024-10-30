package projeto.api.tasks.infra.exceptions;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import projeto.api.tasks.domain.task.TaskNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> methodArgumentHandler(MethodArgumentNotValidException exc) {
        var erros = exc.getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        (f) -> f.getField(),
                        (f) -> f.getDefaultMessage()));

        return ResponseEntity.unprocessableEntity().body(erros);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<?> conflicHandler(RuntimeException exc) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ExceptionResponse(409,exc.getMessage(),LocalDateTime.now()));
    }

    @ExceptionHandler({TaskNotFoundException.class})
    public ResponseEntity<?> notFoundHandler(RuntimeException exc) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponse(404,exc.getMessage(),LocalDateTime.now()));
    }

    private record ExceptionResponse(Integer status, String message, LocalDateTime timestamp) {}
}
