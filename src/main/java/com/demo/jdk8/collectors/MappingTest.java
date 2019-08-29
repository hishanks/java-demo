package com.demo.jdk8.collectors;

import com.demo.jdk8.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Collectors.mapping
 *
 * @author Shanks
 * @date 2019-04-15
 */
public class MappingTest {

    @Test
    void testMapping() {
        User u1 = new User("1", 10);
        User u2 = new User("2", 10);
        User u3 = new User("3", 10);
        User u4 = new User("3", 20);
        List<User> users = new ArrayList<>(Arrays.asList(u1, u2, u3, u4));
    }
}
