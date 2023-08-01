package hello.itemservice.web.exception.ex;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// messageSource도 이용가능하다.
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "error.bad")
//@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "잘못된 요청 오류")
public class BadRequestException extends RuntimeException{
}
