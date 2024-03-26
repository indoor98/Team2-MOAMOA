package team2.proto.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team2.proto.entity.AdminPage;
import team2.proto.entity.User;
import team2.proto.repository.admin.AdminAuthRepository;
import team2.proto.repository.user.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminAuthServiceImpl implements AdminAuthService {

    private final AdminAuthRepository adminAuthRepository;

    private final UserRepository userRepository;


    @Transactional
    @Override
    public List<AdminPage> getAllAuthList() {
        System.out.println("getAllAuthList");
        List<AdminPage> list = adminAuthRepository.findAll();
        System.out.println(list.get(0).toString());
        return list;
    }

    @Transactional
    @Override
    public Integer updateAuth(Long authNo, boolean approve) {
        Optional<AdminPage> adminPageOptional = adminAuthRepository.findById(authNo);
        if (adminPageOptional.isPresent()) {
            AdminPage adminPage = adminPageOptional.get();

            if (approve) {
                adminPage.setAuthYn(true);
                adminPage.setAuthDate(LocalDateTime.now());

                adminAuthRepository.save(adminPage);

                User user = adminPage.getUser();
                if (user != null) {
                    user.setAuthentication(true);
                    userRepository.save(user);
                }
            }

        }
        return null;
    }

    @Transactional
    @Override
    public Integer updateAdmin(Long adminNo, boolean adminApprove) {
        Optional<User> byId = userRepository.findById(adminNo);
        if (byId.isPresent()) {
            User adminUser = byId.get();

            if (adminApprove) {

            if (adminUser != null) {
                adminUser.setAdminYn(true);
                userRepository.save(adminUser);
            }
        }

    }
        return null;
}


}