package com.xj.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xj.dao.DemoDao;
import com.xj.model.Demo;
import com.xj.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class DemoManagerImplTest extends BaseManagerMockTestCase {
    private DemoManagerImpl manager = null;
    private DemoDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(DemoDao.class);
        manager = new DemoManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetDemo() {
        log.debug("testing get...");

        final Long id = 7L;
        final Demo demo = new Demo();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(id)));
            will(returnValue(demo));
        }});

        Demo result = manager.get(id);
        assertSame(demo, result);
    }

    @Test
    public void testGetDemoes() {
        log.debug("testing getAll...");

        final List demoes = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(demoes));
        }});

        List result = manager.getAll();
        assertSame(demoes, result);
    }

    @Test
    public void testSaveDemo() {
        log.debug("testing save...");

        final Demo demo = new Demo();
        // enter all required fields
        demo.setCreateDate(new java.util.Date());
        demo.setEnabled(Boolean.FALSE);
        demo.setName("UyKqMbWvKeMhXuLvIvByBjShSjZdDsVqSvPeLsCwYcUjYaShZm");
        demo.setUpdateDate(new java.util.Date());
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(demo)));
        }});

        manager.save(demo);
    }

    @Test
    public void testRemoveDemo() {
        log.debug("testing remove...");

        final Long id = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});

        manager.remove(id);
    }
}