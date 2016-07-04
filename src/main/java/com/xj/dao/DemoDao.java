package com.xj.dao;

import java.util.List;

import com.xj.model.Demo;

/**
 * An interface that provides a data management interface to the Demo table.
 */
public interface DemoDao extends GenericDao<Demo, Long> {

    /**
     * Gets a list of demos ordered by the upper(name).
     *
     * @return List populated list of demos
     */
    List<Demo> getDemos();

    /**
     * Gets a list of enabled demos ordered by the upper(name).
     *
     * @return List populated list of demos
     */
    List<Demo> getDemosEnabled();

}