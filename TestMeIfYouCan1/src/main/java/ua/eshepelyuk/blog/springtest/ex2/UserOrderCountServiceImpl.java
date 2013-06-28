package ua.eshepelyuk.blog.springtest.ex2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

public class UserOrderCountServiceImpl implements UserOrderCountService {

    public static final String USER_ORDER_CACHE = "userOrderCount";
    private Map<Long, Integer> counterRepo;

    @Autowired
    private BankService bankService;

    public UserOrderCountServiceImpl(Map<Long, Integer> counterRepo) {
        this.counterRepo = counterRepo;
    }

    @Override
    public Double getAccountBalance(Long userId) {
        return bankService.getBalanceByEmail("user@user.user");
    }

    @Override
    @Cacheable("userOrderCount")
    public Integer getOrderCount(Long userId) {
        return null;
    }

    @Override
    @CacheEvict(USER_ORDER_CACHE)
    public void updateOrderCount(Long userId, Integer orderCnt) {
    }
}
