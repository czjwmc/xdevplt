package com.xj.dao.hibernate;

import com.xj.model.XdpSystem;
import com.xj.dao.XdpSystemDao;
import com.xj.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("xdpSystemDao")
public class XdpSystemDaoHibernate extends GenericDaoHibernate<XdpSystem, Long> implements XdpSystemDao {

    public XdpSystemDaoHibernate() {
        super(XdpSystem.class);
    }
}
