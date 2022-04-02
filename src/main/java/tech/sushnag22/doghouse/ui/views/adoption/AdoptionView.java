package tech.sushnag22.doghouse.ui.views.adoption;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import tech.sushnag22.doghouse.backend.entity.Adoption;
import tech.sushnag22.doghouse.backend.repository.AdoptionRepository;
import tech.sushnag22.doghouse.ui.MainLayout;

@PreserveOnRefresh
@Route(value = "manage-adoption", layout = MainLayout.class)
@PageTitle("Manage Dog Adoption")
public class AdoptionView extends VerticalLayout {
    private final AdoptionRepository adoptionRepository;
    private final TextField textField;
    private final Grid<Adoption> grid;
    private final Button newButton;
    private final AdoptionEditView adoptionEditView;

    public AdoptionView(AdoptionRepository adoptionRepository, AdoptionEditView adoptionEditView){
        this.adoptionRepository = adoptionRepository;
        this.adoptionEditView = adoptionEditView;

        this.textField = new TextField("Adopter Name: ");
        this.textField.setValueChangeMode(ValueChangeMode.EAGER);
        this.textField.addValueChangeListener(e -> this.refreshGrid(e.getValue()));

        this.grid = new Grid<>(Adoption.class);
        this.grid.setColumns("adoptionDate", "adopter.name", "dog.name");
        this.grid.asSingleSelect().addValueChangeListener(e -> adoptionEditView.setAdoption(e.getValue()));
        this.refreshGrid(null);

        this.newButton = new Button("New Adoption", VaadinIcon.PLUS.create());
        this.newButton.addClickListener(e -> this.adoptionEditView.setAdoption(new Adoption()));

        this.adoptionEditView.setAdoptionEditViewHandler(() -> {
            this.refreshGrid(this.textField.getValue());
            this.adoptionEditView.setVisible(false);
            this.adoptionEditView.setAdoption(null);
        });

        this.add(this.textField);
        this.add(this.grid);
        this.add(this.newButton);
        this.add(this.adoptionEditView);
    }

    private void refreshGrid(String filter){
        this.grid.setItems(
                filter != null && !"".equals(filter.trim())?
                        adoptionRepository.findByAdopter_NameContainsIgnoreCase(filter)
                        :
                        adoptionRepository.findAll()
        );
    }

}