package com.demo.lombok.builder;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * 了解下lombok.Builder
 *
 * @author Shanks
 * @date 2019-07-31
 */
public class BuilderTest {

    @Test
    void builderDefault() {
        BankAccount a = BankAccount.builder().name("a").age(20).build();
        System.out.println(a);

        BankAccount b = BankAccount.builder().name("b").build();
        System.out.println(b);

        BankAccount c = BankAccount.builder()
                .name("c")
                .age(30)
                .cards(null)
                .build();
        System.out.println(c);

        BankAccount d = BankAccount.builder()
                .name("d")
                .age(40)
                .cardTypes(null)
                .build();
        System.out.println(d);

        BankAccount e = BankAccount.builder()
                .name("e")
                .age(50)
                .cards(new ArrayList<>(Arrays.asList("100", "200")))
                .cardTypes(new ArrayList<>(Arrays.asList(11, 22)))
                .build();
        System.out.println(e);
    }

    @Test
    void test() {
        User user = User.userBuilder()
                .name("Shanks")
                .age(20)
                .birthday(new Date())
                .create();
        System.out.println(user);
    }
}