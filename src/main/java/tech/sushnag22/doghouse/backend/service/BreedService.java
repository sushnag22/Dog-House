package tech.sushnag22.doghouse.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;
import tech.sushnag22.doghouse.backend.entity.Breed;
import tech.sushnag22.doghouse.backend.repository.BreedRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BreedService implements CrudListener<Breed> {

    private final BreedRepository breedRepository;

    @Override
    public List<Breed> findAll() {
        return breedRepository.findAll();
    }

    @Override
    public Breed add(Breed breed) {
        return breedRepository.save(breed);
    }

    @Override
    public Breed update(Breed breed) {
        return breedRepository.save(breed);
    }

    @Override
    public void delete(Breed breed) {
        breedRepository.delete(breed);
    }
}
