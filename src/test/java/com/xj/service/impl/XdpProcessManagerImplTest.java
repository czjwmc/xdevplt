package com.xj.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xj.dao.XdpProcessDao;
import com.xj.model.XdpProcess;
import com.xj.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class XdpProcessManagerImplTest extends BaseManagerMockTestCase {
    private XdpProcessManagerImpl manager = null;
    private XdpProcessDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(XdpProcessDao.class);
        manager = new XdpProcessManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetXdpProcess() {
        log.debug("testing get...");

        final Long id = 7L;
        final XdpProcess xdpProcess = new XdpProcess();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(id)));
            will(returnValue(xdpProcess));
        }});

        XdpProcess result = manager.get(id);
        assertSame(xdpProcess, result);
    }

    @Test
    public void testGetXdpProcesses() {
        log.debug("testing getAll...");

        final List xdpProcesses = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(xdpProcesses));
        }});

        List result = manager.getAll();
        assertSame(xdpProcesses, result);
    }

    @Test
    public void testSaveXdpProcess() {
        log.debug("testing save...");

        final XdpProcess xdpProcess = new XdpProcess();
        // enter all required fields
        xdpProcess.setCode("Ou");
        xdpProcess.setEnabled(Boolean.FALSE);
        xdpProcess.setName("EmFyKsYvXqTkHyYdTqAlGhSnVxWgWeUlDuFiNgDvYeAxRqOvOc");
        xdpProcess.setSortNo(6.271870554231561E7L);
        xdpProcess.setUpdateDate(new java.util.Date());
        xdpProcess.setUpdateUser("JmYkXuIyHtNkJkVlCpBvKnLmNmDlTwJnKcPzXbOaPlGwKqIqQiNuQlStAwEzGjXaSjKtTqIlMlZaGlLeMvOmPgEnGdPhAgUjHoClQqJgVtEpNxFxXnPkKhDlCuDdBqBeDwTuTfQjAgIcRhWqGxLlZyMpOkJaTqGxFlYcJdDzHgVhGxKdDyNsWdJvEcGkLcFvCxCcLfMhBoOgMkUmZkIpIgJoOvGhZhCjOsXcFmLjZoMlAdUjXvBxRvPyMoMeReW");
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(xdpProcess)));
        }});

        manager.save(xdpProcess);
    }

    @Test
    public void testRemoveXdpProcess() {
        log.debug("testing remove...");

        final Long id = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});

        manager.remove(id);
    }
}