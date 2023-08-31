package by.auditsalution.selection.service;

import by.auditsalution.selection.exception.ServiceException;
import by.auditsalution.selection.model.Account;
import by.auditsalution.selection.model.Card;
import by.auditsalution.selection.model.Card1CTemp;

import java.util.List;
import java.util.Map;

public interface CardService {

    List<Card1CTemp> createCardTempFromFiles(String inputFilePath) throws ServiceException;

    Map<Account, List<Card>> convertToCards(List<Card1CTemp> card1CTempList, Map<String, Account> replacementAccountMap);

}
