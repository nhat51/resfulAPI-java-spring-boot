package com.example.restfulapi.status;

public enum Status {
    ACTIVE,DELETE;

    public enum TransactionStatus {
        SUCCESS, FAIL
    }

    public enum PaymentStatus {
        PAID, UNPAID, REFUND, REFUNDED
    }

    public enum OrderStatus {
        PENDING,
        CONFIRM,
        REJECT,
        REFUND,
        DONE,
        DELETED
    }
    public static class OrderMessage{
        public static String NOT_ENOUGH_BALANCE = "Balance is not enough";
        public static String NOT_FOUND_USER = "Can not found user";
        public static String NOT_FOUND_WALLET = "Wallet not found";
    }
}
