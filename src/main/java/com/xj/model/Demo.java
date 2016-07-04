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
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 * This class represents the basic "Demo" object that allows for demo and test.
 *
 * @author <a href="mailto:wx@xujis.com">wx</a>
 */
@Entity
@Indexed
@XmlRootElement
public class Demo extends BaseObject implements Serializable {
	private static final long serialVersionUID = 496753847745097527L;
	private Long id;
    private Integer version;
    private String name;                    // required, unique, length:50
    private Double price;                   // Currency format, Min:-999,999.99, Max:999,999.99
    private Long qty;                    	// Number format, Min:-9,999, Max:9,999
    private Double total;                   // Currency format, Min:-9,999,999,999,999.99, Max:9,999,999,999,999.99
    private Double discountRate;     		// discount format, Min:0.000(0.0%), Max:1.000(100.0%)
    private Double percent;                 // Percent format, Min:-99.9999(-9999.99%), Max:99.9999(9999.99%)
    private Date date;                      // Date format
    private Date datetime;                  // Date time format
    private String description;             // Text area, length:200
    private boolean enabled;                // Check box, default:true
    private User createUser;
    private Date createDate;
    private User updateUser;
    private Date updateDate;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Demo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
     * @return the price
     */
    @Field
    public Double getPrice() {
        return this.price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the quantity
     */
    @Field
    public Long getQty() {
        return this.qty;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQty(Long qty) {
        this.qty = qty;
    }

    /**
     * @return the total
     */
    @Field
    public Double getTotal() {
        return this.total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * @return the discountRate
     */
    @Field
    public Double getDiscountRate() {
        return this.discountRate;
    }

    /**
     * @param discountRate the discountRate to set
     */
    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    /**
     * @return the percent
     */
    @Field
    public Double getPercent() {
        return this.percent;
    }

    /**
     * @param percent the percent to set
     */
    public void setPercent(Double percent) {
        this.percent = percent;
    }

    /**
     * @return the date
     */
    @Field
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the datetime
     */
    @Field
    public Date getDatetime() {
        return datetime;
    }

    /**
     * @param datetime the datetime to set
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
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
     * @return the createUser
     */
    @ManyToOne(optional=false)
    @IndexedEmbedded(depth = 1, prefix = "createUser_")
    public User getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser the createUser to set
     */
    public void setCreateUser(User createUser) {
        this.createUser = createUser;
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
        if (!(o instanceof Demo)) {
            return false;
        }
        final Demo object = (Demo) o;
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
