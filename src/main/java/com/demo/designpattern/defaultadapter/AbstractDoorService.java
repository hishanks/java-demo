package com.demo.designpattern.defaultadapter;

/**
 * 在Java语言API中用了这样的模式，并且全都遵循一定的命名规范：Abstract ＋接口名
 *
 * @author zhoukai
 * @date 2018/6/9
 */
public abstract class AbstractDoorService implements DoorService {

    @Override
    public String open(String name) {
        return String.format("%s，开下门", name);
    }

    @Override
    public String close(String name) {
        return String.format("%s，关下门", name);
    }

    /**
     * 门除了“关”、“闭”基础功能，还可以新增有“警报”功能
     *
     * @return
     */
    String alarm() {
        return "Alarm from AbstractDoorService..";
    }
}
