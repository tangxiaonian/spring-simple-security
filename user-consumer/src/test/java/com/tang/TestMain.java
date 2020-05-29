package com.tang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TestMain {

    @Test
    public void test01() {

        log.info("{}", "hello");

        System.out.println(String.format("%s", "hello"));

    }

}
