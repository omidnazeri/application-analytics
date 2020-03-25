package com.omid.app.analytics.core.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_user_package", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_fk", "c_package_name"})})
public class UserPackage {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "c_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_fk", nullable = false)
    private User user;

    @Column(name = "c_package_name")
    private String packageName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
