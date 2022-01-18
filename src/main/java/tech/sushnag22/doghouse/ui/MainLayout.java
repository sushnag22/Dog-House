package tech.sushnag22.doghouse.ui;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import tech.sushnag22.doghouse.backend.entity.User;
import tech.sushnag22.doghouse.ui.views.SignInView;

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

        RouterLink logOutLink = new RouterLink("Log out", SignInView.class);
        logOutLink.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(new VerticalLayout(logOutLink));
    }

}