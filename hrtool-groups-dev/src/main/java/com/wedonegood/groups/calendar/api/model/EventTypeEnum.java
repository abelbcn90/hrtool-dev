package com.wedonegood.groups.calendar.api.model;

public enum EventTypeEnum {
    BANK_HOLIDAY(0),
    COMPANY_DAY_OFF(1),
    ;

    private long id;

    EventTypeEnum(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public static EventTypeEnum getById(long id) {
        for(EventTypeEnum e : values()) {
            if(e.getId() == id) {
                return e;
            }
        }
        return null;
    }
}
