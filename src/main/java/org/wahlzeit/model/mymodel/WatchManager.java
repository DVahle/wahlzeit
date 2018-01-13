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

import org.wahlzeit.services.ObjectManager;

import java.util.HashMap;

/**
 * Handles and persists Watch and WatchType data.
 */
public class WatchManager extends ObjectManager {

    private HashMap<String, WatchType> typeMap = new HashMap<>();

    /**
     * Creates a Watch instance of type with typeName.
     */
    public synchronized Watch createWatch(String typeName) {
        assertIsValidWatchTypeName(typeName);
        WatchType watchType = getWatchType(typeName);
        return watchType.createInstance();
    }

    /**
     * If not existing yet, this method creates a new WatchType from typeName and remembers it in typeMap.
     */
    private WatchType getWatchType(String typeName) {
        if (typeMap.containsKey(typeName)) {
            return typeMap.get(typeName);
        } else {
            WatchType newWatchType = new WatchType(typeName, this);
            typeMap.put(typeName, newWatchType);
            return newWatchType;
        }
    }

    /**
     * @methodtype assertion.
     */
    private void assertIsValidWatchTypeName(String typeName) {
        if (typeName == null) throw new IllegalArgumentException("typeName must not be null");
    }
}
