package team2.proto.email.service;

import team2.proto.email.dao.EmailMessageDTO;

public interface EmailService {

    public void sendMail(EmailMessageDTO emailMessageDTO);
}
