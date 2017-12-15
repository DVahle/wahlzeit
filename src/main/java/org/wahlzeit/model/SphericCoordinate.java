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

import java.util.HashMap;
import java.util.Objects;

/**
 * This class holds a coordinate in spheric space.
 * SphericCoordinate is a shared value object.
 */
public class SphericCoordinate extends AbstractCoordinate {

    /**
     * Shared object memory, stores all AbstractCoordinate value objects, so they can be reused
     */
    static private final HashMap<SphericCoordinate, SphericCoordinate> allSharedCoordinates = new HashMap<>();

    /**
     * Exchanges a temporarily created value Object with an equal shared value object.
     * This method is thread safe.
     *
     * @param newTmpObject value object that was created and should be exchanged for equal shared value object
     * @return the reference to the shared value object that is equal to newTmpObject
     */
    static protected SphericCoordinate getSharedObject(SphericCoordinate newTmpObject) {
        synchronized (allSharedCoordinates) {
            SphericCoordinate mappedValue = allSharedCoordinates.get(newTmpObject);
            //there was no equal object before
            if (mappedValue == null) {
                allSharedCoordinates.put(newTmpObject, newTmpObject);
                return newTmpObject;
            } else {
                return mappedValue;
            }
        }
    }

    /**
     * distance to the origin must be >= 0
     */
    private final double radius;

    /**
     * vertical angle must be between 0 and PI
     */
    private final double latitude;

    /**
     * horizontal angle must be between -PI and +PI
     */
    private final double longitude;

    /**
     * @param radius    distance to origin
     * @param latitude  vertical angle between 0 and PI
     * @param longitude horizontal angle between -PI and +PI
     * @throws IllegalArgumentException if one of the arguments ignores the contracts
     * @throws AssertionError           if class invariant was damaged
     * @methodtype constructor
     */
    private SphericCoordinate(double radius, double latitude, double longitude) throws IllegalArgumentException {
        assertPositiveRadius(radius);
        assertCorrectLatitude(latitude);
        assertCorrectLongitude(longitude);
        this.radius = radius;
        this.latitude = latitude;
        this.longitude = longitude;
        assertClassInvariant();
    }

    /**
     * Default constructor. Position is initialized to(radius, latitude, longitude) = (0.0, 0.0, 0.0).
     *
     * @throws AssertionError if class invariant was damaged
     * @methodtype constructor
     */
    private SphericCoordinate() {
        this(0.0, 0.0, 0.0);
    }

    /**
     * Creation method returning the shared value object to a specific spheric position.
     *
     * @param radius    distance to origin
     * @param latitude  vertical angle between 0 and PI
     * @param longitude horizontal angle between -PI and +PI
     * @throws IllegalArgumentException if one of the arguments ignores the contracts
     * @throws AssertionError           if class invariant was damaged
     * @methodtype creation
     */
    static public SphericCoordinate getSphericCoordinate(double radius, double latitude, double longitude) throws IllegalArgumentException {
        SphericCoordinate tmpObject = new SphericCoordinate(radius, latitude, longitude);
        return getSharedObject(tmpObject).asSphericCoordinate();
    }

    /**
     * Creation method returning the default SphericCoordinate object. Position is set to (radius, latitude, longitude) = (0.0, 0.0, 0.0).
     *
     * @throws AssertionError if class invariant was damaged
     * @methodtype creation
     */
    static public SphericCoordinate getSphericCoordinate() {
        return getSphericCoordinate(0.0, 0.0, 0.0);
    }

    /**
     * Converts this object into cartesian coordinate system
     *
     * @throws ConversionException if conversion leads to an invalid CartesianCoordinate
     * @throws AssertionError      if class invariant was damaged
     * @methodtype conversion
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() throws ConversionException {
        assertClassInvariant();

        double x = radius * Math.sin(latitude) * Math.cos(longitude);
        double y = radius * Math.sin(latitude) * Math.sin(longitude);
        double z = radius * Math.cos(latitude);

        try {
            CartesianCoordinate cartesianCoordinate = CartesianCoordinate.getCartesianCoordinate(x, y, z);

            //postcondition: reconverting the cartesianCoordinate to a SphericCoordinate should be equal to the original SphericCoordinate
            //can not use both postconditions in SpericCoordinate.asCartesianCoordinate and CartesianCoordinate.asSphericCoordinate due to endless recursion
            //assert cartesianCoordinate.asSphericCoordinate().isEqual(this);
            assertClassInvariant();
            return cartesianCoordinate;
        } catch (IllegalArgumentException | AssertionError error) {
            throw new ConversionException("Could not convert SphericCoordinate to CartesianCoordinate", error);
        }
    }

    /**
     * Returns this object. A conversion is not necessary
     *
     * @throws AssertionError if class invariant was damaged
     * @methodtype conversion
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        assertClassInvariant();
        return this;
    }

    /**
     * Compares this Coordinate with otherCoordinate.
     *
     * @return true if otherCoordinate has the same position.
     * @throws ConversionException if coordinate can not be converted into a SphericCoordinate
     * @throws AssertionError      if class invariant was damaged
     */
    @Override
    public boolean isEqual(Coordinate coordinate) throws ConversionException {
        assertClassInvariant();

        if (coordinate == null) {
            return false;
        }
        SphericCoordinate other = coordinate.asSphericCoordinate();

        final double EPSILON = 10E-4;

        boolean result = (isDoubleEqual(this.getRadius(), other.getRadius(), EPSILON)) &&
                (isDoubleEqual(this.getLatitude(), other.getLatitude(), EPSILON)) &&
                (isDoubleEqual(this.getLongitude(), other.getLongitude(), EPSILON));
        assertClassInvariant();
        return result;
    }

