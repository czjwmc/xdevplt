package com.xj.webapp.action;

import com.opensymphony.xwork2.Preparable;
import com.xj.service.XdpProcessManager;
import com.xj.dao.SearchException;
import com.xj.model.XdpProcess;
import com.xj.webapp.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

public class XdpProcessAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = 4287289328739565357L;
	private XdpProcessManager xdpProcessManager;
    private List<XdpProcess> xdpProcesses;
    private XdpProcess xdpProcess;
    private Long id;
    private String query;

    public void setXdpProcessManager(XdpProcessManager xdpProcessManager) {
        this.xdpProcessManager = xdpProcessManager;
    }

    public List<XdpProcess> getXdpProcesses() {
        return xdpProcesses;
    }

    /**
     * Grab the entity from the database before populating with request parameters
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            String xdpProcessId = getRequest().getParameter("xdpProcess.id");
            if (xdpProcessId != null && !xdpProcessId.equals("")) {
                xdpProcess = xdpProcessManager.get(new Long(xdpProcessId));
            }
        }
    }

    public void setQ(String q) {
        this.query = q;
    }

    public String list() {
        try {
            xdpProcesses = xdpProcessManager.search(query, XdpProcess.class);
        } catch (SearchException se) {
            addActionError(se.getMessage());
            xdpProcesses = xdpProcessManager.getAll();
        }
        return SUCCESS;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public XdpProcess getXdpProcess() {
        return xdpProcess;
    }

    public void setXdpProcess(XdpProcess xdpProcess) {
        this.xdpProcess = xdpProcess;
    }

    public String delete() {
    	try {
        xdpProcessManager.remove(xdpProcess.getId());
        } catch (Exception e) {
    		List<Object> args = new ArrayList<Object>();
    	    args.add(getText("xdpProcessList.xdpProcess"));
            args.add(xdpProcess.getName());
            addActionError(getText("errors.delete", args));
            return INPUT;
        }
        List<Object> args = new ArrayList<Object>();
        args.add(xdpProcess.getName());
        saveMessage(getText("xdpProcess.deleted",args));
        return SUCCESS;
    }

    public String edit() {
        if (id != null) {
        	try {
            xdpProcess = xdpProcessManager.get(id);
    		} catch (Exception e) {
    			List<Object> args = new ArrayList<Object>();
            	args.add(getText("xdpProcessList.xdpProcess"));
                args.add(id.toString());
                addActionError(getText("errors.get", args));
                list();
                return ERROR;
    		}
        } else {
            xdpProcess = new XdpProcess();
            xdpProcess.setEnabled(true);
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

        boolean isNew = (xdpProcess.getId() == null);
        
        try {
            xdpProcess = xdpProcessManager.saveXdpProcess(xdpProcess);
        } catch (Exception e) {
        	List<Object> args = new ArrayList<Object>();
        	args.add(getText("xdpProcessList.xdpProcess"));
            args.add(xdpProcess.getName());
            addActionError(getText("errors.save", args));
            return INPUT;
        }
        List<Object> args = new ArrayList<Object>();
        args.add(xdpProcess.getName());        
        if (isNew) {
            saveMessage(getText("xdpProcess.added",args));
        } else {
        	saveMessage(getText("xdpProcess.updated",args));
        }


        if (!isNew) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }
}