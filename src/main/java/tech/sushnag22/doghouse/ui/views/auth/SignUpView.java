package tech.sushnag22.doghouse.ui.views.auth;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import tech.sushnag22.doghouse.backend.service.UserService;
import tech.sushnag22.doghouse.ui.components.SignUpForm;

@Route("signup")
public class SignUpView extends VerticalLayout {

    private UserService userService;

    public SignUpView(UserService userService) {
        this.userService = userService;
        SignUpForm signUpForm = new SignUpForm(userService);
        H2 header = new H2("Sign Up");
        HorizontalLayout horizontalLayout = new HorizontalLayout(header, signUpForm);
        horizontalLayout.setAlignItems(Alignment.CENTER);
        horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setHorizontalComponentAlignment(Alignment.CENTER, header);
        add(header, horizontalLayout);

    }

}
