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
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {

    private Location location1;
    private Location location2;
    private Location location3;

    @Before
    public void initLocations() throws ConversionException {
        location1 = new Location("location1");
        location2 = new Location("location2", new CartesianCoordinate());
        location3 = new Location("location3", new CartesianCoordinate(1.0, 2.0, 3.0));
    }

    /**
     *
     */
    @Test
    public void testConstructors() {
        assertNotNull(location1);
        assertNotNull(location2);
        assertNotNull(location3);

        //check properties after creation
        assertEquals("location1", location1.getName());
        assertEquals("location2", location2.getName());
        assertEquals("location3", location3.getName());

        assertEquals(new CartesianCoordinate(), location1.getCoordinate());
        assertEquals(new CartesianCoordinate(), location2.getCoordinate());
        assertEquals(new CartesianCoordinate(1.0, 2.0, 3.0), location3.getCoordinate());
    }

    /**
     *
     */
    @Test
    public void testEquals() {
        assertNotEquals(location1, location2);
        location1.setName(location2.getName());
        assertEquals(location1, location2);
        assertEquals(location1.hashCode(), location2.hashCode());
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSetNameParameter() {
        location1.setName(null);
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidConstructorNameParameter1() {
        new Location(null);
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidConstructorNameParameter2() throws ConversionException {
        new Location(null, new CartesianCoordinate());
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSetCoordinateParameter() throws ConversionException {
        location1.setCoordinate(null);
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidConstructorCoordinateParameter() throws ConversionException {
        new Location("location4", null);
    }
}
