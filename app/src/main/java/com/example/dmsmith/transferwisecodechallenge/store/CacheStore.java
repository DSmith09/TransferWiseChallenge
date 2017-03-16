package com.example.dmsmith.transferwisecodechallenge.store;


import android.support.annotation.Nullable;

import java.util.HashMap;

import javax.inject.Singleton;

@Singleton
public class CacheStore {
    private static CacheStore INSTANCE;
    private HashMap<String, Object> mCacheMap;

    private CacheStore() {

    }

    public static CacheStore getInstance() {
        if (INSTANCE == null) {
            synchronized (CacheStore.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CacheStore();
                }
            }
        }
        return INSTANCE;
    }

    public void add(String key, Object value) {
        if (mCacheMap == null) {
            mCacheMap = new HashMap<>();
        }
        if (mCacheMap.containsKey(key)) {
            mCacheMap.replace(key, value);
        } else {
            mCacheMap.put(key, value);
        }
    }

    @Nullable
    public Object getValue(String key) {
        if (mCacheMap == null) {
            mCacheMap = new HashMap<>();
        }
        return mCacheMap.get(key);
    }

    public void invalidateCache() {
        mCacheMap = null;
    }
}
