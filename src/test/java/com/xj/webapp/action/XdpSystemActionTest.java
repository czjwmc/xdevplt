package com.xj.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import com.xj.service.XdpSystemManager;
import com.xj.model.XdpSystem;
import com.xj.webapp.action.BaseActionTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class XdpSystemActionTest extends BaseActionTestCase {
    private XdpSystemAction action;

    @Before
    public void onSetUp() {
        super.onSetUp();

        action = new XdpSystemAction();
        XdpSystemManager xdpSystemManager = (XdpSystemManager) applicationContext.getBean("xdpSystemManager");
        action.setXdpSystemManager(xdpSystemManager);

        // add a test xdpSystem to the database
        XdpSystem xdpSystem = new XdpSystem();

        // enter all required fields
        xdpSystem.setFilePath("" + Math.random());
        xdpSystem.setName("" + Math.random());
        xdpSystem.setUpdateDate(new java.util.Date());

        xdpSystemManager.save(xdpSystem);
    }

    @Test
    public void testGetAllXdpSystems() throws Exception {
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getXdpSystems().size() >= 1);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        XdpSystemManager xdpSystemManager = (XdpSystemManager) applicationContext.getBean("xdpSystemManager");
        xdpSystemManager.reindex();

        action.setQ("*");
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertEquals(4, action.getXdpSystems().size());
    }

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(-1L);
        assertNull(action.getXdpSystem());
        assertEquals("success", action.edit());
        assertNotNull(action.getXdpSystem());
        assertFalse(action.hasActionErrors());
    }

    @Test
    public void testSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setId(-1L);
        assertEquals("success", action.edit());
        assertNotNull(action.getXdpSystem());

        XdpSystem xdpSystem = action.getXdpSystem();
        // update required fields
        xdpSystem.setFilePath("BcTzMbGyIrWkVfOhSwLbPcDuMgMzAxFsMvKfYaLoGyJsCmMjLrCqBeJmGbMnWzNqUiFmUdSqLdKeCyNpBcSpGkInCbRrCgFvDnTx");
        xdpSystem.setName("OsFzVnOkSuEpMaFfAdWeFuZhRiGvXmZyUtXhBtOpAaKkQdJfBk");
        xdpSystem.setUpdateDate(new java.util.Date());

        action.setXdpSystem(xdpSystem);

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
        XdpSystem xdpSystem = new XdpSystem();
        xdpSystem.setId(-2L);
        action.setXdpSystem(xdpSystem);
        assertEquals("success", action.delete());
        assertNotNull(request.getSession().getAttribute("messages"));
    }
}