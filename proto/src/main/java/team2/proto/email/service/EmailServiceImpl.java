package team2.proto.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.spring6.SpringTemplateEngine;
import team2.proto.email.EmailMessageDTO;
import team2.proto.repository.post.PostRepository;
import team2.proto.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl  {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine springTemplateEngine;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    // 포스트 타이틀
//    private String postTile;

//    private final UserService userService;

    /**
    @info : 지정된 주소로 이메일 전송 - (지원 거절 메시지)
    @name : sendMail
    @date : 2024/03/27 4:29 PM
    @author :
    @version : 1.0.0
    @param :
    @return :
    @Description : 이메일주소, 내용, 제목 필요
     */


    @Transactional
    public void sendMail(EmailMessageDTO emailMessageDTO) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emailMessageDTO.getTo());
            helper.setSubject(emailMessageDTO.getSubject());
            helper.setText(emailMessageDTO.getMessage(), true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
