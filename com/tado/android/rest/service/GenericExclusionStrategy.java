package com.tado.android.rest.service;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class GenericExclusionStrategy implements ExclusionStrategy {
    private final Class<?> mClassToBeExcluded;

    public GenericExclusionStrategy(Class<?> classToBeExcluded) {
        this.mClassToBeExcluded = classToBeExcluded;
    }

    public boolean shouldSkipField(FieldAttributes f) {
        return this.mClassToBeExcluded.equals(f.getDeclaringClass());
    }

    public boolean shouldSkipClass(Class<?> clazz) {
        return this.mClassToBeExcluded.equals(clazz);
    }
}
