package ua.eshepelyuk.blog.springtest.cache;

public interface BankService {
    String getBalanceByEmail(String email);

    void updateBalanceByEmail(String email, String amount);
}
