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
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * tests the WatchPhoto class
 */
public class WatchPhotoTest {

    private WatchPhoto photo1;
    private WatchPhoto photo2;
    private WatchPhoto photo3;

    @ClassRule
    public static TestRule chain = RuleChain.
            outerRule(new LocalDatastoreServiceTestConfigProvider()).
            around(new RegisteredOfyEnvironmentProvider());

    @Before
    public void setup() {
        photo1 = new WatchPhoto();
        photo2 = new WatchPhoto();

        PhotoId id = new PhotoId(123);
        photo3 = new WatchPhoto(id);
    }

    @Test
    public void testConstructors() {
        assertNotNull(photo1);
        assertNotNull(photo2);
        assertNotNull(photo3);
    }

    @Test
    public void testSetterAndGetters() {
        photo1.setBrand("Diesel");
        assertEquals(photo1.getBrand(), "Diesel");

        photo1.setHousingMaterial("Stainless Steel");
        assertEquals(photo1.getHousingMaterial(), "Stainless Steel");

        photo1.setWristBandMaterial("Leather");
        assertEquals(photo1.getWristBandMaterial(), "Leather");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvilParametersBrand() {
        photo1.setBrand("Diesel");
        photo1.setBrand(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvilParametersHousingMaterial() {
        photo2.setHousingMaterial(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvilParametersWristBandMaterial() {
        photo3.setWristBandMaterial("Leather");
        photo3.setWristBandMaterial(null);
    }

}
