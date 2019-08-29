package com.demo.datastructure.collections.intersection;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 两个对象集合的交集（具体交集条件看具体需求）
 *
 * @author Shanks
 * @date 2019-04-28
 */
public class ObjectListIntersection {

    public static void main(String[] args) {

        TypeAlpha typeAlpha1 = new TypeAlpha(1L, 2, 1.1);
        TypeAlpha typeAlpha2 = new TypeAlpha(2L, 4, 2.2);
        TypeAlpha typeAlpha3 = new TypeAlpha(3L, 6, 3.3);
        TypeAlpha typeAlpha4 = new TypeAlpha(4L, 8, 4.4);
        List<TypeAlpha> typeAlphas = Lists.newArrayList(typeAlpha1, typeAlpha2, typeAlpha3, typeAlpha4);
        System.out.println("..................................................");
        typeAlphas.forEach(System.out::println);

        System.out.println("..................................................");
        TypeBeta typeBeta1 = new TypeBeta(1L, 10, 11);
        TypeBeta typeBeta2 = new TypeBeta(2L, 20, 21);
        TypeBeta typeBeta3 = new TypeBeta(4L, 40, 41);
        TypeBeta typeBeta4 = new TypeBeta(5L, 50, 51);
        List<TypeBeta> typeBetas = Lists.newArrayList(typeBeta1, typeBeta2, typeBeta3, typeBeta4);
        typeBetas.forEach(System.out::println);

        System.out.println("--------------------------------------------------");
        List<TypeAlpha> collect = typeAlphas.stream()
                .filter(item -> typeBetas.stream()
                        .map(TypeBeta::getId)
                        .collect(Collectors.toList())
                        .contains(item.getId()))
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
