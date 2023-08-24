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
     * @param value
     * @return
     */
    public static Account getAccount(String value){
        String searchAccount = value;
        if (value.contains(".")){
            List<String> splitList = Arrays.asList(value.split("\\."));
            List<String> splitArrayList = new ArrayList<>(splitList);
            if (splitArrayList.size() == 3){
                for (Account day : Account.values()) {
                    if (day.getValue().equals(searchAccount)){
                        return day;
                    }
                }
                splitArrayList.remove(2);
                searchAccount = new StringBuilder().append(splitArrayList.get(0)).append(".").append(splitArrayList.get(1)).toString();
            }
            if (splitArrayList.size() == 2){
                for (Account day : Account.values()) {
                    if (day.getValue().equals(searchAccount)){
                        return day;
                    }
                }
                splitArrayList.remove(1);
                searchAccount = splitArrayList.get(0);
            }
            if (splitArrayList.size() == 1){
                for (Account day : Account.values()) {
                    if (day.getValue().equals(searchAccount)){
                        return day;
                    }
                }
            }
        } else {
            for (Account day : Account.values()) {
                if (day.getValue().equals(searchAccount)) {
                    return day;
                }
            }
        }
        // TODO: 26.06.2023 null возвращать плохо -- вернуть ошибку new Thread();
        return null;
    }

}
