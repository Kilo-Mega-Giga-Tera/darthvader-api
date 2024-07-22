package kr.app.darthvader.global.error.advice;

import kr.app.darthvader.global.error.exception.UserMessageException;
import kr.app.darthvader.domain.common.model.dto.ErrorResult;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Map;
import java.util.Optional;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResult<Map<String, Object>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String error = Optional.ofNullable(e.getBindingResult().getFieldError()).map(DefaultMessageSourceResolvable::getDefaultMessage).orElse("시스템에러");
        return new ErrorResult<>(Map.of("message", error, "status", String.valueOf(HttpStatus.BAD_REQUEST)));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ErrorResult<Map<String, Object>> noResourceFoundExceptionHandler() {
        return new ErrorResult<>(Map.of("message", "페이지를 찾을 수 없습니다", "status", String.valueOf(HttpStatus.NOT_FOUND)));
    }

    @ExceptionHandler(UserMessageException.class)
    public ErrorResult<Map<String, Object>> userMessageExceptionHandler(UserMessageException e) {
        return new ErrorResult<>(Map.of("message", e.getMessage(), "status", String.valueOf(HttpStatus.BAD_REQUEST)));
    }

    @ExceptionHandler(Exception.class)
    public ErrorResult<Map<String, Object>> exceptionHandler() {
        return new ErrorResult<>(Map.of("message", "시스템에러", "status", String.valueOf(HttpStatus.BAD_REQUEST)));
    }

}
