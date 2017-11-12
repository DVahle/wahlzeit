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

import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

/**
 * This class extends the PhotoFactory class so it uses WatchPhotos instead of Photos
 */
public class WatchPhotoFactory extends PhotoFactory {

    /**
     * Logger for logging events
     */
    private static final Logger log = Logger.getLogger(WatchPhotoFactory.class.getName());

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static WatchPhotoFactory instance = null;

    /**
     * @methodtype constructor
     */
    protected WatchPhotoFactory() {
        super();
    }

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    public static void initialize() {
        getInstance(); // drops result due to getInstance() side-effects
    }

    /**
     * Public singleton access method
     */
    public static synchronized WatchPhotoFactory getInstance() {
        if (instance == null) {
            log.config(LogBuilder.createSystemMessage().addAction("setting generic WatchPhotoFactory").toString());
            setInstance(new WatchPhotoFactory());
        }

        return instance;
    }


    /**
     * Method to set the singleton instance of WatchPhotoFactory.
     */
    protected static synchronized void setInstance(WatchPhotoFactory watchPhotoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initalize WatchPhotoFactory twice");
        }

        instance = watchPhotoFactory;
    }


    /**
     * @methodtype factory
     */
    @Override
    public WatchPhoto createPhoto() {
        return new WatchPhoto();
    }

    /**
     * Creates a new photo with the specified id
     */
    @Override
    public WatchPhoto createPhoto(PhotoId id) {
        return new WatchPhoto(id);
    }

    /**
     * Loads a photo. The Java object is loaded from the Google Datastore, the Images in all sizes are loaded from the
     * Google Cloud storage.
     */
    @Override
    public WatchPhoto loadPhoto(PhotoId id) {
	   /* Photo result =
                OfyService.ofy().load().type(WatchPhoto.class).ancestor(KeyFactory.createKey("Application", "Wahlzeit")).filter(Photo.ID, id).first().now();
        for (PhotoSize size : PhotoSize.values()) {
            GcsFilename gcsFilename = new GcsFilename("picturebucket", filename);



        }*/
        return null;
    }

}
