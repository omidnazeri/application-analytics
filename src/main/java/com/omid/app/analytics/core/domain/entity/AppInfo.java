package com.omid.app.analytics.core.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_app_info", uniqueConstraints = {
        @UniqueConstraint(name = "unique_app_token", columnNames = {"c_app_token"}),
        @UniqueConstraint(name = "unique_app_service_key", columnNames = {"c_service_key"}),
        @UniqueConstraint(name = "unique_app_package_name", columnNames = {"c_package_name"})
})
public class AppInfo {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "c_id")
    private String id;

    @Column(name = "c_package_name")
    private String packageName;

    @Column(name = "c_app_token")
    private String appToken;

    @Column(name = "c_service_key")
    private String serviceKey;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppInfo info = (AppInfo) o;
        return Objects.equals(id, info.id) &&
                Objects.equals(packageName, info.packageName) &&
                Objects.equals(appToken, info.appToken) &&
                Objects.equals(serviceKey, info.serviceKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, packageName, appToken, serviceKey);
    }
}
