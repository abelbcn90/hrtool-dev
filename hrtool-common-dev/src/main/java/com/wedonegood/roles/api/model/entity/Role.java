package com.wedonegood.roles.api.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.wedonegood.common.client.Client;
import com.wedonegood.common.model.common.BaseEntity;

/**
 * @author Abel Pulido Ponce
 *
 */
@Entity
public class Role extends BaseEntity {

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
