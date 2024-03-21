package team2.proto.service.adminauth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team2.proto.dto.adminauth.AdminAuthRequestDTO;
import team2.proto.entity.AdminPage;
import team2.proto.repository.adminauth.AdminAuthRepository;

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