package ohrm.navigation;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.targets.SearchableTarget;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.InputField;
import ohrm.domain.UserRoles;

public class Login {

    private static final SearchableTarget USERNAME_FIELD = InputField.withPlaceholder("Username");
    private static final SearchableTarget PASSWORD_FIELD = InputField.withPlaceholder("Password");
    private static final SearchableTarget LOGIN_BUTTON = Button.withText("Login");

    public static Performable as(UserRoles user) {
        return Task.where("{} logs in as " + user.username(),
                Open.url("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"),
                Enter.theValue(user.username()).into(USERNAME_FIELD),
                Enter.theValue(user.password()).into(PASSWORD_FIELD),
                Click.on(LOGIN_BUTTON)
        );
    }
}
