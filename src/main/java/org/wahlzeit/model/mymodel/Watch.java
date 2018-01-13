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

package org.wahlzeit.model.mymodel;

/**
 * This class handles the data behind a Photo of a watch
 */
public class Watch {

    /**
     * Manager of this object. Has no use but was modeled in UML-Classdiagram.
     */
    private WatchManager manager;

    /**
     * WatchType of this Watch.
     */
    private WatchType type;

    /**
     * Material of which the watches wristband is made of.
     * Should never be null. Use empty String instead.
     */
    protected String wristBandMaterial = "";

    /**
     * Material of which the watches housing is made of
     * Should never be null. Use empty String instead.
     */
    protected String housingMaterial = "";

    /**
     * Name of the brand who produced the watch
     * Should never be null. Use empty String instead.
     */
    protected String brand = "";


    /**
     * @methodtype constructor
     */
    public Watch(WatchType type) {
        this.type = type;
    }

    /**
     * @methodtype get
     */
    public String getWristBandMaterial() {
        return wristBandMaterial;
    }

    /**
     * @throws IllegalArgumentException if wristBandMaterial is null
     * @methodtype set
     */
    public void setWristBandMaterial(String wristBandMaterial) throws IllegalArgumentException {
        if (wristBandMaterial == null) {
            throw new IllegalArgumentException("wristBandMaterial must not be null");
        }
        this.wristBandMaterial = wristBandMaterial;
    }

    /**
     * @methodtype get
     */
    public String getHousingMaterial() {
        return housingMaterial;
    }

    /**
     * @throws IllegalArgumentException if housingMaterial is null
     * @methodtype set
     */
    public void setHousingMaterial(String housingMaterial) throws IllegalArgumentException {
        if (housingMaterial == null) {
            throw new IllegalArgumentException("housingMaterial must not be null");
        }
        this.housingMaterial = housingMaterial;
    }

    /**
     * @methodtype get
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @throws IllegalArgumentException if brand is null
     * @methodtype set
     */
    public void setBrand(String brand) throws IllegalArgumentException {
        if (brand == null) {
            throw new IllegalArgumentException("brand must not be null");
        }
        this.brand = brand;
    }


    /**
     * @methodtype get
     */
    public WatchType getType() {
        return type;
    }

}
