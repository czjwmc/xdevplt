package com.xj.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import com.xj.service.XdpProcessManager;
import com.xj.model.XdpProcess;
import com.xj.webapp.action.BaseActionTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class XdpProcessActionTest extends BaseActionTestCase {
    private XdpProcessAction action;

    @Before
    public void onSetUp() {
        super.onSetUp();

        action = new XdpProcessAction();
        XdpProcessManager xdpProcessManager = (XdpProcessManager) applicationContext.getBean("xdpProcessManager");
        action.setXdpProcessManager(xdpProcessManager);

        // add a test xdpProcess to the database
        XdpProcess xdpProcess = new XdpProcess();

        // enter all required fields
        xdpProcess.setCode("" + Math.random());
        xdpProcess.setEnabled(Boolean.FALSE);
        xdpProcess.setName("" + Math.random());
        xdpProcess.setSortNo(1.5317825228091958E9L);
        xdpProcess.setUpdateDate(new java.util.Date());
        xdpProcess.setUpdateUser("PiJhIzWgSqNhCzUxSgTlZdEcUwMwJmPrMaVlBnRjDzTrRfCrKoJrQoDoZqQaBoJtLlEvPaKjEiRdAtEsMbFvZkGdSgQpJyNoBdRfZqQtGfCsRmPgZcUeAwIwQwHsNsPgMnNjBqBgIlUwCfRfKaRiPgTmSuLpNnEoNuFnNdJcKrVnLjXiEnTfTsDsMnKdPlPuAkPnQjYtSlXsRkFiBbQyIsKfRqKdVpZdRtTqUxAvSeXfQcWhDpKcUiAnCdHkZlP");

        xdpProcessManager.save(xdpProcess);
    }

    @Test
    public void testGetAllXdpProcesses() throws Exception {
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getXdpProcesses().size() >= 1);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        XdpProcessManager xdpProcessManager = (XdpProcessManager) applicationContext.getBean("xdpProcessManager");
        xdpProcessManager.reindex();

        action.setQ("*");
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertEquals(4, action.getXdpProcesses().size());
    }

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(-1L);
        assertNull(action.getXdpProcess());
        assertEquals("success", action.edit());
        assertNotNull(action.getXdpProcess());
        assertFalse(action.hasActionErrors());
    }

    @Test
    public void testSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setId(-1L);
        assertEquals("success", action.edit());
        assertNotNull(action.getXdpProcess());

        XdpProcess xdpProcess = action.getXdpProcess();
        // update required fields
        xdpProcess.setCode("Jy");
        xdpProcess.setEnabled(Boolean.FALSE);
        xdpProcess.setName("WoCoNdEyWxHzKxPsOqIiHjLyZrRwTkPdSzUwVoIkKkDkEcQvFm");
        xdpProcess.setSortNo(1.8584403073756976E9L);
        xdpProcess.setUpdateDate(new java.util.Date());
        xdpProcess.setUpdateUser("JqRlWyEyPsTkAkIyPuJeZwNgIrNhRyGuUkXoXnWbIdOeLuUmDnMwFlTaQuAcLcBdHsYhEmElAfRtIlKdNqYxJxWsOvLhMaTiZaGaBbThYdGdRuYjMoRrQhDuHmNoNdHeEnKnSdZuSjQsIfNyEaIzQyMbDaNhHqYpSbIqLyEuDvLpLaXhPgIzFmLtRsTeIaHcWeHkVqRlEoXdKaOtVuFmKiWaNoRtBgXaNdPkTqRjAoHuGtQiEcJbTlCoTgWlCuV");

        action.setXdpProcess(xdpProcess);

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
        XdpProcess xdpProcess = new XdpProcess();
        xdpProcess.setId(-2L);
        action.setXdpProcess(xdpProcess);
        assertEquals("success", action.delete());
        assertNotNull(request.getSession().getAttribute("messages"));
    }
}