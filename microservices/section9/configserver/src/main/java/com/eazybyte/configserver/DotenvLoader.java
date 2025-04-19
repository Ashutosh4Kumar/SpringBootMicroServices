package com.eazybyte.configserver;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Map;

public class DotenvLoader {
    public static void loadEnv() {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMalformed()   // Optional: Handle malformed .env files gracefully
                .ignoreIfMissing()     // Optional: Avoid errors if .env is missing
                .load();

        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });
    }
}