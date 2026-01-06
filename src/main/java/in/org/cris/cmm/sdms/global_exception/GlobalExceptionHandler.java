package in.org.cris.cmm.sdms.global_exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Handle unique constraint violations dynamically
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String message = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();
        Map<String, String> errors = new HashMap<>();

        // Regex to find the field name in PostgreSQL UNIQUE constraint message
        Pattern pattern = Pattern.compile("Key \\((.+)\\)=\\((.+)\\) already exists");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            String fields = matcher.group(1);   // e.g., "section_code" or "org_code, section_code"
            String value = matcher.group(2);    // conflicting value
            Arrays.stream(fields.split(",")).forEach(field -> {
                errors.put(field.trim(), String.format("Value '%s' for field '%s' already exists", value, field.trim()));
            });
        } else {
            errors.put("error", "Data integrity violation: " + message);
        }

        return new ResponseEntity<>(errors, HttpStatus.CONFLICT); // 409 Conflict
    }

    // ✅ Custom DuplicateRecordException
    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<Map<String, String>> handleDuplicate(DuplicateRecordException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("error", ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.CONFLICT);
    }

    // Handle other exceptions (optional)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
