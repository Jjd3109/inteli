package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {

    public static void main(String[] args) {

        hello hel = new hello();
        hel.setData("hello");
        String data = hel.getData();
        System.out.println("data 값 : " + data);
        System.out.println("test값 ~!~!~1");


        SpringApplication.run(JpashopApplication.class, args);
    }

}
