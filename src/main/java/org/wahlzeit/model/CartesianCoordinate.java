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

import org.wahlzeit.utils.PatternInstance;

import java.util.HashMap;
import java.util.Objects;

/**
 * This class represents a coordinate in cartesian space.
 * CartesianCoordinate is a shared value object.
 */
@PatternInstance(
        patternName = "Flyweight",
        participants = {"CartesianCoordinate"}
)
public class CartesianCoordinate extends AbstractCoordinate {

    /**
     * Shared object memory, stores all AbstractCoordinate value objects, so they can be reused
     */
    static private final HashMap<CartesianCoordinate, CartesianCoordinate> allSharedCoordinates = new HashMap<>();

    /**
     * Exchanges a temporarily created value Object with an equal shared value object.
     * This method is thread safe.
     *
     * @param newTmpObject value object that was created and should be exchanged for equal shared value object
     * @return the reference to the shared value object that is equal to newTmpObject
     */
    static protected CartesianCoordinate getSharedObject(CartesianCoordinate newTmpObject) {
        synchronized (allSharedCoordinates) {
            CartesianCoordinate mappedValue = allSharedCoordinates.get(newTmpObject);
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
     * A coordinate is defined as a combination of a x, y and z value.
     */
    private final double x;
    private final double y;
    private final double z;

    /**
     * Constructor initializing the Coordinate to a specific position.
     *
     * @param x horizontal position
     * @param y vertical position
     * @param z depth position
     * @throws IllegalArgumentException if x, y or z is either NaN or +-Infinity
     * @throws AssertionError           if class invariant was damaged
     * @methodtype constructor
     */
    private CartesianCoordinate(double x, double y, double z) throws IllegalArgumentException {
        assertValidDouble(x);
        assertValidDouble(y);
        assertValidDouble(z);
        this.x = x;
        this.y = y;
        this.z = z;
        assertClassInvariant();
    }

    /**
     * Default constructor. Position is initialized to(x, y, z) = (0.0, 0.0, 0.0).
     *
     * @throws AssertionError if class invariant was damaged
     * @methodtype constructor
     */
    private CartesianCoordinate() {
        this(0.0, 0.0, 0.0);
    }

    /**
     * Creation method returning the shared value object to a specific cartesian position.
     *
     * @param x horizontal position
     * @param y vertical position
     * @param z depth position
     * @throws IllegalArgumentException if x, y or z is either NaN or +-Infinity
     * @throws AssertionError           if class invariant was damaged
     * @methodtype creation
     */
    static public CartesianCoordinate getCartesianCoordinate(double x, double y, double z) throws IllegalArgumentException {
        CartesianCoordinate tmpObject = new CartesianCoordinate(x, y, z);
        return getSharedObject(tmpObject).asCartesianCoordinate();
    }

    /**
     * Creation method returning the default CartesianCoordinate object. Position is set to (x, y, z) = (0.0, 0.0, 0.0).
     *
     * @throws AssertionError if class invariant was damaged
     * @methodtype creation
     */
    static public CartesianCoordinate getCartesianCoordinate() {
        return getCartesianCoordinate(0.0, 0.0, 0.0);
    }

    /**
     * Converts coordinates to cartesian coordinate system.
     *
     * @throws AssertionError if class invariant was damaged
     * @methodtype conversion
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        assertClassInvariant();
        return this;
    }

    /**
     * Converts coordinates to spheric coordinate system.
     *
     * @throws ConversionException if the conversion leads to an invalid SphericCoordinate
     * @throws AssertionError      if class invariant was damaged
     * @methodtype conversion
     */
    @Override
    public SphericCoordinate asSphericCoordinate() throws ConversionException {
        assertClassInvariant();
        double radius = Math.sqrt(x * x + y * y + z * z);
        if (radius == 0.0) {
            return SphericCoordinate.getSphericCoordinate(0.0, 0.0, 0.0);
        }
        //vertical
        double latitude = Math.acos(z / radius);
        //horizontal
        double longitude = Math.atan2(y, x);
        try {
            SphericCoordinate sphericCoordinate = SphericCoordinate.getSphericCoordinate(radius, latitude, longitude);

            //postcondition: reconverting the sphericCoordinate to a CartesianCoordinate should be equal to the original CartesianCoordinate
            assert sphericCoordinate.asCartesianCoordinate().isEqual(this);
            assertClassInvariant();
            return sphericCoordinate;
        } catch (IllegalArgumentException | AssertionError error) {
            throw new ConversionException("Could not convert CartesianCoordinate to SphericCoordinate", error);
        }
    }

    /**
     * Compares this Coordinate with coordinate.
     *
     * @return true if coordinate has the same position. False if they differed or coordinate was null
     * @throws ConversionException if coordinate can not be converted into CartesianCoordinate space
     * @throws AssertionError      if class invariant was damaged
     */
    public boolean isEqual(Coordinate coordinate) throws ConversionException {
        assertClassInvariant();
        if (coordinate == null) return false;

        CartesianCoordinate other = coordinate.asCartesianCoordinate();

        final double EPSILON = 10E-4;

        boolean result = (isDoubleEqual(this.getX(), other.getX(), EPSILON)) &&
                (isDoubleEqual(this.getY(), other.getY(), EPSILON)) &&
                (isDoubleEqual(this.getZ(), other.getZ(), EPSILON));

        assertClassInvariant();
        return result;
    }

    /**
     * @throws AssertionError if class invariant was damaged
     */
    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ());
    }

