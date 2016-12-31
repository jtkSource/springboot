package jtk.springboot.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by jubin on 16/10/16.
 */
@SpringBootApplication()
@EnableEurekaServer
public class EurekaServer {

    public static void main(String[] args) {
        SpringApplication eurekaServer = new SpringApplication(EurekaServer.class);
        eurekaServer.addListeners(new ApplicationPidFileWriter("/var/tmp/eureka-server.pid"));
        eurekaServer.run(args);
    }
}