    /**
     * Usual hashCode method, but in case this coordinate can not be converted into cartesian space,
     * it will use it's spheric coordinates for hashing. The HashMap in that case is only able to find an equal object
     * if that object is also a SphericCoordinate.
     *
     * @throws AssertionError if class invariant was damaged
     */
    @Override
    public int hashCode() {
        assertClassInvariant();
        try {
            return this.asCartesianCoordinate().hashCode();
        } catch (ConversionException e) {
            e.printStackTrace();
            return Objects.hash(getRadius(), getLatitude(), getLongitude());
        }
    }

    /**
     * @methodtype get
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @return a new shared value object with new radius but old latitude and longitude position
     * @throws IllegalArgumentException if radius < 0
     * @throws AssertionError           if class invariant was damaged
     * @methodtype set
     */
    public SphericCoordinate setRadius(double radius) {
        assertClassInvariant();
        assertPositiveRadius(radius);
        return getSphericCoordinate(radius, getLatitude(), getLongitude());
    }

    /**
     * @methodtype get
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @return a new shared value object with new latitude but old radius and longitude position
     * @throws IllegalArgumentException if latitude < 0 or latitude > +Math.PI
     * @throws AssertionError           if class invariant was damaged
     * @methodtype set
     */
    public SphericCoordinate setLatitude(double latitude) {
        assertClassInvariant();
        assertCorrectLatitude(latitude);
        return getSphericCoordinate(getRadius(), latitude, getLongitude());
    }

    /**
     * @methodtype get
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @return a new shared value object with new longitude but old radius and latitude position
     * @throws IllegalArgumentException if longitude is not in range from -PI to +PI
     * @throws AssertionError           if class invariant was damaged
     * @methodtype set horizontal angle
     */
    public SphericCoordinate setLongitude(double longitude) {
        assertClassInvariant();
        assertCorrectLongitude(longitude);
        return getSphericCoordinate(getRadius(), getLatitude(), longitude);
    }

    /**
     * Removes this object from the shared object memory.
     * Therefor call this method to clear memory from this object in case this value object will never be used again.
     * This method is thread safe.
     *
     * @return whether this object has been removed successfully
     */
    public boolean dispose() {
        synchronized (allSharedCoordinates) {
            return allSharedCoordinates.remove(this) == null;
        }
    }

    /**
     * @throws IllegalArgumentException if radius < 0
     * @methodtype assertion
     */
    protected void assertPositiveRadius(double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("Radius must not be < 0 but was " + radius);
        }
    }

    /**
     * @throws IllegalArgumentException if latitude < 0 or latitude > +Math.PI
     * @methodtype assertion
     */
    protected void assertCorrectLatitude(double latitude) {
        if (latitude < 0 || latitude > +Math.PI) {
            throw new IllegalArgumentException("Latitude must be between 0 and +PI but was " + latitude);
        }
    }

    /**
     * @throws IllegalArgumentException if longitude < -Math.PI or longitude > +Math.PI
     * @methodtype assertion
     */
    protected void assertCorrectLongitude(double longitude) {
        if (longitude < -Math.PI || longitude > +Math.PI) {
            throw new IllegalArgumentException("Longitude must be between -PI and +PI but was " + longitude);
        }
    }

    /**
     * Checks the class invariant
     *
     * @throws AssertionError if any of the state defining variables is in an invalid range
     * @methodtype assertion
     */
    protected void assertClassInvariant() {
        try {
            assertPositiveRadius(this.getRadius());
            assertCorrectLatitude(this.getLatitude());
            assertCorrectLongitude(this.getLongitude());
        } catch (IllegalArgumentException iae) {
            throw new AssertionError(iae.getMessage());
        }
    }
}
