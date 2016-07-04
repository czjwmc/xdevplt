package com.xj.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.xj.dao.XdpGroupDocTypeDao;
import com.xj.model.XdpGroupDocType;

@Repository("xdpGroupDocTypeDao")
public class XdpGroupDocTypeDaoHibernate extends GenericDaoHibernate<XdpGroupDocType, Long> implements XdpGroupDocTypeDao {

    public XdpGroupDocTypeDaoHibernate() {
        super(XdpGroupDocType.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<XdpGroupDocType> getXdpGroupDocTypes() {
    	Session session = getSessionFactory().getCurrentSession();
        return session.createQuery("from XdpGroupDocType a order by a.id desc").list();
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<XdpGroupDocType> getXdpGroupDocTypes(String xdpGroupDocTypeId) {
    	Session session = getSessionFactory().getCurrentSession();
    	String strSQLFrom = "select a from XdpGroupDocType a";
    	String strSQLWhere = " where a.xdpGroup.id = '" + xdpGroupDocTypeId + "' ";
    	String strSQLOrderBy = " order by a.id desc";
    	String strSQL = strSQLFrom + strSQLWhere + strSQLOrderBy;
        return session.createQuery(strSQL).list();
    }
    
}
