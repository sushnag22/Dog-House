package tech.sushnag22.doghouse.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;
import tech.sushnag22.doghouse.backend.entity.Dog;
import tech.sushnag22.doghouse.backend.repository.DogRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DogService implements CrudListener<Dog> {

    private final DogRepository dogRepository;

    @Override
    public List<Dog> findAll() {
        return dogRepository.findAll();
    }

    @Override
    public Dog add(Dog dog) {
        return dogRepository.save(dog);
    }

    @Override
    public Dog update(Dog dog) {
        return dogRepository.save(dog);
    }

    @Override
    public void delete(Dog dog) {
        dogRepository.delete(dog);
    }
}