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
public class Card implements Serializable {

    private static final long serialVersionUID = 1L;

    private String period; // Период / 14.01.20
    private String document; // Документ / Поступление материалов по чеку РУП
    private String analyticsDt; // Аналитика Дт / Марка стандартная
    private String analyticsKt; // Аналитика Кт / Хозяйственные расходы
    private String debitAccount; // Дебет / Счет = 10.1
    private double debitSum; // Дебет / Сумма = 19,00
    private String creditAccount; // Кредит / Счет = 71.1
    private double creditSum; // Кредит / Сумма = 19,00
    private String balanceChar; // Текущее сальдо / 'Д'
    private double balanceSum; // Текущее сальдо / Сумма = 90,80
    private double commonColumn; // Для сортировки
    private Amount amount;  // Кол-во
    private Currency amountCurrency; // В валюте
}
