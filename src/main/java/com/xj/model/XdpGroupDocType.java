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
 * This class represents the basic "XdpGroupDocType" object.
 *
 */
@Entity

@XmlRootElement
public class XdpGroupDocType extends BaseObject implements Serializable {

	private static final long serialVersionUID = 1832160857234426308L;
	private Long id;
	private Integer version;
	private XdpGroup xdpGroup;               // required
    private String name;                    // required, length:50, unique
    private XdpProcess xdpProcess;          // required
    private Long sortNo;                   // length:2
    private String ruleName;               // length:50
    private String ruleFile;               // length:500
    private String ruleFilePath;
    private Long maxMgr;
    private String templateName;           // length:50
    private String templateFile;           // length:500
    private String templateFilePath;
    private Long docNum;                   // length:4
    private String description;             // length:100
    private boolean enabled;                // default:true
    private User updateUser;
    private Date updateDate;


    /**
     * Default constructor - creates a new instance with no values set.
     */
    public XdpGroupDocType() {
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
     * @return the xdpGroup
     */
    @ManyToOne(optional=false)
    @IndexedEmbedded(depth = 1, prefix = "xdpGroup_")
    public XdpGroup getXdpGroup() {
        return xdpGroup;
    }

    /**
     * @param xdpGroup the xdpGroup to set
     */
    public void setXdpGroup(XdpGroup xdpGroup) {
        this.xdpGroup = xdpGroup;
    }


    /**
	 * @return the name
	 */
    @Column(nullable = false, length = 50)
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
	 * @return the maxMgr
	 */
    @Column(length = 50)
    @Field
    public Long getMaxMgr() {
        return maxMgr;
    }

    /**
	 * @param maxMgr the maxMgr to set
	 */
	public void setMaxMgr(Long maxMgr) {
        this.maxMgr = maxMgr;
    }
	
	
	 /**
     * @return the xdpProcess
     */
    @ManyToOne(optional=false)
    @IndexedEmbedded(depth = 1, prefix = "xdpProcess_")
    public XdpProcess getXdpProcess() {
        return xdpProcess;
    }

    /**
     * @param xdpProcess the xdpProcess to set
     */
    public void setXdpProcess(XdpProcess xdpProcess) {
        this.xdpProcess = xdpProcess;
    }
    
    /**
	 * @return the sortNo
	 */
    @Column(length = 2,nullable = false)
    @Field
    public Long getSortNo() {
        return sortNo;
    }

    /**
	 * @param sortNo the sortNo to set
	 */
    public void setSortNo(Long sortNo) {
        this.sortNo = sortNo;
    }
    
    /**
   	 * @return the ruleName
   	 */
    @Column(length = 50)
    @Field
    public String getRuleName() {
            return ruleName;
        }

       /**
   	 * @param ruleName the ruleName to set
   	 */
    public void setRuleName(String ruleName) {
           this.ruleName = ruleName;
       }

    /**
   	 * @return the ruleFile
   	 */
    @Column(length = 500)
    @Field
    public String getRuleFile() {
            return ruleFile;
        }

       /**
   	 * @param ruleFile the ruleFile to set
   	 */
    public void setRuleFile(String ruleFile) {
           this.ruleFile = ruleFile;
       }
    
    /**
   	 * @return the ruleFilePath
   	 */
    @Column(length = 500)
    @Field
    public String getRuleFilePath() {
            return ruleFilePath;
        }

       /**
   	 * @param ruleFile the ruleFile to set
   	 */
    public void setRuleFilePath(String ruleFilePath) {
           this.ruleFilePath = ruleFilePath;
       }

    /**
   	 * @return the ruleFilePath
   	 */
    @Column(length = 500)
    @Field
    public String getTemplateFilePath() {
            return templateFilePath;
        }

       /**
   	 * @param ruleFile the ruleFile to set
   	 */
    public void setTemplateFilePath(String templateFilePath) {
           this.templateFilePath = templateFilePath;
       }
    
    
    /**
   	 * @return the templateName
   	 */
    @Column(length = 50)
    @Field
    public String getTemplateName() {
            return templateName;
        }

       /**
   	 * @param templateName the templateName to set
   	 */
    public void setTemplateName(String templateName) {
           this.templateName = templateName;
       }
    
    /**
   	 * @return the templateFile
   	 */
    @Column(length = 500)
    @Field
    public String getTemplateFile() {
            return templateFile;
        }

       /**
   	 * @param templateFile the templateFile to set
   	 */
    public void setTemplateFile(String templateFile) {
           this.templateFile = templateFile;
       }
    
    /**
	 * @return the docNum
	 */
    @Column(length = 4,nullable = false)
    @Field
    public Long getDocNum() {
        return docNum;
    }

    /**
	 * @param docNum the docNum to set
	 */
    public void setDocNum(Long docNum) {
        this.docNum = docNum;
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
        if (!(o instanceof XdpGroupDocType)) {
            return false;
        }
        final XdpGroupDocType object = (XdpGroupDocType) o;
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
