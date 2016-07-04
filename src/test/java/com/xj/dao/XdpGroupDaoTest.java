package com.xj.dao;

import com.xj.dao.BaseDaoTestCase;
import com.xj.model.XdpGroup;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.test.annotation.ExpectedException;

import java.util.List;

public class XdpGroupDaoTest extends BaseDaoTestCase {
    @Autowired
    private XdpGroupDao xdpGroupDao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveXdpGroup() {
        XdpGroup xdpGroup = new XdpGroup();

        // enter all required fields
        xdpGroup.setEnabled(Boolean.FALSE);
        xdpGroup.setName("AfFfBdZvMrNdIjZtAkNaZqUaVuPjCnTwNaUeLpXeHjLiViXhTe");
        xdpGroup.setUpdateDate(new java.util.Date());
        xdpGroup.setUpdateUser("ZwEmGpPtKsYsPmTpWcGxHfCrRfQhCoTpAoBlGfUtPzUgYtGmGvGfUcOwGaFtNvLaZgDjEqGxWaNbPrUkAtFtUsOgAcHcYmWmLmGgOfYtJsBmCwAkYoIgMbJaQwQyGjThWeJxUzDvBcFyDfPqFhYmWqIoUxPsEhSpHoIpKlJmZiLuJlHbRpSgYlOpBgPsYxKnRsBcPlGwBiKdGePtTcUkKuEgNtAkQwGhGdDaSjQdAhYlLcKyIiLiGqNmXfIqHaK");

        log.debug("adding xdpGroup...");
        xdpGroup = xdpGroupDao.save(xdpGroup);

        xdpGroup = xdpGroupDao.get(xdpGroup.getId());

        assertNotNull(xdpGroup.getId());

        log.debug("removing xdpGroup...");

        xdpGroupDao.remove(xdpGroup.getId());

        // should throw DataAccessException 
        xdpGroupDao.get(xdpGroup.getId());
    }
}