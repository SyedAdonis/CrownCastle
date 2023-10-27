package API;

import Parameters.ApiCollection;
import Parameters.BaseClass;
import Parameters.Pojo.NewDeck;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;

public class PlayBlackJack extends BaseClass {
    ApiCollection api;

    @BeforeClass
    public void beforeClass(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        api = new ApiCollection(apiHost);
    }

    @Test(priority = 0, enabled = true)
    public void theCardGame() throws Exception {

        // Checking if the site is up - Not needed as the other calls proves that the site is up. Doing this step to follow the doc
        api.siteIsUp();

        // Number of Players
        String[] players = new String[]{"player1", "player2"};

        // Getting a new Deck
        NewDeck nd = api.brandNewDeck();
        String id = nd.getDeck_id();

        // Shuffling it
        api.shuffle(id);

        // Dealing 3 cards to 2 players. Note [I'm new to blackjack, so please forgive my inexperience.]
        Arrays.stream(players).forEach(n -> {api.drawAndPile(id, 3, n);});

        // Check all players for blackjack and print the names of players with blackjack to the console.
        api.checkForBlackJack(id, players);



    }
}
