package hello.itemservice.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MyExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        try{
            //IllegalArgument 가 발생하면 sendError로 was에 넘기게 된다.
            //이때 return new ModelAndView()를 설정 가능하며, 새로운 페이지를 설정해주는 것도 가능(Web Page)하고 빈값이면 적용된 서블릿을 리턴한다.
            if(ex instanceof IllegalArgumentException){
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return new ModelAndView();
            }

        } catch (IOException e) {
            log.error("resolver ex", e);
        }

        return null; // 그냥 하던데로 기본 예외 처리가 진행한다. 예외가 발생되고 서블릿 밖(was)으로 예외가 던저진다.
    }
}
