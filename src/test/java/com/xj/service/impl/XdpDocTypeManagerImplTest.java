package com.xj.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xj.dao.XdpDocTypeDao;
import com.xj.model.XdpDocType;
import com.xj.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class XdpDocTypeManagerImplTest extends BaseManagerMockTestCase {
    private XdpDocTypeManagerImpl manager = null;
    private XdpDocTypeDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(XdpDocTypeDao.class);
        manager = new XdpDocTypeManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetXdpDocType() {
        log.debug("testing get...");

        final Long id = 7L;
        final XdpDocType XdpDocType = new XdpDocType();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(id)));
            will(returnValue(XdpDocType));
        }});

        XdpDocType result = manager.get(id);
        assertSame(XdpDocType, result);
    }

    @Test
    public void testGetXdpDocTypes() {
        log.debug("testing getAll...");

        final List XdpDocTypes = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(XdpDocTypes));
        }});

        List result = manager.getAll();
        assertSame(XdpDocTypes, result);
    }

    @Test
    public void testSaveXdpDocType() {
        log.debug("testing save...");

        final XdpDocType XdpDocType = new XdpDocType();
        // enter all required fields
        XdpDocType.setEnabled(Boolean.FALSE);
        XdpDocType.setName("UaXiWoBkKiTgSoBiSpUfQrVwGoZsLuAvBuBuRvPoZvFcQnJaKk");
        XdpDocType.setSortNo(7.736403703395766E8L);
        XdpDocType.setUpdateDate(new java.util.Date());
        XdpDocType.setUpdateUser("OjYzIhGjKxAeTgOtHbZoLdYwYrPrXhRaUpFsXvKxUiCyOpJbBjAhYgOuEtLtBxVhOhAnIpLyNcLvQsOdJnVeBsGmEvPqJgNjGzHrXcGwMfQhDqKmPcAiXaGzBaPeDwNgHeMaYpJfGoWpOlUaMsVkPkXdOaSbPoJfLsRxPxJcLjUtPpHsVxTlTlHjCyZtVbTjPqZkOcRcRgMwDaNiCwVoBzZfFeDxCaWqSgJpQoQxGhTjMgQmSoRcZiHtDtDdZiA");
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(XdpDocType)));
        }});

        manager.save(XdpDocType);
    }

    @Test
    public void testRemoveXdpDocType() {
        log.debug("testing remove...");

        final Long id = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});

        manager.remove(id);
    }
}