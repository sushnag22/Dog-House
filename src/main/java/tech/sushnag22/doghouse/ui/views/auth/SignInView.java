package tech.sushnag22.doghouse.ui.views.auth;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import tech.sushnag22.doghouse.backend.service.UsersService;
import tech.sushnag22.doghouse.ui.components.SignInForm;

@PreserveOnRefresh
@Route("")
public class SignInView  extends VerticalLayout {
    private UsersService usersService;

    public SignInView(UsersService usersService) {
        this.usersService = usersService;

        SignInForm signInForm = new SignInForm(usersService);
        H1 title = new H1("Dog Adoption Management System");
        H2 header = new H2("Sign In");
        H4 text = new H4("(Or use the default credentials- username: anonymous and password: user1)");
        HorizontalLayout horizontalLayout = new HorizontalLayout(signInForm);
        horizontalLayout.setWidthFull();
        horizontalLayout.setAlignItems(Alignment.CENTER);
        horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        setHeightFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setHorizontalComponentAlignment(Alignment.CENTER, header);
        add(title, header, text, horizontalLayout);
    }
}