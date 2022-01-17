package tech.sushnag22.doghouse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sushnag22.doghouse.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
