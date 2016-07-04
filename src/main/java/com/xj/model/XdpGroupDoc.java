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
 * This class represents the basic "XdpGroup" object.
 *
 */
@Entity
//@Indexed
@XmlRootElement
public class XdpGroupDoc extends BaseObject implements Serializable {
	private static final long serialVersionUID = 9089490600881114850L;
	private Long id;
	private Integer version;
	private String name;                    // required, length:50, unique
    private String ver;                    // required, length:10, unique
    private XdpGroupDocType xdpGroupDocType;   // required
    private String mgrNo;                   // length:8,required
    private String docName;                  // length:100
    private String docFile;                 // length:500
    private String description;             // length:100
    private boolean enabled;                // default:true
    private String checkUser;
    private Date checkDate;
    private String createUser;
    private Date createDate;        
    private String updateUser;
    private Date updateDate;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public XdpGroupDoc() {
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
	 * @return the ver
	 */
    @Column(nullable = false, length = 10, unique = true)
    @Field
    public String getVer() {
        return ver;
    }

    /**
	 * @param ver the ver to set
	 */
	public void setVer(String ver) {
        this.ver = ver;
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
	  * @return the xdpGroupDocType
	*/
	@ManyToOne(optional=false)
	@IndexedEmbedded(depth = 1, prefix = "xdpGroupDocType_")
	public XdpGroupDocType getXdpGroupDocType() {
	    return xdpGroupDocType;
	}

	/**
	 * @param xdpGroupDocType the xdpGroupDocType to set
	 */
	public void setXdpGroupDocType(XdpGroupDocType xdpGroupDocType) {
	    this.xdpGroupDocType = xdpGroupDocType;
	}
	
	/**
	 * @return the mgrNo
	 */
    @Column(nullable = false, length = 8, unique = true)
    @Field
    public String getMgrNo() {
        return mgrNo;
    }

    /**
	 * @param mgrNo the mgrNo to set
	 */
	public void setMgrNo(String mgrNo) {
        this.mgrNo = mgrNo;
    }

    /**
	 * @return the docName
	 */
    @Column(length = 100)
    @Field
    public String getDocName() {
        return docName;
    }

    /**
	 * @param docName the docName to set
	 */
    public void setDocName(String docName) {
        this.docName = docName;
    }
    
    /**
	 * @return the docFile
	 */
    @Column(length = 500)
    @Field
    public String getDocFile() {
        return docFile;
    }

    /**
	 * @param docFile the docFile to set
	 */
    public void setDocFile(String docFile) {
        this.docFile = docFile;
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
	 * @return the checkUser
	 */
	@Column(nullable = false)
	@Field
	public String getCheckUser() {
		return checkUser;
	}

	/**
	 * @param checkUser the checkUser to set
	 */
	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}
	
	/**
	 * @return the checkDate
	 */
	@Column(nullable = false)
	@Field
	public Date getCheckDate() {
		return checkDate;
	}

	/**
	 * @param checkDate the checkDate to set
	 */
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	/**
	 * @return the createUser
	 */
	@Column(nullable = false)
	@Field
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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
	 * @return the updateUser
	 */
	@Column(nullable = false)
	@Field
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * @return the createDate
	 */
	@Column(nullable = false)
	@Field
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof XdpGroupDoc)) {
            return false;
        }
        final XdpGroupDoc object = (XdpGroupDoc) o;
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
