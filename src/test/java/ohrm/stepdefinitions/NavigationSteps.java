package ohrm.stepdefinitions;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import ohrm.search.Search;
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
    @When("{actor} is in the user directory")
    public void views_the_user_directory(Actor actor) {
        actor.attemptsTo(
                Navigate.toTheUserDirectory()
        );
    }

    @DataTableType
    public UserCardDetails userCardDetails(Map<String, String> entry) {
        return new UserCardDetails(entry.get("Name"), entry.get("Role"));
    }

    @Then("{actor} should see the following users:")
    public void she_should_see_the_following_users(Actor actor,
                                                   List<UserCardDetails> userDetails) {
        System.out.println("userDetails = " + userDetails);

        List<UserCardDetails> displayedUsers = actor.asksFor(DisplayedUsers.onThePage());
        assertThat(displayedUsers).containsAll(userDetails);
    }

    @Then("{actor} should see users with names and roles")
    public void sheShouldSeeUsersWithNamesAndRoles(Actor actor) {
        List<UserCardDetails> displayedUsers = actor.asksFor(DisplayedUsers.onThePage());
        assertThat(displayedUsers)
                .isNotEmpty()
                .allMatch(user -> !user.name().isEmpty());
    }

    String chosenName;

    @When("{actor} searches for a user by first name")
    public void sheSearchesForAUserByFirstName(Actor actor) {
        // Find the list of all the full user names
        List<String> displayedUserNames = actor.asksFor(DisplayedUsers.onThePage())
                .stream()
                .map(UserCardDetails::name)
                .toList();

        // Pick a random  name
        chosenName = displayedUserNames.get((int) (Math.random() * displayedUserNames.size()));

        // Extract the first name
        String firstName = chosenName.split(" ")[0];

        actor.attemptsTo(
                Search.forAnEmployeeCalled(firstName)
        );
    }

    @Then("{actor} should see the user in the results")
    public void sheShouldSeeTheUserInTheResults(Actor actor) {
        List<UserCardDetails> displayedUsers = actor.asksFor(DisplayedUsers.onThePage());
        assertThat(displayedUsers).hasSize(1)
                .allMatch(user -> user.name().equals(chosenName));
    }
}
