package com.xj.dao;

import com.xj.dao.BaseDaoTestCase;
import com.xj.model.XdpDocType;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.test.annotation.ExpectedException;

import java.util.List;

public class XdpDocTypeDaoTest extends BaseDaoTestCase {
    @Autowired
    private XdpDocTypeDao XdpDocTypeDao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveXdpDocType() {
        XdpDocType XdpDocType = new XdpDocType();

        // enter all required fields
        XdpDocType.setEnabled(Boolean.FALSE);
        XdpDocType.setName("VnGpCjSeShKaYwHpGmPrXpUuZiXmHsBiHjOaEcGeXfBsHxTpFh");
        XdpDocType.setSortNo(7.736586836589351E8L);
        XdpDocType.setUpdateDate(new java.util.Date());
        XdpDocType.setUpdateUser("DnWdQtGtBeEiVhTgIaQsWuYrHyUwVtQtMqRqJkWaAtZoOeYbVnEcGmJyGmIiTzRjIgRzLuJnOuQlDzUdGkOzGvFsZbSrNiWfApCeWeQpOzJtPvRbTcXbTsOuHdSzDuBqSyTjYlCaVvJiObOsLlShPoMyPjExMnOkCnAgAuHdWnQlQyNoBaVhJsFhZzXjZjVcDaDeOjHlMmSbBnKfViDrStBuSaMrKcGtDuXyKoRfFzUuOvPeRrYzTiChEgQwVnB");

        log.debug("adding XdpDocType...");
        XdpDocType = XdpDocTypeDao.save(XdpDocType);

        XdpDocType = XdpDocTypeDao.get(XdpDocType.getId());

        assertNotNull(XdpDocType.getId());

        log.debug("removing XdpDocType...");

        XdpDocTypeDao.remove(XdpDocType.getId());

        // should throw DataAccessException 
        XdpDocTypeDao.get(XdpDocType.getId());
    }
}