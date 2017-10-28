package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {

    private Location location1;
    private Location location2;
    private Location location3;

    @Before
    public void initLocations() {
        location1 = new Location("location1");
        location2 = new Location("location2", new Coordinate());
        location3 = new Location("location3", new Coordinate(1.0, 2.0, 3.0));
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

        assertEquals(new Coordinate(), location1.getCoordinate());
        assertEquals(new Coordinate(), location2.getCoordinate());
        assertEquals(new Coordinate(1.0, 2.0, 3.0), location3.getCoordinate());
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
    public void testInvalidConstructorNameParameter2() {
        new Location(null, new Coordinate());
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSetCoordinateParameter() {
        location1.setCoordinate(null);
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidConstructorCoordinateParameter() {
        new Location("location4", null);
    }
}
