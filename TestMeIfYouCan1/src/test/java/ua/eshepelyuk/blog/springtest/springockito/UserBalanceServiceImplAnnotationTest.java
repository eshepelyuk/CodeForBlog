package ua.eshepelyuk.blog.springtest.springockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.lang.Double.valueOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringockitoContextLoader.class, locations = "classpath:/springtest/springockito/applicationContext.xml")
public class UserBalanceServiceImplAnnotationTest {
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
    public void shouldReturnMockedBalance() {
        Double balance = userBalanceService.getAccountBalance("user@bank.com");
        assertEquals(balance, valueOf(123.45D));
    }
}
