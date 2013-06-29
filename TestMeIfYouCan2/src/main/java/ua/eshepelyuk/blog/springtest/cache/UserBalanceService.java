package ua.eshepelyuk.blog.springtest.cache;

interface UserBalanceService {
    Double getAccountBalance(String email);

    void updateAccountBalance(String email, Double amount);
}
