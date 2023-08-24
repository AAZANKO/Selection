package by.auditsalution.selection.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SampleResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private int countTransactions;                          // Общее количество проводок / Количество элементов генеральной совокупности

    private double sumPopulationSample;                     // Сумма генеральной совокупности для выборки (общая сумма по кредиту и дебету)

    private double amountOfFoundAccountsCredit;             // Сумма по кредиту - по всем найденным счетам

    private double amountOfFoundAccountsDebit;              // Сумма по дебету - по всем найденным счетам

    private double totalOfFoundAccounts;                    // Итого: = Сумма по дебету + Сумма по кредиту

}
