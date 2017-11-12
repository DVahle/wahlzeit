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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * tests the WatchPhotoFactory class
 */
public class WatchPhotoFactoryTest {

    private WatchPhotoFactory watchPhotoFactory;

    @ClassRule
    public static TestRule chain = RuleChain.
            outerRule(new LocalDatastoreServiceTestConfigProvider()).
            around(new RegisteredOfyEnvironmentProvider());

    @Before
    public void setup() {
        watchPhotoFactory = WatchPhotoFactory.getInstance();
    }

    @Test
    public void testConstruction() {
        assertNotNull(watchPhotoFactory);
    }

    @Test
    public void testCreateWatchPhoto() {
        WatchPhoto watchPhoto1 = watchPhotoFactory.createPhoto();
        PhotoId id = new PhotoId(123);
        WatchPhoto watchPhoto2 = watchPhotoFactory.createPhoto(id);

        assertNotNull(watchPhoto1);
        assertNotNull(watchPhoto2);
    }

    @Test
    public void testSingleton() {
        WatchPhotoFactory watchPhotoFactory2 = WatchPhotoFactory.getInstance();
        assertTrue(watchPhotoFactory == watchPhotoFactory2);
    }
}
