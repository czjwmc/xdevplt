package com.xj.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xj.dao.XdpSystemDao;
import com.xj.model.XdpSystem;
import com.xj.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class XdpSystemManagerImplTest extends BaseManagerMockTestCase {
    private XdpSystemManagerImpl manager = null;
    private XdpSystemDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(XdpSystemDao.class);
        manager = new XdpSystemManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetXdpSystem() {
        log.debug("testing get...");

        final Long id = 7L;
        final XdpSystem xdpSystem = new XdpSystem();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(id)));
            will(returnValue(xdpSystem));
        }});

        XdpSystem result = manager.get(id);
        assertSame(xdpSystem, result);
    }

    @Test
    public void testGetXdpSystems() {
        log.debug("testing getAll...");

        final List xdpSystems = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(xdpSystems));
        }});

        List result = manager.getAll();
        assertSame(xdpSystems, result);
    }

    @Test
    public void testSaveXdpSystem() {
        log.debug("testing save...");

        final XdpSystem xdpSystem = new XdpSystem();
        // enter all required fields
        xdpSystem.setFilePath("UfRdIuBxXwGkJcXuJoBvKbEkSpYjIfRlGiFoQvVzMbNiZiRoXgLhVtQbNfNtFoZxDwSsZvJgWeUgKpMmAiUvNyPiRvBaZcYzVoBc");
        xdpSystem.setName("ElYiGaLzQiDgDxEyRoUlCkBqVzIgFwMxJdCiErSrKeTiWpHwSx");
        xdpSystem.setUpdateDate(new java.util.Date());
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(xdpSystem)));
        }});

        manager.save(xdpSystem);
    }

    @Test
    public void testRemoveXdpSystem() {
        log.debug("testing remove...");

        final Long id = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});

        manager.remove(id);
    }
}