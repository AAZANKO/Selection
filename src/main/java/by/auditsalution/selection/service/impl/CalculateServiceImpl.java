package by.auditsalution.selection.service.impl;

import by.auditsalution.selection.comparator.CardComparator;
import by.auditsalution.selection.exception.ServiceException;
import by.auditsalution.selection.model.*;
import by.auditsalution.selection.service.CalculateService;
import by.auditsalution.selection.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CalculateServiceImpl implements CalculateService {

    private final CardComparator card1CUnifiedComparator;
    private final ExcelService excelService;

    @Override
    public void calculate(InitialData initialValue, Map<Account, List<Card>> accountListMap) throws ServiceException {

//        OutputFormResult of = OutputFormResult.of();

        OutputResult outputFormResult = new OutputResult();
        SampleResult sampleResult = new SampleResult();

        // TODO: 18.05.2023 6. Нажать кнопку "3. Найти счета"
//        Map<Account, List<Card1C>> accountListMap = searchAccounts(initialValue, accountListMap);
//        if (accountListMap.size() == 0){
//            throw new ServiceException("Счета " + initialValue.getAccount() + " в карете НЕ найдены!!!");
//        }
//

        // отфильтровать счета найти проводки у которых сумма поля Кредит и Дебет отрицательные!!!
        Map<Account, List<Card>> minusAccountListMap = getMinusSum(accountListMap);
        outputFormResult.setMinusAccountMaxMap(minusAccountListMap); // сохранено для отдельного разбирательства
        cleanAccountListMap(accountListMap, minusAccountListMap); // удалить неправильные проводки

        // отфильтровать счета найти проводки у которых сумма поля Кредит и Дебет заполнено!!!
        Map<Account, List<Card>> doubleAccountListMap = getDoubleSum(accountListMap);
        outputFormResult.setDoubleAccountMaxMap(doubleAccountListMap); // сохранено для отдельного разбирательства
        cleanAccountListMap(accountListMap, doubleAccountListMap); // удалить неправильные проводки

        // найти счета "90.9", "91.9", "99"
        Map<Account, List<Card>> notValidAccountsListMap = getNotValidAccounts(accountListMap);
        // outputFormResult.setAccount90Map(notValidAccountsListMap); // не нужно сохранять !!!!
        cleanAccountListMap(accountListMap, notValidAccountsListMap); // удалить неправильные проводки

        /**
         * нужно это или нет ?!
         */
        // Нажать кнопку "4. Посчитать сумму"
        Map<Account, TotalSum> totalSumSearchAccountMap = getTotalSum(accountListMap);
        Map<Account, TotalSum> totalMinusSumMap = getTotalSum(minusAccountListMap);
        Map<Account, TotalSum> totalDoubleSumListMap = getTotalSum(doubleAccountListMap);
        // Map<Account, TotalSum> totalNotValidAccountsMap = getTotalSum(notValidAccountsListMap); // не нужно сохранять !!!!

        /**
         * нужно это или нет ?!
         */
        // Общее количество проводок
        int countTransactionSearchAccount = getCountTransaction(accountListMap);
        int countTransactionMinusSum = getCountTransaction(minusAccountListMap);
        int countTransactionDoubleSum = getCountTransaction(doubleAccountListMap);
        // int countTransactionNotValidAccounts = getCountTransaction(notValidAccountsListMap); // 11.06.2023 не нужно сохранять !!!!


        // TODO: 18.05.2023 8. Нажать кнопку "5. Выборка макс совокупности"
        // слияние в один столбец
        Map<Account, List<Card>> mergerAccountListMap = getMergerAccount(accountListMap);
        Map<Account, List<Card>> mergerMinusSumListMap = getMergerAccount(minusAccountListMap);
        Map<Account, List<Card>> mergerDoubleSumListMap = getMergerAccount(doubleAccountListMap);

        // сортировка
        sortedCard1CList(mergerAccountListMap);
        sortedCard1CList(mergerMinusSumListMap);
        sortedCard1CList(mergerDoubleSumListMap);

        int countMaxElement = initialValue.getCountMaxElement(); // кол-во элементов макс совокупности, которую нужно выбрать

        // список максимальной совокупности
        Map<Account, List<Card>> maxAccountListMap = getMaxElement(mergerAccountListMap, countMaxElement);
        Map<Account, List<Card>> maxMinusAccountListMap = getMaxElement(mergerMinusSumListMap, countMaxElement);
        Map<Account, List<Card>> maxDoubleAccountListMap = getMaxElement(mergerDoubleSumListMap, countMaxElement);

        // добавляем максимальные совокупности в результирующую форму
        outputFormResult.setAccountMaxMap(maxAccountListMap);
        outputFormResult.setMinusAccountMaxMap(maxMinusAccountListMap);
        outputFormResult.setDoubleAccountMaxMap(maxDoubleAccountListMap);

        // подсчитываем Итого для максимальной совокупности
        Map<Account, TotalSum> accountTotalMaxMap = getTotalSum(maxAccountListMap);
        Map<Account, TotalSum> minusTotalMaxMap = getTotalSum(maxMinusAccountListMap);
        Map<Account, TotalSum> doubleTotalMaxMap = getTotalSum(maxDoubleAccountListMap);
        // сохраняем Итого для максимальной совокупности
        outputFormResult.setAccountTotalMaxMap(accountTotalMaxMap);
        outputFormResult.setMinusTotalMaxMap(minusTotalMaxMap);
        outputFormResult.setDoubleTotalMaxMap(doubleTotalMaxMap);


        // TODO: 18.05.2023 9. Нажать кнопку "6. Выборка рандомной совокупности"
        int sampleSizeResult = initialValue.getCountRandomElement();

        // TODO: 25.05.2023 удалить МАХ выборку из рандомной....!!!!!!!!!!!!
        cleanAccountListMap(accountListMap, maxAccountListMap);
        cleanAccountListMap(minusAccountListMap, maxMinusAccountListMap);
        cleanAccountListMap(doubleAccountListMap, maxDoubleAccountListMap);

        Map<Account, List<Card>> randomAccountListMap = getRandomCardList(accountListMap, sampleSizeResult);
        Map<Account, List<Card>> randomMinusAccountListMap = getRandomCardList(minusAccountListMap, sampleSizeResult);
        Map<Account, List<Card>> randomDoubleAccountListMap = getRandomCardList(doubleAccountListMap, sampleSizeResult);

        outputFormResult.setAccountRandomMap(randomAccountListMap);
        outputFormResult.setMinusAccountRandomMap(randomMinusAccountListMap);
        outputFormResult.setDoubleAccountRandomMap(randomDoubleAccountListMap);

        // подсчитываем Итого для максимальной совокупности
        Map<Account, TotalSum> accountTotalRandomMap = getTotalSum(randomAccountListMap);
        Map<Account, TotalSum> minusTotalRandomMap = getTotalSum(randomMinusAccountListMap);
        Map<Account, TotalSum> doubleTotalRandomMap = getTotalSum(randomDoubleAccountListMap);

        outputFormResult.setAccountTotalRandomMap(accountTotalRandomMap);
        outputFormResult.setMinusTotalRandomMap(minusTotalRandomMap);
        outputFormResult.setDoubleTotalRandomMap(doubleTotalRandomMap);

        try {



            excelService.writeToExcel(outputFormResult, sampleResult, initialValue);
        } catch (Exception e){
            throw new ServiceException("Не удалось записать файл");
        }

    }

    private Map<Account, List<Card>> getMaxElement(Map<Account, List<Card>> map, int countMaxElement) {
        Map<Account, List<Card>> result = new HashMap<>(Account.values().length);
        for (Account account : map.keySet()) {
            List<Card> maxSampleList = map.get(account).stream()
                    .limit(countMaxElement)
                    .collect(Collectors.toList());
            result.put(account, maxSampleList);
        }
        return result;
    }

    private int getCountTransaction(Map<Account, List<Card>> map) {
        int result = 0;
        for (Account account : map.keySet()) {
            List<Card> card1CList = map.get(account);
            if (card1CList != null && !card1CList.isEmpty()){
                result = result + card1CList.size();
            }
        }
        return result;
    }

    private Map<Account, TotalSum> getTotalSum(Map<Account, List<Card>> map) {
        Map<Account, TotalSum> result = new HashMap<>(Account.values().length);
        for (Account account : map.keySet()) {
            List<Card> card1CList = map.get(account);
            if (card1CList != null && !card1CList.isEmpty()){
                double debit = map.get(account).stream().mapToDouble(Card::getDebitSum).sum();
                double credit = map.get(account).stream().mapToDouble(Card::getCreditSum).sum();
                TotalSum totalSum = TotalSum.builder()
                        .credit(credit)
                        .debit(debit)
                        .build();
                result.put(account, totalSum);
            }
        }
        return result;
    }

    private void cleanAccountListMap(Map<Account, List<Card>> firstMap, Map<Account, List<Card>> lastMap) {
        for (Account account : firstMap.keySet()) {
            if (lastMap.get(account) != null){
                firstMap.get(account).removeAll(lastMap.get(account));
            }
        }
    }

    private Map<Account, List<Card>> getNotValidAccounts(Map<Account, List<Card>> searchAccountMap) {
        Map<Account, List<Card>> account90Map = new HashMap<>(Account.values().length);
        for (Account account : searchAccountMap.keySet()) {
            List<Card> card1CList = searchAccountMap.get(account);
            if (card1CList != null && !card1CList.isEmpty()) {
                List<Card> filterList = card1CList.stream()
                        .filter(x -> checkAccount(x.getCreditAccount()) | checkAccount(x.getDebitAccount()))
                        .collect(Collectors.toList());
                account90Map.put(account, filterList);
            }
        }
        return account90Map;
    }

    private boolean checkAccount(String value){
        List<String> noValidAccountsList = Arrays.asList("90.9", "91.9", "99");
        for (String account : noValidAccountsList) {
            if (account.contains(value)){
                return true;
            }
        }
        return false;
    }

    private Map<Account, List<Card>> getDoubleSum(Map<Account, List<Card>> searchAccountMap) {
        Map<Account, List<Card>> doubleSumMap = new HashMap<>(Account.values().length);
        for (Account account : searchAccountMap.keySet()) {
            List<Card> card1CList = searchAccountMap.get(account);
            if (card1CList != null && !card1CList.isEmpty()) {
                List<Card> filterList = card1CList.stream()
                        .filter(it -> it.getCreditSum() > 0.0d && it.getDebitSum() > 0.0d)
                        .collect(Collectors.toList());
                doubleSumMap.put(account, filterList);
            }
        }
        return doubleSumMap;
    }

    private Map<Account, List<Card>> getMinusSum(Map<Account, List<Card>> searchAccountsList) {
        Map<Account, List<Card>> minusSumMap = new HashMap<>(Account.values().length);
        for (Account account : searchAccountsList.keySet()) {
            List<Card> card1CList = searchAccountsList.get(account);
            if (card1CList != null && !card1CList.isEmpty()){
                List<Card> filterList = card1CList.stream()
                        .filter(it -> it.getCreditSum() < 0.0d && it.getDebitSum() < 0.0d)
                        .collect(Collectors.toList());
                minusSumMap.put(account, filterList);
            }
        }
        return minusSumMap;
    }

    private Map<Account, List<Card>> getRandomCardList(Map<Account, List<Card>> map, int sampleSizeResult) {
        Map<Account, List<Card>> allMap = new HashMap<>(Account.values().length);
        Random random = new Random();
        for (Account account : map.keySet()) {
            List<Card> card1CList = map.get(account);
            if (card1CList != null && !card1CList.isEmpty()) {
                if (card1CList.size() <= sampleSizeResult) {
                    allMap.put(account, card1CList);
                } else {
                    int currentIndex = sampleSizeResult;
                    List<Card> currentCList = new ArrayList<>(sampleSizeResult);
                    Set<Integer> counter = new HashSet<>();
                    while (currentIndex > 0) {
                        int index = random.nextInt(card1CList.size());
                        if (counter.add(index)) {
                            currentCList.add(card1CList.get(index));
                            currentIndex--;
                        }
                    }
                    allMap.put(account, currentCList);
                }
            }
        }
        return allMap;
    }

    private void sortedCard1CList(Map<Account, List<Card>> accountListMap) {
        for (Account account : accountListMap.keySet()) {
            List<Card> card1CList = accountListMap.get(account);
            if (card1CList != null && !card1CList.isEmpty()) {
                card1CList.sort(card1CUnifiedComparator.reversed());
            }
        }
    }

    private Map<Account, List<Card>> getMergerAccount(Map<Account, List<Card>> accountListMap) {
        Map<Account, List<Card>> allMap = new HashMap<>(Account.values().length);
        for (Account account : accountListMap.keySet()) {
            List<Card> card1CList = accountListMap.get(account);
            if (card1CList != null && !card1CList.isEmpty()) {
                for (Card card1C : card1CList) {
                    if (card1C.getDebitSum() > card1C.getCreditSum()) {
                        card1C.setCommonColumn(card1C.getDebitSum());
                    } else {
                        card1C.setCommonColumn(card1C.getCreditSum());
                    }
                }
                allMap.put(account, card1CList);
            }
        }
        return allMap;
    }

    @Deprecated
    private Map<Account, List<Card>> searchAccounts(InitialData initialValue, List<Card> cardList) {
        Map<Account, List<Card>> allMap = new HashMap<>(Account.values().length);
        SearchType accountType = initialValue.getAccountType();
        if (SearchType.ALL_ACCOUNTS.equals(accountType)){
           for (Account account : Account.values()) {
                List<Card> card1CList = cardList.stream()
                        .filter(it -> it.getDebitAccount().contains(account.getValue()) | it.getCreditAccount().contains(account.getValue()))
                        .collect(Collectors.toList());
                allMap.put(account, card1CList);
            }
        } else if (SearchType.ONE_ACCOUNTS.equals(accountType)) {
            String account = initialValue.getAccount();
            List<Card> card1CList = cardList.stream()
                    .filter(it -> account.contains(it.getDebitAccount()) || account.contains(it.getCreditAccount()))
                    .collect(Collectors.toList());
            Optional<Account> optionalAccount = Arrays.stream(Account.values()).filter(it -> it.getValue().contains(account)).findFirst();
            optionalAccount.ifPresent(value -> allMap.put(value, card1CList));
        } else {
            throw new ServiceException("Не удалось определить счет...");
        }
        return allMap;
    }


}