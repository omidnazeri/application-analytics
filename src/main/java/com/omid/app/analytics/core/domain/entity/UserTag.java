package com.omid.app.analytics.core.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_user_tag", uniqueConstraints = {@UniqueConstraint(columnNames = {"app_fk", "user_fk", "c_name"})})
public class UserTag {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "c_id")
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "c_date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "app_fk", nullable = false)
    private AppInfo app;

    @ManyToOne
    @JoinColumn(name = "user_fk", nullable = false)
    private User user;

    @Column(name = "c_name")
    private String name;

    @Column(name = "c_value")
    private String value;

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

    public AppInfo getApp() {
        return app;
    }

    public void setApp(AppInfo app) {
        this.app = app;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
