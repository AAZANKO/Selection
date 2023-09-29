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
        Map<Account, List<Card>> accountListMap = AccountUtil.getEmptyAccountListMap();
        List<Card> card1C7List = new ArrayList<>(card1CTemps.size());
        Optional<Account> account = Optional.empty();
        for (Card1CTemp card1CTemp : card1CTemps) {
            if (Card1CUtil.isCardNumber(card1CTemp.getCell0())){
                if (account.isPresent() && !card1C7List.isEmpty()){
                    accountListMap.put(account.get(), card1C7List);
                    card1C7List = new ArrayList<>(card1CTemps.size());
                }
                Optional<String> accountFromCardNumber = Card1CUtil.getAccountFromCardNumber(card1CTemp.getCell0());
                if (accountFromCardNumber.isPresent()) {
                    account = getAccountOptional(replacementAccountMap, accountFromCardNumber.get(), account);
                } else {
                    throw new ServiceException("Не найден счет из карты...!!!");
                }
            }
            if (isOperationRows1C7(card1CTemp)){
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

//    @Deprecated
    public Map<Account, List<Card>> getCard1CV8Of(List<Card1CTemp> card1CTemps, Map<String, Account> replacementAccountMap) {
        Map<Account, List<Card>> accountListMap = AccountUtil.getEmptyAccountListMap();
        List<Card> card1C8List = new ArrayList<>(card1CTemps.size());
        Optional<Account> account = Optional.empty();
        for (Card1CTemp card1CTemp : card1CTemps) {
            if (Card1CUtil.isCardNumber(card1CTemp.getCell0())){
                if (account.isPresent() && !card1C8List.isEmpty()){
                    accountListMap.put(account.get(), card1C8List);
                    card1C8List = new ArrayList<>(card1CTemps.size());
                }
                Optional<String> accountFromCardNumber = Card1CUtil.getAccountFromCardNumber(card1CTemp.getCell0());
                if (accountFromCardNumber.isPresent()) {
                    account = getAccountOptional(replacementAccountMap, accountFromCardNumber.get(), account);
                } else {
                    throw new ServiceException("Не найден счет из карты...!!!");
                }
            }
            if (isOperationRows1C8(card1CTemp)){
                Card card1C = new Card();
                card1C.setPeriod(card1CTemp.getCell0());
                card1C.setDocument(card1CTemp.getCell1());
                card1C.setAnalyticsDt(card1CTemp.getCell2());
//                card1C.setAnalyticsKt(card1CTemp.getCell3());
                card1C.setDebitAccount(card1CTemp.getCell3());
                card1C.setDebitSum(convertToDouble(card1CTemp.getCell4()));
                card1C.setCreditAccount(card1CTemp.getCell5());
                card1C.setCreditSum(convertToDouble(card1CTemp.getCell6()));
                card1C.setBalanceChar(card1CTemp.getCell7());
                card1C.setBalanceSum(convertToDouble(card1CTemp.getCell8()));
                card1C8List.add(card1C);
            } else if (isAmountRows(card1CTemp)) {
                Card card1C = card1C8List.get(getLastIndex(card1C8List));
                String value = card1CTemp.getCell4().trim().isBlank() ? card1CTemp.getCell4() : card1CTemp.getCell6();
                card1C.setAmount(Amount.builder()
                        .name(card1CTemp.getCell3())
                        .value(value)
                        .build());
            } else if (isAmountCurrency(card1CTemp)) {
                Card card1C = card1C8List.get(getLastIndex(card1C8List));
                card1C.setAmountCurrency(Currency.builder()
                        .name(card1CTemp.getCell3())
                        .value(card1CTemp.getCell4())
                        .build());
            }
        }
        if (account.isPresent() && !card1C8List.isEmpty()){
            accountListMap.put(account.get(), card1C8List);
        }
        return accountListMap;
    }

    private Optional<Account> getAccountOptional(Map<String, Account> replacementAccountMap, String accountFromCardNumber, Optional<Account> account) {
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
        return account;
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

    private boolean isOperationRows1C7(Card1CTemp card1CTemp) {
        return card1CTemp.getCell0() != null && VariableTypeUtil.isData1C7(card1CTemp.getCell0());
    }

    private boolean isOperationRows1C8(Card1CTemp card1CTemp) {
        return card1CTemp.getCell0() != null && VariableTypeUtil.isData1C8(card1CTemp.getCell0());
    }

    private double convertToDouble(String value) {
        return value.trim().isBlank() ? 0.0d : Double.parseDouble(value.trim());
    }

}
