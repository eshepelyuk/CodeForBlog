package ua.eshepelyuk.blog.springtest.ex2;

interface UserOrderCountService {
    Double getAccountBalance(Long userId);

    Integer getOrderCount(Long userId);

    void updateOrderCount(Long userId, Integer orderCnt);
}
