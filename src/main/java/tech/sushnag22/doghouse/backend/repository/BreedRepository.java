package tech.sushnag22.doghouse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import tech.sushnag22.doghouse.backend.entity.Breed;

import java.util.List;

public interface BreedRepository extends JpaRepository<Breed, Long> {
    @Procedure(procedureName = "findBreedByName")
    List<Breed> findByNameContainsIgnoreCase(String name);
    void findBreedByName(String name);
}
