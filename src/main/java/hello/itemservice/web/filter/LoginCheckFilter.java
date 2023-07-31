package hello.itemservice.web.filter;

import hello.itemservice.web.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whiteList = {"/", "/members/add", "/login", "/logout", "/css/*"};
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURL = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        try {
            log.info("인증 체크 필터 시작 {}", requestURL);
            if(isLoginCheckPath(requestURL)){
                log.info("인증 체크 로직 실행 {}", requestURL);
                HttpSession session = httpRequest.getSession(false);
                if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
                    httpResponse.sendRedirect("/login?redirectURL="+requestURL);
                    return;
                }
            }
            chain.doFilter(request, response);
        }catch(Exception e){
            throw e;
        }finally {
            log.info("인증 체크 필터 종료 {}", requestURL);
        }
    }

    private boolean isLoginCheckPath(String requestURL) {
        return !PatternMatchUtils.simpleMatch(whiteList, requestURL);
    }
}
