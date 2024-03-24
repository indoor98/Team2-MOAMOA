package team2.proto.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.proto.entity.AdminPage;
import team2.proto.entity.PostUser;
import team2.proto.entity.User;

import java.lang.reflect.Member;
import java.util.List;

@Repository
public interface AdminAuthRepository extends JpaRepository<AdminPage, Long> {


}
