package tech.sushnag22.doghouse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sushnag22.doghouse.backend.entity.Adopter;

import java.util.List;

public interface AdopterRepository extends JpaRepository<Adopter, Long> {
    List<Adopter> findByFirstNameContainsIgnoreCase(String name);
}
