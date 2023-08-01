package hello.itemservice.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURL = httpRequest.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        try {
            log.info("REQUEST [{}] [{}] [{}]", uuid, requestURL, request.getDispatcherType());
            chain.doFilter(request, response);
        }catch (Exception e){
            throw e;
        }finally {
            log.info("RESPONSE [{}][{}][{}]", uuid, requestURL, request.getDispatcherType());
        }
    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
