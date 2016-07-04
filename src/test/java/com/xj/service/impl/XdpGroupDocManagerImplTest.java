package com.xj.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xj.dao.XdpGroupDocDao;
import com.xj.model.XdpGroupDoc;
import com.xj.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class XdpGroupDocManagerImplTest extends BaseManagerMockTestCase {
    private XdpGroupDocManagerImpl manager = null;
    private XdpGroupDocDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(XdpGroupDocDao.class);
        manager = new XdpGroupDocManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetXdpGroupDoc() {
        log.debug("testing get...");

        final Long id = 7L;
        final XdpGroupDoc xdpGroupDoc = new XdpGroupDoc();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(id)));
            will(returnValue(xdpGroupDoc));
        }});

        XdpGroupDoc result = manager.get(id);
        assertSame(xdpGroupDoc, result);
    }

    @Test
    public void testGetXdpGroupDocs() {
        log.debug("testing getAll...");

        final List xdpGroupDocs = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(xdpGroupDocs));
        }});

        List result = manager.getAll();
        assertSame(xdpGroupDocs, result);
    }

    @Test
    public void testSaveXdpGroupDoc() {
        log.debug("testing save...");

        final XdpGroupDoc xdpGroupDoc = new XdpGroupDoc();
        // enter all required fields
        xdpGroupDoc.setCheckDate(new java.util.Date());
        xdpGroupDoc.setCheckUser("PtXwYwVqOzYiYtLlWoSnBoOwJbGnJaMaOyDlEdFjTnDnRtAkIhBqTrZxQwVuZzPqZzZgIxPsBvEjRfWkZeDbZrAvLuUaItPlRhRlTuTlKfNzOwRwHcTzTbVjMxCyUlXiOuVuJfNaFnIdNhXfEmTcYlNeStCeFdSwUvApSzZfOvFpTcGhUxBdCqZfMtXgGvYlUkIgGwTqUrDsZkAuZyVzGbPwKcVyInQhTrEkBlJzAgPmNoEbOgAqJbOtYiKrFiF");
        xdpGroupDoc.setCreateDate(new java.util.Date());
        xdpGroupDoc.setCreateUser("YdSkJkIdYiWaMeCjEoAcZmUcLvVeHzLhVwLwKdMhXvZnHxVfFsNbBqHkMzBhBuPsHgZvXsAbQiAvGdOzVhBrNsPeArHuZfJoRnAwFbVhCwWiEaMiXdXyGtJcCvNqIvMxUnOdZbBgYwFtOhKnUbWxIgRbSpTrExHgMnBoVzDqTfOqArUqJdSqHxYwShGxRlTqRlLoApVwVwFkSsGfUtGiMsGdKlIdFkJxIjVyRtUnHoLkNnIfDnGdWxYlYcFsTgR");
        xdpGroupDoc.setEnabled(Boolean.FALSE);
        xdpGroupDoc.setMgrNo("AdAzYaHu");
        xdpGroupDoc.setName("FcShNyWkVmPwNtPgSgRhQyUpWyWqQxBiRrQcSnSuCeGjOhNjYd");
        xdpGroupDoc.setUpdateDate(new java.util.Date());
        xdpGroupDoc.setUpdateUser("KnWtYqFtElPrGyWoGtZoRmKkPtBqTuZrWbUyInZxMsSiZkQjOyQyCmFzSdZuCtVpBeFnMxPkEwHqEpJcSsQfBcJkWyImOzXmDoEmXtUsOsOvLeGnHcJbUzFzEoElWwVoDpMwTfLnXnBlSaGzSnJxIpJpFgRsOoDfIdExJuAaZmUhMeXhZuVsLxJiFfToFoTmIkIpSmWgOqDwWaXaTtUgQmBpIyXbUnZaTbDnBpZtMpIxRsHvMcWiTmOjCvAsIeN");
        xdpGroupDoc.setVer("CfWeTbJwOt");
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(xdpGroupDoc)));
        }});

        manager.save(xdpGroupDoc);
    }

    @Test
    public void testRemoveXdpGroupDoc() {
        log.debug("testing remove...");

        final Long id = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});

        manager.remove(id);
    }
}