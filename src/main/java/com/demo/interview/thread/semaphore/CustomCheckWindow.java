package com.demo.interview.thread.semaphore;

import java.util.concurrent.Semaphore;

/**
 * 以现实场景的窗口安检为例，模拟Semaphore的工作流程，默认条件是：旅客多，窗口少
 *
 * @author Shanks
 * @date 2019-05-22
 */
public class CustomCheckWindow {

    public static void main(String[] args) {
        // 设定3个信号量，即3个服务窗口
        Semaphore semaphore = new Semaphore(3);
        // 设定排队接受安检的旅客数量为5人
        int passengersNumber = 5;

        for (int i = 1; i <= passengersNumber; i++) {
            new SecurityCheckThread(i, semaphore).start();
        }
    }

    private static class SecurityCheckThread extends Thread {

        private int seq;
        private Semaphore semaphore;

        public SecurityCheckThread(int seq, Semaphore semaphore) {
            this.seq = seq;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                // 只有在调用Semaphore对象的acquire()成功后，才可以往下执行
                semaphore.acquire();
                System.out.println("No." + this.seq + " 乘客正在查验中...");

                // 假设号码是整除2的人是身份可疑人员，sleep(1000)模拟需要花更长时间来安检
                if (seq % 2 == 0) {
                    Thread.sleep(1000);
                    System.out.println("No." + this.seq + " 乘客，身份可疑，不能出国！🚫");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 完成后执行release()释放持有的信号量，下一个线程就可以马上获取这个空闲信号量进入执行
                semaphore.release();
                System.out.println("No." + this.seq + " 乘客已完成安检");
            }
        }
    }
}