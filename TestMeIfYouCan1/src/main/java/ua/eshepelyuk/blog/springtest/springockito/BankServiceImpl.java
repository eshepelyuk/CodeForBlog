package ua.eshepelyuk.blog.springtest.springockito;

public class BankServiceImpl implements BankService {
    @Override
    public String getBalanceByEmail(String email) {
        throw new UnsupportedOperationException("Operation failed due to external exception");
    }
}
