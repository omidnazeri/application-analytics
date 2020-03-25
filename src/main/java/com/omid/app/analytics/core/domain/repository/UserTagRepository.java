package com.omid.app.analytics.core.domain.repository;

import com.omid.app.analytics.core.domain.entity.AppInfo;
import com.omid.app.analytics.core.domain.entity.User;
import com.omid.app.analytics.core.domain.entity.UserTag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface UserTagRepository extends CrudRepository<UserTag, String> {
    UserTag getByAppAndUserAndName(AppInfo appInfo, User user, String tagName);

    @Query("select app.appToken,name,value, count(1), count(distinct user.id) from UserTag where date >= trunc(?1) and date < trunc(?2) group by app.appToken,name,value")
    List<Object> getDailyReport(Date from, Date to);

    @Query("select value from UserTag where app=?1 and name='event' group by value order by value")
    List<String> getEventsByApp(AppInfo appInfo);

    @Query(value = "select cdate, cvalue, operator, count(DISTINCT userfk) " +
            "from " +
            "(select trunc(utag.c_date) as cdate, " +
            "utag.c_value as cvalue, " +
            "case when (lower(us.c_sim_type) like '%mci%' or lower(us.c_sim_type) like '%tci%') then 'mci' " +
            "when (lower(us.c_sim_type) like '%irancell%' or lower(us.c_sim_type) like '%mtn%') then 'mtn' " +
            "when (lower(us.c_sim_type) like '%rightel%' or lower(us.c_sim_type) like '%righ tel%') then 'rightel' " +
            "else 'other' end  as operator, " +
            "utag.user_fk as userfk " +
            "from T_USER_TAG utag " +
            "inner join t_user us on us.c_id=utag.user_fk " +
            "where utag.c_name = 'event' " +
            "and utag.c_date >= trunc(:from) " +
            "and utag.c_date < trunc(:to) " +
            "and utag.app_fk = :appId) " +
            "group by cdate, cvalue, operator order by cdate desc", nativeQuery = true)
    List<Object> installReport(@Param("appId") String appId, @Param("from") Date from, @Param("to") Date to);

    @Query(value = "select trunc(c_date), c_event, count(DISTINCT user_fk),operator " +
            "from " +
            "(select t1.c_date, " +
            "( " +
            "select " +
            "c_value " +
            "from T_USER_TAG t2 " +
            "where t2.APP_FK=t1.APP_FK " +
            "and t2.C_NAME='event' " +
            "and t2.USER_FK=t1.USER_FK " +
            ") as c_event, " +
            "case when (lower(us1.c_sim_type) like '%mci%' or lower(us1.c_sim_type) like '%tci%') then 'mci' " +
            "when (lower(us1.c_sim_type) like '%irancell%' or lower(us1.c_sim_type) like '%mtn%') then 'mtn' " +
            "when (lower(us1.c_sim_type) like '%rightel%' or lower(us1.c_sim_type) like '%righ tel%') then 'rightel' else 'other' end  as operator, " +
            "t1.user_fk " +
            "from T_USER_TAG t1 " +
            "inner join t_user us1 on us1.c_id=t1.user_fk " +
            "where t1.APP_FK= :appId " +
            "and t1.C_NAME='status' " +
            "and t1.C_VALUE='confirm-otp' " +
            "and t1.C_DATE >= trunc(:from) " +
            "and t1.C_DATE < trunc(:to) " +
            ") " +
            "group by trunc(c_date),operator, c_event",nativeQuery = true)
    List<Object> subReport(@Param("appId") String appId, @Param("from") Date from, @Param("to") Date to);

    @Query(value = "select trunc(c_date), c_event, count(DISTINCT user_fk),operator " +
            "from " +
            "(select t1.c_date, " +
            "( " +
            "select " +
            "c_value " +
            "from T_USER_TAG t2 " +
            "where t2.APP_FK=t1.APP_FK " +
            "and t2.C_NAME='event' " +
            "and t2.USER_FK=t1.USER_FK " +
            ") as c_event, " +
            "case when (lower(us1.c_sim_type) like '%mci%' or lower(us1.c_sim_type) like '%tci%') then 'mci' " +
            "when (lower(us1.c_sim_type) like '%irancell%' or lower(us1.c_sim_type) like '%mtn%') then 'mtn' " +
            "when (lower(us1.c_sim_type) like '%rightel%' or lower(us1.c_sim_type) like '%righ tel%') then 'rightel' else 'other' end  as operator, " +
            "t1.user_fk " +
            "from T_USER_TAG t1 " +
            "inner join t_user us1 on us1.c_id=t1.user_fk " +
            "where t1.APP_FK= :appId " +
            "and t1.C_NAME='status' " +
            "and t1.C_VALUE='activateByPhone' " +
            "and t1.C_DATE >= trunc(:from) " +
            "and t1.C_DATE < trunc(:to) " +
            ") " +
            "group by trunc(c_date),operator, c_event",nativeQuery = true)
    List<Object> mtnSubReport(@Param("appId") String appId, @Param("from") Date from, @Param("to") Date to);

    @Query(value = "select trunc(c_date), c_event, count(DISTINCT user_fk),operator " +
            "from " +
            "(select t1.c_date, " +
            "( " +
            "select " +
            "c_value " +
            "from T_USER_TAG t2 " +
            "where t2.APP_FK=t1.APP_FK " +
            "and t2.C_NAME='event' " +
            "and t2.USER_FK=t1.USER_FK " +
            ") as c_event, " +
            "case when (lower(us1.c_sim_type) like '%mci%' or lower(us1.c_sim_type) like '%tci%') then 'mci' " +
            "when (lower(us1.c_sim_type) like '%irancell%' or lower(us1.c_sim_type) like '%mtn%') then 'mtn' " +
            "when (lower(us1.c_sim_type) like '%rightel%' or lower(us1.c_sim_type) like '%righ tel%') then 'rightel' else 'other' end  as operator, " +
            "t1.user_fk " +
            "from T_USER_TAG t1 " +
            "inner join t_user us1 on us1.c_id=t1.user_fk " +
            "where t1.APP_FK= :appId " +
            "and t1.C_NAME='status' " +
            "and t1.C_VALUE='get-otp' " +
            "and t1.C_DATE >= trunc(:from) " +
            "and t1.C_DATE < trunc(:to) " +
            ") " +
            "group by trunc(c_date),operator, c_event",nativeQuery = true)
    List<Object> getOtpReport(@Param("appId") String appId, @Param("from") Date from, @Param("to") Date to);
}