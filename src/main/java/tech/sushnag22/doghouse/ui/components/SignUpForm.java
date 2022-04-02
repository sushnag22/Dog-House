package tech.sushnag22.doghouse.ui.components;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
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

public class SignUpForm extends FormLayout {

    private UserService userService;

    TextField username = new TextField("Username");
    PasswordField password = new PasswordField("Password");
    TextField email = new TextField("Email");
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");

    Button signUpButton = new Button("Sign up");

    Span alreadySignedUp = new Span("Existing User?");
    Anchor goToLoginForm = new Anchor("","Sign In");


    public SignUpForm(UserService userService) {

        this.userService = userService;
        signUpButton.addClickListener(buttonClickEvent -> signUpEvent());
        signUpButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        signUpButton.addClickShortcut(Key.ENTER);
        add(new VerticalLayout(
                firstName,
                lastName,
                email,
                username,
                password,
                signUpButton,
                alreadySignedUp,
                goToLoginForm
        ));
    }

    public void signUpEvent() {
        User user = new User(
                username.getValue(),
                password.getValue(),
                email.getValue(),
                firstName.getValue(),
                lastName.getValue());
        if(userService.isValid(user) == null) {
            System.out.println("Success");
            userService.save(user);
            UI.getCurrent().navigate("");
        } else {
            System.out.println("Error");
            for (String s : userService.isValid(user)) {
                Notification notification = new Notification(s, 3000);
                notification.open();
            }
        }

    }
}