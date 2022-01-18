package tech.sushnag22.doghouse.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;
import tech.sushnag22.doghouse.backend.entity.Adopter;
import tech.sushnag22.doghouse.backend.repository.AdopterRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdopterService implements CrudListener<Adopter> {

    private final AdopterRepository adopterRepository;

    @Override
    public List<Adopter> findAll() {
        return adopterRepository.findAll();
    }

    @Override
    public Adopter add(Adopter adopter) {
        return adopterRepository.save(adopter);
    }

    @Override
    public Adopter update(Adopter adopter) {
        return adopterRepository.save(adopter);
    }

    @Override
    public void delete(Adopter adopter) {
        adopterRepository.delete(adopter);
    }
}
