package tech.sushnag22.doghouse.ui.views.adopter;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import tech.sushnag22.doghouse.backend.entity.Adopter;
import tech.sushnag22.doghouse.backend.repository.AdopterRepository;

@Route("edit-adopter")
@PageTitle("Edit Adopter Details")
public class AdopterView extends VerticalLayout {

    private final TextField textField;
    private final Grid<Adopter> grid;
    private final Button newButton;
    private final AdopterRepository adopterRepository;
    private final AdopterEditView adopterEditView;

    public AdopterView(AdopterRepository adopterRepository, AdopterEditView adopterEditView) {
        this.adopterRepository = adopterRepository;
        this.adopterEditView = adopterEditView;

        this.textField = new TextField("Adopter Name: ");
        this.textField.setValueChangeMode(ValueChangeMode.EAGER);
        this.textField.addValueChangeListener(e -> refreshGrid(e.getValue()));

        this.grid = new Grid<>(Adopter.class);
        this.grid.setColumns("firstName", "lastName" ,"birthDate", "gender", "email", "phone", "address");
        this.grid.asSingleSelect().addValueChangeListener(e -> adopterEditView.setAdopter(e.getValue()));
        this.refreshGrid(null);

        this.newButton = new Button("New Adopter");
        this.newButton.addClickListener(e -> adopterEditView.setAdopter(new Adopter()));

        this.adopterEditView.setAdopterEditViewHandler(() -> {
            this.refreshGrid(textField.getValue());
            this.adopterEditView.setVisible(false);
        });

        this.add(textField);
        this.add(grid);
        this.add(newButton);
        this.add(adopterEditView);
    }

    private void refreshGrid(String name) {
        grid.setItems(
                name != null && !"".equals(name.trim()) ?
                        adopterRepository.findByFirstNameContainsIgnoreCase(name)
                        :
                        adopterRepository.findAll()
        );
    }

}
