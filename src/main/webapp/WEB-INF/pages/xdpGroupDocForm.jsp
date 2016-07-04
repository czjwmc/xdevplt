<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="xdpGroupDocDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='xdpGroupDocDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="xdpGroupDocList.xdpGroupDoc"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="xdpGroupDocDetail.heading"/></h2>
    <fmt:message key="xdpGroupDocDetail.message"/>
</div>

<div class="span7">
    <s:form id="xdpGroupDocForm" action="saveXdpGroupDoc" method="post" validate="true" cssClass="well form-horizontal">
            <s:hidden key="xdpGroupDoc.id"/>
        <s:textfield key="xdpGroupDoc.checkDate" required="true" maxlength="255" size="11" title="date" datepicker="true"/>
        <s:textfield key="xdpGroupDoc.checkUser" required="true" maxlength="255" />
        <s:textfield key="xdpGroupDoc.createDate" required="true" maxlength="255" size="11" title="date" datepicker="true"/>
        <s:textfield key="xdpGroupDoc.createUser" required="true" maxlength="255" />
        <s:textfield key="xdpGroupDoc.description" required="false" maxlength="200" />
        <s:textfield key="xdpGroupDoc.docFile" required="false" maxlength="500" />
        <s:textfield key="xdpGroupDoc.docName" required="false" maxlength="100" />
        <s:checkbox key="xdpGroupDoc.enabled" theme="css_xhtml"/>
        <s:textfield key="xdpGroupDoc.mgrNo" required="true" maxlength="8" />
        <s:textfield key="xdpGroupDoc.name" required="true" maxlength="50" />
        <s:textfield key="xdpGroupDoc.updateDate" required="true" maxlength="255" size="11" title="date" datepicker="true"/>
        <s:textfield key="xdpGroupDoc.updateUser" required="true" maxlength="255" />
        <s:textfield key="xdpGroupDoc.ver" required="true" maxlength="10" />
        <s:textfield key="xdpGroupDoc.version" required="false" maxlength="255" />

        <div id="actions" class="form-actions">
            <s:submit type="button" cssClass="btn btn-primary" method="save" key="button.save" theme="simple">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </s:submit>
            <c:if test="${not empty xdpGroupDoc.id}">
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
        $("input[type='text']:visible:enabled:first", document.forms['xdpGroupDocForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
