import java.util.Objects;

public class Card {
    private final String type;


    public Card(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("Card Type: %s \n",type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(type, card.type);
    }

}