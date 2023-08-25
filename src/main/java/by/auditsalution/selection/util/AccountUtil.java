package by.auditsalution.selection.util;

import by.auditsalution.selection.model.Account;
import by.auditsalution.selection.model.Card;
import lombok.experimental.UtilityClass;

import java.util.*;

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
                        return Optional.of(account);
                    }
                }
                splitArrayList.remove(2);
                searchAccount = new StringBuilder().append(splitArrayList.get(0)).append(".").append(splitArrayList.get(1)).toString();
            }
            if (splitArrayList.size() == 2){
                for (Account account : Account.values()) {
                    if (account.getValue().equals(searchAccount)){
                        return Optional.of(account);
                    }
                }
                splitArrayList.remove(1);
                searchAccount = splitArrayList.get(0);
            }
            if (splitArrayList.size() == 1){
                for (Account account : Account.values()) {
                    if (account.getValue().equals(searchAccount)){
                        return Optional.of(account);
                    }
                }
            }
        } else {
            for (Account account : Account.values()) {
                if (account.getValue().equals(searchAccount)) {
                    return Optional.of(account);
                }
            }
        }
        return Optional.empty();
    }

}
