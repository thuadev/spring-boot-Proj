package com.thuadev.proj.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class BaseEntity {
    @Id
    private long id;
    @Column
    private Date created;
    @Column
    private Date updated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
