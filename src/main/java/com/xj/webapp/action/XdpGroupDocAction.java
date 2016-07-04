package com.xj.webapp.action;

import com.opensymphony.xwork2.Preparable;
import com.xj.service.XdpGroupDocManager;
import com.xj.dao.SearchException;
import com.xj.model.XdpGroupDoc;
import com.xj.webapp.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

public class XdpGroupDocAction extends BaseAction implements Preparable {
	private static final long serialVersionUID = -6984532071548729101L;
	private XdpGroupDocManager xdpGroupDocManager;
    private List<XdpGroupDoc> xdpGroupDocs;
    private XdpGroupDoc xdpGroupDoc;
    private Long id;
    private String query;

    public void setXdpGroupDocManager(XdpGroupDocManager xdpGroupDocManager) {
        this.xdpGroupDocManager = xdpGroupDocManager;
    }

    public List<XdpGroupDoc> getXdpGroupDocs() {
        return xdpGroupDocs;
    }

    /**
     * Grab the entity from the database before populating with request parameters
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            String xdpGroupDocId = getRequest().getParameter("xdpGroupDoc.id");
            if (xdpGroupDocId != null && !xdpGroupDocId.equals("")) {
                xdpGroupDoc = xdpGroupDocManager.get(new Long(xdpGroupDocId));
            }
        }
    }

    public void setQ(String q) {
        this.query = q;
    }

    public String list() {
        try {
            xdpGroupDocs = xdpGroupDocManager.search(query, XdpGroupDoc.class);
        } catch (SearchException se) {
            addActionError(se.getMessage());
            xdpGroupDocs = xdpGroupDocManager.getAll();
        }
        return SUCCESS;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public XdpGroupDoc getXdpGroupDoc() {
        return xdpGroupDoc;
    }

    public void setXdpGroupDoc(XdpGroupDoc xdpGroupDoc) {
        this.xdpGroupDoc = xdpGroupDoc;
    }

    public String delete() {
    	try {
            xdpGroupDocManager.remove(xdpGroupDoc.getId());
        } catch (Exception e) {
    		List<Object> args = new ArrayList<Object>();
    	    args.add(getText("xdpGroupDocList.xdpGroupDoc"));
            args.add(xdpGroupDoc.getName());
            addActionError(getText("errors.delete", args));
            return INPUT;
        }
   	
        List<Object> args = new ArrayList<Object>();
        args.add(xdpGroupDoc.getName());
        saveMessage(getText("xdpGroupDoc.deleted"));

        return SUCCESS;
    }

    public String edit() {
        if (id != null) {
        	try {
            xdpGroupDoc = xdpGroupDocManager.get(id);
    		} catch (Exception e) {
    			List<Object> args = new ArrayList<Object>();
            	args.add(getText("xdpGroupDocList.xdpGroupDoc"));
                args.add(id.toString());
                addActionError(getText("errors.get", args));
                list();
                return ERROR;
    		}
        } else {
            xdpGroupDoc = new XdpGroupDoc();
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

        boolean isNew = (xdpGroupDoc.getId() == null);
        try {
        xdpGroupDocManager.save(xdpGroupDoc);
    } catch (Exception e) {
    	List<Object> args = new ArrayList<Object>();
    	args.add(getText("xdpGroupDocList.xdpGroupDoc"));
        args.add(xdpGroupDoc.getName());
        addActionError(getText("errors.save", args));
        return INPUT;
    }
        String key = (isNew) ? "xdpGroupDoc.added" : "xdpGroupDoc.updated";
        List<Object> args = new ArrayList<Object>();
        args.add(xdpGroupDoc.getName());
        saveMessage(getText(key));

        if (!isNew) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }
}