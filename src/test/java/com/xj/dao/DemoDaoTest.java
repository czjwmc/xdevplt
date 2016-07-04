package com.xj.dao;

import com.xj.dao.BaseDaoTestCase;
import com.xj.model.Demo;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.test.annotation.ExpectedException;

import java.util.List;

public class DemoDaoTest extends BaseDaoTestCase {
    @Autowired
    private DemoDao demoDao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveDemo() {
        Demo demo = new Demo();

        // enter all required fields
        demo.setCreateDate(new java.util.Date());
        demo.setEnabled(Boolean.FALSE);
        demo.setName("ChAkInLjQdZhDdJiVxZmHhPiJxMcUxXbUxJhUkSvLrVxTrGmNv");
        demo.setUpdateDate(new java.util.Date());

        log.debug("adding demo...");
        demo = demoDao.save(demo);

        demo = demoDao.get(demo.getId());

        assertNotNull(demo.getId());

        log.debug("removing demo...");

        demoDao.remove(demo.getId());

        // should throw DataAccessException 
        demoDao.get(demo.getId());
    }
}