package mx.com.qtx.dipArq07m05appWebSB.webSeguridad.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;

//@Component
public class PasswordUtileria implements CommandLineRunner{
    public static String encodePassword(String password) {
 //       return new BCryptPasswordEncoder().encode(password);
        return PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password);
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
 //       return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
        return PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(rawPassword, encodedPassword);
    }   

    public static void main(String[] args) {
        String password = "tekamachalko";
        String encodedPassword = encodePassword(password);
        System.out.println("Password: " + password);
        System.out.println("Encoded Password: " + encodedPassword);
        System.out.println("Matches: " + matches(password, encodedPassword));
    }   

    @Override
    public void run(String... args) throws Exception {
        List<String> lstPasswords = Arrays.asList("tekamachalko","tekolutla","tlatelolko"); 
        for(String password : lstPasswords) {
            String encodedPassword = encodePassword(password);
            System.out.println("Password: " + password);
            System.out.println("Encoded Password: " + encodedPassword);
            System.out.println("Matches: " + matches(password, encodedPassword));
        }
    }
}
