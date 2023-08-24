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


    private Map<Account, List<Card>> minusAccountMaxMap;

    private Map<Account, List<Card>> minusAccountRandomMap;


    private Map<Account, List<Card>> doubleAccountMaxMap;

    private Map<Account, List<Card>> doubleAccountRandomMap;


    private Map<Account, List<Card>> accountMaxMap;

    private Map<Account, List<Card>> accountRandomMap;


    private Map<Account, TotalSum> accountTotalMaxMap;

    private Map<Account, TotalSum> accountTotalRandomMap;


    private Map<Account, TotalSum> minusTotalMaxMap;

    private Map<Account, TotalSum> minusTotalRandomMap;


    private Map<Account, TotalSum> doubleTotalMaxMap;

    private Map<Account, TotalSum> doubleTotalRandomMap;

}
