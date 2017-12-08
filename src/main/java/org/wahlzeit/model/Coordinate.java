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
public interface Coordinate {

    /**
     * Converts coordinates to cartesian coordinate system.
     *
     * @throws ConversionException if conversion leads to an invalid Coordinate
     * @methodtype conversion
     */
    public CartesianCoordinate asCartesianCoordinate() throws ConversionException;

    /**
     * Converts coordinates to spheric coordinate system.
     *
     * @throws ConversionException if conversion leads to an invalid Coordinate
     * @methodtype conversion
     */
    public SphericCoordinate asSphericCoordinate() throws ConversionException;

    /**
     * @throws ConversionException if coordinate can not be converted into the same coordinate space
     * @methodtype boolean-query.
     */
    public boolean isEqual(Coordinate coordinate) throws ConversionException;

    /**
     * Computes the distance in cartesian coordinate system between this and coordinate.
     * This equals a call of getCartesianDistance.
     * Returns +Infinity if coordinate is null
     *
     * @throws ConversionException if coordinate can not be converted into the same coordinate space
     * @methodtype query-method
     */
    public double getDistance(Coordinate coordinate) throws ConversionException;

    /**
     * Computes the distance in cartesian coordinate system between this and coordinate.
     * Returns +Infinity if coordinate is null
     *
     * @throws ConversionException if coordinate can not be converted into the same coordinate space
     * @methodtype query-method
     */
    public double getCartesianDistance(Coordinate coordinate) throws ConversionException;


    /**
     * Computes the distance around a sphere between two points on that sphere. If the radius differs, the bigger radius is used.
     * Returns +Infinity if coordinate is null
     *
     * @throws ConversionException if coordinate can not be converted into the same coordinate space
     * @methodtype query-method
     */
    public double getSphericDistance(Coordinate coordinate) throws ConversionException;
}
