package com.xj.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.security.core.GrantedAuthority;

/**
 * This class represents the basic "XdpGroup" object.
 *
 */
@Entity
@Table(name = "xdpGroup")
@NamedQueries({
        @NamedQuery(
                name = "findXdpGroupByName",
                query = "select r from XdpGroup r where r.name = :name "
        )
})

public class XdpGroup extends BaseObject implements Serializable,GrantedAuthority {
	private static final long serialVersionUID = 706669431272318687L;
	private Long id;
	private Integer version;
    private String name;                    // required, length:50, unique
    private String code;                    // required,length:3,unique
    private String email;                   // length:100
    private Long maxMgrNo;                  // length:5,required
    private String description;             // length:100
    private boolean enabled;                // default:true
    private User updateUser;
    private Date updateDate;

    private Set<User> users = new HashSet<User>();

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public XdpGroup() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
	 * @return the name
	 */
    @Column(nullable = false, length = 50, unique = true)
    @Field
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
	 * @return the name
	 */
    @Column(nullable = false, length = 3, unique = true)
    @Field
    public String getCode() {
        return code;
    }

    /**
	 * @param name the name to set
	 */
	public void setCode(String code) {
        this.code = code;
    }

    /**
	 * @return the email
	 */
    @Column(length = 100)
    @Field
    public String getEmail() {
        return email;
    }

    /**
	 * @param email the email to set
	 */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
	 * @return the email
	 */
    @Column(length = 5,nullable = false,unique = true)
    @Field
    public Long getMaxMgrNo() {
        return maxMgrNo;
    }

    /**
	 * @param email the email to set
	 */
    public void setMaxMgrNo(Long maxMgrNo) {
        this.maxMgrNo = maxMgrNo;
    }

	/**
	 * @return the description
	 */
	@Column(length = 200)
    public String getDescription() {
        return this.description;
    }

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
        this.description = description;
	}

	/**
	 * @return the enabled
	 */
	@Field
    public boolean isEnabled() {
        return enabled;
    }

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the updateUser
     */
    @ManyToOne(optional=false)
    @IndexedEmbedded(depth = 1, prefix = "updateUser_")
    public User getUpdateUser() {
        return updateUser;
    }

    /**
     * @param updateUser the updateUser to set
     */
    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

	/**
	 * @return the updateDate
	 */
	@Column(nullable = false)
	@Field
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

    /**
	 * @return the users
	 */
	@ManyToMany(mappedBy="xdpGroups")
	//@ContainedIn
	public Set<User> getUsers() {
        return users;
    }

    /**
	 * @param users the users to set
	 */
	public void setUsers(Set<User> users) {
        this.users = users;
    }

	/**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof XdpGroup)) {
            return false;
        }
        final XdpGroup object = (XdpGroup) o;
        return !(id != null ? !id.equals(object.id) : object.id != null);

    }

	/**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(this.id)
                .toString();
    }

    @Transient
    public String getAuthority() {
        return getName();
    }
}
