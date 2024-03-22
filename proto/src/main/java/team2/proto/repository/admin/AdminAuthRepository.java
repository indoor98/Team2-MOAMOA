package team2.proto.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import team2.proto.entity.AdminPage;

public interface AdminAuthRepository extends JpaRepository<AdminPage, Long> {

}
