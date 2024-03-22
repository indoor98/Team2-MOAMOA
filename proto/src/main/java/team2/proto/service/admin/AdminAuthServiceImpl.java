package team2.proto.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team2.proto.entity.AdminPage;
import team2.proto.repository.admin.AdminAuthRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminAuthServiceImpl implements AdminAuthService {

    private final AdminAuthRepository adminAuthRepository;



    @Transactional
    @Override
    public List<AdminPage> getAllAuthList() {
        List<AdminPage> list = adminAuthRepository.findAll();
        return list;
    }
}