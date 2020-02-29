package com.cc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.Random;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class SellApplicationTests {


    @Test
    public void test(){
        Random random = new Random();
        Integer number = random.nextInt(9000000) + 10000;
        System.out.println(number);
    }
}
