package com.xj.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.xj.dao.DemoDao;
import com.xj.model.Demo;

@Repository("demoDao")
public class DemoDaoHibernate extends GenericDaoHibernate<Demo, Long> implements DemoDao {

    public DemoDaoHibernate() {
        super(Demo.class);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Demo> getDemos() {
    	Session session = getSessionFactory().getCurrentSession();
    	return session.createQuery("from Demo a order by a.id desc)").list();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Demo> getDemosEnabled() {
    	Session session = getSessionFactory().getCurrentSession();
    	return session.createQuery("from Demo a where a.enabled=TRUE order by a.id desc").list();
    }
}
