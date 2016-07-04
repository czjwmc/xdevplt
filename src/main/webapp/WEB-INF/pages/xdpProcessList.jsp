<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="xdpProcessList.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>

<div class="span10">
    <h2><fmt:message key="xdpProcessList.heading"/></h2>
    <fmt:message key="xdpProcessList.message"/>

    <div id="actions" class="form-actions">
        <a class="btn btn-primary" href="<c:url value='/editXdpProcess'/>" >
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/>
        </a>
        <a class="btn" href="<c:url value="/mainMenu"/>" >
            <i class="icon-ok"></i> <fmt:message key="button.done"/>
        </a>
    </div>

    <display:table name="xdpProcesses" class="table table-condensed table-striped table-hover" requestURI="" id="xdpProcessList" export="true" pagesize="25">
        <display:column property="id" sortable="true"  media="html" titleKey="xdpProcess.id"/>        
        <display:column property="id" media="csv excel xml pdf" titleKey="xdpProcess.id"/>
        <display:column property="name" href="editXdpProcess"  paramId="id" paramProperty="id" sortable="true" titleKey="xdpProcess.name"/>        
        <display:column property="code" sortable="true" titleKey="xdpProcess.code"/>
        <display:column property="sortNo" sortable="true" titleKey="xdpProcess.sortNo"/>       
        <display:column sortProperty="enabled" sortable="true" titleKey="xdpProcess.enabled">
            <input type="checkbox" disabled="disabled" <c:if test="${xdpProcessList.enabled}">checked="checked"</c:if>/>
        </display:column>
        <display:setProperty name="paging.banner.item_name"><fmt:message key="xdpProcessList.xdpProcess"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="xdpProcessList.xdpProcesses"/></display:setProperty>

        <display:setProperty name="export.excel.filename"><fmt:message key="xdpProcessList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="xdpProcessList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="xdpProcessList.title"/>.pdf</display:setProperty>
    </display:table>
</div>
