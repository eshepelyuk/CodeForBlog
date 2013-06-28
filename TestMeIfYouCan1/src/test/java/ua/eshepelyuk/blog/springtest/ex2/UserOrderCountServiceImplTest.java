package ua.eshepelyuk.blog.springtest.ex2;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/springtest/ex2/applicationContext.xml")
public class UserOrderCountServiceImplTest {

    @Autowired
    private BankService bankService;
    @Autowired
    private UserOrderCountService userOrderCountService;

//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @Autowired
//    private CacheManager cacheManager;

//    private Map<Long, Integer> cache;

    @Before
    public void setUp() throws Exception {
//        cache = (Map<Long, Integer>) cacheManager.getCache(USER_ORDER_CACHE).getNativeCache();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException() throws Exception {
        userOrderCountService.getAccountBalance(null);
    }

    //    @Test
//    public void shouldTakeValueFromCache() throws Exception {
//        assertEquals(cache.size(), 0);
//    }
}
