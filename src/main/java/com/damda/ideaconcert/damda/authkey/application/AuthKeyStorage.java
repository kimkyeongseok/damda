package com.damda.ideaconcert.damda.authkey.application;

public interface AuthKeyStorage {
    void create(AuthKeyStorageRequest request);
    String read(AuthKeyStorageRequest request);
    void delete(AuthKeyStorageRequest request);
}
