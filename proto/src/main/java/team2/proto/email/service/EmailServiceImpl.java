package team2.proto.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.spring6.SpringTemplateEngine;
import team2.proto.email.EmailService;
import team2.proto.repository.post.PostRepository;
import team2.proto.service.authentication.UserService;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine springTemplateEngine;

    private final PostRepository postRepository;

    // 포스트 타이틀
    private String postTile;

    private final UserService userService;

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

    // 메일 내용 작성
    @Transactional
    @Override
    public MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();

        // 보내는 사람, 메일 제목
        message.addRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject("모아모아 공동구매 인원 모집 완료");

        // 포스트 타이틀

        // 메일 내용
        String msgg = "";
        msgg += "<div style='margin:100px;'>";
        msgg += "<h1> 안녕하세요</h1>";
        msgg += "<h1> 대학생 공동구매 플랫폼 모아모아입니다.</h1>";
        msgg += "<br>";
        msgg += "<p>현재 공동구매 신청하신 <p>" + "<p>(여기에 포스트 타이틀 틀어갈 예정)<p>" + "<p>의 모집이 완료되었습니다.<p>";
        msgg += "<br>";
        msgg += "<p>결제를 진행해주시길 바랍니다.<p>";
        msgg += "<br>";
//        msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
//        msgg += "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
//        msgg += "<div style='font-size:130%'>";
        msgg += "CODE : <strong>";
        msgg += "</strong><div><br/> ";
        msgg += "</div>";
        message.setText(msgg, "utf-8", "html");// 내용, charset 타입, subtype
        // 보내는 사람의 이메일 주소, 보내는 사람 이름
        message.setFrom(new InternetAddress("holyplase129@gmail.com", "모아모아"));// 보내는 사람

        return message;
    }

    @Transactional
    @Override
    public String postTitle() {

        return null;
    }
}
