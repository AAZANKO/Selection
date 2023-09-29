package by.auditsalution.selection.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Builder
public class OutputResult implements Serializable {

    private static final long serialVersionUID = 1L;

    // счета с отрицательными суммами
    private Map<Account, List<Card>> minusAccountMaxMap;

    private Map<Account, List<Card>> minusAccountRandomMap;

    // счета с одинакоыми счетами (кредит/дебет)
    private Map<Account, List<Card>> doubleAccountMaxMap;

    private Map<Account, List<Card>> doubleAccountRandomMap;

    // обычные счета
    private Map<Account, List<Card>> accountMaxMap;

    private Map<Account, List<Card>> accountRandomMap;

    // сумма по счета
    private Map<Account, TotalSum> accountTotalMaxMap;

    private Map<Account, TotalSum> accountTotalRandomMap;

    // сумма по счетам с отрицательными суммами
    private Map<Account, TotalSum> minusTotalMaxMap;

    private Map<Account, TotalSum> minusTotalRandomMap;

    // сумма по счетам с одинакоыми счетами (кредит/дебет)
    private Map<Account, TotalSum> doubleTotalMaxMap;

    private Map<Account, TotalSum> doubleTotalRandomMap;

}
