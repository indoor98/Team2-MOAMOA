package team2.proto.service.adminauth;

import team2.proto.dto.adminauth.AdminAuthDTO;
import team2.proto.dto.adminauth.AdminAuthRequestDTO;
import team2.proto.entity.AdminPage;

import java.util.List;

public interface AdminAuthService {

    public List<AdminPage> getAllAuthList();


}