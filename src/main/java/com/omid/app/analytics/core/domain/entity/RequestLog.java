package com.omid.app.analytics.core.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_request_log")
public class RequestLog {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "c_id")
	private String id;

	@Column(name = "c_ip")
	private String ipAddress;

	@Column(name = "c_page_uri")
	private String pageURI;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "c_date")
	private Date date;

	@ManyToOne
	@JoinColumn(name = "user_fk")
	private User user;

	@ManyToOne
	@JoinColumn(name = "app_fk", nullable = false)
	private AppInfo app;

    @Column(name = "c_user_agent")
	private String userAgent;

	@Column(name = "c_result")
	private String result;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getPageURI() {
		return pageURI;
	}

	public void setPageURI(String pageURI) {
		this.pageURI = pageURI;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AppInfo getApp() {
		return app;
	}

	public void setApp(AppInfo app) {
		this.app = app;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
