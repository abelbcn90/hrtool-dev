package com.wedonegood.userRole;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.wedonegood.common.model.common.BaseEntity;
import com.wedonegood.common.user.api.model.entity.User;
import com.wedonegood.groups.api.model.entity.Groups;
import com.wedonegood.roles.api.model.entity.Role;

/**
 * 
 * @author Abel Pulido Ponce
 *
 */
@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
public class UserRole extends BaseEntity {
	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;
	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "group_id")
	private Groups group;
	
	public UserRole() {
		
	}
	
	public UserRole(final User user, final Role role, final Groups group) {
		this.user = user;
		this.role = role;
		this.group = group;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the group
	 */
	public Groups getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(Groups group) {
		this.group = group;
	}
}
