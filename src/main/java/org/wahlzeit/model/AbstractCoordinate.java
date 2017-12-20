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

/**
 * This class contains all common methods of CartesianCoordinate and SphericCoordinate.
 */
@PatternInstance(
        patternName = "Template Method",
        participants = {"Coordinate, AbstractCoordinate, CartesianCoordinate, SphericCoordinate"}
)
public abstract class AbstractCoordinate implements Coordinate {


    /**
     * Computes the distance in cartesian coordinate system between this and coordinate.
     * This equals a call of getCartesianDistance.
     * Returns +Infinity if coordinate is null
     *
     * @throws ConversionException      if coordinates can not be converted into CartesianCoordinate space
     * @throws IllegalArgumentException if coordinate is null
     * @methodtype query-method
     */
    public double getDistance(Coordinate coordinate) throws IllegalArgumentException, ConversionException {
        assertCoordinateNotNull(coordinate);
        return getCartesianDistance(coordinate);
    }


    /**
     * Computes the distance in cartesian coordinate system between this and coordinate.
     *
     * @throws ConversionException      if coordinates can not be converted into CartesianCoordinate space
     * @throws IllegalArgumentException if coordinate is null
     * @methodtype query-method
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) throws IllegalArgumentException, ConversionException {
        assertCoordinateNotNull(coordinate);
        CartesianCoordinate own = this.asCartesianCoordinate();
        CartesianCoordinate other = coordinate.asCartesianCoordinate();

        final double deltaX = own.getX() - other.getX();
        final double deltaY = own.getY() - other.getY();
        final double deltaZ = own.getZ() - other.getZ();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
    }


    /**
     * Computes the distance around a sphere between two points on that sphere. If the radius differs, the bigger radius is used.
     * Coordinate must not be null
     *
     * @throws ConversionException      if coordinates can not be converted into SphericCoordinate space
     * @throws IllegalArgumentException if coordinate is null
     * @methodtype query-method
     */
    @Override
    public double getSphericDistance(Coordinate coordinate) throws IllegalArgumentException, ConversionException {
        assertCoordinateNotNull(coordinate);
        SphericCoordinate other = coordinate.asSphericCoordinate();
        SphericCoordinate own = this.asSphericCoordinate();

        double deltaAngle = Math.acos(Math.sin(own.getLatitude()) * Math.sin(other.getLatitude()) +
                Math.cos(own.getLatitude()) * Math.cos(other.getLatitude()) * Math.cos(Math.abs(own.getLongitude() - other.getLongitude())));

        return deltaAngle * Math.max(own.getRadius(), other.getRadius());
    }

    /**
     * Compares this Coordinate with coordinate.
     * This is a default implementation using CartesianCoordinate
     *
     * @return true if coordinate has the same position.
     * @throws ConversionException if coordinate or this can not be converted into required coordinate space
     */
    public boolean isEqual(Coordinate coordinate) throws ConversionException {
        if (coordinate == null) {
            return false;
        }

        CartesianCoordinate other = coordinate.asCartesianCoordinate();
        CartesianCoordinate own = this.asCartesianCoordinate();

        final double EPSILON = 10E-4;

        return (isDoubleEqual(own.getX(), other.getX(), EPSILON)) &&
                (isDoubleEqual(own.getY(), other.getY(), EPSILON)) &&
                (isDoubleEqual(own.getZ(), other.getZ(), EPSILON));
    }

    /**
     *
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            try {
                return isEqual((Coordinate) obj);
            } catch (IllegalStateException | ConversionException ise) {
                ise.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }


    /**
     * compares two double values with epsilon tolerance
     *
     * @methodtype boolean-query
     */
    protected boolean isDoubleEqual(double a, double b, double epsilon) {
        return Math.abs(a - b) < epsilon;
    }

    /**
     * @throws IllegalArgumentException if coordinate is null
     * @methodtype assertion
     */
    protected void assertCoordinateNotNull(Coordinate coordinate) throws IllegalArgumentException {
        if (coordinate == null) {
            throw new IllegalArgumentException("Parameter coordinate must not be null!");
        }
    }
}
