package com.demo.jdk8.completablefuture;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author Shanks
 * @date 2019-03-29
 */
public class ShopTest {

    @Test
    void test() {

        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        // 执行更多任务，比如查询其他商店
        doSomethingElse();

        // 在计算商品价格的同时
        try {
            double price = futurePrice.get();
            System.out.printf("Price is $%.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    @Test
    void test1() {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        CompletableFuture<Double> futurePrice = shop.getPriceAsyncComplete("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        // 执行更多任务，比如查询其他商店
        doSomethingElse();

        // 在计算商品价格的同时
        try {
            double price = futurePrice.join();
            System.out.printf("Price is $%.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    public void doSomethingElse() {

        StringBuilder builder = new StringBuilder();
        for (byte i = 0; i < Byte.MAX_VALUE; i++) {
            builder.append(i);
        }
        System.out.println(builder.toString());
    }
}
