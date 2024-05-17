package org.example;


import org.junit.jupiter.api.Test;


public class ThreadLocalTest {
    @Test
    public void testThreadLocalSetAndGet() {
        ThreadLocal tl = new ThreadLocal();
        new Thread(() -> {
            tl.set("zhangsan");
            System.out.println(tl.get());
        }).start();
    }
}
