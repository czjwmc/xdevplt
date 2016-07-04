package com.xj.webapp.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Preparable;
import com.xj.dao.SearchException;
import com.xj.model.XdpDocType;
import com.xj.service.XdpDocTypeManager;

public class XdpDocTypeAction extends BaseAction implements Preparable {
	private static final long serialVersionUID = -3309728171113165970L;
	private XdpDocTypeManager xdpDocTypeManager;
    private List<XdpDocType> xdpDocTypes;
    private XdpDocType xdpDocType;
    private Long id;
    private String query;

    public void setXdpDocTypeManager(XdpDocTypeManager xdpDocTypeManager) {
        this.xdpDocTypeManager = xdpDocTypeManager;
    }
    
    public XdpDocTypeManager getXdpDocTypeManager() {
        return xdpDocTypeManager;
    }


    public List<XdpDocType> getXdpDocTypes() {
        return xdpDocTypes;
    }

    /**
     * Grab the entity from the database before populating with request parameters
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            String xdpDocTypeId = getRequest().getParameter("xdpDocType.id");
            if (xdpDocTypeId != null && !xdpDocTypeId.equals("")) {
                xdpDocType = xdpDocTypeManager.get(new Long(xdpDocTypeId));
            }
        }
    }

    public void setQ(String q) {
        this.query = q;
    }

    public String list() {
        try {
            xdpDocTypes = xdpDocTypeManager.search(query, XdpDocType.class);
        } catch (SearchException se) {
            addActionError(se.getMessage());
            xdpDocTypes = xdpDocTypeManager.getAll();
        }
        return SUCCESS;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public XdpDocType getXdpDocType() {
        return xdpDocType;
    }

    public void setXdpDocType(XdpDocType xdpDocType) {
        this.xdpDocType = xdpDocType;
    }

    public String delete() {
    	try {
        xdpDocTypeManager.remove(xdpDocType.getId());
    	}catch (Exception e) {
    		List<Object> args = new ArrayList<Object>();
    	    args.add(getText("xdpDocTypeList.XdpDocType"));
            args.add(xdpDocType.getName());
            addActionError(getText("errors.delete", args));
            return INPUT;
        }
        List<Object> args = new ArrayList<Object>();
        args.add(xdpDocType.getName());    	
        saveMessage(getText("xdpDocType.deleted"));

        return SUCCESS;
    }

    public String edit() {
        if (id != null) {
        	try {       	
                xdpDocType = xdpDocTypeManager.get(id);
        	} catch (Exception e) {
    			List<Object> args = new ArrayList<Object>();
            	args.add(getText("xdpDocTypeList.XdpDocType"));
                args.add(id.toString());
                addActionError(getText("errors.get", args));
                list();
                return ERROR;
        	}
        } else {
            xdpDocType = new XdpDocType();
        }

        return SUCCESS;
    }

    public String save() throws Exception {
        if (cancel != null) {
            return "cancel";
        }

        if (delete != null) {
            return delete();
        }

        boolean isNew = (xdpDocType.getId() == null);
        try {
            xdpDocType = xdpDocTypeManager.saveXdpDocType(xdpDocType);
        } catch (Exception e) {
        	List<Object> args = new ArrayList<Object>();
        	args.add(getText("xdpDocTypeList.XdpDocType"));
            args.add(xdpDocType.getName());
            addActionError(getText("errors.save", args));
            return INPUT;
        }

        String key = (isNew) ? "xdpDocType.added" : "xdpDocType.updated";
        saveMessage(getText(key));

        if (!isNew) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }
}