package ua.eshepelyuk.blog.springtest.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public class UserBalanceServiceImpl implements UserBalanceService {
    @Autowired
    private BankService bankService;

    @Override
    @Cacheable(value = "userBalanceCache", key = "#email")
    public Double getAccountBalance(String email) {
        return Double.valueOf(bankService.getBalanceByEmail(email));
    }

    @Override
    @CacheEvict(value = "userBalanceCache", key = "#email", beforeInvocation = true)
    public void updateAccountBalance(String email, Double amount) {
        bankService.updateBalanceByEmail(email, String.valueOf(amount));
    }
}

