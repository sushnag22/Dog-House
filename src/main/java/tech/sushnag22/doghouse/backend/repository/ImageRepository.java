package tech.sushnag22.doghouse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.sushnag22.doghouse.backend.entity.Image;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findById(int id);
}