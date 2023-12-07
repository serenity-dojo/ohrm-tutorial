package ohrm.navigation;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import org.openqa.selenium.By;

public class Navigate {
    public static Task toTheUserDirectory() {
        return Task.where("{} goes to the user directory page",
                Click.on(By.linkText("Directory"))
        );
    }
}
