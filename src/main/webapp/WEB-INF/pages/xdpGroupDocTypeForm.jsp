<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="xdpGroupDocTypeDetail.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="xdpGroupDocTypeList.xdpGroupDocType"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="xdpGroupDocTypeDetail.heading"/></h2>
    <fmt:message key="xdpGroupDocTypeDetail.message"/>
</div>

<div class="span8">
    <s:form id="xdpGroupDocTypeForm" action="saveXdpGroupDocType" enctype="multipart/form-data" method="post" validate="true" cssClass="well form-horizontal">
        <s:hidden key="xdpGroupDocType.id"/>
        <s:hidden key="xdpGroupDocType.ruleFile"/>
        <s:hidden key="xdpGroupDocType.updateUser.id"/>
        <s:hidden key="xdpGroupDocType.xdpGroup.id"/>
        <s:hidden key="xdpGroupDocType.xdpProcess.id"/>
        <s:hidden key="xdpGroupDocType.updateDate"/>  
        <fieldset class="control-group">
            <label class="control-label"><fmt:message key="xdpGroupDocType.id"/>:</label>
            <div class="controls readonly">
                <s:property value="xdpGroupDocType.id"/>
            </div>
        </fieldset>              
        <s:textfield key="xdpGroupDocType.name" required="true" maxlength="50" />
        <s:select name="xdpGroupDocType.xdpGroup.id"  list="availableXdpGroups" listKey="value" listValue="label" label="%{getText('XdpGroup')}"/>
        <s:select name="xdpGroupDocType.xdpProcess.id" list="availableXdpProcesses" listKey="value" listValue="label" label="%{getText('Code')}"/>
        <s:textfield key="xdpGroupDocType.docNum" required="true" maxlength="4" style="width:60px"/>
        <s:checkbox key="xdpGroupDocType.enabled" theme="css_xhtml"/>
        <fieldset class="control-group">
            <label class="control-label"><fmt:message key="xdpGroupDocType.ruleFile"/>:</label>
            <div class="controls readonly">
                <a href="filedownload?ruleFile=${xdpGroupDocType.ruleFile}&id=${xdpGroupDocType.id}"/> <c:out value="${xdpGroupDocType.ruleFile}"></c:out></a>
            </div>
        </fieldset>                         
        <s:file name="fileupload1" label="%{getText('xdpGroupDocTypeForm.fileupload')}" />
        <s:textfield key="xdpGroupDocType.ruleName" required="false" maxlength="50" />
        <s:textfield key="xdpGroupDocType.sortNo" required="true" maxlength="2" style="width:60px"/>
        <fieldset class="control-group">
            <label class="control-label"><fmt:message key="xdpGroupDocType.templateName"/>:</label>
            <div class="controls readonly">
                <a href="templateFiledownload?templateFile=${xdpGroupDocType.templateFile}&id=${xdpGroupDocType.id}"/> <c:out value="${xdpGroupDocType.templateFile}"></c:out></a>
            </div>
        </fieldset>
        <s:file name="fileupload2" label="%{getText('xdpGroupDocTypeForm.templateFileupload')}" />
        <s:textfield key="xdpGroupDocType.templateName" required="false" maxlength="50" />
        <s:textfield key="xdpGroupDocType.description" required="false" maxlength="200" />        
        <c:if test="${not empty xdpGroupDocType.id}">
            <fieldset class="control-group">
            <label class="control-label"><fmt:message key="xdpGroupDocType.updateUser"/>:</label>
            <div class="controls readonly">
                <s:property value="xdpGroupDocType.updateUser.fullName"/>
                <c:if test="${xdpGroupDocType.updateUser.enabled=='false'}">(<fmt:message key="label.disabled"/>)</c:if>
            </div>
            </fieldset>
            <fieldset class="control-group">
            <label class="control-label"><fmt:message key="xdpGroupDocType.updateDate"/>:</label>
            <div class="controls readonly">
                <s:date name="xdpGroupDocType.updateDate" format="%{getText('datetime.format')}" />
            </div>
            </fieldset>  
        </c:if>
        <div id="actions" class="form-actions">
            <s:submit type="button" cssClass="btn btn-primary" method="save" key="button.save" theme="simple">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </s:submit>
            <c:if test="${not empty xdpGroupDocType.id}">
                <s:submit type="button" cssClass="btn btn-warning" method="delete" key="button.delete"
                    onclick="return confirmMessage(msgDelConfirm)" theme="simple">
                    <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
                </s:submit>
            </c:if>
            <s:submit type="button" cssClass="btn" method="cancel" key="button.cancel" theme="simple">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </s:submit>
        </div>
    </s:form>
</div>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['xdpGroupDocTypeForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
