package by.auditsalution.selection.service;

import by.auditsalution.selection.exception.ServiceException;
import by.auditsalution.selection.model.Account;
import by.auditsalution.selection.model.Card1CTemp;
import by.auditsalution.selection.model.Saldo;

import java.util.List;
import java.util.Map;

public interface SaldoService {

    List<Card1CTemp> convertFileToObject(String inputFilePath) throws ServiceException;

    Map<Account, List<Saldo>> convertObjectToSaldo(List<Card1CTemp> card1CTempList);

}
