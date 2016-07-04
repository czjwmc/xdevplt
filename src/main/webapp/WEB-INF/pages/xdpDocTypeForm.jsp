<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="xdpDocTypeDetail.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="xdpDocTypeList.XdpDocType"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="xdpDocTypeDetail.heading"/></h2>
    <fmt:message key="xdpDocTypeDetail.message"/>
</div>

<div class="span7">
    <s:form id="xdpDocTypeForm" action="saveXdpDocType" method="post" validate="true" cssClass="well form-horizontal">
        <s:hidden key="xdpDocType.id"/>
        <s:hidden key="xdpDocType.updateUser.id"/>
        <s:hidden key="xdpDocType.updateDate"/>  
        <fieldset class="control-group">
            <label class="control-label"><fmt:message key="xdpDocType.id"/>:</label>
            <div class="controls readonly">
                <s:property value="xdpDocType.id"/>
            </div>
        </fieldset>
        <s:textfield key="xdpDocType.name" required="true" maxlength="50" />
        <s:checkbox key="xdpDocType.enabled" theme="css_xhtml"/>
        <s:textfield key="xdpDocType.ruleFile" required="false" maxlength="500" />
        <s:textfield key="xdpDocType.ruleName" required="false" maxlength="50" />
        <s:textfield key="xdpDocType.sortNo" required="true" maxlength="2" style="width:60px"/>
        <s:textfield key="xdpDocType.templateFile" required="false" maxlength="500" />
        <s:textfield key="xdpDocType.templateName" required="false" maxlength="50" />
        <s:textfield key="xdpDocType.description" required="false" maxlength="200" />
        <c:if test="${not empty xdpDocType.id}">
            <fieldset class="control-group">
            <label class="control-label"><fmt:message key="xdpDocType.updateUser"/>:</label>
            <div class="controls readonly">
                <s:property value="xdpDocType.updateUser.fullName"/>
                <c:if test="${xdpDocType.updateUser.enabled=='false'}">(<fmt:message key="label.disabled"/>)</c:if>
            </div>
            </fieldset>
            <fieldset class="control-group">
            <label class="control-label"><fmt:message key="xdpDocType.updateDate"/>:</label>
            <div class="controls readonly">
                <s:date name="xdpDocType.updateDate" format="%{getText('datetime.format')}" />
            </div>
            </fieldset>  
        </c:if>
 
        <s:textfield key="xdpDocType.version" required="false" maxlength="255" style="width:60px"/>
        

        <div id="actions" class="form-actions">
            <s:submit type="button" cssClass="btn btn-primary" method="save" key="button.save" theme="simple">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </s:submit>
            <c:if test="${not empty XdpDocType.id}">
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
        $("input[type='text']:visible:enabled:first", document.forms['XdpDocTypeForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
