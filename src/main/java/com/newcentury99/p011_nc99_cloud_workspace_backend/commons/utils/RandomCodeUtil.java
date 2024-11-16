package com.newcentury99.p011_nc99_cloud_workspace_backend.commons.utils;

import java.security.SecureRandom;
import java.util.Date;

public class RandomCodeUtil {
    public static String generate(char[] charSet, int length) {
        // 임시 코드 생성기
        StringBuilder tempCodeBuffer = new StringBuilder();
        SecureRandom secureRandomNumberGenerator = new SecureRandom();
        secureRandomNumberGenerator.setSeed(new Date().getTime());

        int randomCharIndex;
        for (int i = 0; i < length; i++) {
            randomCharIndex = secureRandomNumberGenerator.nextInt(charSet.length);
            tempCodeBuffer.append(charSet[randomCharIndex]);
        }
        return tempCodeBuffer.toString();
    }
}
