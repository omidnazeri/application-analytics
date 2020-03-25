package com.omid.test.service;

import com.omid.app.analytics.Application;
import com.omid.app.analytics.core.domain.entity.User;
import com.omid.app.analytics.core.exception.UserNotFoundException;
import com.omid.app.analytics.core.service.UserService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    private static String createdUserId;

    @Test(expected = UserNotFoundException.class)
    public void test1UserNotFoundTest() throws UserNotFoundException {
        userService.getUser("user1");
    }

    @Test
    public void test2CreateUserTest() {
        User user = userService.findAndCreateUser("android1", "google1");

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        createdUserId = user.getId();
    }

    @Test
    public void test3GetUserTest() throws UserNotFoundException {
        User user = userService.getUser(createdUserId);

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertEquals(user.getId(), createdUserId);
    }

}