package tech.sushnag22.doghouse.ui.views.adopter;

import com.vaadin.flow.component.select.Select;
import tech.sushnag22.doghouse.backend.entity.Adopter;
import tech.sushnag22.doghouse.backend.entity.User;
import tech.sushnag22.doghouse.backend.repository.AdopterRepository;
import tech.sushnag22.doghouse.backend.repository.UserRepository;
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
public class AdopterEditView extends VerticalLayout implements KeyNotifier {
    private final AdopterRepository adopterRepository;
    private final UserRepository userRepository;
    private Adopter adopter;
    private final Button saveButton;
    private final Button deleteButton;
    private final Button cancelButton;
    private final HorizontalLayout buttonsHorizontalLayout;
    private final Binder<Adopter> binder;
    private final TextField name;
    private final DatePicker birthDate;
    private final TextField gender;
    private final TextField email;
    private final TextField phone;
    private final TextField address;
    private final ConfirmDialog confirmDialog;
    private AdopterEditViewHandler adopterEditViewHandler;

    @Autowired
    public AdopterEditView(AdopterRepository adopterRepository, UserRepository userRepository) {
        this.adopterRepository = adopterRepository;
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
        this.email = new TextField("Email ID: ");
        this.phone = new TextField("Phone Number: ");
        this.address = new TextField("Address: ");

        this.binder = new Binder<>(Adopter.class);
        this.binder.bindInstanceFields(this);

        this.addKeyPressListener(Key.ENTER, e -> save());
        this.confirmDialog = new ConfirmDialog("Are you sure you want to delete the item?", e -> {
            this.adopterRepository.delete(adopter);
            this.adopterEditViewHandler.onChange();
            this.adopter = null;
        });

        this.add(name);
        this.add(birthDate);
        this.add(gender);
        this.add(email);
        this.add(phone);
        this.add(address);
        this.add(buttonsHorizontalLayout);
        this.setVisible(false);
    }

    public void setAdopter(Adopter adopter) {
        if (adopter != null) {
            if (adopter.getId() != null) {
                this.adopter = adopterRepository.findById(adopter.getId()).get();
            } else {
                this.adopter = adopter;
            }

            this.binder.setBean(this.adopter);
            this.setVisible(true);
            this.name.focus();
        }else{
            this.adopter = null;
        }
    }

    public void setAdopterEditViewHandler(AdopterEditView.AdopterEditViewHandler adopterEditViewHandler) {
        this.adopterEditViewHandler = adopterEditViewHandler;
    }

    private void save() {
        this.adopterRepository.save(adopter);
        this.adopterEditViewHandler.onChange();
        this.adopter = null;
    }

    private void delete() {
        confirmDialog.open();
    }

    private void cancel() {
        this.adopter = null;
        this.adopterEditViewHandler.onChange();
    }

    public interface AdopterEditViewHandler {
        void onChange();
    }

}