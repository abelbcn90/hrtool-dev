package com.wedonegood.employee.rest.v1.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.wedonegood.common.user.rest.dto.UserDto;
import com.wedonegood.employee.api.model.entity.Employee;
import com.wedonegood.groups.rest.v1.dto.GroupDto;
import com.wedonegood.roles.api.model.entity.Role;
import com.wedonegood.roles.rest.v1.dto.RoleDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class EmployeeDto {
	
    @ApiModelProperty(value = "Employee id", allowEmptyValue = true, example = "432", position = 1, notes = "empty to create new employee")
    private Long employeeId;
    
    @ApiModelProperty(value = "Employee user", position = 2)
    private UserDto user;
    
    @ApiModelProperty(value = "Employee number", position = 3)
    private String number;
    
    @ApiModelProperty(value = "Employee nin", position = 4)
    private String nin;
    
    @ApiModelProperty(value = "Employee address", position = 5)
    private String address;
    
    @ApiModelProperty(value = "Employee country", position = 6)
    private String country;
    
    @ApiModelProperty(value = "Employee city", position = 7)
    private String city;
    
    @ApiModelProperty(value = "Employee postalCode", position = 8)
    private String postalCode;
    
    @ApiModelProperty(value = "Employee birthday", position = 9)
    private LocalDate birthday;
    
    @ApiModelProperty(value = "Employee passportScan", position = 10)
    private String passportScan;
    
    @ApiModelProperty(value = "Employee permitId", position = 11)
    private Long permitId;
    
    @ApiModelProperty(value = "Employee contratId", position = 12)
    private Long contractId;
    
    @ApiModelProperty(value = "Employee jobPosition", position = 13)
    private String jobPosition;
    
    @ApiModelProperty(value = "Employee billingId", position = 14)
    private Long billingId;
    
    @ApiModelProperty(value = "Employee hireDate", position = 15)
    private LocalDate hireDate;
    
    @ApiModelProperty(value = "Employee salary", position = 16)
    private Integer salary;
    
    @ApiModelProperty(value = "Employee groupId", position = 17)
    private GroupDto group;
    
    @ApiModelProperty(value = "Employee vacationAllowance", position = 18)
    private Integer vacationAllowance;
    
    @ApiModelProperty(value = "Employee vacationTaken", position = 19)
    private Integer vacationTaken;
    
    @ApiModelProperty(value = "Employee sicknessTaken", position = 20)
    private String sicknessTaken;
    
    @ApiModelProperty(value = "Employee manager", position = 21)
    private Long managerId;
    
    @ApiModelProperty(value = "Employee profilePicture", position = 22)
    private String profilePicture;
    
    @ApiModelProperty(value = "Employee roles", position = 23)
    private List<RoleDto> roles;
    
    @ApiModelProperty(readOnly = true, value = "Active flag", position = 100)
    private boolean active;
    
    @ApiModelProperty(readOnly = true, value = "Created by user", example = "John Doe", position = 101)
    private String createdBy;
    
    @ApiModelProperty(readOnly = true, value = "Creation date", position = 102)
    private LocalDateTime createdDate;
    
    @ApiModelProperty(readOnly = true, value = "Modified by user", example = "Jane Doe", position = 103)
    private String modifiedBy;
    
    @ApiModelProperty(readOnly = true, value = "Modification date", position = 104)
    private LocalDateTime modifiedDate;

    /*
     * Constructors
     */
    
    public EmployeeDto() {
    	this.user = new UserDto();
    }

    public EmployeeDto(final Employee employee) {
    	this.employeeId = employee.getId();
    	this.user = new UserDto(employee.getUser());
    	this.number = employee.getNumber();
    	this.nin = employee.getNin();
    	this.address = employee.getAddress();
    	this.country = employee.getCountry();
    	this.city = employee.getCity();
    	this.postalCode = employee.getPostalCode();
    	this.birthday = employee.getBirthday();
    	this.passportScan = employee.getPassportScan();
    	this.permitId = employee.getPermit().getId();
    	this.contractId = employee.getContract().getId();
    	this.jobPosition = employee.getJobPosition();
    	this.billingId = employee.getBilling().getId();
    	this.hireDate = employee.getHireDate();
    	this.salary = employee.getSalary();
    	this.group = new GroupDto(employee.getGroup());
    	this.vacationAllowance = employee.getVacationAllowance();
    	this.vacationTaken = employee.getVacationTaken();
    	this.sicknessTaken = employee.getSicknessTaken();
    	this.managerId = employee.getManager().getUserId();
    	this.profilePicture = employee.getProfilePicture();
    	
//    	this.roleGroups = new ArrayList<RoleGroupsDto>();
//    	
//    	if (null != employee.getRoleGroups()) {
//	    	for (final RoleGroups rg : employee.getRoleGroups()) {
//	    		this.roleGroups.add(new RoleGroupsDto(rg.getRole(), rg.getGroups()));
//	    	}
//    	}
    	
    	this.roles = new ArrayList<RoleDto>();
    	
    	if (null != employee.getRoles()) {
    		for (final Role role : employee.getRoles()) {
    			this.roles.add(new RoleDto(role));
    		}
    	}
    	
        this.active = employee.isActive();
        this.createdDate = employee.getCreatedDate();
        this.createdBy = employee.getCreatedBy();
        this.modifiedDate = employee.getModifiedDate();
        this.modifiedBy = employee.getModifiedBy();
    }
    
    /*
     * Getters & Setters
     */

	/**
	 * @return the employeeId
	 */
	public Long getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the user
	 */
	public UserDto getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserDto user) {
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
	 * @return the permitId
	 */
	public Long getPermitId() {
		return permitId;
	}

	/**
	 * @param permitId the permitId to set
	 */
	public void setPermitId(Long permitId) {
		this.permitId = permitId;
	}

	/**
	 * @return the contractId
	 */
	public Long getContractId() {
		return contractId;
	}

	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(Long contractId) {
		this.contractId = contractId;
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
	 * @return the billingId
	 */
	public Long getBillingId() {
		return billingId;
	}

	/**
	 * @param billingId the billingId to set
	 */
	public void setBillingId(Long billingId) {
		this.billingId = billingId;
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
	public GroupDto getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(GroupDto group) {
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
	 * @return the managerId
	 */
	public Long getManagerId() {
		return managerId;
	}

	/**
	 * @param managerId the managerId to set
	 */
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
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
	 * @return the roles
	 */
	public List<RoleDto> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
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

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the modifiedDate
	 */
	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
