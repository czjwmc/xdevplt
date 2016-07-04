package com.xj.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import com.xj.service.XdpGroupDocManager;
import com.xj.model.XdpGroupDoc;
import com.xj.webapp.action.BaseActionTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class XdpGroupDocActionTest extends BaseActionTestCase {
    private XdpGroupDocAction action;

    @Before
    public void onSetUp() {
        super.onSetUp();

        action = new XdpGroupDocAction();
        XdpGroupDocManager xdpGroupDocManager = (XdpGroupDocManager) applicationContext.getBean("xdpGroupDocManager");
        action.setXdpGroupDocManager(xdpGroupDocManager);

        // add a test xdpGroupDoc to the database
        XdpGroupDoc xdpGroupDoc = new XdpGroupDoc();

        // enter all required fields
        xdpGroupDoc.setCheckDate(new java.util.Date());
        xdpGroupDoc.setCheckUser("CyCnTnMwNgOjIeSiDqOcSdBgEaBaGaHzDaHqWgMrUwDwGbPdArZlGzTwWaQkAcNbBxRpFyOrLuBmIkZeVxSrQvNxPlPoPtObGuOkUxYwPyJsRqYfXbZhJvIcGoDuRsHlPjYuApIzAaZyXeKzGcWkZmWeMnTqXvHkVmRuQgMfHrJkVeVcUsCnOoNaTxUfHoJpJmWrXlYfNyVeKrFnQbBhKvXnLwAfQuBcFpJvAiAfBaUtZxLwUqSbDiCgJnWtKsS");
        xdpGroupDoc.setCreateDate(new java.util.Date());
        xdpGroupDoc.setCreateUser("LhHoGrVvRnZkMuUtMySkVyRzSjKpRwCoDwLmCxBfXfGpPmArHoUgOfWvMhGtDlSaJfIcKlIwDnUaIgXxCjGeLaWeEbFxHeAqUvYzGyAwOxXmFbUpHwLhUmIvWiAvHrFrVmUyDrWlBtErVxUbKeVzWuGjOxQnPeKoZsNgKkNvUuPvSuJjIgQxCvKqIvZvLtBbPrHyFqJrFqCeGkRkPdNuPyMnZlBhReTdTaZfLuLgFpByMpSjVtNqSyYhAzFySjC");
        xdpGroupDoc.setEnabled(Boolean.FALSE);
        xdpGroupDoc.setMgrNo("" + Math.random());
        xdpGroupDoc.setName("" + Math.random());
        xdpGroupDoc.setUpdateDate(new java.util.Date());
        xdpGroupDoc.setUpdateUser("RiFwMhCwNuRlIgPaApLiHeJwRoCtSvXaUpAmHnHdUqWvDoXmQnMwWfAbRwVtXoXeFuEuTpZgBdVaAjFlAjCzYwTnGtVzXrBqWaJlYlSiPqOlYcLaSwIiQxLaOrRlFzDyWkQnWcAhNxLkOjNqShUuDrIaErTsTuDfRrRjLaZbTwScJrSbZoRzEhKzTvXwHfTtKoQrXcMnKpTzPtYhUpJuHdInXtVoGbWrRaFwGoSnDnDqUtSzSuWpUlLeGoLgRvH");
        xdpGroupDoc.setVer("" + Math.random());

        xdpGroupDocManager.save(xdpGroupDoc);
    }

    @Test
    public void testGetAllXdpGroupDocs() throws Exception {
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getXdpGroupDocs().size() >= 1);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        XdpGroupDocManager xdpGroupDocManager = (XdpGroupDocManager) applicationContext.getBean("xdpGroupDocManager");
        xdpGroupDocManager.reindex();

        action.setQ("*");
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertEquals(4, action.getXdpGroupDocs().size());
    }

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(-1L);
        assertNull(action.getXdpGroupDoc());
        assertEquals("success", action.edit());
        assertNotNull(action.getXdpGroupDoc());
        assertFalse(action.hasActionErrors());
    }

    @Test
    public void testSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setId(-1L);
        assertEquals("success", action.edit());
        assertNotNull(action.getXdpGroupDoc());

        XdpGroupDoc xdpGroupDoc = action.getXdpGroupDoc();
        // update required fields
        xdpGroupDoc.setCheckDate(new java.util.Date());
        xdpGroupDoc.setCheckUser("ThCrUfMrCwXoPjCtZlWtJfKyKuXnUmZaYpQpLdGdAtRcOkDqHjUcHdIkOmIrIbJlOvSkImItRoGwSyMtAqGfJzYrLcZlEvEaCtQlNhMnYwVuSeHmWoBxNjIuWiIwImSzHnEgPzBiNbXpGhWuNaBlViQgPeIzVcVqCyZzGmKxMcBgLcTtSiAuAgOoMkUyTqIjKxCePoAaCwTgHlNqDiBwDqNbWpZaFtXoQwCsLiFtTpRuHhPbXkEePjLuGdLdNuX");
        xdpGroupDoc.setCreateDate(new java.util.Date());
        xdpGroupDoc.setCreateUser("FsYaEyAvGhQjNjQlMbKyOoEwYhMfNzAuChAaDnGoQnHpXbEvVlDqPfHwGqYzPoDoEfDyCvBwYbGrWaOrPwLpRqIuVtKwFyAoYfEcIiUaVgXnOpRtXgSpUsPtEiLgBxVkOyRrFvPnZmCfJwJxBmCtHpOqKcRvIwBjQcDbPfSnBvFrGuHjGaMoCwHkUhMzPrFuVzLkUwTsYrDpGnZqKcNeYoSjRtPkDoNyHdIsFsEoAuCxJjIyOcWxEdPaEsKyDsX");
        xdpGroupDoc.setEnabled(Boolean.FALSE);
        xdpGroupDoc.setMgrNo("YmIlWyDf");
        xdpGroupDoc.setName("IxPcYoLdUeAtDkMdXtUiRpPhZiVoFaQwXfUxJaZaCzLsFsSmXo");
        xdpGroupDoc.setUpdateDate(new java.util.Date());
        xdpGroupDoc.setUpdateUser("XfIdVbBnUuZfAgMwAnDuUpZtNsWcTwPkWkYdHuMbAlLaUeTkYjRoXsNsMjSaKpBlAqLiJjAfXwAbWxCqMdLqMhAvZmYdMrQuApEfPdEjSmOfEiGmKvNiCzFrCvPrLlLuFiVhJkPrWmFtBdDgBwTnZlEgMgQpCkLaSgJaChZjXdFbOoWxLoXjAtXfMxPgNqNcKcTgKqYrHbCyXzDvKyCkHvUlYsIjLyCaPiZbUhKyRdCfPgUoHqRwPpPrZjFzPxO");
        xdpGroupDoc.setVer("SgWqMkPyBj");

        action.setXdpGroupDoc(xdpGroupDoc);

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
        XdpGroupDoc xdpGroupDoc = new XdpGroupDoc();
        xdpGroupDoc.setId(-2L);
        action.setXdpGroupDoc(xdpGroupDoc);
        assertEquals("success", action.delete());
        assertNotNull(request.getSession().getAttribute("messages"));
    }
}