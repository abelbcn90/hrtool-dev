package com.wedonegood.login.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wedonegood.employee.user.api.model.entity.User;
import com.wedonegood.login.model.user.repository.UserRepository;
import com.wedonegood.login.security.exception.BadOldPasswordException;

/**
 * 
 * @author Abel Pulido Ponce
 *
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User getUserByUserEmail(final String email) {
        return this.userRepository.findOneByUserEmail(email);
    }

    public User getUserByUserId(final long id) {
        return this.userRepository.findById(id).get();
    }

    @Transactional
    public void changePassword(final long userId, final String oldPassword, final String newPassword) {
        final User u = getUserByUserId(userId);

    	if (!u.getUserPassword().equalsIgnoreCase(this.bCryptPasswordEncoder.encode(oldPassword))) {
//            throw new BadOldPasswordException("Введен неверный старый пароль");
            throw new BadOldPasswordException("Incorrect old password");
        }

        final String pwd = this.bCryptPasswordEncoder.encode(newPassword);
        u.setUserPassword(pwd);
        u.setUserChangePwd(false);
        
        this.userRepository.saveAndFlush(u);
    }

    @Transactional
    public void changePassword(final long userId, final String newPassword) {
    	final User u = getUserByUserId(userId);
    	final String pwd = this.bCryptPasswordEncoder.encode(newPassword);
        u.setUserPassword(pwd);
        u.setUserChangePwd(false);
        
        this.userRepository.saveAndFlush(u);
    }

    @Transactional
    public int recordLoginFailure(final String userEmail) {
    	final User u = getUserByUserEmail(userEmail);
        
        if (null == u) {
            return -1;
        }
        
        u.setUserAttemptCnt(u.getUserAttemptCnt() + 1);
        
        return this.userRepository.saveAndFlush(u).getUserAttemptCnt();
    }

    @Transactional
    public void clearAttemtCnt(final long userId) {
        final User u = getUserByUserId(userId);
        u.setUserAttemptCnt(0);
        
        this.userRepository.saveAndFlush(u);
    }
}
