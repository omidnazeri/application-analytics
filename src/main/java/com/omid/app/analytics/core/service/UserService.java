package com.omid.app.analytics.core.service;

import com.omid.app.analytics.core.domain.entity.User;
import com.omid.app.analytics.core.domain.entity.UserPackage;
import com.omid.app.analytics.core.domain.repository.UserPackageRepository;
import com.omid.app.analytics.core.domain.repository.UserRepository;
import com.omid.app.analytics.core.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserPackageRepository userPackageRepository;

    public UserService(UserRepository userRepository,
                       UserPackageRepository userPackageRepository) {
        this.userRepository = userRepository;
        this.userPackageRepository = userPackageRepository;
    }

    public User findAndCreateUser(String androidId, String googleAdId) {
        if (googleAdId != null && !googleAdId.equals("")) {
            User user = userRepository.findByGoogleAdId(googleAdId);

            if (user == null) {
                user = userRepository.findByAndroidId(androidId);

                if (user == null) {
                    user = new User();
                    user.setDate(new Date());
                    user.setAndroidId(androidId);
                    user.setGoogleAdId(googleAdId);

                    user = userRepository.save(user);
                } else {
                    user.setGoogleAdId(googleAdId);
                    user = userRepository.save(user);
                }
            }

            return user;
        } else {
            User user = userRepository.findByAndroidId(androidId);
            if (user == null) {
                user = new User();
                user.setDate(new Date());
                user.setAndroidId(androidId);
                user.setGoogleAdId(googleAdId);
                user = userRepository.save(user);
            }

            return user;
        }
    }

    public User findAndCreateUser(String androidId, String imei, String imsi,
                                  String googleAdId, String brand, String model,
                                  String androidVersion, String simType,
                                  String installedPackages, String bluetoothAddress) {
        if (googleAdId != null && !googleAdId.equals("")) {
            User user = userRepository.findByGoogleAdId(googleAdId);

            if (user == null) {
                user = userRepository.findByAndroidId(androidId);

                if (user == null) {
                    user = new User();
                    user.setDate(new Date());
                    user.setAndroidId(androidId);
                    user.setImei(imei);
                    user.setImsi(imsi);
                    user.setGoogleAdId(googleAdId);
                    user.setBrand(brand);
                    user.setModel(model);
                    user.setAndroidVersion(androidVersion);
                    user.setSimType(simType);
                    user.setBluetoothAddress(bluetoothAddress);
                    String[] packages = installedPackages.split(",");
                    user = userRepository.save(user);

                    for (String installedPackage : packages) {
                        UserPackage userPackage = new UserPackage();
                        userPackage.setUser(user);
                        userPackage.setPackageName(installedPackage);
                        userPackageRepository.save(userPackage);
                    }
                } else {
                    user.setGoogleAdId(googleAdId);
                    user.setBluetoothAddress(bluetoothAddress);
                    user = userRepository.save(user);
                }
            }

            return user;
        } else {
            User user = userRepository.findByAndroidId(androidId);
            if (user == null) {
                user = new User();
                user.setDate(new Date());
                user.setAndroidId(androidId);
                user.setImei(imei);
                user.setImsi(imsi);
                user.setGoogleAdId(googleAdId);
                user.setBrand(brand);
                user.setModel(model);
                user.setAndroidVersion(androidVersion);
                user.setSimType(simType);
                user.setBluetoothAddress(bluetoothAddress);
                String[] packages = installedPackages.split(",");
                user = userRepository.save(user);

                for (String installedPackage : packages) {
                    UserPackage userPackage = new UserPackage();
                    userPackage.setUser(user);
                    userPackage.setPackageName(installedPackage);
                    userPackageRepository.save(userPackage);
                }
            }

            return user;
        }
    }


    public User saveUserInfo(String userId, String imei, String imsi,
                             String brand, String model,
                             String androidVersion, String simType,
                             String installedPackages, String bluetoothAddress) throws UserNotFoundException {
        User user = getUser(userId);

        user.setImei(imei);
        user.setImsi(imsi);
        user.setBrand(brand);
        user.setModel(model);
        user.setAndroidVersion(androidVersion);
        user.setSimType(simType);
        user.setBluetoothAddress(bluetoothAddress);
        String[] packages = installedPackages.split(",");
        user = userRepository.save(user);

        for (String installedPackage : packages) {
            UserPackage userPackage = userPackageRepository.findByUserAndPackageName(user, installedPackage);
            if (userPackage == null) {
                userPackage = new UserPackage();
                userPackage.setUser(user);
                userPackage.setPackageName(installedPackage);
                userPackageRepository.save(userPackage);
            }
        }

        return user;
    }


    public User getUser(String userId) throws UserNotFoundException {
        if (userId == null)
            throw new UserNotFoundException();

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }
}
