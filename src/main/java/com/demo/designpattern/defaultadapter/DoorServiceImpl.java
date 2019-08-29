package com.demo.designpattern.defaultadapter;

/**
 * 声明类型的工作仍然由Java接口承担，可是同一时候给出一个Java抽象类，且实现了这个接口
 * 而其它同属于这个抽象类型的详细类能够选择实现这个Java接口，也能够选择继承这个抽象类
 * 也就是说在层次结构中，Java接口在最上面，然后紧跟着抽象类。哈，这下两个的最大长处都能发挥到极至了
 * 这个模式就是“缺省适配模式”
 * 在Java语言API中用了这样的模式，并且全都遵循一定的命名规范：Abstract ＋接口名
 *
 * @author zhoukai
 * @date 2018/6/9
 */
public class DoorServiceImpl extends AbstractDoorService implements DoorService {

    @Override
    public String open(String name) {
        return "DoorServiceImpl: " + super.open(name);
    }

    @Override
    public String close(String name) {
        return "DoorServiceImpl: " + super.close(name);

    }

    @Override
    String alarm() {
        return "DoorServiceImpl: " + super.alarm();
    }
}
