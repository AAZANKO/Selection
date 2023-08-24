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
public class InitialData implements Serializable {

    private static final long serialVersionUID = 1L;

    private AuditRisk risk;            // Аудиторский риск

    private double factor;              // Фактор надежности

    private int level;                  // Уровень существенности для данного раздела

    private int countMaxElement;        // Элементы наибольшей стоимости и ключевые элементы

    private int countRandomElement;     // Кол-во рандомных элементов совокупности и ключевых элементов

    private String account;             // Счет

    private SearchType accountType;     // кол-во счетов (один / все)
}
