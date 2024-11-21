package com.estsoft.springdemoproject.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.salt.RandomSaltGenerator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


class JasyptConfigAESTest {
    @Test
    @Disabled
    void stringEncryptor() {
        String password = "암호화하려는민감정보(패스워드/url/id등)";

        System.out.println(jasyptEncoding(password));
    }

    public String jasyptEncoding(String value) {
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setPassword("시크릿키(대칭키)");
        pbeEnc.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        pbeEnc.setIvGenerator(new RandomIvGenerator());
        pbeEnc.setSaltGenerator(new RandomSaltGenerator());
        return pbeEnc.encrypt(value);
    }

}