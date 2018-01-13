package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.mymodel.Watch;
import org.wahlzeit.model.mymodel.WatchType;

import java.util.HashSet;

public class WatchTypeTest {

    private WatchType type1;
    private WatchType type11;
    private WatchType type2;
    private WatchType type3;

    @Before
    public void setup() {
        type1 = new WatchType("type1", null);
        type11 = new WatchType("type1", null);
        type2 = new WatchType("type2", null);
        type3 = new WatchType("type3", null);
    }

    @Test
    public void testConstructor() {
        Assert.assertNotNull(type1);
        Assert.assertNotNull(type11);
        Assert.assertNotNull(type2);
        Assert.assertNotNull(type3);
    }

    @Test
    public void testEquals() {
        Assert.assertEquals(type1, type11);
        Assert.assertNotEquals(type1, type2);
        Assert.assertNotEquals(type11, type3);

        Assert.assertEquals(type1.hashCode(), type11.hashCode());
        Assert.assertNotEquals(type1.hashCode(), type2.hashCode());
        Assert.assertNotEquals(type11.hashCode(), type3.hashCode());
    }

    @Test
    public void testCreateInstance() {
        Watch watch = type1.createInstance();
        Assert.assertNotNull(watch);
        Assert.assertEquals(watch.getType(), type1);
    }

    @Test
    public void testSubTypes() {
        type1.addSubType(type2);
        type2.addSubType(type3);

        Assert.assertTrue(type1.isSubType(type1));
        Assert.assertTrue(type1.isSubType(type2));
        Assert.assertTrue(type1.isSubType(type3));
        Assert.assertTrue(type2.isSubType(type3));

        Assert.assertFalse(type3.isSubType(type2));
        Assert.assertFalse(type3.isSubType(type1));
        Assert.assertFalse(type2.isSubType(type1));
    }

    @Test
    public void testSetSubTypes() {
        HashSet<WatchType> set = new HashSet<>();
        set.add(type2);
        set.add(type3);
        type1.setSubTypes(set);
        Assert.assertFalse(type2.isSubType(type3));
        Assert.assertTrue(type1.isSubType(type2));
        Assert.assertTrue(type1.isSubType(type3));
    }

    @Test
    public void testSuperType() {
        type3.setSuperType(type2);
        type2.setSuperType(type1);
        Assert.assertTrue(type1.isSubType(type2));
        Assert.assertTrue(type1.isSubType(type3));
        Assert.assertTrue(type2.isSuperType(type1));
        Assert.assertTrue(type3.isSuperType(type1));
        Assert.assertTrue(type3.isSuperType(type2));
    }
}
