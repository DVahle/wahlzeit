package org.wahlzeit.model.mymodel;

import org.wahlzeit.services.ObjectManager;

import java.util.HashMap;

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
