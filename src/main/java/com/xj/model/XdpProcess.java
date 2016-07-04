package com.xj.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 * This class represents the basic "XdpProcess" object.
 *
 */
@Entity

@XmlRootElement
public class XdpProcess extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7430789907014903743L;
	private Long id;
	private Integer version;
    private String name;                    // required, length:50, unique
    private String code;                    // required,length:2,unique
    private Long sortNo;                   // length:100
    private String description;             // length:100
    private boolean enabled;                // default:true
    private User updateUser;
    private Date updateDate;


    /**
     * Default constructor - creates a new instance with no values set.
     */
    public XdpProcess() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DocumentId
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
    @Column(nullable = false, length = 2, unique = true)
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
    @Column(length = 2,nullable = false)
    @Field
    public Long getSortNo() {
        return sortNo;
    }

    /**
	 * @param email the email to set
	 */
    public void setSortNo(Long sortNo) {
        this.sortNo = sortNo;
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
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof XdpProcess)) {
            return false;
        }
        final XdpProcess object = (XdpProcess) o;
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
}
