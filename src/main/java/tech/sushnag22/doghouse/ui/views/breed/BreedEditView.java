package tech.sushnag22.doghouse.ui.views.breed;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import tech.sushnag22.doghouse.backend.entity.Breed;
import tech.sushnag22.doghouse.backend.repository.BreedRepository;
import tech.sushnag22.doghouse.ui.components.ConfirmDialog;


@UIScope
@SpringComponent
public class BreedEditView extends VerticalLayout implements KeyNotifier {
    private final BreedRepository breedRepository;
    private Breed breed;
    private final Button saveButton;
    private final Button deleteButton;
    private final Button cancelButton;
    private final HorizontalLayout buttonsHorizontalLayout;
    private final Binder<Breed> binder;
    private final TextField name;
    private final ConfirmDialog confirmDialog;
    private BreedEditViewHandler breedEditViewHandler;

    @Autowired
    public BreedEditView(BreedRepository breedRepository) {
        this.breedRepository = breedRepository;

        this.saveButton = new Button("Save", VaadinIcon.CHECK.create());
        this.deleteButton = new Button("Delete", VaadinIcon.TRASH.create());
        this.cancelButton = new Button("Cancel");

        this.saveButton.getElement().getThemeList().add("primary");
        this.deleteButton.getElement().getThemeList().add("error");
        this.cancelButton.getElement().getThemeList().add("warning");

        this.saveButton.addClickListener(e -> save());
        this.deleteButton.addClickListener(e -> delete());
        this.cancelButton.addClickListener(e -> cancel());

        this.buttonsHorizontalLayout = new HorizontalLayout(saveButton, deleteButton, cancelButton);

        this.name = new TextField("Name: ");

        this.binder = new Binder<>(Breed.class);
        this.binder.bindInstanceFields(this);

        this.addKeyPressListener(Key.ENTER, e -> save());
        this.confirmDialog = new ConfirmDialog("Are you sure you want to delete the item?", e -> {
            this.breedRepository.delete(breed);
            this.breedEditViewHandler.onChange();
            this.breed = null;
        });

        this.add(name);
        this.add(buttonsHorizontalLayout);
        this.setVisible(false);
    }

    public void setBreed(Breed breed) {
        if (breed != null) {
            if (breed.getId() != null) {
                this.breed = breedRepository.findById(breed.getId()).get();
            } else {
                this.breed = breed;
            }

            this.binder.setBean(this.breed);
            this.setVisible(true);
            this.name.focus();
        }else{
            this.breed = null;
        }
    }

    public void setBreedEditViewHandler(BreedEditViewHandler breedtEditViewHandler) {
        this.breedEditViewHandler = breedtEditViewHandler;
    }

    private void save() {
        this.breedRepository.save(breed);
        this.breedEditViewHandler.onChange();
        this.breed = null;
    }

    private void delete() {
        confirmDialog.open();
    }

    private void cancel() {
        this.breed = null;
        this.breedEditViewHandler.onChange();
    }

    public interface BreedEditViewHandler {
        void onChange();
    }

}