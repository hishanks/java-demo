package com.demo.interview.thread.geektime;

/**
 * @author Shanks
 * @date 2019-05-26
 */
public class Main {

    public static void main(String[] args) {
        VolatileExample example = new VolatileExample();
        example.reader();
        example.writer();
        example.reader();
    }
}

class VolatileExample {
    int x = 0;
    volatile boolean v = false;

    public void writer() {
        x = 42;
        v = true;
        System.out.println("执行writer");
    }

    public void reader() {
        if (v == true) {
            // 这里 x 会是多少呢？
            System.out.println(x);
        } else {
            System.out.println("reader v:" + v);
        }
    }
}

