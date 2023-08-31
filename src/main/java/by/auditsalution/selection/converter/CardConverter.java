package by.auditsalution.selection.converter;

import by.auditsalution.selection.exception.ServiceException;
import by.auditsalution.selection.model.*;
import by.auditsalution.selection.util.AccountUtil;
import by.auditsalution.selection.util.Card1CUtil;
import by.auditsalution.selection.util.VariableTypeUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class CardConverter {

    private static final String AMOUNT = "Кол-во";
    private static final String CURRENCY = "В валюте";

    public Map<Account, List<Card>> getCard1CV7Of(List<Card1CTemp> card1CTemps, Map<String, Account> replacementAccountMap) {
        /**
         * создать Темп Мап, добавлять туда счиатнные счета
         *
         * кождый счет в свой счет А01
         *
         * добавить проверку, что если под этим ключом уже есть данные - то добавить
         *
         * если нет - то добавить
         */
        Map<Account, List<Card>> accountListMap = AccountUtil.getEmptyAccountListMap();
        List<Card> card1C7List = new ArrayList<>(card1CTemps.size());
        Optional<Account> account = Optional.empty();
        for (Card1CTemp card1CTemp : card1CTemps) {
            if (Card1CUtil.isCardNumber(card1CTemp.getCell0())){
                if (account.isPresent() && !card1C7List.isEmpty()){
                    accountListMap.put(account.get(), card1C7List);
                    card1C7List = new ArrayList<>(card1CTemps.size());
                }
                String accountFromCardNumber = Card1CUtil.getAccountFromCardNumber(card1CTemp.getCell0());
                if (replacementAccountMap != null && !replacementAccountMap.isEmpty()){
                    Account replacementAccount = replacementAccountMap.get(accountFromCardNumber);
                    if (replacementAccount != null){
                        account = Optional.of(replacementAccount) ;
                    }else {
                        account = AccountUtil.getAccount(accountFromCardNumber);
                    }
                } else {
                    account = AccountUtil.getAccount(accountFromCardNumber);
                }
                if (!account.isPresent()){
                    throw new ServiceException("Не найден счет из карты...!!!");
                }
            }
            if (isOperationRows(card1CTemp)){
                Card card1C = new Card();
                card1C.setPeriod(card1CTemp.getCell0());
                card1C.setDocument(card1CTemp.getCell1());
                card1C.setAnalyticsDt(card1CTemp.getCell2());

                card1C.setDebitAccount(card1CTemp.getCell3());
                card1C.setDebitSum(convertToDouble(card1CTemp.getCell4()));
                card1C.setCreditAccount(card1CTemp.getCell5());
                card1C.setCreditSum(convertToDouble(card1CTemp.getCell6()));
                card1C.setBalanceChar(card1CTemp.getCell7());
                card1C.setBalanceSum(convertToDouble(card1CTemp.getCell8()));
                card1C7List.add(card1C);
            } else if (isAmountRows(card1CTemp)) {
                Card card1C = card1C7List.get(getLastIndex(card1C7List));
                card1C.setAmount(Amount.builder()
                        .name(card1CTemp.getCell2())
                        .value(card1CTemp.getCell4())
                        .build());
            } else if (isAmountCurrency(card1CTemp)) {
                Card card1C = card1C7List.get(getLastIndex(card1C7List));
                card1C.setAmountCurrency(Currency.builder()
                        .name(card1CTemp.getCell2())
                        .value(card1CTemp.getCell4())
                        .build());
            }
        }
        if (account.isPresent() && !card1C7List.isEmpty()){
            accountListMap.put(account.get(), card1C7List);
        }
        return accountListMap;
    }

    @Deprecated
    public Map<Account, List<Card>> getCard1CV8Of(List<Card1CTemp> card1CTemps) {
        /**
         * // TODO: 16.06.2023 Не использовать - !!!!!!
         *
         * до конца не дописана, только для версии 1с7
         */
        Map<Account, List<Card>> accountListMap = AccountUtil.getEmptyAccountListMap();
        List<Card> card1C8List = new ArrayList<>(card1CTemps.size());
        for (Card1CTemp card1CTemp : card1CTemps) {
            if (isOperationRows(card1CTemp)) {
                Card card1C = new Card();
                card1C.setPeriod(card1CTemp.getCell0());
                card1C.setDocument(card1CTemp.getCell1());
                card1C.setAnalyticsDt(card1CTemp.getCell2());
                card1C.setAnalyticsKt(card1CTemp.getCell3());
                card1C.setDebitAccount(card1CTemp.getCell4());
                card1C.setDebitSum(convertToDouble(card1CTemp.getCell5()));
                card1C.setCreditAccount(card1CTemp.getCell6());
                card1C.setCreditSum(convertToDouble(card1CTemp.getCell7()));
                card1C.setBalanceChar(card1CTemp.getCell8());
                card1C.setBalanceSum(convertToDouble(card1CTemp.getCell9()));
                card1C8List.add(card1C);
                // TODO: 11.06.2023 ПЕРЕСМОТРЕТЬ ПОЛЯ - ТК МОЖЕТ НЕ СОВПАСТЬ В 1С8
            } else if (isAmountRows(card1CTemp)) {
                Card card1C = card1C8List.get(getLastIndex(card1C8List));
                card1C.setAmount(Amount.builder()
                        .name(card1CTemp.getCell2())
                        .value(card1CTemp.getCell4())
                        .build());
            } else if (isAmountCurrency(card1CTemp)) {
                Card card1C = card1C8List.get(getLastIndex(card1C8List));
                card1C.setAmountCurrency(Currency.builder()
                        .name(card1CTemp.getCell2())
                        .value(card1CTemp.getCell4())
                        .build());
            }
        }
        return accountListMap;
    }

    private int getLastIndex(List<Card> card1CList) {
        return card1CList.size() - 1;
    }

    private boolean isAmountRows(Card1CTemp card1CTemp) {
        return card1CTemp.getCell2() != null && card1CTemp.getCell2().contains(AMOUNT);
    }

    private boolean isAmountCurrency(Card1CTemp card1CTemp) {
        return card1CTemp.getCell2() != null && card1CTemp.getCell2().contains(CURRENCY);
    }

    private boolean isOperationRows(Card1CTemp card1CTemp) {
        return card1CTemp.getCell0() != null && VariableTypeUtil.isData(card1CTemp.getCell0());
    }

    private double convertToDouble(String value) {
        return value.trim().isBlank() ? 0.0d : Double.parseDouble(value.trim());
    }

}
