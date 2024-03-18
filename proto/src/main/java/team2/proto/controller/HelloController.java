package team2.proto.controller;

// 지울 예정 개발용 컨트롤러

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String helloSecurity(HttpServletRequest request, Model model) {
        System.out.println("deubg Hello");
        return "성공";
    }
}
