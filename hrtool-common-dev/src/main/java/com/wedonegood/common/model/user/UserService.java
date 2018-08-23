package com.wedonegood.common.model.user;


import java.util.List;

import com.wedonegood.common.user.api.model.entity.User;

public interface UserService {
    User getUserByUserEmail(String email);
    User getUserByUserId(long id);
    void changePassword(long userId, String oldPassword, String newPassword);
    void changePassword(long userId, String newPassword);
    int recordLoginFailure(String userEmail);
    void clearAttemtCnt(long userId);
    void setChangePasswordFlag(final long userId);
    User save(final User user);
    List<User> findManagers(final Long client);
}
