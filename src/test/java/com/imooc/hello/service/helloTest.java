package com.imooc.hello.service;

import com.imooc.hello.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class helloTest {

    @Resource
    HelloService helloService;

    @Test
    public void sevnHelloTest(){
        helloService.codeHello();
    }
}
