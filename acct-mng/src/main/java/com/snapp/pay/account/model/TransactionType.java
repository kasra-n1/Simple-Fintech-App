package com.snapp.pay.account.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionType {

    DEPOSIT(0), WITHDRAW(1), TRANSFER(2);

    private final int value;

    @Converter(autoApply = true)
    public static class TransactionTypeConverter implements AttributeConverter<TransactionType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(TransactionType attribute) {
            return attribute == null ? null : attribute.getValue();
        }

        @Override
        public TransactionType convertToEntityAttribute(Integer dbData) {
            return dbData == null ? null : getTransactionTypeByValue(dbData);
        }

        private TransactionType getTransactionTypeByValue(int value) {
            return switch (value) {
                case 0 -> DEPOSIT;
                case 1 -> WITHDRAW;
                case 2 -> TRANSFER;
                default -> throw new IllegalArgumentException("No transaction type found for value " + value);
            };
        }

    }

}
