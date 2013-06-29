package ua.eshepelyuk.blog.springtest.cache;

public class BankServiceImpl implements BankService {
    @Override
    public String getBalanceByEmail(String email) {
        throw new UnsupportedOperationException("Operation failed due to external exception");
    }

    @Override
    public void updateBalanceByEmail(String email, String amount) {
        throw new UnsupportedOperationException("Operation failed due to external exception");
    }
}
