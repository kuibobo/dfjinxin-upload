package io.dfjinxin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "io.dfjinxin"
})
public class DfjinxinUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(DfjinxinUploadApplication.class, args);
    }

}
