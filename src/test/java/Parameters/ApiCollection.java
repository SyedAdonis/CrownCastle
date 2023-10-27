package Parameters;

import Parameters.Pojo.NewDeck;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.testng.Assert;
import utils.MiscUtils;
import utils.RestUtil;

import java.util.*;

public class ApiCollection {

    private final String host;
    public ApiCollection(String host) {
        this.host = host;
    }

    public void siteIsUp() throws Exception {
        try {
            RestUtil.getMethod(host);
            MiscUtils.writeInfo("The site is Up");
        }
        catch (Exception e) {
            throw new Exception("The site is Down");
        }
    }
    
    public NewDeck brandNewDeck(){
        return RestUtil.getMethod(host, "/api/deck/new")
                .as(NewDeck.class);
    }

    public void shuffle(String deck_id) {
        NewDeck nd = RestUtil.getMethod(host, String.format("/api/deck/%s/shuffle", deck_id))
                .as(NewDeck.class);
        Assert.assertTrue(nd.getShuffled(), "Cards didn't get shuffled");
    }


    public String drawCards(String deck_id, Map<String, Object> count) {
        String responseString = RestUtil.getMethod(host, String.format("/api/deck/%s/draw", deck_id), count)
                .asString();

        // Directly using Gson to save time. Normally use the Pojo concept in here too.
        JsonArray jArray = JsonParser.parseString(responseString).getAsJsonObject().get("cards").getAsJsonArray();

        List<String> cards = new ArrayList<>();
        jArray.forEach( n -> {
            cards.add(n.getAsJsonObject().get("code").getAsString());
        });

        //Returning Comma Separated cards string
        return String.join(",", cards);
    }

    public void pileCard(String deck_id, String pileName, Map<String, Object> cards) {
        String response = RestUtil.getMethod(host, String.format("/api/deck/%s/pile/%s/add", deck_id, pileName), cards).asString();
        String success = RestUtil.getJsonParameterByPath(response, "success");
        Assert.assertTrue(success.equalsIgnoreCase("true"), String.format("Not able to create pile for %s ", pileName));
    }

    public void drawAndPile(String deck_id, Integer numberOfCards, String pileName) {

        Map<String, Object> count = Collections.singletonMap("count", numberOfCards);
        String playerOne = drawCards(deck_id, count);
        Map<String, Object> playerOnePile = Collections.singletonMap("cards", playerOne);
        pileCard(deck_id, pileName, playerOnePile);
    }

    public List<String> listPile(String deck_id, String pileName) {
        String pile = RestUtil.getMethod(host, String.format("/api/deck/%s/pile/%s/list", deck_id, pileName)).asString();

        // Directly using Gson to save time. Normally use the Pojo concept in here too.
        JsonArray jArray = JsonParser.parseString(pile).getAsJsonObject()
                .getAsJsonObject("piles").getAsJsonObject(pileName).getAsJsonArray("cards");

        List<String> cards = new ArrayList<>();
        jArray.forEach( n -> {
            cards.add(n.getAsJsonObject().get("value").getAsString());
        });
        return cards;
    }

    public void checkForBlackJack(String deck_id, String[] pileNames) {
        HashMap<String,List<String>> map = new HashMap<>();
        List<String> faceCards = List.of("QUEEN", "KING", "JACK");
        Arrays.stream(pileNames).forEach(n -> {
            List<String> pile = listPile(deck_id, n);
            map.put(n, pile);
        });
        map.forEach((k,v) -> {
            System.out.println("\n"+k + " pile: "+ String.join(",",v));
            if (v.contains("ACE") && v.stream().anyMatch(faceCards::contains) ) {
                MiscUtils.writeOnConsole(k + " Has Blackjack");
            }
            else{
                MiscUtils.writeInfo(k + " doesn't have Blackjack");
            }
        });

    }
}
