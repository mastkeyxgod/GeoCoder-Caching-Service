package ru.mastkey.geo.util;

public interface CacheDBConnector {
    public String getValueByKey(String key);

    public void saveValueByKey(String key, String value);
}
