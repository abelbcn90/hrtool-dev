package com.wedonegood.employee.roles.api.model.entity;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.wedonegood.employee.model.common.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Abel Pulido Ponce
 *
 */
@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
@Table(name="role")
public class Role extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

    @Column(unique = true, nullable = false)
    private String name;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_function",
	    joinColumns = @JoinColumn(name = "role_id"),
	    inverseJoinColumns = @JoinColumn(name = "function_id")
	)
    private List<Function> functions;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;
    
    /*
     * Constructors
     */
    
    public Role() {
    	
    }
    
    public Role(final String name, final List<Function> functions) {
    	this.name = name;
    	this.functions = functions;
    }
    
    public Role(final String name, final List<Function> functions, final Client client) {
    	this.name = name;
    	this.functions = functions;
    	this.client = client;
    }
    
    /*
	 * Getters & Setters
	 */

	/**
	 * @return the name
	 */
    public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the functions
	 */
	public List<Function> getFunctions() {
		return functions;
	}

	/**
	 * @param functions the functions to set
	 */
	public void setFunctions(List<Function> functions) {
		this.functions = functions;
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
}
