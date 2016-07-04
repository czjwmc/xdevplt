package com.xj.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import com.xj.service.XdpGroupDocTypeManager;
import com.xj.model.XdpGroupDocType;
import com.xj.webapp.action.BaseActionTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class XdpGroupDocTypeActionTest extends BaseActionTestCase {
    private XdpGroupDocTypeAction action;

    @Before
    public void onSetUp() {
        super.onSetUp();

        action = new XdpGroupDocTypeAction();
        XdpGroupDocTypeManager xdpGroupDocTypeManager = (XdpGroupDocTypeManager) applicationContext.getBean("xdpGroupDocTypeManager");
        action.setXdpGroupDocTypeManager(xdpGroupDocTypeManager);

        // add a test xdpGroupDocType to the database
        XdpGroupDocType xdpGroupDocType = new XdpGroupDocType();

        // enter all required fields
        xdpGroupDocType.setDocNum(8.064687324901702E8L);
        xdpGroupDocType.setEnabled(Boolean.FALSE);
        xdpGroupDocType.setName("" + Math.random());
        xdpGroupDocType.setSortNo(4.317693120976509E8L);
        xdpGroupDocType.setUpdateDate(new java.util.Date());
        xdpGroupDocType.setUpdateUser("EhOwBqKjZzCxLqAePoVeRsFwWyTfJkRwThEbRyUkWkJeDlSxQzTkEhNiFwWcBvEjBrRsCySnBaDaOyVrSxKfWlLaYxNrTtSfUoMpUwQpGuXcIfDxAfRwXwGjEiMtVwXeAxXyGpNnOqJuBpCfHeOkIzNzDrBkDyYoJgFvEkOfXnKgNaNvXdXeWzZyPbQfPnIyMjBiAgAyOdUfKyRjStQdHeOzNuFfWxCzXvCpZdMpYmQiDdMxXfQxPoQdCiDgIcE");

        xdpGroupDocTypeManager.save(xdpGroupDocType);
    }

    @Test
    public void testGetAllXdpGroupDocTypes() throws Exception {
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getXdpGroupDocTypes().size() >= 1);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        XdpGroupDocTypeManager xdpGroupDocTypeManager = (XdpGroupDocTypeManager) applicationContext.getBean("xdpGroupDocTypeManager");
        xdpGroupDocTypeManager.reindex();

        action.setQ("*");
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertEquals(4, action.getXdpGroupDocTypes().size());
    }

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(-1L);
        assertNull(action.getXdpGroupDocType());
        assertEquals("success", action.edit());
        assertNotNull(action.getXdpGroupDocType());
        assertFalse(action.hasActionErrors());
    }

    @Test
    public void testSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setId(-1L);
        assertEquals("success", action.edit());
        assertNotNull(action.getXdpGroupDocType());

        XdpGroupDocType xdpGroupDocType = action.getXdpGroupDocType();
        // update required fields
        xdpGroupDocType.setDocNum(1.6143829309463556E9L);
        xdpGroupDocType.setEnabled(Boolean.FALSE);
        xdpGroupDocType.setName("YaYkTyIpUwQoWfYgCtSoWpSrWgJqJhWzBfHpBjJtJgAdYaZnQk");
        xdpGroupDocType.setSortNo(3.7969305084446475E7L);
        xdpGroupDocType.setUpdateDate(new java.util.Date());
        xdpGroupDocType.setUpdateUser("HzKbAhRqEtQyVhCfLaYqMdPeIoHkWmFjQcYxGgXcZkXnRbGaIbUvLnSiUlXcLtJeNpUsQdGgDjUsIeLzRwApYwFlCaAqWdWjFjTsLaByEeEwNzIpEsZeGbQnNxMvQfGuQzBrCcIrAbZwLbIvEcEkNuDbWgUyVzQqApSaMiAgAxUxRxVzLgXxNoAqYwBuIvBdPuAeOlBpMdFzLvRlYoZlDrYzWcGgAqAvTqLzFfMvBxEpWkTuHnNrMoWgYiObFfG");

        action.setXdpGroupDocType(xdpGroupDocType);

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
        XdpGroupDocType xdpGroupDocType = new XdpGroupDocType();
        xdpGroupDocType.setId(-2L);
        action.setXdpGroupDocType(xdpGroupDocType);
        assertEquals("success", action.delete());
        assertNotNull(request.getSession().getAttribute("messages"));
    }
}