package com.demo.java.cloneable;

import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * String类型：拷贝的也是引用，但是其内容存储在字符串池，修改时会重新生成新的字符串，原有字符串内容不变（final修饰），等同于基本类型
 *
 * @author Shanks
 * @date 2019-03-28
 */
public class CloneableTest {

    /**
     * 浅拷贝
     *
     * @throws CloneNotSupportedException CloneNotSupportedException
     */
    @Test
    void shallowCopy() throws CloneNotSupportedException {
        Person person = new Person(1, 15, "abc", new Address("四川", "天府二街"));
        Person clonePerson = (Person) person.clone();

        /* @后面的地址不同，说明clone的对象和原对象是两个不同的对象 */
        // com.demo.base.cloneable.Person@eec5a4a
        System.out.println(person);
        // com.demo.base.cloneable.Person@2b2948e2
        System.out.println(clonePerson);

        // 这里display充当了原本toString打印对象内容的作用
        // Person{id=1, age=15, name='abc', address=Address{province='四川', street='天府二街'}}
        System.out.println(person.display());
        // Person{id=1, age=15, name='abc', address=Address{province='四川', street='天府二街'}}
        System.out.println(clonePerson.display());

        System.out.println("--------------------------------------------------");

        // 修改基本类型的值
        clonePerson.setId(2);
        // 基本类型的包装类
        clonePerson.setAge(20);
        // 字符类
        clonePerson.setName("def");

        Address address = clonePerson.getAddress();
        // 修改引用变量的属性值
        address.setStreet("天府四街");

        /*
         * 创建一个新对象，然后将当前对象的非静态字段复制到该对象，如果字段类型是值类型（基本类型或其包装类）的，那么对该字段进行复制；
         * 如果字段是引用类型的，则只复制该字段的引用而不复制引用指向的对象；
         * 此时新对象里面的引用类型字段相当于是原始对象里面引用类型字段的一个副本，原始对象与新对象里面的引用字段指向的是同一个对象；
         * 所以，修改person或者clonePerson的值类型的字段时，只是本身对象的值类型的字段发生变化，互相不影响；
         * 但是修改其中任何一个中的引用类型字段时，则两个对象都会发生变化，因为他们各自的引用类型字段的引用指向的对象是堆中的同一个
         * */
        // Person{id=1, age=15, name='abc', address=Address{province='四川', street='天府四街'}}
        System.out.println(person.display());
        // Person{id=2, age=20, name='def', address=Address{province='四川', street='天府四街'}}
        System.out.println(clonePerson.display());

        System.out.println("==================================================");

        Person p = new Person(3, 100, "abc", new Address("上海", "外滩SOHO"));
        Person clone = (Person) p.clone();
        // com.demo.base.cloneable.Person@6ddf90b0
        System.out.println(p);
        // com.demo.base.cloneable.Person@57536d79
        System.out.println(clone);
        // Person{id=3, age=100, name='abc', address=Address{province='上海', street='外滩SOHO'}}
        System.out.println(p.display());
        // Person{id=3, age=100, name='abc', address=Address{province='上海', street='外滩SOHO'}}
        System.out.println(clone.display());

        // 这里取原对象，对原对象的值类型的字段和引用类型的字段进行重新赋值，查看变化
        p.setId(4);
        p.setAge(200);
        Address pAddress = p.getAddress();
        pAddress.setStreet("soho 3Q");

        // Person{id=4, age=200, name='abc', address=Address{province='上海', street='soho 3Q'}}
        System.out.println(p.display());
        // Person{id=3, age=100, name='abc', address=Address{province='上海', street='soho 3Q'}}
        System.out.println(clone.display());
    }

    /**
     * 深拷贝
     * 这种方法的缺点就是当一个类里面有很多引用类型时，需要手动调用很多clone；
     * 而且如果引用类型内部还有引用类型时，那么代码将会很恶心，量也很大；
     * 所以这种方式一般用于引用类型变量较少的时候。
     *
     * @throws CloneNotSupportedException CloneNotSupportedException
     */
    @Test
    void deepCopy() throws CloneNotSupportedException {

        PersonDeep personDeep = new PersonDeep(10, 35, "x", new AddressDeep("新世界", "西海"));
        PersonDeep personDeepClone = (PersonDeep) personDeep.clone();

        // com.demo.base.cloneable.PersonDeep@eec5a4a
        System.out.println(personDeep);
        System.out.println(personDeep.display());
        // com.demo.base.cloneable.PersonDeep@2b2948e2
        System.out.println(personDeepClone);
        System.out.println(personDeepClone.display());

        System.out.println("---------- modify ----------");
        personDeepClone.setId(11);
        personDeepClone.setAge(17);
        personDeepClone.setName("y");
        AddressDeep addressDeep = personDeepClone.getAddressDeep();
        addressDeep.setStreet("东海");
        personDeepClone.setAddressDeep(addressDeep);

        /* 实现了深拷贝，拷贝之后的对象和原对象的值类型、引用类型字段改变均互不影响 */
        // PersonDeep{id=10, age=35, name='x', addressDeep=Address{province='新世界', street='西海'}}
        System.out.println(personDeep.display());
        // PersonDeep{id=11, age=17, name='y', addressDeep=Address{province='新世界', street='东海'}}
        System.out.println(personDeepClone.display());
    }

    /**
     * 基于序列化的深拷贝
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    void testDeepCloneSerializable() throws IOException, ClassNotFoundException {

        PersonDeepSerializable person = new PersonDeepSerializable(99, 26, "admin", new AddressDeepSerializable("青浦区", "徐泾北城"));
        PersonDeepSerializable personDeepSerializable = (PersonDeepSerializable) person.deepCloneSerializable();
        System.out.println(person);
        System.out.println(person.display());
        System.out.println(personDeepSerializable);
        System.out.println(personDeepSerializable.display());

        person.setId(88);
        person.setAge(25);
        AddressDeepSerializable addressDeepSerializable = person.getAddressDeepSerializable();
        addressDeepSerializable.setStreet("海棠馨苑");

        personDeepSerializable.setId(77);
        AddressDeepSerializable copy = personDeepSerializable.getAddressDeepSerializable();
        copy.setStreet("soho");

        System.out.println(person.display());
        System.out.println(personDeepSerializable.display());
    }
}
