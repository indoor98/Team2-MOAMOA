package team2.proto.controller;

// 지울 예정 개발용 컨트롤러

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String helloSecurity() {
        return "Hello Security!";
    }
}
