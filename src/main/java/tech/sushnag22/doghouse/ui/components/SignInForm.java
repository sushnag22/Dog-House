package tech.sushnag22.doghouse.ui.components;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import tech.sushnag22.doghouse.backend.entity.Users;
import tech.sushnag22.doghouse.backend.service.UsersService;

public class SignInForm extends FormLayout {

    private UsersService usersService;

    TextField username = new TextField("Username");
    PasswordField password = new PasswordField("Password");

    Button signInButton = new Button("Sign In");

    Span notAMember = new Span("New User?");
    Anchor signUp = new Anchor("signup", "Sign Up");


    public SignInForm(UsersService usersService) {

        this.usersService = usersService;
        ComponentUtil.setData( UI.getCurrent() , Users.class , null);
        signInButton.addClickListener(buttonClickEvent -> SignInEvent());
        signInButton.addClickShortcut(Key.ENTER);
        signInButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        username.setClearButtonVisible(true);
        password.setClearButtonVisible(true);

        add(new VerticalLayout(
                username,
                password,
                signInButton,
                notAMember,
                signUp
        ));
    }

    public void SignInEvent() {

        Users user = usersService.findUserByUsernameAndPassword((String)username.getValue(), (String)password.getValue());

        if(user != null) {
            ComponentUtil.setData( UI.getCurrent() , Users.class , user);
            UI.getCurrent().navigate("home");
        } else {
            com.vaadin.flow.component.notification.Notification notification = new Notification(
                    "Wrong Credentials", 3000);
            notification.open();
        }
    }
}