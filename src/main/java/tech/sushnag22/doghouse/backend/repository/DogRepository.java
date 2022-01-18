package tech.sushnag22.doghouse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sushnag22.doghouse.backend.entity.Dog;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {
    List<Dog> findByNameContainsIgnoreCase(String name);
}
