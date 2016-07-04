package com.xj.dao.hibernate;

import com.xj.model.XdpDocType;
import com.xj.dao.XdpDocTypeDao;
import com.xj.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("xdpDocTypeDao")
public class XdpDocTypeDaoHibernate extends GenericDaoHibernate<XdpDocType, Long> implements XdpDocTypeDao {

    public XdpDocTypeDaoHibernate() {
        super(XdpDocType.class);
    }
}
