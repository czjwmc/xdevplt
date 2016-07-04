package com.xj.dao;

import com.xj.dao.BaseDaoTestCase;
import com.xj.model.XdpProcess;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.test.annotation.ExpectedException;

import java.util.List;

public class XdpProcessDaoTest extends BaseDaoTestCase {
    @Autowired
    private XdpProcessDao xdpProcessDao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveXdpProcess() {
        XdpProcess xdpProcess = new XdpProcess();

        // enter all required fields
        xdpProcess.setCode("Ad");
        xdpProcess.setEnabled(Boolean.FALSE);
        xdpProcess.setName("LmMeSuZrFhFlDxAcNqXlQxXxFoMvJpXfYcZmKpCeRrKuKkPcEj");
        xdpProcess.setSortNo(1.8210154465496004E9L);
        xdpProcess.setUpdateDate(new java.util.Date());
        xdpProcess.setUpdateUser("XoAxJtKqUpAtCwOdVkSrHtRwMmDnFoOyOdGoSpTtNrHaWiYvDyYbSpCyZgOmWjKfJhVvKeEhSzUoTfMkItMoJqQmQaHzUdZwMeInYcVjPuThHzUzPvQtYrHvHzRdJiHaTgHoHmXsSzGtDxIyIeWtIvXuVfGoLlIcKtRjRtIcQdRiWbLuTbEuXdXfJvSlIlQyIlFsUqSuFrYmBdZhJzRcMwPkHiXbBkQzDqZnAlOfUpIdWcAoCtFyWqJdTeLjYkZ");

        log.debug("adding xdpProcess...");
        xdpProcess = xdpProcessDao.save(xdpProcess);

        xdpProcess = xdpProcessDao.get(xdpProcess.getId());

        assertNotNull(xdpProcess.getId());

        log.debug("removing xdpProcess...");

        xdpProcessDao.remove(xdpProcess.getId());

        // should throw DataAccessException 
        xdpProcessDao.get(xdpProcess.getId());
    }
}