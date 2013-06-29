package ua.eshepelyuk.blog.springtest.cache;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.lang.Double.valueOf;
import static org.mockito.Mockito.times;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringockitoContextLoader.class, locations = "classpath:/springtest/cache/applicationContext.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserBalanceServiceImplTest {
    @Autowired
    private UserBalanceService userBalanceService;
    @Autowired
    @ReplaceWithMock
    private BankService bankService;

    @Before
    public void setUp() throws Exception {
        Mockito.when(bankService.getBalanceByEmail("user@bank.com")).thenReturn(String.valueOf(valueOf(123.45D)));
    }

    @Test
    public void shouldCacheBalance() {
        userBalanceService.getAccountBalance("user@bank.com");
        Mockito.verify(bankService).getBalanceByEmail("user@bank.com");

        userBalanceService.getAccountBalance("user@bank.com");
        Mockito.verifyNoMoreInteractions(bankService);
    }

    @Test
    public void shouldEvictCacheRecord() throws Exception {
        userBalanceService.getAccountBalance("user@bank.com");
        Mockito.verify(bankService).getBalanceByEmail("user@bank.com");

        userBalanceService.updateAccountBalance("user@bank.com", 10D);

        userBalanceService.getAccountBalance("user@bank.com");
        Mockito.verify(bankService, times(2)).getBalanceByEmail("user@bank.com");
    }
}