package ohrm.search;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.waits.Wait;
import org.hamcrest.Matchers;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.*;

public class Search {

    private static final Target SEARCH_FIELD = Target.the("Search by name field")
            .locatedBy("//div[contains(@class,'oxd-input-group')][contains(.,'Employee Name')]//input");

    private static final Target AUTOCOMPLETE_OPTION = Target.the("Autocomplete option {0}")
            .locatedBy("//div[contains(@class,'oxd-autocomplete-option')][contains(.,'{0}')]");

    public static Task forAnEmployeeCalled(String firstName) {
        return Task.where("{0} searches for an employee called " + firstName,
                Enter.theValue(firstName).into(SEARCH_FIELD),
                Wait.until(autocompleteOptions(), hasItem(containsString(firstName))),
                Click.on(AUTOCOMPLETE_OPTION.of(firstName)),
                Click.on(Button.withText("Search"))
        );
    }

    public static Question<Collection<String>> autocompleteOptions() {
        return Question.about("autocomplete options")
                .answeredBy(actor -> Text.ofEach(".oxd-autocomplete-option").answeredBy(actor));
    }

}
