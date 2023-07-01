package com.snapp.pay.account.util;

import java.util.UUID;

public class CodeGeneratorUtil {

    public static String generateTrackingCode() {
        return String.valueOf(Math.abs(UUID.randomUUID().toString().hashCode()));
    }

}
