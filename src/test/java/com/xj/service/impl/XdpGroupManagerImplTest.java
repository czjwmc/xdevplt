package com.xj.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xj.dao.XdpGroupDao;
import com.xj.model.XdpGroup;
import com.xj.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class XdpGroupManagerImplTest extends BaseManagerMockTestCase {
    private XdpGroupManagerImpl manager = null;
    private XdpGroupDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(XdpGroupDao.class);
        manager = new XdpGroupManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetXdpGroup() {
        log.debug("testing get...");

        final Long id = 7L;
        final XdpGroup xdpGroup = new XdpGroup();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(id)));
            will(returnValue(xdpGroup));
        }});

        XdpGroup result = manager.get(id);
        assertSame(xdpGroup, result);
    }

    @Test
    public void testGetXdpGroups() {
        log.debug("testing getAll...");

        final List xdpGroups = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(xdpGroups));
        }});

        List result = manager.getAll();
        assertSame(xdpGroups, result);
    }

    @Test
    public void testSaveXdpGroup() {
        log.debug("testing save...");

        final XdpGroup xdpGroup = new XdpGroup();
        // enter all required fields
        xdpGroup.setEnabled(Boolean.FALSE);
        xdpGroup.setName("TcNdDfLfFqSlZeLcYdMrMzDrSvDhNtBtZbZvIyLzJeObRaQrVf");
        xdpGroup.setUpdateDate(new java.util.Date());
        xdpGroup.setUpdateUser("ZlJxLiXoHtHlMiOyLfOoQiJaSsDxKkNtOxRqSvMsBjMhMaKuDxZaVeSuNuYiUkEvLvNtHoXiHrMlKhQtOuRsVkGhXqLvTvXeYmGfJkHgMnDoHlYpNaEnDqXhLtGuYyKeAqHfQpKsZpRxRwWhRcTgTaSyWsNfKuGaNwDbMuQdMiBgVwVbXgKjInDxQbHiBsDkEcXwAjQdYzGjXgRdIgLvSjOmYoXrArNfJgXnVaUaQwYrRtMtMcJiJrEaXoYdJfM");
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(xdpGroup)));
        }});

        manager.save(xdpGroup);
    }

    @Test
    public void testRemoveXdpGroup() {
        log.debug("testing remove...");

        final Long id = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});

        manager.remove(id);
    }
}