package by.auditsalution.selection.comparator;

import by.auditsalution.selection.model.Card;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class CardComparator implements Comparator<Card> {

    @Override
    public int compare(Card o1, Card o2) {
        return Double.compare(o1.getCommonColumn(), o2.getCommonColumn());
    }
}
