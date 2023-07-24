package hello.itemservice.web.session;

import hello.itemservice.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void 세션테스트(){

        //우리가 만든 sessionManager에 Member객체와 응답에 저장한 response 초기화
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member, response);

        //임의로 생성한 request에 Cookie값을 셋팅
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        //임의로 셋팅한 request를 sessionManager에서 찾아서
        //값이 나오고 해당값이 위에서 초기화한 Member랑 같은지 비교
        Object result = sessionManager.getSession(request);
        Assertions.assertThat(result).isEqualTo(member);

        //세션 만료도 테스트
        sessionManager.expire(request);
        Object expire = sessionManager.getSession(request);
        Assertions.assertThat(expire).isNull();

    }
}