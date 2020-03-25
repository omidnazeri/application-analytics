package com.omid.app.analytics.core.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_user", uniqueConstraints = {@UniqueConstraint(columnNames = {"c_android_id"})})
public class User {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "c_id")
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "c_date")
    private Date date;

    @Column(name = "c_android_id")
    private String androidId;

    @Column(name = "c_imei")
    private String imei;

    @Column(name = "c_imsi")
    private String imsi;

    @Column(name = "c_google_ad_id")
    private String googleAdId;

    @Column(name = "c_brand")
    private String brand;

    @Column(name = "c_model")
    private String model;

    @Column(name = "c_android_version")
    private String androidVersion;

    @Column(name = "c_sim_type")
    private String simType;

    @Column(name = "c_bluetooth_address")
    private String bluetoothAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getGoogleAdId() {
        return googleAdId;
    }

    public void setGoogleAdId(String googleAdId) {
        this.googleAdId = googleAdId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getSimType() {
        return simType;
    }

    public void setSimType(String simType) {
        this.simType = simType;
    }

    public String getBluetoothAddress() {
        return bluetoothAddress;
    }

    public void setBluetoothAddress(String bluetoothAddress) {
        this.bluetoothAddress = bluetoothAddress;
    }
}