    /**
     * @methodtype get
     */
    public double getX() {
        return x;
    }

    /**
     * @return a new shared value object with new x but old y and z position
     * @throws IllegalArgumentException if x is either NaN or +-Infinity
     * @throws AssertionError           if class invariant was damaged
     * @methodtype set
     */
    public CartesianCoordinate setX(double x) throws IllegalArgumentException {
        assertClassInvariant();
        assertValidDouble(x);
        return getCartesianCoordinate(x, getY(), getZ());
    }

    /**
     * @methodtype get
     */
    public double getY() {
        return y;
    }

    /**
     * @return a new shared value object with new y but old x and z position
     * @throws IllegalArgumentException if y is either NaN or +-Infinity
     * @throws AssertionError           if class invariant was damaged
     * @methodtype set
     */
    public CartesianCoordinate setY(double y) throws IllegalArgumentException {
        assertClassInvariant();
        assertValidDouble(y);
        return getCartesianCoordinate(getX(), y, getZ());
    }

    /**
     * @methodtype get
     */
    public double getZ() {
        return z;
    }

    /**
     * @return a new shared value object with new z but old x and y position
     * @throws IllegalArgumentException if z is either NaN or +-Infinity
     * @throws AssertionError           if class invariant was damaged
     * @methodtype set
     */
    public CartesianCoordinate setZ(double z) throws IllegalArgumentException {
        assertClassInvariant();
        assertValidDouble(z);
        return getCartesianCoordinate(getX(), getY(), z);
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
     * Double.NaN and Infinite are not allowed
     *
     * @throws IllegalArgumentException if x is either NaN or +-Infinity
     * @methodtype assertion
     */
    protected void assertValidDouble(double x) throws IllegalArgumentException {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            throw new IllegalArgumentException("expected a valid double value but was " + x);
        }
    }

    /**
     * checks the class invariant
     *
     * @throws AssertionError if one of the state defining variables has an invalid double value
     * @methodtype assertion
     */
    protected void assertClassInvariant() throws AssertionError {
        try {
            assertValidDouble(this.getX());
            assertValidDouble(this.getY());
            assertValidDouble(this.getZ());
        } catch (IllegalArgumentException iae) {
            throw new AssertionError(iae.getMessage());
        }
    }
}
