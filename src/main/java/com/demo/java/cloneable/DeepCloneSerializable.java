package com.demo.java.cloneable;

import java.io.*;

/**
 * @author Shanks
 * @date 2019-03-28
 */
public class DeepCloneSerializable implements Serializable {

    private static final long serialVersionUID = 4752878936698502312L;

    protected Object deepCloneSerializable() throws IOException, ClassNotFoundException {
        // 序列化
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(this);

        // 反序列化
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return objectInputStream.readObject();
    }
}
