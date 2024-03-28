package team2.proto.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;

public interface EmailService {

    public void sendMail(EmailMessageDTO emailMessageDTO);
}
