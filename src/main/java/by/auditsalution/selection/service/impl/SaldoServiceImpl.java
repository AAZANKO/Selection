package by.auditsalution.selection.service.impl;

import by.auditsalution.selection.exception.ServiceException;
import by.auditsalution.selection.model.Account;
import by.auditsalution.selection.model.Card1CTemp;
import by.auditsalution.selection.model.Saldo;
import by.auditsalution.selection.service.SaldoService;

import java.util.List;
import java.util.Map;

public class SaldoServiceImpl implements SaldoService {


    @Override
    public List<Card1CTemp> convertFileToObject(String inputFilePath) throws ServiceException {
        return null;
    }

    @Override
    public Map<Account, List<Saldo>> convertObjectToSaldo(List<Card1CTemp> card1CTempList) {
        return null;
    }
}
