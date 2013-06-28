package ua.eshepelyuk.blog.springtest.springockito;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class UserBalanceServiceImpl implements UserBalanceService {
    @Autowired
    private BankService bankService;
    @Override
    public Double getAccountBalance(String email) {
        return Double.valueOf(bankService.getBalanceByEmail(email));
    }
}

