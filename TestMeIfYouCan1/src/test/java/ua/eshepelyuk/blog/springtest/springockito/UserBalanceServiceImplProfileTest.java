package ua.eshepelyuk.blog.springtest.springockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/springtest/springockito/testApplicationContext.xml")
@ActiveProfiles(profiles = {"springTest"})
public class UserBalanceServiceImplProfileTest {
    @Autowired
    private UserBalanceService userBalanceService;
    @Autowired
    private BankService bankService;
    @Before
    public void setUp() throws Exception {
        Mockito.when(bankService.getBalanceByEmail("user@bank.com")).thenReturn(String.valueOf(123.45D));
    }
    @Test
    public void shouldReturnMockedBalance() {
        Double balance = userBalanceService.getAccountBalance("user@bank.com");
        assertEquals(balance, Double.valueOf(123.45D));
    }
}
