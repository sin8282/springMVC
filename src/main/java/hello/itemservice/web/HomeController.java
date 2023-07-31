package hello.itemservice.web;

import hello.itemservice.domain.member.Member;
import hello.itemservice.domain.member.MemberRepository;
import hello.itemservice.web.session.SessionConst;
import hello.itemservice.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final SessionManager sessionManager;

    @GetMapping("/")
    public  String homeLogin(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model){

        if(loginMember == null){
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
