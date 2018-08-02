package com.wedonegood.common.model.user.entity;

public interface IUser {
    Long getUserId();
    String getUserEmail();
    String getUserFirstName();
    String getUserLastName();
    String getUserPhone();
    Long getClientId();
    String getLanguageCode();
}
