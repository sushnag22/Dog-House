package tech.sushnag22.doghouse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sushnag22.doghouse.backend.entity.Adopter;
import tech.sushnag22.doghouse.backend.entity.Users;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    List<Adopter> findByUsernameContainsIgnoreCase(String name);
}