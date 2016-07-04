package com.xj.dao;

import com.xj.dao.BaseDaoTestCase;
import com.xj.model.XdpGroupDocType;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.test.annotation.ExpectedException;

import java.util.List;

public class XdpGroupDocTypeDaoTest extends BaseDaoTestCase {
    @Autowired
    private XdpGroupDocTypeDao xdpGroupDocTypeDao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveXdpGroupDocType() {
        XdpGroupDocType xdpGroupDocType = new XdpGroupDocType();

        // enter all required fields
        xdpGroupDocType.setDocNum(1.6124359494696894E9L);
        xdpGroupDocType.setEnabled(Boolean.FALSE);
        xdpGroupDocType.setName("GcPjZmIyHrMpObPgTxIxWkMxXvGxTnCaZhDvDxFlMuTvKfTsBl");
        xdpGroupDocType.setSortNo(3.5762937440171844E8L);
        xdpGroupDocType.setUpdateDate(new java.util.Date());
        xdpGroupDocType.setUpdateUser("SlFkPaCyDwNlKgMlQpKiMdAvRtPcOmFgSyQzWkAzNvWzXfXrMfUpTfAlSfQvBbTnXgMlTvYjSpCiYpChOnOyUaXrIgWhLrGdAbQhAvVlDzKcGaKhZoRxOoSmOjTeFtXiCsAmMqYnNqKgImOyQrUkDgZnQqAaIuOpHcUdFrKrYwSpInJmCsNfWsSvFjAzWmXmCbXcOvJyXmItOaAoUkCaIfMjNaIqSvKaOvWgKnDjTtMeNtIeOwGjFsWbYmWkWtA");

        log.debug("adding xdpGroupDocType...");
        xdpGroupDocType = xdpGroupDocTypeDao.save(xdpGroupDocType);

        xdpGroupDocType = xdpGroupDocTypeDao.get(xdpGroupDocType.getId());

        assertNotNull(xdpGroupDocType.getId());

        log.debug("removing xdpGroupDocType...");

        xdpGroupDocTypeDao.remove(xdpGroupDocType.getId());

        // should throw DataAccessException 
        xdpGroupDocTypeDao.get(xdpGroupDocType.getId());
    }
}