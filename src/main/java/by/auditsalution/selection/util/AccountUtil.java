package by.auditsalution.selection.util;

import by.auditsalution.selection.model.Account;
import by.auditsalution.selection.model.Card;
import by.auditsalution.selection.model.SearchType;
import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class AccountUtil {

    private static Map<Account, List<Card>> accountListMap = new HashMap<>(Account.values().length);

    static {
        for (Account account : Account.values()) {
            accountListMap.put(account, null);
        }
    }

    public static Map<Account, List<Card>> getEmptyAccountListMap(){
        return accountListMap;
    }

    /**
     * Придет значение value
     * - если точек - то сразу по одному счету будет искать
     * - если одна точка - то по счетам с точкой
     * - если две точки - то сразу по
     *
     * @param originalAccount
     * @return
     */
    public static Optional<Account> getAccount(String originalAccount){
        String searchAccount = originalAccount;
        if (originalAccount.contains(".")){
            List<String> splitList = Arrays.asList(originalAccount.split("\\."));
            List<String> splitArrayList = new ArrayList<>(splitList);
            if (splitArrayList.size() == 3){
                for (Account account : Account.values()) {
                    if (account.getValue().equals(searchAccount)){
                        return Optional.ofNullable(account);
                    }
                }
                splitArrayList.remove(2);
                searchAccount = new StringBuilder().append(splitArrayList.get(0)).append(".").append(splitArrayList.get(1)).toString();
            }
            if (splitArrayList.size() == 2){
                for (Account account : Account.values()) {
                    if (account.getValue().equals(searchAccount)){
                        return Optional.ofNullable(account);
                    }
                }
                splitArrayList.remove(1);
                searchAccount = splitArrayList.get(0);
            }
            if (splitArrayList.size() == 1){
                for (Account account : Account.values()) {
                    if (account.getValue().equals(searchAccount)){
                        return Optional.ofNullable(account);
                    }
                }
            }
        } else {
            for (Account account : Account.values()) {
                if (account.getValue().equals(searchAccount)) {
                    return Optional.ofNullable(account);
                }
            }
        }
        return Optional.empty();
    }

    public static Optional<SearchType> verificationAccount(String account) {
        Pattern accountsPattern = Pattern.compile("\\d{2}");
        Pattern subAccountsOneDotsPattern = Pattern.compile("\\d{2}\\.\\d{1,2}");
        Pattern subAccountsTwoDotsPattern  = Pattern.compile("\\d{2}\\.\\d{1,2}\\.\\d{1,2}");
        Matcher accountsPatternMatcher = accountsPattern.matcher(account);
        Matcher subAccountsOneDotsMatcher = subAccountsOneDotsPattern.matcher(account);
        Matcher subAccountsTwoDotsMatcher = subAccountsTwoDotsPattern.matcher(account);
        if (account.isBlank()){
            return Optional.of(SearchType.ALL_ACCOUNTS);
        } else if (accountsPatternMatcher.matches() | subAccountsOneDotsMatcher.matches() | subAccountsTwoDotsMatcher.matches()) {
            return Optional.of(SearchType.ONE_ACCOUNTS);
        } else {
            return Optional.empty();
        }
    }

}
