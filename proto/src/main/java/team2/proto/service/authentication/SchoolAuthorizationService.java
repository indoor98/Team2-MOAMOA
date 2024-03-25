package team2.proto.service.authentication;

import team2.proto.entity.AdminPage;

public interface SchoolAuthorizationService {
    public void save(AdminPage adminPage) throws Exception;
}
