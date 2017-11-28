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

/**
 * This class represents a coordinate in cartesian space
 */
public class CartesianCoordinate extends AbstractCoordinate {

    /**
     * A coordinate is defined as a combination of a x, y and z value.
     */
    private double x = 0.0;
    private double y = 0.0;
    private double z = 0.0;

    /**
     * Constructor initializing the Coordinate to a specific position.
     *
     * @param x horizontal position
     * @param y vertical position
     * @param z depth position
     * @methodtype constructor
     */
    public CartesianCoordinate(double x, double y, double z) {
        assertClassInvariant();
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
     * @methodtype constructor
     */
    public CartesianCoordinate() {
        assertClassInvariant();
    }

    /**
     * Converts coordinates to cartesian coordinate system.
     *
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
     * @methodtype conversion
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        assertClassInvariant();
        double radius = Math.sqrt(x * x + y * y + z * z);
        if (radius == 0.0) {
            return new SphericCoordinate(0.0, 0.0, 0.0);
        }
        //vertical
        double latitude = Math.acos(z / radius);
        //horizontal
        double longitude = Math.atan2(y, x);

        SphericCoordinate sphericCoordinate = new SphericCoordinate(radius, latitude, longitude);

        //postcondition: reconverting the sphericCoordinate to a CartesianCoordinate should be equal to the original CartesianCoordinate
        assert sphericCoordinate.asCartesianCoordinate().isEqual(this);
        assertClassInvariant();
        return sphericCoordinate;
    }

    /**
     * Compares this Coordinate with coordinate.
     *
     * @return true if coordinate has the same position. False if they differed or coordinate was null
     */
    public boolean isEqual(Coordinate coordinate) {
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
     *
     */
    @Override
    public int hashCode() {
        assertClassInvariant();
        return (int) (x + y + z);
    }

    /**
     * @methodtype get
     */
    public double getX() {
        return x;
    }

    /**
     * @methodtype set
     */
    public void setX(double x) {
        assertClassInvariant();
        assertValidDouble(x);
        this.x = x;
        assertClassInvariant();
    }

    /**
     * @methodtype get
     */
    public double getY() {
        return y;
    }

    /**
     * @methodtype set
     */
    public void setY(double y) {
        assertClassInvariant();
        assertValidDouble(y);
        this.y = y;
        assertClassInvariant();
    }

    /**
     * @methodtype get
     */
    public double getZ() {
        return z;
    }

    /**
     * @methodtype set
     */
    public void setZ(double z) {
        assertClassInvariant();
        assertValidDouble(z);
        this.z = z;
        assertClassInvariant();
    }

    /**
     * Double.NaN and Infinite are not allowed
     *
     * @methodtype assertion
     */
    protected void assertValidDouble(double x) {
        assert !Double.isNaN(x) && !Double.isInfinite(x);
    }

    /**
     * checks the class invariant
     *
     * @methodtype assertion
     */
    protected void assertClassInvariant() {
        assertValidDouble(this.getX());
        assertValidDouble(this.getY());
        assertValidDouble(this.getZ());
    }
}
