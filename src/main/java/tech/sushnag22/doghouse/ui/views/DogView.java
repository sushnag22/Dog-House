package tech.sushnag22.doghouse.ui.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import tech.sushnag22.doghouse.backend.entity.Dog;
import tech.sushnag22.doghouse.backend.repository.DogRepository;

@Route("edit-dog")
@PageTitle("Edit Dog Details")
public class DogView extends VerticalLayout {

    private final TextField textField;
    private final Grid<Dog> grid;
    private final Button newButton;
    private final DogRepository dogRepository;
    private final DogEditView dogEditView;

    public DogView(DogRepository dogRepository, DogEditView dogEditView) {
        this.dogRepository = dogRepository;
        this.dogEditView = dogEditView;

        this.textField = new TextField("Dog Name: ");
        this.textField.setValueChangeMode(ValueChangeMode.EAGER);
        this.textField.addValueChangeListener(e -> refreshGrid(e.getValue()));

        this.grid = new Grid<>(Dog.class);
        this.grid.setColumns("name", "birthDate", "gender", "colour", "description", "location");
        this.grid.asSingleSelect().addValueChangeListener(e -> dogEditView.setDog(e.getValue()));
        this.refreshGrid(null);

        this.newButton = new Button("New Dog");
        this.newButton.addClickListener(e -> dogEditView.setDog(new Dog()));

        this.dogEditView.setDogEditViewHandler(() -> {
            this.refreshGrid(textField.getValue());
            this.dogEditView.setVisible(false);
        });

        this.add(textField);
        this.add(grid);
        this.add(newButton);
        this.add(dogEditView);
    }

    private void refreshGrid(String name) {
        grid.setItems(
                name != null && !"".equals(name.trim()) ?
                        dogRepository.findByNameContainsIgnoreCase(name)
                        :
                        dogRepository.findAll()
        );
    }

}
