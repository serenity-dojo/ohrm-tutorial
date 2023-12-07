package ohrm.stepdefinitions;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import ohrm.domain.UserCardDetails;
import ohrm.domain.UserRoles;
import ohrm.navigation.Login;
import ohrm.navigation.Navigate;
import ohrm.directory.DisplayedUsers;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class NavigationSteps {

    @Given("{actor} is logged in as an admin")
    public void logged_in_as_an_admin(Actor actor) {
        actor.attemptsTo(
                Login.as(UserRoles.ADMIN)
        );
    }

    @When("{actor} views the user directory")
    public void views_the_user_directory(Actor actor) {
        actor.attemptsTo(
                Navigate.toTheUserDirectory()
        );
    }

    @DataTableType
    public UserCardDetails userCardDetails(Map<String, String> entry) {
        return new UserCardDetails(entry.get("Name"),entry.get("Role"));
    }

    @Then("{actor} should see the following users:")
    public void she_should_see_the_following_users(Actor actor,
                                                   List<UserCardDetails> userDetails) {
        System.out.println("userDetails = " + userDetails);

        List<UserCardDetails> displayedUsers = actor.asksFor(DisplayedUsers.onThePage());
        assertThat(displayedUsers).containsAll(userDetails);
    }
}
