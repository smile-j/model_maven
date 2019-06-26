package com.dong.base.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Administrator on 2018/5/24.
 */
@Entity
public class Timer {

    @Id
    private Long id;
    private String Type;
    private String allPath;
    private String jobCycel;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getAllPath() {
        return allPath;
    }

    public void setAllPath(String allPath) {
        this.allPath = allPath;
    }

    public String getJobCycel() {
        return jobCycel;
    }

    public void setJobCycel(String jobCycel) {
        this.jobCycel = jobCycel;
    }
}
