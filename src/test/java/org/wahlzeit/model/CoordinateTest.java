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

    private CartesianCoordinate cartCoordDefault;
    private CartesianCoordinate cartCoordClose;
    private CartesianCoordinate cartCoordFar;

    private SphericCoordinate sphericCoordDefault;
    private SphericCoordinate sphericCoordClose;
    private SphericCoordinate sphericCoordFar;

    static private final double tolerance = 1E-4;

    @Before
    public void initCoordinates() {
        cartCoordDefault = new CartesianCoordinate();
        cartCoordClose = new CartesianCoordinate(0.1, -0.2, 0.3);
        cartCoordFar = new CartesianCoordinate(1000, 2000, -3000);

        sphericCoordDefault = new SphericCoordinate();
        sphericCoordClose = new SphericCoordinate(0.1, Math.PI / 4, Math.PI);
        sphericCoordFar = new SphericCoordinate(1000, 0.33, -1.2);
    }

    /**
     *
     */
    @Test
    public void testConstructors() {
        assertNotNull(cartCoordDefault);
        assertNotNull(cartCoordClose);
        assertNotNull(cartCoordFar);

        assertNotNull(sphericCoordDefault);
        assertNotNull(sphericCoordClose);
        assertNotNull(sphericCoordFar);

        //check properties after creation
        assertEquals(0.0, cartCoordDefault.getX(), 0.0);
        assertEquals(0.0, cartCoordDefault.getY(), 0.0);
        assertEquals(0.0, cartCoordDefault.getZ(), 0.0);

        assertEquals(0.1, cartCoordClose.getX(), 0.0);
        assertEquals(-0.2, cartCoordClose.getY(), 0.0);
        assertEquals(0.3, cartCoordClose.getZ(), 0.0);

        assertEquals(1000, cartCoordFar.getX(), 0.0);
        assertEquals(2000, cartCoordFar.getY(), 0.0);
        assertEquals(-3000, cartCoordFar.getZ(), 0.0);


        assertEquals(0.0, sphericCoordDefault.getRadius(), 0.0);
        assertEquals(0.0, sphericCoordDefault.getLatitude(), 0.0);
        assertEquals(0.0, sphericCoordDefault.getLongitude(), 0.0);

        assertEquals(0.1, sphericCoordClose.getRadius(), 0.0);
        assertEquals(Math.PI / 4, sphericCoordClose.getLatitude(), 0.0);
        assertEquals(Math.PI, sphericCoordClose.getLongitude(), 0.0);

        assertEquals(1000, sphericCoordFar.getRadius(), 0.0);
        assertEquals(0.33, sphericCoordFar.getLatitude(), 0.0);
        assertEquals(-1.2, sphericCoordFar.getLongitude(), 0.0);
    }

    /**
     *
     */
    @Test
    public void testCartesianEquals() {
        //check evil parameters
        assertFalse(cartCoordDefault.equals(null));
        assertFalse(cartCoordDefault.isEqual(null));
        assertFalse(cartCoordDefault.equals(new Object()));

        //make coordinates equal with setters
        assertFalse(cartCoordClose.equals(cartCoordFar));
        cartCoordFar.setX(cartCoordClose.getX());
        cartCoordFar.setY(cartCoordClose.getY());
        cartCoordFar.setZ(cartCoordClose.getZ());
        assertTrue(cartCoordClose.isEqual(cartCoordFar));
        assertTrue(cartCoordClose.equals(cartCoordFar));
        assertEquals(cartCoordClose.hashCode(), cartCoordFar.hashCode());

        //check one different value
        cartCoordFar.setX(0.0);
        assertFalse(cartCoordClose.isEqual(cartCoordFar));
        assertFalse(cartCoordClose.equals(cartCoordFar));
        cartCoordFar.setX(cartCoordClose.getX());

        cartCoordFar.setY(0.0);
        assertFalse(cartCoordClose.equals(cartCoordFar));
        cartCoordFar.setX(cartCoordClose.getY());

        cartCoordFar.setZ(0.0);
        assertFalse(cartCoordClose.equals(cartCoordFar));
        cartCoordFar.setZ(cartCoordClose.getZ());
    }

    /**
     *
     */
    @Test
    public void testSphericEquals() {
        //check evil parameters
        assertFalse(sphericCoordDefault.equals(null));
        assertFalse(sphericCoordDefault.isEqual(null));
        assertFalse(sphericCoordDefault.equals(new Object()));

        //make coordinates equal with setters
        assertFalse(sphericCoordClose.equals(sphericCoordFar));
        sphericCoordFar.setRadius(sphericCoordClose.getRadius());
        sphericCoordFar.setLatitude(sphericCoordClose.getLatitude());
        sphericCoordFar.setLongitude(sphericCoordClose.getLongitude());
        assertTrue(sphericCoordClose.isEqual(sphericCoordFar));
        assertTrue(sphericCoordClose.equals(sphericCoordFar));
        assertEquals(sphericCoordClose.hashCode(), sphericCoordFar.hashCode());

        //check one different value
        sphericCoordFar.setRadius(0.0);
        assertFalse(sphericCoordClose.isEqual(sphericCoordFar));
        assertFalse(sphericCoordClose.equals(sphericCoordFar));
        sphericCoordFar.setRadius(sphericCoordClose.getRadius());

        sphericCoordFar.setLatitude(0.0);
        assertFalse(sphericCoordClose.equals(sphericCoordFar));
        sphericCoordFar.setLatitude(sphericCoordClose.getLatitude());

        sphericCoordFar.setLongitude(0.0);
        assertFalse(sphericCoordClose.equals(sphericCoordFar));
        sphericCoordFar.setLongitude(sphericCoordClose.getLongitude());
    }

    /**
     * Since getDistance is the same as getCartesianDistance, this test covers both methods
     */
    @Test
    public void testGetDistance() {
        //check distance to itself
        assertEquals(0.0, cartCoordDefault.getDistance(cartCoordDefault), 0.0);
        assertEquals(0.0, cartCoordClose.getDistance(cartCoordClose), 0.0);
        assertEquals(0.0, cartCoordFar.getDistance(cartCoordFar), 0.0);

        assertEquals(0.0, sphericCoordDefault.getDistance(sphericCoordDefault), 0.0);
        assertEquals(0.0, sphericCoordClose.getDistance(sphericCoordClose), 0.0);
        assertEquals(0.0, sphericCoordFar.getDistance(sphericCoordFar), 0.0);

        //check same distance in both directions
        assertEquals(cartCoordDefault.getDistance(cartCoordClose), cartCoordClose.getDistance(cartCoordDefault), tolerance);
        assertEquals(cartCoordClose.getDistance(cartCoordFar), cartCoordFar.getDistance(cartCoordClose), tolerance);

        assertEquals(sphericCoordDefault.getDistance(sphericCoordClose), sphericCoordClose.getDistance(sphericCoordDefault), tolerance);
        assertEquals(sphericCoordClose.getDistance(sphericCoordFar), sphericCoordFar.getDistance(sphericCoordClose), tolerance);

        //check correct distance
        assertEquals(0.374166, cartCoordDefault.getDistance(cartCoordClose), tolerance);
        assertEquals(3741.978105, cartCoordClose.getDistance(cartCoordFar), tolerance);

        assertEquals(sphericCoordFar.getRadius(), sphericCoordFar.getDistance(new SphericCoordinate()), tolerance);
    }

    /**
     *
     */
    @Test
    public void testSphericDistance() {
        //example from Wkipedia (https://de.wikipedia.org/wiki/Orthodrome)
        final double earthRadius = 6370.0;  //km
        SphericCoordinate berlin = new SphericCoordinate(earthRadius, (52.517) / 180.0 * Math.PI, ((13.40 + 180.0) / 360.0) * 2 * Math.PI - Math.PI);
        SphericCoordinate tokio = new SphericCoordinate(earthRadius, (35.70) / 180.0 * Math.PI, ((139.767 + 180.0) / 360.0) * 2 * Math.PI - Math.PI);

        double sphericDistance = berlin.getSphericDistance(tokio);
        assertEquals(sphericDistance, 8918, 2.0);

        CartesianCoordinate berlinCart = berlin.asCartesianCoordinate();
        CartesianCoordinate tokioCart = tokio.asCartesianCoordinate();

        assertEquals(sphericDistance, tokioCart.getSphericDistance(berlinCart), 2.0);
        assertEquals(sphericDistance, tokioCart.getSphericDistance(berlin), 2.0);
        assertEquals(sphericDistance, berlin.getSphericDistance(tokioCart), 2.0);
    }

    /**
     *
     */
    @Test
    public void testCoordinateConversion() {
        assertEquals(cartCoordClose.asCartesianCoordinate(), cartCoordClose);
        assertEquals(sphericCoordClose.asSphericCoordinate(), sphericCoordClose);

        assertEquals(cartCoordDefault.asSphericCoordinate().asCartesianCoordinate(), cartCoordDefault);
        assertEquals(cartCoordClose.asSphericCoordinate().asCartesianCoordinate(), cartCoordClose);
        assertEquals(cartCoordFar.asSphericCoordinate().asCartesianCoordinate(), cartCoordFar);

        assertEquals(sphericCoordDefault.asCartesianCoordinate().asSphericCoordinate(), sphericCoordDefault);
        assertEquals(sphericCoordClose.asCartesianCoordinate().asSphericCoordinate(), sphericCoordClose);
        assertEquals(sphericCoordFar.asCartesianCoordinate().asSphericCoordinate(), sphericCoordFar);
    }
}
