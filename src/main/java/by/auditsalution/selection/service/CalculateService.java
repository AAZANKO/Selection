package by.auditsalution.selection.service;

import by.auditsalution.selection.exception.ServiceException;
import by.auditsalution.selection.model.Account;
import by.auditsalution.selection.model.Card;
import by.auditsalution.selection.model.InitialData;

import java.util.List;
import java.util.Map;

public interface CalculateService {

    void calculate(InitialData initialValue, Map<Account, List<Card>> accountListMap) throws ServiceException;

}
