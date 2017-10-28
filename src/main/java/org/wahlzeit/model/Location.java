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
 * A location is related to a photo so one can tell where the image was taken
 */
public class Location {
    /**
     * Name of the location. Must not be null but can be empty.
     */
    private String name = "";

    /**
     * Coordinate of the location.
     */
    public Coordinate coordinate = new Coordinate();

    /**
     * This constructor only sets the name but uses a default coordinate
     *
     * @methodtype constructor
     */
    public Location(String name) {
        checkName(name);
        this.name = name;
    }

    /**
     * This constructor
     *
     * @methodtype constructor
     */
    public Location(String name, Coordinate coordinate) {
        //parameter checks
        checkName(name);
        checkCoordinate(coordinate);
        this.name = name;
        this.coordinate = coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Location) {
            Location other = (Location) o;
            return this.getName().equals(other.getName()) && this.getCoordinate().equals(other.getCoordinate());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode() + this.getCoordinate().hashCode();
    }

    /**
     * @methodtype get
     */
    public String getName() {
        return name;
    }

    /**
     * @methodtype set
     */
    public void setName(String name) {
        checkName(name);
        this.name = name;
    }

    /**
     *
     */
    private void checkName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("null is no valid name for a location. Use \"\" instead");
        }
    }

    /**
     *
     */
    private void checkCoordinate(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("null is no valid coordinate. Use a default constructed coordinate instead");
        }
    }

    /**
     * @methodtype get
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * @methodtype set
     */
    public void setCoordinate(Coordinate coordinate) {
        checkCoordinate(coordinate);
        this.coordinate = coordinate;
    }


}
