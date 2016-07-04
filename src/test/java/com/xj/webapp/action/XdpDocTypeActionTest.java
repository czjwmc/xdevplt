package com.xj.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import com.xj.service.XdpDocTypeManager;
import com.xj.model.XdpDocType;
import com.xj.webapp.action.BaseActionTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class XdpDocTypeActionTest extends BaseActionTestCase {
    private XdpDocTypeAction action;

    @Before
    public void onSetUp() {
        super.onSetUp();

        action = new XdpDocTypeAction();
        XdpDocTypeManager XdpDocTypeManager = (XdpDocTypeManager) applicationContext.getBean("XdpDocTypeManager");
        action.setXdpDocTypeManager(XdpDocTypeManager);

        // add a test XdpDocType to the database
        XdpDocType XdpDocType = new XdpDocType();

        // enter all required fields
        XdpDocType.setEnabled(Boolean.FALSE);
        XdpDocType.setName("" + Math.random());
        XdpDocType.setSortNo(1.8825326282596855E9L);
        XdpDocType.setUpdateDate(new java.util.Date());
        XdpDocType.setUpdateUser("XlPkWmOwVgPcAqExEiKtCwLqSqSrFpRhAdIkPfIgMiAaVeZwVkCjHlLxLrInPxNbFdPfCfDzUyAqVgSeJxHkHqRsAkKaZtYeYcSoEcLrPwVkCxEyGyWoRqDjUjLfZlRuDyWiDyEvOmNlHbGaVbBiVaXvSoYwHnTkRhRiAvIdEoTrLhXnWyWgKeNcLcEoUnQoZjKkFuOlDwQxCpOmMhPfVlAhUhHySaUwCvUzYrGpSoGkMsLbDxOxDdYmGxUnZwB");

        XdpDocTypeManager.save(XdpDocType);
    }

    @Test
    public void testGetAllXdpDocTypes() throws Exception {
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getXdpDocTypes().size() >= 1);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        XdpDocTypeManager XdpDocTypeManager = (XdpDocTypeManager) applicationContext.getBean("XdpDocTypeManager");
        XdpDocTypeManager.reindex();

        action.setQ("*");
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertEquals(4, action.getXdpDocTypes().size());
    }

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(-1L);
        assertNull(action.getXdpDocType());
        assertEquals("success", action.edit());
        assertNotNull(action.getXdpDocType());
        assertFalse(action.hasActionErrors());
    }

    @Test
    public void testSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setId(-1L);
        assertEquals("success", action.edit());
        assertNotNull(action.getXdpDocType());

        XdpDocType XdpDocType = action.getXdpDocType();
        // update required fields
        XdpDocType.setEnabled(Boolean.FALSE);
        XdpDocType.setName("AsGyUiSsMqIyPbFpPxGmRfEuFxMcNvQvGsSoPtVtXyAfElKwJe");
        XdpDocType.setSortNo(6.44363510148496E7L);
        XdpDocType.setUpdateDate(new java.util.Date());
        XdpDocType.setUpdateUser("LpWoOyPdOvVoFzYhKqAsHuIpRdFdDdIeTuToPaHrLmAiHqWkZtLsLqOdKmHuWjYwQyZtUsPpJhDmXzCaMjMeQhLfHwOxBkWdIdGmVmUsCtOwPiPbBhFfGuNnBnOpByCjLvGqXsWuEaAkUiKiEuQeHuNtZqLpDzHhNhGmOkXqHkTdArSpJeLbGqUpKvNhSrCfWcYyQjUfMxBkXoWsGmApNqEeDtKeLqGfPxMaXlXfBeUlXtXhOgRzWkSqFfEjCpH");

        action.setXdpDocType(XdpDocType);

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
        XdpDocType XdpDocType = new XdpDocType();
        XdpDocType.setId(-2L);
        action.setXdpDocType(XdpDocType);
        assertEquals("success", action.delete());
        assertNotNull(request.getSession().getAttribute("messages"));
    }
}