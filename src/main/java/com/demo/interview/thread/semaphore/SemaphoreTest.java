package com.demo.interview.thread.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Semaphore 美[ˈseməfɔːr] 翻译成字面意思为信号量
 * Semaphore可以控同时访问的线程个数，通过acquire()获取一个许可，如果没有就等待，而release()释放一个许可。
 *
 * @author Shanks
 * @date 2019-05-16
 */
public class SemaphoreTest {

    public static void main(String[] args) {

        // 工人数
        int workersNumber = 8;
        // 机器数目（参数permits表示许可数目，即同时可以允许多少线程进行访问）
        int permits = 5;
        Semaphore semaphore = new Semaphore(permits);
        for (int i = 1; i <= workersNumber; i++) {
            new WorkThread(i, semaphore).start();
        }
    }

    private static class WorkThread extends Thread {

        private int index;
        private Semaphore semaphore;

        public WorkThread(int index, Semaphore semaphore) {
            this.index = index;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                // 获取
                semaphore.acquire();
                System.out.println(this.index + "号工人占用一个机器，正在生产...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放
                semaphore.release();
                System.out.println(this.index + "号工人完成工作，释放出机器！");
            }
        }
    }
}
