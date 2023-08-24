package by.auditsalution.selection.util;

import lombok.experimental.UtilityClass;

/**
 * 3 стр = 3 - Кредит;                  1 - Дебит
 * 5 стр = 5 - Кредит;                  3 - Дебит
 * 6 стр = 5, 6 - Кредит;               2, 3 - Дебит
 * 7 стр = 7, 6 - Кредит;               2, 3, 4 - Дебит
 * 8 стр = 8, 7 - Кредит;               2, 3, 4, 5 - Дебит
 * 9 стр = 8, 7, 6, 9 - Кредит;         1, 2, 3, 4 - Дебит
 * 10 стр = 11, 10, 6, 7, 8, 9 - Кредит; 1, 2, 3, 4 - Дебит
 * 11 стр = 11, 10, 6, 7, 8, 9 - Кредит; 1, 2, 3, 4 - Дебит
 * 13 стр = 11, 10, 7, 8, 9, 12, 13 - Кредит; 2, 3, 4, 5 - Дебит
 */

@UtilityClass
public class AnalyticsUtil {

    public String getAnalyticsDebit(String value) {
        StringBuilder stringBuilderDebit = new StringBuilder();
        String[] split = getStrings(value);
        int length = getLength(split);
        if (length == 3) {
            stringBuilderDebit.append(split[0]);
        } else if (length == 5) {
            stringBuilderDebit.append(split[2]);
        } else if (length == 6) {
            stringBuilderDebit.append(split[1]).append('\n').append(split[2]);
        } else if (length == 7) {
            stringBuilderDebit.append(split[1]).append('\n').append(split[2]).append('\n').append(split[3]);
        } else if (length == 8) {
            stringBuilderDebit.append(split[1]).append('\n').append(split[2]).append('\n').append(split[3]).append('\n').append(split[4]);
        } else if (length == 9) {
            stringBuilderDebit.append(split[0]).append('\n').append(split[1]).append('\n').append(split[2]).append('\n').append(split[3]);
        } else if (length == 10) {
            stringBuilderDebit.append(split[0]).append('\n').append(split[1]).append('\n').append(split[2]).append('\n').append(split[3]);
        } else if (length == 11) {
            stringBuilderDebit.append(split[0]).append('\n').append(split[1]).append('\n').append(split[2]).append('\n').append(split[3]);
        } else if (length == 13) {
            stringBuilderDebit.append(split[1]).append('\n').append(split[2]).append('\n').append(split[3]).append('\n').append(split[4]);
        } else {
            return "x";
        }
        return stringBuilderDebit.toString();
    }

    public String getAnalyticsCredit(String value) {
        StringBuilder stringBuilderCredit = new StringBuilder();
        String[] split = getStrings(value);
        int length = getLength(split);

        if (length == 3) {
            stringBuilderCredit.append(split[2]);
        } else if (length == 5) {
            stringBuilderCredit.append(split[4]);
        } else if (length == 6) {
            stringBuilderCredit.append(split[4]).append('\n').append(split[5]);
        } else if (length == 7) {
            stringBuilderCredit.append(split[5]).append('\n').append(split[6]);
        } else if (length == 8) {
            stringBuilderCredit.append(split[6]).append('\n').append(split[7]);
        } else if (length == 9) {
            stringBuilderCredit.append(split[5]).append('\n').append(split[6]).append('\n').append(split[7]).append('\n').append(split[8]);
        } else if (length == 10) {
            stringBuilderCredit.append(split[5]).append('\n').append(split[6]).append('\n').append(split[7]).append('\n').append(split[8])
                    .append('\n').append(split[9]).append('\n').append(split[10]);
        } else if (length == 11) {
            stringBuilderCredit.append(split[5]).append('\n').append(split[6]).append('\n').append(split[7]).append('\n').append(split[8])
                    .append('\n').append(split[9]).append('\n').append(split[10]);
        }else if (length == 13) {
            stringBuilderCredit.append(split[6]).append('\n').append(split[7]).append('\n').append(split[8]).append('\n').append(split[9]).append('\n').append(split[10])
                    .append('\n').append(split[11]).append('\n').append(split[12]);
        } else {
            return "x";
        }
        return stringBuilderCredit.toString();
    }

    private String[] getStrings(String value) {
        String[] split = value.split("\n");
        return split;
    }

    private int getLength(String[] split) {
        return split.length;
    }


}
