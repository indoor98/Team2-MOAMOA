package team2.proto.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import team2.proto.entity.AdminPage;

public interface AdminPageRepository extends JpaRepository<AdminPage, Long> {
}
