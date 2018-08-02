package com.wedonegood.common.user.api.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.wedonegood.common.client.Client;
import com.wedonegood.common.language.Language;
import com.wedonegood.common.model.common.BaseEntity;
import com.wedonegood.common.model.user.entity.IUser;

@Entity
public class User extends BaseEntity implements IUser {

//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    private Long userId;

    @Column(unique = true, nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String userFirstName;

    @Column(nullable = false)
    private String userLastName;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String userPhone;

    @Column(nullable = false)
    private int userAttemptCnt = 0;

    @Column(nullable = false)
    private boolean userChangePwd;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "language_code")
    private Language language;
    
    @Override
    public Long getUserId() {
        return this.getId();
    }
    
    @Override
	public Long getClientId() {
		return this.getClient().getId();
	}

	@Override
	public String getLanguageCode() {
		return this.getLanguage().getCode();
	}

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getUserAttemptCnt() {
        return userAttemptCnt;
    }

    public void setUserAttemptCnt(int userAttemptCnt) {
        this.userAttemptCnt = userAttemptCnt;
    }

    public boolean isUserChangePwd() {
        return userChangePwd;
    }

    public void setUserChangePwd(boolean userChangePwd) {
        this.userChangePwd = userChangePwd;
    }

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * @return the language
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}
}
