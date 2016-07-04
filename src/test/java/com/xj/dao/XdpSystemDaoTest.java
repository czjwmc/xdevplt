package com.xj.dao;

import com.xj.dao.BaseDaoTestCase;
import com.xj.model.XdpSystem;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.test.annotation.ExpectedException;

import java.util.List;

public class XdpSystemDaoTest extends BaseDaoTestCase {
    @Autowired
    private XdpSystemDao xdpSystemDao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveXdpSystem() {
        XdpSystem xdpSystem = new XdpSystem();

        // enter all required fields
        xdpSystem.setFilePath("UvYxHoMiFoWxKhDgXlFrOcUbKrWqLzDpZvYhSePiNsCbJnFkWuIeWyCeQuVfZdDtLoMwBlRlBgLbMtEnQkAaYvDeNqQwRoAcTyIh");
        xdpSystem.setName("BqBkUrWtJiMzQyEvCuPdMyHdHwTwZdEpJkDaEvYhNnQnShRmDi");
        xdpSystem.setUpdateDate(new java.util.Date());

        log.debug("adding xdpSystem...");
        xdpSystem = xdpSystemDao.save(xdpSystem);

        xdpSystem = xdpSystemDao.get(xdpSystem.getId());

        assertNotNull(xdpSystem.getId());

        log.debug("removing xdpSystem...");

        xdpSystemDao.remove(xdpSystem.getId());

        // should throw DataAccessException 
        xdpSystemDao.get(xdpSystem.getId());
    }
}