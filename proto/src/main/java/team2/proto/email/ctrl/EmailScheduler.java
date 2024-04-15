package team2.proto.email.ctrl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import team2.proto.email.dao.EmailMessageDTO;
import team2.proto.email.service.EmailService;
import team2.proto.entity.Post;
import team2.proto.entity.PostUser;
import team2.proto.repository.post.PostRepository;
import team2.proto.repository.post.PostUserRepository;

import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class EmailScheduler {

    private final PostRepository postRepository;
    private final PostUserRepository postUserRepository;
    private final EmailService emailService;

    // 메일 전송
    @Scheduled(fixedDelay = 60000) // 10초마다 실행
    @Transactional
    public void sendEmailScheduler() {
        List<Post> posts = postRepository.findAll();

        for (Post post : posts) {
            Long postId = post.getId();
            Integer headCount = post.getHeadCount();
            long userCount = postUserRepository.countByPostId(postId);

            if (headCount == userCount) {
                List<PostUser> postUsers = postUserRepository.findAllByPostId(postId);
                for (PostUser postUser : postUsers) {
//                    if (!postUser.isEmailSent()) { 일단 emailsent로 주석
                        String email = postUser.getUser().getEmail();
                        String subject = "모아모아 공동구매 인원 모집 완료";
                        String message = "모집이 완료되었습니다. 결제를 진행해주시길 바랍니다.";
                        EmailMessageDTO emailMessage = EmailMessageDTO.builder()
                                .to(email)
                                .subject(subject)
                                .message(message)
                                .build();
                        emailService.sendMail(emailMessage);

                        // 일단 emailsent로 주석
                        postUser.setEmailSent(true);
                        postUserRepository.save(postUser);

                }
            }
        }
    }

}
