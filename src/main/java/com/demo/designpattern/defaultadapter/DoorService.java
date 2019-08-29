package com.demo.designpattern.defaultadapter;

/**
 * 在缺省设计模式层次结构中，Java接口在最上面，然后紧跟着抽象类
 *
 * @author zhoukai
 * @date 2018/6/9
 */
public interface DoorService {

    String open(String name);

    String close(String name);
}
