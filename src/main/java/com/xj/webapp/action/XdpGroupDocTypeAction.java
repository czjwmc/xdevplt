package com.xj.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Preparable;
import com.xj.Constants;
import com.xj.dao.SearchException;
import com.xj.model.LabelValue;
import com.xj.model.XdpGroup;
import com.xj.model.XdpGroupDocType;
import com.xj.model.XdpProcess;
import com.xj.service.XdpGroupDocTypeManager;
import com.xj.service.XdpProcessManager;

public class XdpGroupDocTypeAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = -635682283102942309L;
	private XdpGroupDocTypeManager xdpGroupDocTypeManager;
	private XdpProcessManager xdpProcessManager;
    private List<XdpGroupDocType> xdpGroupDocTypes;
    private List<XdpGroup> xdpGroups;
	private XdpGroup xdpGroup;
    private List<XdpProcess> xdpProcesses;
    private XdpProcess xdpProcess;
    private XdpGroupDocType xdpGroupDocType;
    private Long id;
    // Query and get/set fields. add by wmc 2013/10/10
    private String xdpGroupDocTypeId;
    private String ruleFile;
    private String ruleFilePath;
    private String templateFile;
    private String templateFilePath;
    //for uploading add by wmc 2013/10/16
    private File fileupload1;
    private String fileupload1FileName;
    private String fileupload1ContentType;
    private File fileupload2;
    private String fileupload2FileName;
    private String fileupload2ContentType;

    private Long maxMgr;

    

    public String getRuleFile() {
        return ruleFile;
    }

    public void setRuleFile(String ruleFile) {
        this.ruleFile = ruleFile;
    }
    

    public String getRuleFilePath() {
        return ruleFilePath;
    }

    public void setRuleFilePath(String ruleFilePath) {
        this.ruleFilePath = ruleFilePath;
    }
    
    public Long getMaxMgr() {
        return maxMgr;
    }

    public void setMaxMgr(Long maxMgr) {
        this.maxMgr = maxMgr;
    }
    
    public String getFileupload1ContentType() {
        return (this.fileupload1ContentType);
    }

    public void setFileupload1ContentType(String fileupload1ContentType) {
        this.fileupload1ContentType = fileupload1ContentType;
    }

    
    
    public String getFileupload1FileName() {
        return (this.fileupload1FileName);
    }

    public void setFileupload1FileName(String fileupload1FileName) {
        this.fileupload1FileName = fileupload1FileName;
    }

    
    
    public File getFileupload1() {
        return (this.fileupload1);
    }

    public void setFileupload1(File fileupload1) {
        this.fileupload1 = fileupload1;
    }

    public String getFileupload2ContentType() {
        return (this.fileupload2ContentType);
    }

    public void setFileupload2ContentType(String fileupload2ContentType) {
        this.fileupload2ContentType = fileupload2ContentType;
    }

    
    
    public String getFileupload2FileName() {
        return (this.fileupload2FileName);
    }

    public void setFileupload2FileName(String fileupload2FileName) {
        this.fileupload2FileName = fileupload2FileName;
    }

    
    
    public File getFileupload2() {
        return (this.fileupload2);
    }

    public void setFileupload2(File fileupload2) {
        this.fileupload2 = fileupload2;
    }

    
    public String getXdpGroupDocTypeId() {
        return xdpGroupDocTypeId;
    }

    public void setXdpGroupDocTypeId(String xdpGroupDocTypeId) {
        this.xdpGroupDocTypeId = xdpGroupDocTypeId;
    }
    

    public String getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(String templateFile) {
        this.templateFile = templateFile;
    }

    public String getTemplateFilePath(){
        return templateFilePath;
    }

    public void setTemplateFilePath(String templateFilePath) {
        this.templateFilePath = templateFilePath;
    }

    
    public XdpGroupDocTypeManager getXdpGroupDocTypeManager() {
        return xdpGroupDocTypeManager;
    }
    

    public void setXdpGroupDocTypeManager(XdpGroupDocTypeManager xdpGroupDocTypeManager) {
        this.xdpGroupDocTypeManager = xdpGroupDocTypeManager;
    }

    public List<XdpGroupDocType> getXdpGroupDocTypes() {
        return xdpGroupDocTypes;
    }
    
    public XdpProcessManager getXdpProcessManager() {
        return xdpProcessManager;
    }
    

    public void setXdpProcessManager(XdpProcessManager xdpProcessManager) {
        this.xdpProcessManager = xdpProcessManager;
    }

    
    public List<XdpGroup> getXdpGroups() {
        return xdpGroups;
    }
    
    public XdpGroup getXdpGroup() {
        return xdpGroup;
    }
    
    public void setXdpGroup(XdpGroup xdpGroup) {
        this.xdpGroup = xdpGroup;
    }
    
    public XdpProcess getXdpProcess() {
        return xdpProcess;
    }
    
    public void setXdpProcess(XdpProcess xdpProcess) {
        this.xdpProcess = xdpProcess;
    }



    
	public List<XdpProcess> getXdpProcesses() {
		return xdpProcesses;
	}

	public void setXdpProcesses(List<XdpProcess> xdpProcesses) {
		this.xdpProcesses = xdpProcesses;
	}
	
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }
    

    public XdpGroupDocType getXdpGroupDocType() {
        return xdpGroupDocType;
    }

    public void setXdpGroupDocType(XdpGroupDocType xdpGroupDocType) {
        this.xdpGroupDocType = xdpGroupDocType;
    }

    /**
     * Grab the entity from the database before populating with request parameters
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            String xdpGroupDocTypeId = getRequest().getParameter("xdpGroupDocType.id");
            if (xdpGroupDocTypeId != null && !xdpGroupDocTypeId.equals("")) {
                xdpGroupDocType = xdpGroupDocTypeManager.get(new Long(xdpGroupDocTypeId));
            }
        }
    }

    public String list() {
    	//add by wmc for refresh xdpGroupDocType page   	
    	if (xdpGroupDocTypeId != null && !"".equals(xdpGroupDocTypeId.trim())) {
        	getSession().setAttribute("XdpGroupDocTypeAction_search_xdpGroupDocTypeId", xdpGroupDocTypeId);
        	 try {
                 xdpGroupDocTypes = xdpGroupDocTypeManager.getXdpGroupDocTypes(xdpGroupDocTypeId);
             } catch (SearchException se) {
                 addActionError(se.getMessage());
                 xdpGroupDocTypes = xdpGroupDocTypeManager.getAll();
             }
         	if (xdpGroupDocTypeId != null) { // Added by wmc for save session
    			getSession().setAttribute("XdpGroupDocTypeAction_search_xdpGroupDocTypeId", xdpGroupDocTypeId);
    		}
        }    	
    	
    	if (xdpGroupDocTypeId == null || "".equals(xdpGroupDocTypeId.trim())) {
    		xdpGroupDocTypes = xdpGroupDocTypeManager.getXdpGroupDocTypes();
        	if (xdpGroupDocTypeId != null) { // Added by wmc for save session
    			getSession().setAttribute("XdpGroupDocTypeAction_search_xdpGroupDocTypeId", xdpGroupDocTypeId);
    		}
    	}    	
        return SUCCESS;
    }

    public String delete() {
    	try {
        xdpGroupDocTypeManager.remove(xdpGroupDocType.getId());
        } catch (Exception e) {
    		List<Object> args = new ArrayList<Object>();
    	    args.add(getText("xdpGroupDocTypeList.xdpGroupDocType"));
            args.add(xdpGroupDocType.getName());
            addActionError(getText("errors.delete", args));
            return INPUT;
        }
        List<Object> args = new ArrayList<Object>();
        args.add(xdpGroupDocType.getName());
        saveMessage(getText("xdpGroupDocType.deleted"));
        return SUCCESS;
    }

    public String edit() {
        if (id != null) {
        	try {
                xdpGroupDocType = xdpGroupDocTypeManager.get(id);
    		} catch (Exception e) {
    			List<Object> args = new ArrayList<Object>();
            	args.add(getText("xdpGroupDocTypeList.xdpGroupDocType"));
                args.add(id.toString());
                addActionError(getText("errors.get", args));
                list();
                return ERROR;
    		}
        } else {
            xdpGroupDocType = new XdpGroupDocType();
            xdpGroupDocType.setEnabled(true); 
        }

        return SUCCESS;
    }

    public String save() throws Exception {
        if (cancel != null) {
            return "cancel";
        }
        //add by wmc 2013/10/21
        if (xdpGroupDocType != null){
        //add by wmc 2013/10/16 for uploading
        //for get the newest mgr
            List<Long> mgrXdpGroups = new ArrayList<Long>();
            xdpGroupDocTypes = xdpGroupDocTypeManager.getAll();
    	    if (xdpGroupDocTypes != null && xdpGroupDocTypes.size() > 0) {
                for (XdpGroupDocType xdpGroupDocType : xdpGroupDocTypes) {
            	    Long Mgr = xdpGroupDocType.getMaxMgr();            
                    mgrXdpGroups.add(Mgr);
                }
                Long max = mgrXdpGroups.get(0);
                for(int x = 1; x <mgrXdpGroups.size(); x++ ) {
            	    if(mgrXdpGroups.get(x)  >max){
            		    max = mgrXdpGroups.get(x);
            	    }
                }
                this.maxMgr=max+1;
                xdpGroupDocType.setMaxMgr(maxMgr);
    	    }
            

            // the directory to upload to
            String path=Constants.SDPPATH + xdpGroupDocType.getMaxMgr();
            String uploadDir = xdpGroupDocType.getRuleFilePath() + path + "/";
            String templateFileUploadDir = xdpGroupDocType.getTemplateFilePath() + path + "/";

            // write the file to the file specified
            File dirPath = new File(uploadDir);
            File templatedirPath = new File(templateFileUploadDir);
            //create path if path Not exist

            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
         
            if (!templatedirPath.exists()) {
            	templatedirPath.mkdirs();
            }
            
            //retrieve the file data
            if (fileupload1FileName != null){
            File files = getFileupload1();
            String filename = fileupload1FileName;
            String filePath = xdpGroupDocType.getRuleFilePath();
            xdpGroupDocTypeManager.upload(path,filename,filePath,files);
            xdpGroupDocType.setRuleFile(filename); 
            }
            
            if (fileupload2FileName != null){
            File files = getFileupload2();
            String filename = fileupload2FileName;
            String filePath = xdpGroupDocType.getTemplateFilePath();
            xdpGroupDocTypeManager.upload(path,filename,filePath,files);
            xdpGroupDocType.setTemplateFile(filename);
            }

  
            if (delete != null) {
                return delete();
            }
            if (xdpGroupDocType != null) {
                boolean isNew = (xdpGroupDocType.getId() == null);
                xdpGroupDocType.setMaxMgr(maxMgr);
                if (xdpGroupDocTypeManager != null) {
                	try {
                        xdpGroupDocType = xdpGroupDocTypeManager.saveXdpGroupDocType(xdpGroupDocType);
                    } catch (Exception e) {
                    	List<Object> args = new ArrayList<Object>();
                    	args.add(getText("xdpGroupDocTypeList.xdpGroupDocType"));
                        args.add(xdpGroupDocType.getName());
                        addActionError(getText("errors.save", args));
                        return INPUT;
                    }
                    String key = (isNew) ? "xdpGroupDocType.added" : "xdpGroupDocType.updated";
                    saveMessage(getText(key)); 
                    if (!isNew) {
                        return INPUT;
                    } else {
                        return SUCCESS;
                    }

                }
            }
            
        }
        return SUCCESS;       
    }
    
   
    // Get available xdpGroupDocTypes.add by wmc 2013/10/10
    public List<LabelValue> getAvailableXdpGroups() {
    	List<LabelValue> availableXdpGroups = new ArrayList<LabelValue>();	
    	if (id == null) {
            availableXdpGroups.add(new LabelValue(getText("label.pleaseSelect"), ""));
        	List<XdpGroup> xdpGroups = null;
        	xdpGroups = xdpGroupManager.getXdpGroupsEnabled();
    	    if (xdpGroups != null && xdpGroups.size() > 0) {
                for (XdpGroup xdpGroup : xdpGroups) {
            	    String label = xdpGroup.getName();
            	    if (xdpGroup.isEnabled() == false) {
            		    label += getText("label.disabled");
           		    }
            	    availableXdpGroups.add(new LabelValue(label, xdpGroup.getId().toString()));
                }
            }
    	}
            return availableXdpGroups;	
    }
    
    public List<LabelValue> getAvailableXdpProcesses() {
    	List<LabelValue> availableXdpProcesses = new ArrayList<LabelValue>();
    	
        	availableXdpProcesses.add(new LabelValue(getText("label.pleaseSelect"), ""));
        	List<XdpProcess> xdpProcesses = null;
        	xdpProcesses = xdpProcessManager.getXdpProcessesEnabled();   	
    	    if (xdpProcesses != null && xdpProcesses.size() > 0) {
                for (XdpProcess xdpProcess : xdpProcesses) {
            	    String label = xdpProcess.getCode();
            	    if (xdpProcess.isEnabled() == false) {
            		    label += getText("label.disabled");
           		    }
            	    availableXdpProcesses.add(new LabelValue(label, xdpProcess.getId().toString()));
                }
    	}
    	return availableXdpProcesses;
    }

    
    public InputStream  getInputStream() throws IOException {
        String filepath = "";
        if (filepath != null&& id != null) {
        	xdpGroupDocType = xdpGroupDocTypeManager.get(id);
        	String path=Constants.SDPPATH + xdpGroupDocType.getMaxMgr();
        	filepath = xdpGroupDocType.getRuleFilePath() + path + "/" + xdpGroupDocType.getRuleFile();
        	this.ruleFile=xdpGroupDocType.getRuleFile();
        }
        return new FileInputStream(filepath);
}
    
    public InputStream  getInputStream2() throws IOException {
        String filepath = "";
        if (filepath != null&& id != null) {
        	xdpGroupDocType = xdpGroupDocTypeManager.get(id);
        	String path=Constants.SDPPATH + xdpGroupDocType.getMaxMgr();
        	filepath = xdpGroupDocType.getTemplateFilePath() + path + "/" + xdpGroupDocType.getTemplateFile();
        	this.templateFile=xdpGroupDocType.getTemplateFile();
        }
        return new FileInputStream(filepath);
}    
    
    //add by wmc 2013/10/14 for download
    public String download() throws Exception {

        return SUCCESS;	
    }
    public String download2() throws Exception {

        return SUCCESS;
    }
}