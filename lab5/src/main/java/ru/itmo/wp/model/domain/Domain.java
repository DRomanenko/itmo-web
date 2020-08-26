package ru.itmo.wp.model.domain;

import java.util.Date;

public interface Domain {
    long getId();
    void setId(long id);
    Date getCreationTime();
    void setCreationTime(Date date);
}
