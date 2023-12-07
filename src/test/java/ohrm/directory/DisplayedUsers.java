package ohrm.directory;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import ohrm.domain.UserCardDetails;

import java.util.List;

public class DisplayedUsers {
    public static Question<List<UserCardDetails>> onThePage() {
        return Question.about("the displayed users").answeredBy(actor ->
            BrowseTheWeb.as(actor).findAll(".orangehrm-directory-card")
                    .stream()
                    .map(
                            card -> new UserCardDetails(
                                    card.findBy(".orangehrm-directory-card-header").getText(),
                                    card.findBy(".orangehrm-directory-card-subtitle").getText()
                            )
                    ).toList()
        );
    }
}
