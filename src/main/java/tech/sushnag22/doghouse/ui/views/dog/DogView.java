package tech.sushnag22.doghouse.ui.views.dog;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import tech.sushnag22.doghouse.backend.entity.Dog;
import tech.sushnag22.doghouse.backend.repository.DogRepository;
import tech.sushnag22.doghouse.ui.MainLayout;

@PreserveOnRefresh
@Route(value = "edit-dog", layout = MainLayout.class)
@PageTitle("Edit Dog Details")
public class DogView extends VerticalLayout {
    private final DogRepository dogRepository;
    private final TextField textField;
    private final Grid<Dog> grid;
    private final Button newButton;
    private final DogEditView dogEditView;

    public DogView(DogRepository dogRepository, DogEditView dogEditView) {
        this.dogRepository = dogRepository;
        this.dogEditView = dogEditView;

        this.textField = new TextField("Dog Name: ");
        this.textField.setValueChangeMode(ValueChangeMode.EAGER);
        this.textField.addValueChangeListener(e -> this.refreshGrid(e.getValue()));

        this.grid = new Grid<>(Dog.class);
        this.grid.setColumns("breed.name", "users.username", "name", "birthDate", "gender", "colour", "description", "location");
        this.grid.asSingleSelect().addValueChangeListener(e -> dogEditView.setDog(e.getValue()));
        this.refreshGrid(null);

        this.newButton = new Button("New Dog", VaadinIcon.PLUS.create());
        this.newButton.addClickListener(e -> this.dogEditView.setDog(new Dog()));

        this.dogEditView.setDogEditViewHandler(() -> {
            this.refreshGrid(this.textField.getValue());
            this.dogEditView.setVisible(false);
            this.dogEditView.setDog(null);
        });

        this.add(this.textField);
        this.add(this.grid);
        this.add(this.newButton);
        this.add(this.dogEditView);
    }

    private void refreshGrid(String filter){
        this.grid.setItems(
                filter != null && !"".equals(filter.trim())?
                        dogRepository.findByNameContainsIgnoreCase(filter)
                        :
                        dogRepository.findAll()
        );
    }
}