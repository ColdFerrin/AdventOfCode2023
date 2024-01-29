package org.coldferrin.day7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class HandPt2 {

    private final ArrayList<Card> cards;

    private final HandTypePt2 handTypePt2;

    public HandPt2(String cards) {
        this.cards = new ArrayList<>();
        for (char card : cards.toCharArray()) {
            this.cards.add(Card.getCard(String.valueOf(card)));
        }

        this.handTypePt2 = HandTypePt2.findHand(this.cards);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public enum Card{
        ACE("A"),
        KING("K"),
        QUEEN("Q"),
        TEN("T"),
        NINE("9"),
        EIGHT("8"),
        SEVEN("7"),
        SIX("6"),
        FIVE("5"),
        FOUR("4"),
        THREE("3"),
        TWO("2"),
        JACK("J");

        public final String value;

        Card(String value) {
            this.value = value;
        }

        public static Card getCard(String value) {
            for (Card card : values()){
                if (card.value.equalsIgnoreCase(value)){
                    return card;
                }
            }
            return null;
        }
    }

    public static class handComparator implements Comparator<HandPt2>{

        @Override
        public int compare(HandPt2 o1, HandPt2 o2) {

            HandTypePt2 handTypePt2O1 = o1.handTypePt2;
            HandTypePt2 handTypePt2O2 = o2.handTypePt2;

            int handComparison = handTypePt2O1.compareTo(handTypePt2O2);

            if (handComparison != 0) {
                return handComparison;
            }

            for (int i = 0; i < o1.cards.size(); i++) {
                int value = o1.cards.get(i).compareTo(o2.cards.get(i));
                if (value != 0) {
                    return value;
                }
            }

            return 0;
        }


    }

    public enum HandTypePt2 {
        FIVE_OF_A_KIND,
        FOUR_OF_A_KIND,
        FULL_HOUSE,
        THREE_OF_A_KIND,
        TWO_PAIR,
        ONE_PAIR,
        HIGH_CARD;

        public static HandTypePt2 findHand(ArrayList<Card> cards){

            Map<Card, Long> counts = cards.stream().collect(Collectors.groupingBy(card -> card, Collectors.counting()));

            if (counts.size() == 1){
                return FIVE_OF_A_KIND;
            } else if (counts.size() == 2 && counts.containsKey(Card.JACK)){
                return FIVE_OF_A_KIND;
            } else if (counts.size() == 2 && counts.containsValue(4L)) {
                return FOUR_OF_A_KIND;
            } else if (counts.size() == 2 && counts.containsValue(3L)) {
                return FULL_HOUSE;
            } else if (counts.size() == 3 && counts.containsValue(3L) && counts.containsKey(Card.JACK)){
                return FOUR_OF_A_KIND;
            } else if (counts.size() == 3 && counts.containsValue(2L) && counts.containsKey(Card.JACK) && counts.get(Card.JACK) == 2L){
                return FOUR_OF_A_KIND;
            } else if (counts.size() == 3 && counts.containsValue(2L) && counts.containsKey(Card.JACK)){
                return FULL_HOUSE;
            } else if (counts.size() == 3 && counts.containsValue(3L)) {
                return THREE_OF_A_KIND;
            } else if (counts.size() == 3) {
                return TWO_PAIR;
            } else if (counts.size() == 4 && counts.containsKey(Card.JACK)) {
                return THREE_OF_A_KIND;
            } else if (counts.size() == 4) {
                return ONE_PAIR;
            } else {
                return HIGH_CARD;
            }
        }
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                '}';
    }
}
