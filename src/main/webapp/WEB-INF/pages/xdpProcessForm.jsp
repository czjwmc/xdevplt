<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="xdpProcessDetail.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="xdpProcessList.xdpProcess"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="xdpProcessDetail.heading"/></h2>
    <fmt:message key="xdpProcessDetail.message"/>
</div>

<div class="span7">
    <s:form id="xdpProcessForm" action="saveXdpProcess" method="post" validate="true" cssClass="well form-horizontal">
            <s:hidden key="xdpProcess.id"/>
            <s:hidden key="xdpProcess.updateUser.id"/>
            <s:hidden key="xdpProcess.updateDate"/>
        <s:textfield key="xdpProcess.name" required="true" maxlength="50" />            
        <s:textfield key="xdpProcess.code" required="true" maxlength="2" />
        <s:textfield key="xdpProcess.sortNo" required="true" maxlength="2" style="width:60px"/>
        <s:textfield key="xdpProcess.description" required="false" maxlength="200" />
        <s:checkbox key="xdpProcess.enabled" theme="css_xhtml"/>      
        <c:if test="${not empty xdpProcess.id}">
            <fieldset class="control-group">
            <label class="control-label"><fmt:message key="xdpProcess.updateUser"/>:</label>
            <div class="controls readonly">
                <s:property value="xdpProcess.updateUser.fullName"/>
                <c:if test="${xdpProcess.updateUser.enabled=='false'}">(<fmt:message key="label.disabled"/>)</c:if>
            </div>
            </fieldset>
            <fieldset class="control-group">
            <label class="control-label"><fmt:message key="xdpProcess.updateDate"/>:</label>
            <div class="controls readonly">
                <s:date name="xdpProcess.updateDate" format="%{getText('datetime.format')}" />
            </div>
            </fieldset>  
        </c:if>
        <div id="actions" class="form-actions">
            <s:submit type="button" cssClass="btn btn-primary" method="save" key="button.save" theme="simple">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </s:submit>
            <c:if test="${not empty xdpProcess.id}">
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
        $("input[type='text']:visible:enabled:first", document.forms['xdpProcessForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
