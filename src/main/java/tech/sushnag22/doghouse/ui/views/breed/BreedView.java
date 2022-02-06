package tech.sushnag22.doghouse.ui.views.breed;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import tech.sushnag22.doghouse.backend.entity.Breed;
import tech.sushnag22.doghouse.backend.repository.BreedRepository;
import tech.sushnag22.doghouse.ui.MainLayout;

@Route(value = "add-breed", layout = MainLayout.class)
@PageTitle("Add Dog Breed")
public class BreedView extends VerticalLayout {

    private final TextField textField;
    private final Grid<Breed> grid;
    private final Button newButton;
    private final BreedRepository breedRepository;
    private final BreedEditView breedEditView;

    public BreedView(BreedRepository breedRepository, BreedEditView breedEditView) {
        this.breedRepository = breedRepository;
        this.breedEditView = breedEditView;

        this.textField = new TextField("Breed Name: ");
        this.textField.setValueChangeMode(ValueChangeMode.EAGER);
        this.textField.addValueChangeListener(e -> refreshGrid(e.getValue()));

        this.grid = new Grid<>(Breed.class);
        this.grid.setColumns("name");
        this.grid.asSingleSelect().addValueChangeListener(e -> breedEditView.setBreed(e.getValue()));
        this.refreshGrid(null);

        this.newButton = new Button("New Breed");
        this.newButton.addClickListener(e -> breedEditView.setBreed(new Breed()));

        this.breedEditView.setBreedEditViewHandler(() -> {
            this.refreshGrid(textField.getValue());
            this.breedEditView.setVisible(false);
        });

        this.add(textField);
        this.add(grid);
        this.add(newButton);
        this.add(breedEditView);
    }

    private void refreshGrid(String name) {
        grid.setItems(
                name != null && !"".equals(name.trim()) ?
                        breedRepository.findByNameContainsIgnoreCase(name)
                        :
                        breedRepository.findAll()
        );
    }

}