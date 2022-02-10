package tech.sushnag22.doghouse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import tech.sushnag22.doghouse.backend.entity.Adopter;
import tech.sushnag22.doghouse.backend.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<Adopter> findByUsernameContainsIgnoreCase(String name);
    @Procedure(name = "findAllUsers")
    void findAllUsers();
}
