package Parameters.Pojo;

// Creating Only One Pojo. Just to demonstrate my clear concept about Serialization and Deserialization
public class NewDeck {
    private String success;
    private String deck_id;
    private Integer remaining;
    private Boolean shuffled;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    public Boolean getShuffled() {
        return shuffled;
    }

    public void setShuffled(Boolean shuffled) {
        this.shuffled = shuffled;
    }


}
