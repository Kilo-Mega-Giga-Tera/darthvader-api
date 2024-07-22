package kr.app.darthvader.global.config.jasEncryptConfig;

import org.assertj.core.api.Assertions;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JasEncryptConfigTest {

    @Test
    public void jasEncryptConfigTest() {
        String url = "jdbc";
        String username = "";
        String password = "";

        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword("");
        jasypt.setAlgorithm("PBEWITHMD5ANDDES");

        String encryptedUrl = jasypt.encrypt(url);
        String encryptedUsername = jasypt.encrypt(username);
        String encryptedPassword = jasypt.encrypt(password);

        Assertions.assertThat(url).isEqualTo(jasypt.decrypt(encryptedUrl));
        Assertions.assertThat(username).isEqualTo(jasypt.decrypt(encryptedUsername));
        Assertions.assertThat(password).isEqualTo(jasypt.decrypt(encryptedPassword));

        System.out.println("encryptedUrl = " + encryptedUrl);
        System.out.println("encryptedUsername = " + encryptedUsername);
        System.out.println("encryptedPassword = " + encryptedPassword);
    }

}