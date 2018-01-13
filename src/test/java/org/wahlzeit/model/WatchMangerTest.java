package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.model.mymodel.Watch;
import org.wahlzeit.model.mymodel.WatchManager;
import org.wahlzeit.model.mymodel.WatchType;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

public class WatchMangerTest {

    private WatchManager manager;

    @ClassRule
    public static RuleChain ruleChain = RuleChain.outerRule(new LocalDatastoreServiceTestConfigProvider()).around(new RegisteredOfyEnvironmentProvider());

    @Before
    public void setup() {
        manager = new WatchManager();
    }

    @Test
    public void testConstructor() {
        Assert.assertNotNull(manager);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWatchInvalid() {
        manager.createWatch(null);
    }

    @Test
    public void testCreateWatch() {
        Watch watch11 = manager.createWatch("type1");
        Watch watch12 = manager.createWatch("type1");
        Watch watch2 = manager.createWatch("type2");
        Assert.assertFalse(watch11 == watch12);
        Assert.assertFalse(watch12 == watch2);

        WatchType type11 = watch11.getType();
        WatchType type12 = watch12.getType();
        WatchType type2 = watch2.getType();
        Assert.assertTrue(type11 == type12);
        Assert.assertFalse(type12 == type2);
    }
}
