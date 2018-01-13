/*
 * Copyright (c) 2017 by Daniel Vahle
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.model.mymodel.Watch;
import org.wahlzeit.model.mymodel.WatchManager;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * tests the WatchPhoto class
 */
public class WatchTest {

    private Watch watch1;
    private Watch watch2;
    private Watch watch3;

    @ClassRule
    public static TestRule chain = RuleChain.
            outerRule(new LocalDatastoreServiceTestConfigProvider()).
            around(new RegisteredOfyEnvironmentProvider());

    @Before
    public void setup() {
        WatchManager manager = new WatchManager();
        watch1 = manager.createWatch("typeA");
        watch2 = manager.createWatch("typeB");
        watch3 = manager.createWatch("typeC");
    }

    @Test
    public void testConstructors() {
        assertNotNull(watch1);
        assertNotNull(watch2);
        assertNotNull(watch3);
    }

    @Test
    public void testSetterAndGetters() {
        watch1.setBrand("Diesel");
        assertEquals(watch1.getBrand(), "Diesel");

        watch1.setHousingMaterial("Stainless Steel");
        assertEquals(watch1.getHousingMaterial(), "Stainless Steel");

        watch1.setWristBandMaterial("Leather");
        assertEquals(watch1.getWristBandMaterial(), "Leather");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvilParametersBrand() {
        watch1.setBrand("Diesel");
        watch1.setBrand(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvilParametersHousingMaterial() {
        watch2.setHousingMaterial(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvilParametersWristBandMaterial() {
        watch3.setWristBandMaterial("Leather");
        watch3.setWristBandMaterial(null);
    }

}
