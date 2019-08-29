package com.demo.designpattern.adapter;

/**
 * @author Shanks
 * @date 2019-05-15
 */
public class SDAdapterTF implements SDCard {

    private TFCard tfCard;

    SDAdapterTF(TFCard tfCard) {
        this.tfCard = tfCard;
    }

    @Override
    public String readSD() {
        System.out.println("Adapter read TF card ");
        return tfCard.readTF();
    }

    @Override
    public int writeSD(String msg) {
        System.out.println("Adapter write TF card");
        return tfCard.writeTF(msg);
    }
}
