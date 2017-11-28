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
public class SphericCoordinate extends AbstractCoordinate {

    /**
     * distance to the origin must be >= 0
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
        assertClassInvariant();
        assertPositiveRadius(radius);
        assertCorrectLatitude(latitude);
        assertCorrectLongitude(longitude);
        this.radius = radius;
        this.latitude = latitude;
        this.longitude = longitude;
        assertClassInvariant();
    }

    /**
     * Default constructor. Position is initialized to(x, y, z) = (0.0, 0.0, 0.0).
     *
     * @methodtype constructor
     */
    public SphericCoordinate() {
        assertClassInvariant();
    }

    /**
     * Converts this object into cartesian coordinate system
     *
     * @methodtype conversion
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        assertClassInvariant();

        double x = radius * Math.sin(latitude) * Math.cos(longitude);
        double y = radius * Math.sin(latitude) * Math.sin(longitude);
        double z = radius * Math.cos(latitude);

        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(x, y, z);

        //postcondition: reconverting the cartesianCoordinate to a SphericCoordinate should be equal to the original SphericCoordinate
        //can not use both postconditions in SpericCoordinate.asCartesianCoordinate and CartesianCoordinate.asSphericCoordinate due to endless recursion
        //assert cartesianCoordinate.asSphericCoordinate().isEqual(this);
        assertClassInvariant();
        return cartesianCoordinate;
    }

    /**
     * Returns this object. A conversion is not necessary
     *
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
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
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
     *
     */
    @Override
    public int hashCode() {
        assertClassInvariant();
        return (int) (radius + latitude + longitude);
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
        assertClassInvariant();
        assertPositiveRadius(radius);
        this.radius = radius;
        assertClassInvariant();
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
        assertClassInvariant();
        assertCorrectLatitude(latitude);
        this.latitude = latitude;
        assertClassInvariant();
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
        assertClassInvariant();
        assertCorrectLongitude(longitude);
        this.longitude = longitude;
        assertClassInvariant();
    }

    /**
     * @methodtype assertion
     */
    protected void assertPositiveRadius(double radius) {
        assert !(radius < 0) : "Radius must not be < 0!";
    }

    /**
     * @methodtype assertion
     */
    protected void assertCorrectLatitude(double latitude) {
        assert !(latitude < 0 || latitude > +Math.PI) : "Latitude must be between 0 and +PI!";
    }

    /**
     * @methodtype assertion
     */
    protected void assertCorrectLongitude(double longitude) {
        assert !(longitude < -Math.PI || longitude > +Math.PI) : "Longitude must be between -PI and +PI!";
    }

    /**
     * Checks the class invariant
     *
     * @methodtype assertion
     */
    protected void assertClassInvariant() {
        assertPositiveRadius(this.getRadius());
        assertCorrectLatitude(this.getLatitude());
        assertCorrectLongitude(this.getLongitude());
    }
}
