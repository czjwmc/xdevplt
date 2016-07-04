package com.xj.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import com.xj.service.DemoManager;
import com.xj.model.Demo;
import com.xj.webapp.action.BaseActionTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DemoActionTest extends BaseActionTestCase {
    private DemoAction action;

    @Before
    public void onSetUp() {
        super.onSetUp();

        action = new DemoAction();
        DemoManager demoManager = (DemoManager) applicationContext.getBean("demoManager");
        action.setDemoManager(demoManager);

        // add a test demo to the database
        Demo demo = new Demo();

        // enter all required fields
        demo.setCreateDate(new java.util.Date());
        demo.setEnabled(Boolean.FALSE);
        demo.setName("" + Math.random());
        demo.setUpdateDate(new java.util.Date());

        demoManager.save(demo);
    }

    @Test
    public void testGetAllDemoes() throws Exception {
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getDemoes().size() >= 1);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        DemoManager demoManager = (DemoManager) applicationContext.getBean("demoManager");
        demoManager.reindex();

        action.setQ("*");
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertEquals(4, action.getDemoes().size());
    }

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(-1L);
        assertNull(action.getDemo());
        assertEquals("success", action.edit());
        assertNotNull(action.getDemo());
        assertFalse(action.hasActionErrors());
    }

    @Test
    public void testSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setId(-1L);
        assertEquals("success", action.edit());
        assertNotNull(action.getDemo());

        Demo demo = action.getDemo();
        // update required fields
        demo.setCreateDate(new java.util.Date());
        demo.setEnabled(Boolean.FALSE);
        demo.setName("CnVbKzBuLiSjMxJjPkCcHkPoSkOvSsWmEeXiEqAlPhXpFsXqOf");
        demo.setUpdateDate(new java.util.Date());

        action.setDemo(demo);

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
        Demo demo = new Demo();
        demo.setId(-2L);
        action.setDemo(demo);
        assertEquals("success", action.delete());
        assertNotNull(request.getSession().getAttribute("messages"));
    }
}