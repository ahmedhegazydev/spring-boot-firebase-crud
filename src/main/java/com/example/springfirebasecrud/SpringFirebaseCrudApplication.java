package com.example.springfirebasecrud;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class SpringFirebaseCrudApplication {

    public static void main(String[] args) throws IOException {

        ClassLoader classLoader = SpringFirebaseCrudApplication.class
                .getClassLoader();


        FileInputStream serviceAccount =
                null;
        try {
            File file =
                    new File(Objects.requireNonNull(
                            Objects.requireNonNull(classLoader.getResource(
                                    "serviceAccount.json"
                            )).getFile()
                    ));
            serviceAccount = new FileInputStream(file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert serviceAccount != null;
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://spring-boot-firebase-crud-default-rtdb.firebaseio.com")
                .build();
        FirebaseApp.initializeApp(options);


        SpringApplication.run(SpringFirebaseCrudApplication.class, args);
    }

}
