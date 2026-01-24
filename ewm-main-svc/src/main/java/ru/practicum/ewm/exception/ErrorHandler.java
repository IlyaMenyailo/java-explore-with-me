package ru.practicum.ewm.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleConstraintViolationException(ConstraintViolationException exception) {
        log.info("ConstraintViolationException: {}", exception.getMessage(), exception);
        return new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Incorrectly made request.",
                exception.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.info("MethodArgumentNotValidException: {}", exception.getMessage(), exception);
        return new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Incorrectly made request.",
                exception.getMessage()
        );
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlerValidationException(ValidationException exception) {
        log.info("ValidationException: {}", exception.getMessage(), exception);
        return new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Incorrectly made request.",
                exception.getMessage()
        );
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(NotFoundException exception) {
        log.info("NotFoundException: {}", exception.getMessage(), exception);
        return new ApiError(
                HttpStatus.NOT_FOUND.value(),
                "The required object was not found.",
                exception.getMessage()
        );
    }

    @ExceptionHandler(DuplicatedDataException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleDuplicatedDataException(DuplicatedDataException exception) {
        log.info("DuplicatedDataException: {}", exception.getMessage(), exception);
        return new ApiError(
                HttpStatus.CONFLICT.value(),
                "Integrity constraint has been violated.",
                exception.getMessage()
        );
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleConflictException(ConflictException exception) {
        log.info("ConflictException: {}", exception.getMessage(), exception);
        return new ApiError(
                HttpStatus.CONFLICT.value(),
                "For the requested operation the conditions are not met.",
                exception.getMessage()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleIllegalArgumentException(IllegalArgumentException exception) {
        log.info("IllegalArgumentException: {}", exception.getMessage(), exception);
        return new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Incorrectly made request.",
                exception.getMessage()
        );
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleIllegalStateException(IllegalStateException exception) {
        log.info("IllegalStateException: {}", exception.getMessage(), exception);
        return new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Incorrectly made request.",
                exception.getMessage()
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        log.info("DataIntegrityViolationException: {}", exception.getMessage(), exception);
        return new ApiError(
                HttpStatus.CONFLICT.value(),
                "Integrity constraint has been violated.",
                exception.getMessage()
        );
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiError handleForbiddenException(ForbiddenException exception) {
        log.info("ForbiddenException: {}", exception.getMessage(), exception);
        return new ApiError(
                HttpStatus.FORBIDDEN.value(),
                "For the requested operation the conditions are not met.",
                exception.getMessage()
        );
    }

    @ExceptionHandler(IncorrectRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleIncorrectRequestException(IncorrectRequestException exception) {
        log.info("IncorrectRequestException: {}", exception.getMessage(), exception);
        return new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Incorrectly made request.",
                exception.getMessage()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleAllExceptions(Exception exception) {
        log.error("Internal Server Error: {}", exception.getMessage(), exception);
        return new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                exception.getMessage()
        );
    }
}