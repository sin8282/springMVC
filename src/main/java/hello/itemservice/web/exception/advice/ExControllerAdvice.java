package hello.itemservice.web.exception.advice;

import hello.itemservice.web.exception.body.ErrorResult;
import hello.itemservice.web.exception.ex.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * // Target all Controllers annotated with @RestController
 * @ControllerAdvice(annotations = RestController.class)
 * public class ExampleAdvice1 {}
 * // Target all Controllers within specific packages
 * @ControllerAdvice("org.example.controllers")
 * public class ExampleAdvice2 {}
 * // Target all Controllers assignable to specific classes
 * @ControllerAdvice(assignableTypes = {ControllerInterface.class,
 * AbstractController.class})
 * public class ExampleAdvice3 {}
 */
@Slf4j
@RestControllerAdvice("hello.itemservice.web.exception.api")
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("@ExceptionHandler " , e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException e){
        log.error("@ExceptionHandler " , e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("@ExceptionHandler " , e);
        return new ErrorResult("EX", "내부 오류");
    }

}
