package team2.proto.service.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team2.proto.entity.AdminPage;
import team2.proto.repository.admin.AdminPageRepository;

@Service
@RequiredArgsConstructor
public class SchoolAuthorizationServiceImpl implements SchoolAuthorizationService {
    private final AdminPageRepository adminPageRepository;

    @Override
    public void save(AdminPage adminPage) throws Exception {
        if (adminPage != null ) {
            adminPageRepository.save(adminPage);
        } else {
            throw new Exception("admingPage 객체가 Null입니다.");
        }
    }
}
