package tech.sushnag22.doghouse.ui.views.home;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import tech.sushnag22.doghouse.ui.MainLayout;

@PageTitle("Dog House")
@Route(value = "home", layout = MainLayout.class)
public class HomeView extends VerticalLayout {

    public HomeView() {
        setSpacing(false);

        Image img = new Image("images/doghouse.png", "dog house");
        img.setWidth("400px");
        add(img);

        add(new H2("Dog Adoption Management System"));
        add(new Paragraph("Manage dog adoption with ease"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
