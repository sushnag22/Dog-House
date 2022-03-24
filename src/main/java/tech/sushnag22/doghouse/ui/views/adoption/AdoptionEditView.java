package tech.sushnag22.doghouse.ui.views.adoption;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import tech.sushnag22.doghouse.backend.entity.Adopter;
import tech.sushnag22.doghouse.backend.entity.Adoption;
import tech.sushnag22.doghouse.backend.entity.Dog;
import tech.sushnag22.doghouse.backend.repository.AdopterRepository;
import tech.sushnag22.doghouse.backend.repository.AdoptionRepository;
import tech.sushnag22.doghouse.backend.repository.DogRepository;
import tech.sushnag22.doghouse.ui.components.ConfirmDialog;

@PreserveOnRefresh
@SpringComponent
@UIScope
public class AdoptionEditView extends VerticalLayout implements KeyNotifier {

    private final AdoptionRepository adoptionRepository;
    private final AdopterRepository adopterRepository;
    private final DogRepository dogRepository;
    private Adoption adoption;
    private final Button saveButton;
    private final Button deleteButton;
    private final Button cancelButton;
    private final HorizontalLayout buttonsHorizontalLayout;
    private AdoptionEditViewHandler adoptionEditViewHandler;
    private final DateTimePicker adoptionDate;
    private final Select<Adopter> adopter;
    private final Select<Dog> dog;
    private final ConfirmDialog confirmDialog;
    private final Binder<Adoption> binder;

    @Autowired
    public AdoptionEditView(AdoptionRepository adoptionRepository, AdopterRepository adopterRepository, DogRepository dogRepository){
        this.adoptionRepository = adoptionRepository;
        this.adopterRepository = adopterRepository;
        this.dogRepository = dogRepository;

        if(ComponentUtil.getData(UI.getCurrent(), Dog.class) == null) {
            UI.getCurrent().navigate("error");
        }

        this.saveButton = new Button("Save", VaadinIcon.CHECK.create());
        this.deleteButton = new Button("Delete", VaadinIcon.TRASH.create());
        this.cancelButton = new Button("Cancel");

        this.saveButton.getElement().getThemeList().add("primary");
        this.deleteButton.getElement().getThemeList().add("error");
        this.cancelButton.getElement().getThemeList().add("warning");

        this.saveButton.addClickListener(e -> save());
        this.deleteButton.addClickListener(e -> delete());
        this.cancelButton.addClickListener(e -> cancel());

        this.buttonsHorizontalLayout = new HorizontalLayout(this.saveButton, this.deleteButton, this.cancelButton);

        this.adoptionDate = new DateTimePicker("Adoption Date: ");
        this.adopter = new Select<>();
        this.dog = new Select<>();

        this.adopter.setLabel("Adopter: ");
        this.dog.setLabel("Dog: ");

        this.adopter.setItemLabelGenerator(Adopter::getName);
        this.dog.setItemLabelGenerator(Dog::getName);

        this.adopter.setItems(this.adopterRepository.findAll());
        this.dog.setItems(this.dogRepository.findAll());

        this.binder = new Binder<>(Adoption.class);
        this.binder.bindInstanceFields(this);

        this.confirmDialog = new ConfirmDialog("Are you sure you want to delete the item?", e -> {
            this.adoptionRepository.delete(this.adoption);
            this.adoptionEditViewHandler.onChange();
        });

        this.add(this.adoptionDate);
        this.add(this.adopter);
        this.add(this.dog);
        this.add(this.buttonsHorizontalLayout);
    }

    private void save(){
        this.adoptionRepository.save(adoption);
        this.adoptionEditViewHandler.onChange();
    }

    private void delete(){
        this.confirmDialog.open();
    }

    private void cancel(){
        this.adoptionEditViewHandler.onChange();
    }

    public void setAdoption(Adoption adoption){
        if(adoption != null){
            if(adoption.getId() != null){
                this.adoption = adoptionRepository.findById(adoption.getId()).get();
            }else{
                this.adoption = adoption;
            }
            this.binder.setBean(this.adoption);
            this.adoptionDate.focus();
            this.setVisible(true);
        }else{
            this.adoption = null;
        }
    }

    public void setAdoptionEditViewHandler(AdoptionEditViewHandler adoptionEditViewHandler){
        this.adoptionEditViewHandler = adoptionEditViewHandler;
    }

    public interface AdoptionEditViewHandler{
        void onChange();
    }
}