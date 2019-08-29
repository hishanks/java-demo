package com.demo.designpattern.defaultadapter;

import org.junit.jupiter.api.Test;

/**
 * @author zhoukai
 * @date 2018/6/9
 */
class DefaultAdapterTest {

    @Test
    void testDefaultAdapter() {

        DoorService doorService = new DoorServiceImpl();
        System.out.println(doorService.open("克里斯"));
        System.out.println(doorService.close("克里斯"));
        System.out.println(((DoorServiceImpl) doorService).alarm());

        System.out.println("=====");

        DoorServiceImpl doorServiceImpl = new DoorServiceImpl();
        System.out.println(doorServiceImpl.open("克里斯"));
        System.out.println(doorServiceImpl.close("克里斯"));
        System.out.println(doorServiceImpl.alarm());
    }
}
