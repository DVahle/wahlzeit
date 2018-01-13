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

import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

/**
 * This class extends the Photo class and adds further attributes specifically for watches
 */
@Subclass
public class WatchPhoto extends Photo {

    /**
     * The watch data linked to this photo.
     * Could not manage to get it serialized correctly by the OfyService, so ignore it.
     */
    @Ignore
    private Watch watchObject;

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
     * Set the data of this photo.
     *
     * @param watchObject the watch data linked to this photo.
     */
    public void setWatchObject(Watch watchObject) {
        this.watchObject = watchObject;
    }

    /**
     * @methodtype get.
     */
    public Watch getWatchObject() {
        return watchObject;
    }
}
