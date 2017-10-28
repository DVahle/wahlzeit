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
 * A coordinate represents the position of a point in a 3D cartesian space.
 */
public class Coordinate {

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
    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Default constructor. Position is initialized to(x, y, z) = (0.0, 0.0, 0.0).
     *
     * @methodtype constructor
     */
    public Coordinate() {

    }

    /**
     * Computes the direct distance between this coordinate and otherCoordinate.
     * @return direct distance from this Coordinate to otherCoordinate. If otherCoordinate is null, +INFINITY is returned
     */
    protected double getDistance(Coordinate otherCoordinate) {
        if (otherCoordinate == null) {
            return Double.POSITIVE_INFINITY;
        }
        final double deltaX = this.getX() - otherCoordinate.getX();
        final double deltaY = this.getY() - otherCoordinate.getY();
        final double deltaZ = this.getZ() - otherCoordinate.getZ();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
    }

    /**
     * Compares this Coordinate with otherCoordinate.
     *
     * @return true if otherCoordinate has the same x, y and z position.
     */
    protected boolean isEqual(Coordinate otherCoordinate) {
        if (otherCoordinate == null) {
            return false;
        }
        return (this.getX() == otherCoordinate.getX()) &&
                (this.getY() == otherCoordinate.getY()) &&
                (this.getZ() == otherCoordinate.getZ());
    }

    /**
     *
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            return isEqual((Coordinate) obj);
        } else {
            return false;
        }
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
