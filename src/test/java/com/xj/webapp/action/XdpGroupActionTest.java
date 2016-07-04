package com.xj.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import com.xj.service.XdpGroupManager;
import com.xj.model.XdpGroup;
import com.xj.webapp.action.BaseActionTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class XdpGroupActionTest extends BaseActionTestCase {
    private XdpGroupAction action;

    @Before
    public void onSetUp() {
        super.onSetUp();

        action = new XdpGroupAction();
        XdpGroupManager xdpGroupManager = (XdpGroupManager) applicationContext.getBean("xdpGroupManager");
        action.setXdpGroupManager(xdpGroupManager);

        // add a test xdpGroup to the database
        XdpGroup xdpGroup = new XdpGroup();

        // enter all required fields
        xdpGroup.setEnabled(Boolean.FALSE);
        xdpGroup.setName("" + Math.random());
        xdpGroup.setUpdateDate(new java.util.Date());
        xdpGroup.setUpdateUser("LmMoBqPhHjCpZfFiCnIuSnKzUzWgTbBuPjIlHjAfIcKpQeFvWxIrOcRzUoQjBdAiFcItKiUzTtEqYyDfUiEvHgXvXcOwMhRpNaPyFwJxMgUnRjZmRuGtCyWpPbLlEmYrNxFnIcDiOvIzAmBbIzRoQxIvIoTtWxJeGkDhRxLnSoGzSsLdCuOjLtLuSwVxHeYuJzBrMfTjRfReFbVfNjSoBjBzRnUyKxDsRsWkCnLuKgOmNwTtEcMuAoKbTgIsMiE");

        xdpGroupManager.save(xdpGroup);
    }

    @Test
    public void testGetAllXdpGroups() throws Exception {
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getXdpGroups().size() >= 1);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        XdpGroupManager xdpGroupManager = (XdpGroupManager) applicationContext.getBean("xdpGroupManager");
        xdpGroupManager.reindex();

        action.setQ("*");
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertEquals(4, action.getXdpGroups().size());
    }

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(-1L);
        assertNull(action.getXdpGroup());
        assertEquals("success", action.edit());
        assertNotNull(action.getXdpGroup());
        assertFalse(action.hasActionErrors());
    }

    @Test
    public void testSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setId(-1L);
        assertEquals("success", action.edit());
        assertNotNull(action.getXdpGroup());

        XdpGroup xdpGroup = action.getXdpGroup();
        // update required fields
        xdpGroup.setEnabled(Boolean.FALSE);
        xdpGroup.setName("QnPyGlSnUpFiDbMwCtZcYnZfRcSfWsGjSrDjJnAoRrDxWmLsWp");
        xdpGroup.setUpdateDate(new java.util.Date());
        xdpGroup.setUpdateUser("JtEvIqBeYjHtHbAdWjOeIfHsPmHjXkHgRrVjPxEaJyOyJnDsRdXeSrUyMdQsYzYjOrTwVwVuAbVhKhZeQyQoGnQcMiVsSnVvJmPrAcQlXfYiDnXjXjKzVtPkQrJaHzNfPcBqHwEnSlQhRxByDuNwVcGtAeDxFaPgWnEvDtRpTnEuNoYxSfObDpRrYmUcVzOaDoJvHgTpDkFjRgLtWiJkLnGjDmYnKoPvTaGcLtLmDwUmJcQsErFeWdAeLeGiAdW");

        action.setXdpGroup(xdpGroup);

        assertEquals("input", action.save());
        assertFalse(action.hasActionErrors());
        assertFalse(action.hasFieldErrors());
        assertNotNull(request.getSession().getAttribute("messages"));
    }

    @Test
    public void testRemove() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setDelete("");
        XdpGroup xdpGroup = new XdpGroup();
        xdpGroup.setId(-2L);
        action.setXdpGroup(xdpGroup);
        assertEquals("success", action.delete());
        assertNotNull(request.getSession().getAttribute("messages"));
    }
}