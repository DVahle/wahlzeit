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
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Default constructor. Position is initialized to(x, y, z) = (0.0, 0.0, 0.0).
     *
     * @methodtype constructor
     */
    public CartesianCoordinate() {

    }

    /**
     * Converts coordinates to cartesian coordinate system.
     *
     * @methodtype conversion
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    /**
     * Converts coordinates to spheric coordinate system.
     *
     * @methodtype conversion
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        double radius = Math.sqrt(x * x + y * y + z * z);
        if (radius == 0.0) {
            return new SphericCoordinate(0.0, 0.0, 0.0);
        }
        //vertical
        double latitude = Math.acos(z / radius);
        //horizontal
        double longitude = Math.atan2(y, x);

        return new SphericCoordinate(radius, latitude, longitude);
    }

    /**
     * Compares this Coordinate with coordinate.
     *
     * @return true if coordinate has the same position.
     */
    public boolean isEqual(Coordinate coordinate) {
        if (coordinate == null) {
            return false;
        }

        CartesianCoordinate other = coordinate.asCartesianCoordinate();

        final double EPSILON = 10E-4;

        return (isDoubleEqual(this.getX(), other.getX(), EPSILON)) &&
                (isDoubleEqual(this.getY(), other.getY(), EPSILON)) &&
                (isDoubleEqual(this.getZ(), other.getZ(), EPSILON));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
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
        this.x = x;
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
        this.y = y;
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
        this.z = z;
    }
}
