package com.xj.webapp.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Preparable;
import com.xj.dao.SearchException;
import com.xj.model.Demo;
import com.xj.service.DemoManager;
import com.xj.service.exception.UniqueException;

public class DemoAction extends BaseAction implements Preparable {
	private static final long serialVersionUID = -4582371305273685835L;
	private DemoManager demoManager;
    private List<Demo> demoes;
    private Demo demo;
    private Long id;
    private String query;

    public void setDemoManager(DemoManager demoManager) {
        this.demoManager = demoManager;
    }

    public List<Demo> getDemoes() {
        return demoes;
    }

    /**
     * Grab the entity from the database before populating with request parameters
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            String demoId = getRequest().getParameter("demo.id");
            if (demoId != null && !demoId.equals("")) {
            	try {
            		demo = demoManager.getDemo(demoId);
            	} catch (Exception e) {
            		List<Object> args = new ArrayList<Object>();
            		args.add(getText("demoList.demo"));
                    args.add(demoId);
                    addActionError(getText("errors.get", args));
            	}
            }
        }
    }

    public void setQ(String q) {
        this.query = q;
    }

    public String list() {
    	if (query == null) {
    		query = (String) getSession().getAttribute("demo_search_query");
    	}
        try {
            demoes = demoManager.search(query, Demo.class);
        } catch (SearchException se) {
            addActionError(se.getMessage());
            demoes = demoManager.getAll();
        }
        if (query != null) {
        	getSession().setAttribute("demo_search_query", query);
        }
        return SUCCESS;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Demo getDemo() {
        return demo;
    }

    public void setDemo(Demo demo) {
        this.demo = demo;
    }

    public String delete() {
    	try {
    		demoManager.removeDemo(demo.getId().toString());
        } catch (Exception e) {
    		List<Object> args = new ArrayList<Object>();
    	    args.add(getText("demoList.demo"));
            args.add(demo.getName());
            addActionError(getText("errors.delete", args));
            return INPUT;
        }

        List<Object> args = new ArrayList<Object>();
        args.add(demo.getName());
        saveMessage(getText("demo.deleted", args));

        return SUCCESS;
    }

    public String edit() {
        if (id != null) {
    		try {
    			demo = demoManager.getDemo(id.toString());
    		} catch (Exception e) {
    			List<Object> args = new ArrayList<Object>();
            	args.add(getText("demoList.demo"));
                args.add(id.toString());
                addActionError(getText("errors.get", args));
                list();
                return ERROR;
    		}
        } else {
        	demo = demoManager.getDemoNew();
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

        boolean isNew = (demo.getId() == null);

        try {
        	demo = demoManager.saveDemo(demo);
        } catch (UniqueException e) {
        	List<Object> args = new ArrayList<Object>();
        	args.add(getText("demoList.demo"));
            args.add(demo.getName());
            addActionError(getText("errors.save.uniqueName", args));
            return INPUT;
        } catch (Exception e) {
        	List<Object> args = new ArrayList<Object>();
        	args.add(getText("demoList.demo"));
            args.add(demo.getName());
            addActionError(getText("errors.save", args));
            return INPUT;
        }

        String key = (isNew) ? "demo.added" : "demo.updated";
        List<Object> args = new ArrayList<Object>();
        args.add(demo.getName());
        saveMessage(getText(key, args));

        if (!isNew) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }
}