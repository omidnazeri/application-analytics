package com.omid.app.analytics.core.domain.repository;

import com.omid.app.analytics.core.domain.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    User findByImei(String imei);
    User findByAndroidId(String androidId);
    User findByGoogleAdId(String googleAdId);

    @Query("UPDATE User b SET b.imei = ?2, b.imsi = ?3, b.brand = ?4, " +
            "b.model = ?5, b.androidVersion = ?6, b.simType = ?7, " +
            "b.bluetoothAddress = ?8 WHERE b.id = ?1")
    void updateUser(String userId, String imei, String imsi, String brand, String model,
                    String androidVersion, String simType, String bluetoothAddress);
}