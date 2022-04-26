package tech.sushnag22.doghouse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sushnag22.doghouse.backend.entity.Adopter;
import tech.sushnag22.doghouse.backend.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<Adopter> findByUsernameContainsIgnoreCase(String name);
}