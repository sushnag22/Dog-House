package tech.sushnag22.doghouse.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;
import tech.sushnag22.doghouse.backend.entity.Image;
import tech.sushnag22.doghouse.backend.repository.ImageRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements CrudListener<Image> {

    private final ImageRepository imageRepository;

    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Image add(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image update(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public void delete(Image image) {
        imageRepository.delete(image);
    }

}

