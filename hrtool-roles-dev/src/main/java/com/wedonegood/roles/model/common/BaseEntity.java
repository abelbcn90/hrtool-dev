package com.wedonegood.roles.model.common;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author Abel Pulido Ponce
 *
 */
@MappedSuperclass
public abstract class BaseEntity extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@Column(name="active")
	private boolean active = true;

	public void addAudit() {
//		UserInfoContext uic = UserInfoContext.getCurrent();
//		if(uic == null) {
//			throw new IllegalStateException();
//		}
//		addAudit(uic.getUserEmail());
		addAudit("test@wedonegood.com");
	}

	public void addAudit(String user) {
		if(getId() == null) {
			setCreatedDate(LocalDateTime.now());
			setCreatedBy(user);
		} else {
			setModifiedDate(LocalDateTime.now());
			setModifiedBy(user);
		}
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}


}
