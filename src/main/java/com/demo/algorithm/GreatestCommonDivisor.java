package com.demo.algorithm;

/**
 * 最大公约数算法
 * 1.更相减损术 -《九章算术》
 * 2.辗转相除法 - 欧几里得
 * <p>
 * gcd(a, b) = gcd(b, a)
 * gcd(a, b) = gcd(-a, b)
 * gcd(a, 0) = |a|
 * gcd(a, b) = gcd(b, a%b) // 0 <= a%b 递推通式
 *
 * @author Shanks
 * @date 2018-08-13
 */
<<<<<<< Updated upstream:src/main/java/com/demo/algorithm/GreatestCommonDivisor.java
class GreatestCommonDivisor {

    public static void main(String[] args) {
=======
public class GreatestCommonDivisorTest {

    @Test
    void testGcd() {
>>>>>>> Stashed changes:src/main/java/com/demo/algorithm/GreatestCommonDivisorTest.java
        int gcd = gcd(30, 45);
        System.out.println(gcd);

        // 三个整数的最大公约数嵌套调用即可
        int i = gcd((gcd(75, 50)), 100);
        System.out.println(i);
    }

    /**
     * 求a和b的最大公约数
     *
     * @param a num1
     * @param b num2
     * @return gcd
     */
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}