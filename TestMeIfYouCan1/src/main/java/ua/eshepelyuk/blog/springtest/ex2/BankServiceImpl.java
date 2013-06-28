package ua.eshepelyuk.blog.springtest.ex2;

public class BankServiceImpl implements BankService {
    @Override
    public Double getBalanceByEmail(String email) {
        throw new IllegalArgumentException(email);
    }
}
