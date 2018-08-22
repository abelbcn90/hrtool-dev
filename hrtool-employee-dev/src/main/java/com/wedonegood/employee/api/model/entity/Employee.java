package com.wedonegood.employee.api.model.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.wedonegood.billing.Billing;
import com.wedonegood.common.user.api.model.entity.User;
import com.wedonegood.contract.Contract;
import com.wedonegood.employee.model.common.BaseEntity;
import com.wedonegood.groups.api.model.entity.Groups;
import com.wedonegood.permit.Permit;
import com.wedonegood.userRole.RoleGroups;

@Entity
public class Employee extends BaseEntity {
	
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(nullable = true)
    private String number;
    
    @Column(nullable = true)
    private String nin;
    
    @Column(nullable = true)
    private String address;
    
    @Column(nullable = true)
    private String country;
    
    @Column(nullable = true)
    private String city;
    
    @Column(nullable = true)
    private String postalCode;
    
    @Column(nullable = true)
    private LocalDate birthday;
    
    @Column(nullable = true)
    private String passportScan;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "permit_id")
    private Permit permit;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id")
    private Contract contract;
    
    @Column(nullable = true)
    private String jobPosition;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "billing_id")
    private Billing billing;
    
    @Column(nullable = true)
    private LocalDate hireDate;
    
    @Column(nullable = true)
    private Integer salary;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Groups group;
    
    @Column(nullable = true)
    private Integer vacationAllowance;
    
    @Column(nullable = true)
    private Integer vacationTaken;
    
    @Column(nullable = true)
    private String sicknessTaken;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id")
    private User manager;
    
    @Column(nullable = true)
    private String profilePicture;
    
    @Transient
    private List<RoleGroups> roleGroups;

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
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the nin
	 */
	public String getNin() {
		return nin;
	}

	/**
	 * @param nin the nin to set
	 */
	public void setNin(String nin) {
		this.nin = nin;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the birthday
	 */
	public LocalDate getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the passportScan
	 */
	public String getPassportScan() {
		return passportScan;
	}

	/**
	 * @param passportScan the passportScan to set
	 */
	public void setPassportScan(String passportScan) {
		this.passportScan = passportScan;
	}

	/**
	 * @return the permit
	 */
	public Permit getPermit() {
		return permit;
	}

	/**
	 * @param permit the permit to set
	 */
	public void setPermit(Permit permit) {
		this.permit = permit;
	}

	/**
	 * @return the contract
	 */
	public Contract getContract() {
		return contract;
	}

	/**
	 * @param contract the contract to set
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
	}

	/**
	 * @return the jobPosition
	 */
	public String getJobPosition() {
		return jobPosition;
	}

	/**
	 * @param jobPosition the jobPosition to set
	 */
	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	/**
	 * @return the billing
	 */
	public Billing getBilling() {
		return billing;
	}

	/**
	 * @param billing the billing to set
	 */
	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	/**
	 * @return the hireDate
	 */
	public LocalDate getHireDate() {
		return hireDate;
	}

	/**
	 * @param hireDate the hireDate to set
	 */
	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	/**
	 * @return the salary
	 */
	public Integer getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(Integer salary) {
		this.salary = salary;
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

	/**
	 * @return the vacationAllowance
	 */
	public Integer getVacationAllowance() {
		return vacationAllowance;
	}

	/**
	 * @param vacationAllowance the vacationAllowance to set
	 */
	public void setVacationAllowance(Integer vacationAllowance) {
		this.vacationAllowance = vacationAllowance;
	}

	/**
	 * @return the vacationTaken
	 */
	public Integer getVacationTaken() {
		return vacationTaken;
	}

	/**
	 * @param vacationTaken the vacationTaken to set
	 */
	public void setVacationTaken(Integer vacationTaken) {
		this.vacationTaken = vacationTaken;
	}

	/**
	 * @return the sicknessTaken
	 */
	public String getSicknessTaken() {
		return sicknessTaken;
	}

	/**
	 * @param sicknessTaken the sicknessTaken to set
	 */
	public void setSicknessTaken(String sicknessTaken) {
		this.sicknessTaken = sicknessTaken;
	}

	/**
	 * @return the manager
	 */
	public User getManager() {
		return manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(User manager) {
		this.manager = manager;
	}

	/**
	 * @return the profilePicture
	 */
	public String getProfilePicture() {
		return profilePicture;
	}

	/**
	 * @param profilePicture the profilePicture to set
	 */
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	/**
	 * @return the roleGroups
	 */
	public List<RoleGroups> getRoleGroups() {
		return roleGroups;
	}

	/**
	 * @param roleGroups the roleGroups to set
	 */
	public void setRoleGroups(List<RoleGroups> roleGroups) {
		this.roleGroups = roleGroups;
	}
}
