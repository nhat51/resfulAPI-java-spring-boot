package com.example.restfulapi.status;

public enum Status {
    ACTIVE,DELETE;

    public enum TransactionStatus {
        SUCCESS, FAIL
    }

    public enum PaymentStatus {
        PENDING, NOT_ENOUGH_BALANCE, DONE, REFUND, REFUNDED
    }

    public enum OrderStatus {
        PENDING,
        CONFIRM,
        REJECT,
        REFUND,
        DONE,
        DELETED
    }
}
