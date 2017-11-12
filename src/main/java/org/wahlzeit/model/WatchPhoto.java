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

import com.googlecode.objectify.annotation.Subclass;

/**
 * This class extends the Photo class and adds further attributes specifically for watches
 */
@Subclass
public class WatchPhoto extends Photo {

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
    public WatchPhoto() {
        super();
    }

    /**
     * @methodtype constructor
     */
    public WatchPhoto(PhotoId myId) {
        super(myId);
    }


    /**
     * @methodtype get
     */
    public String getWristBandMaterial() {
        return wristBandMaterial;
    }

    /**
     * @methodtype set
     */
    public void setWristBandMaterial(String wristBandMaterial) {
        if (wristBandMaterial != null) {
            this.wristBandMaterial = wristBandMaterial;
        }
    }

    /**
     * @methodtype get
     */
    public String getHousingMaterial() {
        return housingMaterial;
    }

    /**
     * @methodtype set
     */
    public void setHousingMaterial(String housingMaterial) {
        if (housingMaterial != null) {
            this.housingMaterial = housingMaterial;
        }
    }

    /**
     * @methodtype get
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @methodtype set
     */
    public void setBrand(String brand) {
        if (brand != null) {
            this.brand = brand;
        }
    }
}
