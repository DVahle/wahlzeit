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
 * This class holds a coordinate in spheric space
 */
public class SphericCoordinate implements Coordinate {

    /**
     * distance to the origin must be > 0
     */
    private double radius = 0.0;

    /**
     * vertical angle must be between 0 and PI
     */
    private double latitude = 0.0;

    /**
     * horizontal angle must be between -PI and +PI
     */
    private double longitude = 0.0;

    /**
     * @param radius    distance to origin
     * @param latitude  vertical angle between 0 and PI
     * @param longitude horizontal angle between -PI and +PI
     * @methodtype constructor
     */
    public SphericCoordinate(double radius, double latitude, double longitude) {
        assertPositiveRadius(radius);
        assertCorrectLatitude(latitude);
        assertCorrectLongitude(longitude);
        this.radius = radius;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Default constructor. Position is initialized to(x, y, z) = (0.0, 0.0, 0.0).
     *
     * @methodtype constructor
     */
    public SphericCoordinate() {

    }

    /**
     * Converts this object into cartesian coordinate system
     *
     * @methodtype conversion
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {

        double x = radius * Math.sin(latitude) * Math.cos(longitude);
        double y = radius * Math.sin(latitude) * Math.sin(longitude);
        double z = radius * Math.cos(latitude);

        return new CartesianCoordinate(x, y, z);
    }

    /**
     * Returns this object. A conversion is not necessary
     *
     * @methodtype conversion
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    /**
     * Compares this Coordinate with otherCoordinate.
     *
     * @return true if otherCoordinate has the same position.
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        if (coordinate == null) {
            return false;
        }

        SphericCoordinate other = coordinate.asSphericCoordinate();

        final double EPSILON = 10E-4;

        return (isDoubleEqual(this.getRadius(), other.getRadius(), EPSILON)) &&
                (isDoubleEqual(this.getLatitude(), other.getLatitude(), EPSILON)) &&
                (isDoubleEqual(this.getLongitude(), other.getLongitude(), EPSILON));
    }

    /**
     * compares two double values with epsilon tolerance
     *
     * @methodtype boolean-query
     */
    private boolean isDoubleEqual(double a, double b, double epsilon) {
        return Math.abs(a - b) < epsilon;
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
        return (int) (radius + latitude + longitude);
    }

    /**
     * Computes the direct distance between this coordinate and otherCoordinate.
     *
     * @return direct distance from this Coordinate to otherCoordinate. If otherCoordinate is null, +INFINITY is returned
     */
    @Override
    public double getDistance(Coordinate coordinate) {
        //simply use the cartesian implementation for this
        CartesianCoordinate a = this.asCartesianCoordinate();

        return a.getDistance(coordinate);
    }


    /**
     * @methodtype get
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @methodtype set
     */
    public void setRadius(double radius) {
        assertPositiveRadius(radius);
        this.radius = radius;
    }

    /**
     * @methodtype get
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @methodtype set
     */
    public void setLatitude(double latitude) {
        assertCorrectLatitude(latitude);
        this.latitude = latitude;
    }

    /**
     * @methodtype get
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @methodtype set horizontal angle between -PI and +PI
     */
    public void setLongitude(double longitude) {
        assertCorrectLongitude(longitude);
        this.longitude = longitude;
    }

    /**
     * @methodtype assertion
     */
    private void assertPositiveRadius(double radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius must not be < 0!");
    }

    /**
     * @methodtype assertion
     */
    private void assertCorrectLatitude(double latitude) {
        if (latitude < 0 || latitude > +Math.PI) {
            throw new IllegalArgumentException("Latitude must be between 0 and +PI!");
        }
    }

    /**
     * @methodtype assertion
     */
    private void assertCorrectLongitude(double longitude) {
        if (longitude < -Math.PI || longitude > +Math.PI) {
            throw new IllegalArgumentException("Longitude must be between -PI and +PI!");
        }
    }
}
