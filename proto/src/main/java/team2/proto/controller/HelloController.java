package team2.proto.controller;

// 지울 예정 개발용 컨트롤러

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team2.proto.domain.User;

@RequiredArgsConstructor
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String helloSecurity(HttpServletRequest request, Model model) {
        // Bean을 통해 현재 인증된 사용자 정보 가져오기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User)principal;
        String nickname = user.getNickname();
        model.addAttribute("nickname", nickname);
        return "hello";
    }
}
