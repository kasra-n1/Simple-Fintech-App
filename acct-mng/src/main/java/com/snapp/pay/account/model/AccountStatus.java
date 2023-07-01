package com.snapp.pay.account.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AccountStatus {

    ACTIVE(0), INACTIVE(1);

    @Getter
    private final int value;

    @Converter(autoApply = true)
    public static class AccountStatusConverter implements AttributeConverter<AccountStatus, Integer> {

        @Override
        public Integer convertToDatabaseColumn(AccountStatus attribute) {
            return attribute == null ? null : attribute.getValue();
        }

        @Override
        public AccountStatus convertToEntityAttribute(Integer dbData) {
            return dbData == null ? null : getAccountStatusByValue(dbData);
        }

        private AccountStatus getAccountStatusByValue(int value) {
            return switch (value) {
                case 0 -> ACTIVE;
                case 1 -> INACTIVE;
                default -> throw new IllegalArgumentException("No account status found for value " + value);
            };
        }

    }

}
