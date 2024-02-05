package lkenneth.expensetracker.domain.expenseTracker.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp initializeFirebase() {
        try {
            FileInputStream serviceAccount = new FileInputStream("key.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))

                    .build();

            return FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            // Handle the exception (e.g., log an error)
            throw new RuntimeException("Failed to initialize Firebase", e);
        }
    }
}

