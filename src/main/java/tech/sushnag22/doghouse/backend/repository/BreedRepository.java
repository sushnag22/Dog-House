package tech.sushnag22.doghouse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sushnag22.doghouse.backend.entity.Breed;

import java.util.List;

public interface BreedRepository extends JpaRepository<Breed, Long> {
    List<Breed> findByNameContainsIgnoreCase(String name);
}