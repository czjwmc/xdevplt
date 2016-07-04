package com.xj.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xj.dao.XdpGroupDocTypeDao;
import com.xj.model.XdpGroupDocType;
import com.xj.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class XdpGroupDocTypeManagerImplTest extends BaseManagerMockTestCase {
    private XdpGroupDocTypeManagerImpl manager = null;
    private XdpGroupDocTypeDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(XdpGroupDocTypeDao.class);
        manager = new XdpGroupDocTypeManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetXdpGroupDocType() {
        log.debug("testing get...");

        final Long id = 7L;
        final XdpGroupDocType xdpGroupDocType = new XdpGroupDocType();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(id)));
            will(returnValue(xdpGroupDocType));
        }});

        XdpGroupDocType result = manager.get(id);
        assertSame(xdpGroupDocType, result);
    }

    @Test
    public void testGetXdpGroupDocTypes() {
        log.debug("testing getAll...");

        final List xdpGroupDocTypes = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(xdpGroupDocTypes));
        }});

        List result = manager.getAll();
        assertSame(xdpGroupDocTypes, result);
    }

    @Test
    public void testSaveXdpGroupDocType() {
        log.debug("testing save...");

        final XdpGroupDocType xdpGroupDocType = new XdpGroupDocType();
        // enter all required fields
        xdpGroupDocType.setDocNum(1.9188373610329378E9L);
        xdpGroupDocType.setEnabled(Boolean.FALSE);
        xdpGroupDocType.setName("XuRjAoYtJtUoZyEsIuHiUaBuWnZvMtSrPoAeTmMnDaJhRcQoDg");
        xdpGroupDocType.setSortNo(1.1276433792294292E9L);
        xdpGroupDocType.setUpdateDate(new java.util.Date());
        xdpGroupDocType.setUpdateUser("VbCuEqJtNtWnHbEzQhGaPzIoOoDuLhIvAzXmWxWxVhZtUjRlMzUtXwUqCiUgKzSoXzGpWcLoJsHjJrTmYoGdSlDxXcCqDwNpDrXlIkVoHoZxPdYgUuEwTbKaNfMlYcKvMzCvJmMtBeJeWzTrSyEvXrPmApPeTnDrXgFjKbVtKcWpGxHcRyPgYpYmFdVpUhAzKuQzNiEwQuTtMoWkXzQkRvDaRlZlUjZcVvXfCtLgZeTxAnGdUpPiChWbRrYbGsP");
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(xdpGroupDocType)));
        }});

        manager.save(xdpGroupDocType);
    }

    @Test
    public void testRemoveXdpGroupDocType() {
        log.debug("testing remove...");

        final Long id = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});

        manager.remove(id);
    }
}