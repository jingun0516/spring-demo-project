package com.estsoft.springdemoproject.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloServiceTest {

    @Test
    void testPrintHello() {
        // given
        HelloService helloService = new HelloService();
        String param = "World";  // 테스트할 파라미터

        // when
        String result = helloService.printHello(param);  // 메서드 호출

        // then
        assertEquals("Hello World", result);  // 결과가 예상 값과 일치하는지 확인
    }

}