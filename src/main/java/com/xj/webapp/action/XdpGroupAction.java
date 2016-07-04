package com.xj.webapp.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Preparable;
import com.xj.dao.SearchException;
import com.xj.model.XdpGroup;
import com.xj.service.XdpGroupManager;

public class XdpGroupAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = 4903481165192614479L;
	private XdpGroupManager xdpGroupManager;
    private List<XdpGroup> xdpGroups;
    private XdpGroup xdpGroup;
    private Long id;
    private String query;

    public void setXdpGroupManager(XdpGroupManager xdpGroupManager) {
        this.xdpGroupManager = xdpGroupManager;
    }

    public List<XdpGroup> getXdpGroups() {
        return xdpGroups;
    }

    /**
     * Grab the entity from the database before populating with request parameters
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            String xdpGroupId = getRequest().getParameter("xdpGroup.id");
            if (xdpGroupId != null && !xdpGroupId.equals("")) {
                xdpGroup = xdpGroupManager.get(new Long(xdpGroupId));
            }
        }
    }

    public void setQ(String q) {
        this.query = q;
    }

    public String list() {
        try {
            xdpGroups = xdpGroupManager.search(query, XdpGroup.class);
        } catch (SearchException se) {
            addActionError(se.getMessage());
            xdpGroups = xdpGroupManager.getAll();
        }
        return SUCCESS;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public XdpGroup getXdpGroup() {
        return xdpGroup;
    }

    public void setXdpGroup(XdpGroup xdpGroup) {
        this.xdpGroup = xdpGroup;
    }

    public String delete() {
    	try {
        xdpGroupManager.remove(xdpGroup.getId());
    	} catch (Exception e) {
    		List<Object> args = new ArrayList<Object>();
    	    args.add(getText("xdpGroupList.xdpGroup"));
            args.add(xdpGroup.getName());
            addActionError(getText("errors.delete", args));
            return INPUT;        
    	}
    	
        List<Object> args = new ArrayList<Object>();
        args.add(xdpGroup.getName());
        saveMessage(getText("xdpGroup.deleted",args));
        return SUCCESS;
    }

    public String edit() {
        if (id != null) {
        	try {         
                xdpGroup = xdpGroupManager.get(id);
                xdpGroup.setEnabled(true);
    		} catch (Exception e) {
    			List<Object> args = new ArrayList<Object>();
            	args.add(getText("xdpGroupList.xdpGroup"));
                args.add(id.toString());
                addActionError(getText("errors.get", args));
                list();
                return ERROR;
    		}
        } else {
            xdpGroup = new XdpGroup();
            xdpGroup.setEnabled(true);
            List<Long> mgrXdpGroups = new ArrayList<Long>();
            xdpGroups = xdpGroupManager.getAll();
        	if (xdpGroups != null && xdpGroups.size() > 0) {
                for (XdpGroup xdpGroup : xdpGroups) {
                	Long Mgr = xdpGroup.getMaxMgrNo();            
                    mgrXdpGroups.add(Mgr);
                }
                Long max = mgrXdpGroups.get(0);
                for(int x = 1; x <mgrXdpGroups.size(); x++ ) {
                	if(mgrXdpGroups.get(x)  >max){
                		max = mgrXdpGroups.get(x);
                	}
                }
                max=max+1;
                xdpGroup.setMaxMgrNo(max);
        	}
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

        boolean isNew = (xdpGroup.getId() == null);
        try {
            xdpGroup = xdpGroupManager.saveXdpGroup(xdpGroup);
        } catch (Exception e) {
        	List<Object> args = new ArrayList<Object>();
        	args.add(getText("xdpGroupList.xdpGroup"));
            args.add(xdpGroup.getName());
            addActionError(getText("errors.save", args));
            return INPUT;
        }
        List<Object> args = new ArrayList<Object>();
        args.add(xdpGroup.getName());
        if (isNew) {
            saveMessage(getText("xdpGroup.added",args));
        } else {
        	saveMessage(getText("xdpGroup.updated",args));
        }

        if (!isNew) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }
}