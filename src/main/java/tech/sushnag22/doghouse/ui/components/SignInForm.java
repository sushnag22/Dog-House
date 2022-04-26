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
import tech.sushnag22.doghouse.backend.entity.User;
import tech.sushnag22.doghouse.backend.service.UserService;

public class SignInForm extends FormLayout {

    private UserService userService;

    TextField username = new TextField("Username");
    PasswordField password = new PasswordField("Password");

    Button signInButton = new Button("Sign In");

    Span notAMember = new Span("New User?");
    Anchor signUp = new Anchor("signup", "Sign Up");


    public SignInForm(UserService userService) {

        this.userService = userService;
        ComponentUtil.setData( UI.getCurrent() , User.class , null);
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

        User user = userService.findUserByUsernameAndPassword((String)username.getValue(), (String)password.getValue());

        if(user != null) {
            ComponentUtil.setData( UI.getCurrent() , User.class , user);
            UI.getCurrent().navigate("home");
        } else {
            com.vaadin.flow.component.notification.Notification notification = new Notification(
                    "Wrong Credentials", 3000);
            notification.open();
        }
    }
}