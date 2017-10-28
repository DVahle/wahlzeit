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

/**
 * All testcases of the class {}@link Coordinate}.
 */
public class CoordinateTest {

    private Coordinate coordDefault;
    private Coordinate coordClose;
    private Coordinate coordFar;

    @Before
    public void initCoordinates() {
        coordDefault = new Coordinate();
        coordClose = new Coordinate(0.1, -0.2, 0.3);
        coordFar = new Coordinate(1000, 2000, -3000);
    }

    /**
     *
     */
    @Test
    public void testConstructors() {
        assertNotNull(coordDefault);
        assertNotNull(coordClose);
        assertNotNull(coordFar);

        //check properties after creation
        assertEquals(0.0, coordDefault.getX(), 0.0);
        assertEquals(0.0, coordDefault.getY(), 0.0);
        assertEquals(0.0, coordDefault.getZ(), 0.0);

        assertEquals(0.1, coordClose.getX(), 0.0);
        assertEquals(-0.2, coordClose.getY(), 0.0);
        assertEquals(0.3, coordClose.getZ(), 0.0);

        assertEquals(1000, coordFar.getX(), 0.0);
        assertEquals(2000, coordFar.getY(), 0.0);
        assertEquals(-3000, coordFar.getZ(), 0.0);
    }

    /**
     *
     */
    @Test
    public void testEquals() {
        //check evil parameters
        assertFalse(coordDefault.equals(null));
        assertFalse(coordDefault.equals(new Object()));

        //make coordinates equal with setters
        assertFalse(coordClose.equals(coordFar));
        coordFar.setX(coordClose.getX());
        coordFar.setY(coordClose.getY());
        coordFar.setZ(coordClose.getZ());
        assertTrue(coordClose.equals(coordFar));
        assertEquals(coordClose.hashCode(), coordFar.hashCode());

        //check one different value
        coordFar.setX(0.0);
        assertFalse(coordClose.equals(coordFar));
        coordFar.setX(coordClose.getX());

        coordFar.setY(0.0);
        assertFalse(coordClose.equals(coordFar));
        coordFar.setX(coordClose.getY());

        coordFar.setZ(0.0);
        assertFalse(coordClose.equals(coordFar));
        coordFar.setX(coordClose.getZ());
    }

    /**
     *
     */
    @Test
    public void testGetDistance() {
        //check distance to itself
        assertEquals(0.0, coordDefault.getDistance(coordDefault), 0.0);
        assertEquals(0.0, coordClose.getDistance(coordClose), 0.0);
        assertEquals(0.0, coordFar.getDistance(coordFar), 0.0);

        //check same distance in both directions
        assertEquals(coordDefault.getDistance(coordClose), coordClose.getDistance(coordDefault), 1E-4);
        assertEquals(coordClose.getDistance(coordFar), coordFar.getDistance(coordClose), 1E-4);

        //check correct distance
        assertEquals(0.374166, coordDefault.getDistance(coordClose), 1E-4);
        assertEquals(3741.978105, coordClose.getDistance(coordFar), 1E-4);
    }
}
