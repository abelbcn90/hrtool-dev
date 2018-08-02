package com.wedonegood.login.model.user;

import com.wedonegood.employee.user.api.model.entity.User;

public interface UserService {
    User getUserByUserEmail(String email);
    User getUserByUserId(long id);
    void changePassword(long userId, String oldPassword, String newPassword);
    void changePassword(long userId, String newPassword);
    int recordLoginFailure(String userEmail);
    void clearAttemtCnt(long userId);
}
