package tech.sushnag22.doghouse.ui;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import tech.sushnag22.doghouse.backend.entity.User;
import tech.sushnag22.doghouse.ui.views.adopter.AdopterView;
import tech.sushnag22.doghouse.ui.views.adoption.AdoptionView;
import tech.sushnag22.doghouse.ui.views.dog.DogView;
import tech.sushnag22.doghouse.ui.views.auth.SignInView;

@JsModule("./js/theme-selector.js")
@Route(value = "home")
public class MainLayout extends AppLayout {

    public MainLayout() {

        if (ComponentUtil.getData(UI.getCurrent(), User.class) == null) {
            UI.getCurrent().navigate("error");
        } else {
            createHeader();
            createDrawer();
        }
    }

    private void createHeader() {

        H2 userGreeting = new H2("Hello, " + ComponentUtil.getData(UI.getCurrent(), User.class).getFirstName() + "!");

        userGreeting.addClassName("logo");
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), userGreeting);
        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");

        addToNavbar(header);

    }

    private void createDrawer() {

        RouterLink editDogLink = new RouterLink("Edit Dog Details", DogView.class);
        editDogLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink editAdopterLink = new RouterLink("Edit Adopter Details", AdopterView.class);
        editDogLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink manageAdoptionLink = new RouterLink("Manage Adoption", AdoptionView.class);
        editDogLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink logOutLink = new RouterLink("Log out", SignInView.class);
        editDogLink.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(new VerticalLayout(editDogLink, editAdopterLink, manageAdoptionLink,logOutLink));
    }

}