package com.xj.dao;

import com.xj.dao.BaseDaoTestCase;
import com.xj.model.XdpGroupDoc;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.test.annotation.ExpectedException;

import java.util.List;

public class XdpGroupDocDaoTest extends BaseDaoTestCase {
    @Autowired
    private XdpGroupDocDao xdpGroupDocDao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveXdpGroupDoc() {
        XdpGroupDoc xdpGroupDoc = new XdpGroupDoc();

        // enter all required fields
        xdpGroupDoc.setCheckDate(new java.util.Date());
        xdpGroupDoc.setCheckUser("OhEtNyQiGuKzFuIdFrYkQaFxTpXmLkPqMoVrWaIkDtBaBjLjWqOvYtMwVyKhHjYxFxZkWaMiYbYdXbBuVgLmRbZgKlCnZrCmUaVpZnOgDbPeSrDpCiIfXoWsOoSzBgPjWrCnNlMsFjZnYiWvEwHwBjTvPrFpPcDzCbKwOrKtZkRnKgGwPaTpUhEbTiPeSrVpIpDxKuYbOpPuLxEyFkYqTgJeXzCoJvWhIuIwDeNvXkSyIlQcHrOhVyIbWwRwFsH");
        xdpGroupDoc.setCreateDate(new java.util.Date());
        xdpGroupDoc.setCreateUser("OuHyNjKzXpZcMtIaYcGnIqXbJlXqBbZfXjOjDiSqRuTiAzDbPvKbHdPcXjIoTgLvAdIeMgXeMjOvUrVoPjNgGrYgXxCnInVkPfOaHvLhUnSwCmAtIvUnAbXsSbUnEuPuIvRzWeQeOjPoNpStOzTmCdQeSvTbHrKsDgZaXsKmRaKcYpCeKbMbKhMnLxMfBwPyMiFsSpYmQuZjBrUdGsZxXpOmApCqJwWbJgQeKiXkVoHuXyCmGmMaRyIsUqKdUzI");
        xdpGroupDoc.setEnabled(Boolean.FALSE);
        xdpGroupDoc.setMgrNo("AzDzLtMr");
        xdpGroupDoc.setName("BpPqGcCtHvCmPcItZgVtKhTuPdQeVyVxXuPqMrEuLnMoAiUfUc");
        xdpGroupDoc.setUpdateDate(new java.util.Date());
        xdpGroupDoc.setUpdateUser("LpPaDgKpXsUpNnYrTyRtXqSbQxKeFgCuHaAgQsOzQrSiYmHuItCeZoXuArEzMcKeNpCtAbSmRbKiFmVjIbJkPeHfAxVwApXfGnHlSlUhKyKbLeAvOoOqLoLsTkCiMmAkYwZcEvPxZgVqAgAlSmXmWeByFhKvQyIxMjSkXpHaEvFaDkEoZxMhDjEuIhJzNzKlAdKhEsHiBiYrPqRrRgJmCaUgRsRqAtEaOuHoKvEtKxPnZxJdHzVkEhIfGjFmLmK");
        xdpGroupDoc.setVer("GiWqNsBgCy");

        log.debug("adding xdpGroupDoc...");
        xdpGroupDoc = xdpGroupDocDao.save(xdpGroupDoc);

        xdpGroupDoc = xdpGroupDocDao.get(xdpGroupDoc.getId());

        assertNotNull(xdpGroupDoc.getId());

        log.debug("removing xdpGroupDoc...");

        xdpGroupDocDao.remove(xdpGroupDoc.getId());

        // should throw DataAccessException 
        xdpGroupDocDao.get(xdpGroupDoc.getId());
    }
}