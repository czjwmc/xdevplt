package com.xj.webapp.action;

import com.opensymphony.xwork2.Preparable;
import com.xj.service.XdpSystemManager;
import com.xj.dao.SearchException;
import com.xj.model.XdpSystem;
import com.xj.webapp.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

public class XdpSystemAction extends BaseAction implements Preparable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7262751493521738992L;
	private XdpSystemManager xdpSystemManager;
    private List<XdpSystem> xdpSystems;
    private XdpSystem xdpSystem;
    private Long id;
    private String query;

    public void setXdpSystemManager(XdpSystemManager xdpSystemManager) {
        this.xdpSystemManager = xdpSystemManager;
    }

    public List<XdpSystem> getXdpSystems() {
        return xdpSystems;
    }

    /**
     * Grab the entity from the database before populating with request parameters
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            String xdpSystemId = getRequest().getParameter("xdpSystem.id");
            if (xdpSystemId != null && !xdpSystemId.equals("")) {
                xdpSystem = xdpSystemManager.get(new Long(xdpSystemId));
            }
        }
    }

    public void setQ(String q) {
        this.query = q;
    }

    public String list() {
        try {
            xdpSystems = xdpSystemManager.search(query, XdpSystem.class);
        } catch (SearchException se) {
            addActionError(se.getMessage());
            xdpSystems = xdpSystemManager.getAll();
        }
        return SUCCESS;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public XdpSystem getXdpSystem() {
        return xdpSystem;
    }

    public void setXdpSystem(XdpSystem xdpSystem) {
        this.xdpSystem = xdpSystem;
    }

    public String edit() {
        if (id != null) {
        	try {
                xdpSystem = xdpSystemManager.get(id);
    		} catch (Exception e) {
    			List<Object> args = new ArrayList<Object>();
            	args.add(getText("xdpSystemList.xdpSystem"));
                args.add(id.toString());
                addActionError(getText("errors.get", args));
                list();
                return ERROR;
    		}
        } else {
            xdpSystem = new XdpSystem();
        }

        return SUCCESS;
    }

    public String save() throws Exception {
        if (cancel != null) {
            return "cancel";
        }

        boolean isNew = (xdpSystem.getId() == null);
        try {
            xdpSystemManager.save(xdpSystem);
            } catch (Exception e) {
        	    List<Object> args = new ArrayList<Object>();
        	    args.add(getText("xdpSystemList.xdpSystem"));
                args.add(xdpSystem.getName());
                addActionError(getText("errors.save", args));
                return INPUT;
            }
        List<Object> args = new ArrayList<Object>();
        args.add(xdpSystem.getName());
        saveMessage(getText("xdpSystem.updated",args));

        if (!isNew) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }
}