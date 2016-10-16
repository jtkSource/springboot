package jtk.springboot.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by jubin on 16/10/16.
 */
@SpringBootApplication
@EnableEurekaClient
public class TestClient {

    public static void main(String[] args) {

        SpringApplication notificationMicroService = new SpringApplication(TestClient.class);
        notificationMicroService.addListeners(new ApplicationPidFileWriter("/var/tmp/eureka-client.pid"));
        notificationMicroService.run(args);

    }
}
