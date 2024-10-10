package subking.config;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
    // 비밀번호 해시화
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    // 비밀번호 확인
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
