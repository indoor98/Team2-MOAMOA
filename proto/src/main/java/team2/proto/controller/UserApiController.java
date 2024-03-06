package team2.proto.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import team2.proto.dto.AddUserRequestDTO;
import team2.proto.service.UserService;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;

      // RestAPI로 변경됨
//    @PostMapping("/user")
//    public String signUp(AddUserRequestDTO request) {
//        userService.save(request);
//        return "redirect:/login";
//    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}
