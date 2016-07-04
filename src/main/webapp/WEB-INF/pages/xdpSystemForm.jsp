<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="xdpSystemDetail.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="xdpSystemList.xdpSystem"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="xdpSystemDetail.heading"/></h2>
    <fmt:message key="xdpSystemDetail.message"/>
</div>

<div class="span7">
    <s:form id="xdpSystemForm" action="saveXdpSystem" method="post" validate="true" cssClass="well form-horizontal">
            <s:hidden key="xdpSystem.id"/>
        <s:textfield key="xdpSystem.name" required="true" maxlength="50" />    
        <s:textfield key="xdpSystem.filePath" required="true" maxlength="100" />
        <s:textfield key="xdpSystem.description" required="false" maxlength="200" />
        <c:if test="${not empty xdpSystem.id}">
           <fieldset class="control-group">
           <label class="control-label"><fmt:message key="xdpSystem.updateUser"/>:</label>
           <div class="controls readonly">
               <s:property value="xdpSystem.updateUser.fullName"/>
               <c:if test="${xdpSystem.updateUser.enabled=='false'}">(<fmt:message key="label.disabled"/>)</c:if>
           </div>
           </fieldset>
           <fieldset class="control-group">
           <label class="control-label"><fmt:message key="xdpSystem.updateDate"/>:</label>
           <div class="controls readonly">
               <s:date name="xdpSystem.updateDate" format="%{getText('datetime.format')}" />
           </div>
           </fieldset>  
       </c:if>

        <div id="actions" class="form-actions">
            <s:submit type="button" cssClass="btn btn-primary" method="save" key="button.save" theme="simple">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </s:submit>
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
        $("input[type='text']:visible:enabled:first", document.forms['xdpSystemForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
