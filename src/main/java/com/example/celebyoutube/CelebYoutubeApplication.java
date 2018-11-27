package com.example.celebyoutube;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CelebYoutubeApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CelebYoutubeApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run();
    }

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "/home/ec2-user/youtube/backend/config/real-application.yml";
}
