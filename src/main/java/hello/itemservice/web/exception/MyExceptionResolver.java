package hello.itemservice.web.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.itemservice.web.exception.ex.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MyExceptionResolver implements HandlerExceptionResolver {

    private final ObjectMapper objectMapper = new ObjectMapper();

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
            //Console에 찍히는 log가 엄청많다. 기존에는 위에처럼 Resolver에서 sendError를 설정하고 was로 넘겨주기만해서 그렇다.
            //그래서 단계별로 거치면서 로그가 여러번 발생하는 것이다.
            //한번에 처리하기위한 수단으로 다음과 같이 response를 전부 작성하고 return 해주면 이관되는 일없이 이매서드에서 error처리를 끝마친다.
            if (ex instanceof UserException) {
                log.info("UserException resolver to 400");
                String acceptHeader = request.getHeader("accept");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                if ("application/json".equals(acceptHeader)) {
                    Map errorResult = new HashMap();
                    errorResult.put("ex", ex.getClass());
                    errorResult.put("message", ex.getMessage());
                    String result = objectMapper.writeValueAsString(errorResult);

                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(result);
                    return new ModelAndView();
                } else {
                    return new ModelAndView("error/500");
                }
            }
        } catch (IOException e) {
            log.error("resolver ex", e);
        }

        return null; // 그냥 하던데로 기본 예외 처리가 진행한다. 예외가 발생되고 서블릿 밖(was)으로 예외가 던저진다.
    }
}
