package tech.sushnag22.doghouse.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;
import tech.sushnag22.doghouse.backend.entity.Adoption;
import tech.sushnag22.doghouse.backend.repository.AdoptionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionService implements CrudListener<Adoption> {

    private final AdoptionRepository adoptionRepository;

    @Override
    public List<Adoption> findAll() {
        return adoptionRepository.findAll();
    }

    @Override
    public Adoption add(Adoption adoption) {
        return adoptionRepository.save(adoption);
    }

    @Override
    public Adoption update(Adoption adoption) {
        return adoptionRepository.save(adoption);
    }

    @Override
    public void delete(Adoption adoption) {
        adoptionRepository.delete(adoption);
    }
}