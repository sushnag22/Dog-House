package tech.sushnag22.doghouse.ui.views.dog;

import tech.sushnag22.doghouse.backend.repository.BreedRepository;
import tech.sushnag22.doghouse.backend.repository.UserRepository;
import tech.sushnag22.doghouse.backend.entity.Dog;
import tech.sushnag22.doghouse.backend.repository.DogRepository;
import tech.sushnag22.doghouse.ui.components.ConfirmDialog;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@UIScope
@SpringComponent
public class DogEditView extends VerticalLayout implements KeyNotifier {
    private final DogRepository dogRepository;
    private final BreedRepository breedRepository;
    private final UserRepository userRepository;
    private Dog dog;
    private final Button saveButton;
    private final Button deleteButton;
    private final Button cancelButton;
    private final HorizontalLayout buttonsHorizontalLayout;
    private final Binder<Dog> binder;
    private final TextField name;
    private final DatePicker birthDate;
    private final TextField gender;
    private final TextField colour;
    private final TextField description;
    private final TextField location;
    private final ConfirmDialog confirmDialog;
    private DogEditViewHandler dogEditViewHandler;

    @Autowired
    public DogEditView(DogRepository dogRepository, BreedRepository breedRepository, UserRepository userRepository) {
        this.dogRepository = dogRepository;
        this.breedRepository = breedRepository;
        this.userRepository = userRepository;

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
        this.birthDate = new DatePicker("Birth Date: ");
        this.gender = new TextField("Gender: ");
        this.colour = new TextField("Colour: ");
        this.description = new TextField("Description: ");
        this.location = new TextField("Location: ");

        this.binder = new Binder<>(Dog.class);
        this.binder.bindInstanceFields(this);

        this.addKeyPressListener(Key.ENTER, e -> save());
        this.confirmDialog = new ConfirmDialog("Are you sure you want to delete the item?", e -> {
            this.dogRepository.delete(dog);
            this.dogEditViewHandler.onChange();
            this.dog = null;
        });

        this.add(this.name);
        this.add(this.birthDate);
        this.add(this.gender);
        this.add(this.colour);
        this.add(this.description);
        this.add(this.location);
        this.add(this.buttonsHorizontalLayout);
        this.setVisible(false);
    }

    public void setDog(Dog dog) {
        if (dog != null) {
            if (dog.getId() != null) {
                this.dog = dogRepository.findById(dog.getId()).get();
            } else {
                this.dog = dog;
            }

            this.binder.setBean(this.dog);
            this.setVisible(true);
            this.name.focus();
        }else{
            this.dog = null;
        }
    }

    public void setDogEditViewHandler(DogEditViewHandler dogEditViewHandler) {
        this.dogEditViewHandler = dogEditViewHandler;
    }

    private void save() {
        this.dogRepository.save(dog);
        this.dogEditViewHandler.onChange();
        this.dog = null;
    }

    private void delete() {
        confirmDialog.open();
    }

    private void cancel() {
        this.dog = null;
        this.dogEditViewHandler.onChange();
    }

    public interface DogEditViewHandler {
        void onChange();
    }

}