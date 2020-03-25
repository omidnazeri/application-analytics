package com.omid.test.service;

import com.omid.app.analytics.Application;
import com.omid.app.analytics.core.domain.entity.AppInfo;
import com.omid.app.analytics.core.exception.AppNotFoundException;
import com.omid.app.analytics.core.service.AppService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppServiceTest {
    @Autowired
    private AppService appService;

    @Test(expected = AppNotFoundException.class)
    public void test1GetApplicationNotFoundTest() throws AppNotFoundException {
        appService.getApplication("dummyToken");
    }

    @Test
    public void test2CreateApplicationTest() {
        AppInfo created = appService.addApplication("com.test", "token1", "key1");

        Assert.assertNotNull(created);
        Assert.assertNotNull(created.getId());
    }

    @Test
    public void test3GetApplicationTest() throws AppNotFoundException {
        AppInfo loaded = appService.getApplication("token1");

        Assert.assertNotNull(loaded);
        Assert.assertNotNull(loaded.getId());
    }

    @Test(expected = DuplicateKeyException.class)
    public void test4CreateDuplicateApplicationTest() {
        AppInfo createdDuplicate = appService.addApplication("com.test", "token1", "key1");
    }

}