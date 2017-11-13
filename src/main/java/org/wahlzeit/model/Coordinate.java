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
     * Converts coordinates to cartesian coordinate system
     *
     * @methodtype conversion
     */
    public CartesianCoordinate asCartesianCoordinate();

    /**
     * Converts coordinates to spheric coordinate system
     *
     * @methodtype conversion
     */
    public SphericCoordinate asSphericCoordinate();

    /**
     * @methodtype boolean-query
     */
    public boolean isEqual(Coordinate coordinate);

    /**
     * Computes the difference in cartesian coordinate system between this and coordinate
     *
     * @methodtype query-method
     */
    public double getDistance(Coordinate coordinate);
}
