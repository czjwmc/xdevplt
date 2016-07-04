<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="xdpGroupDocList.title"/></title>
    <meta name="menu" content="XdpGroupDocMenu"/>
</head>

<div class="span10">
    <h2><fmt:message key="xdpGroupDocList.heading"/></h2>
    <fmt:message key="xdpGroupDocList.message"/>
    <form method="post" action="${ctx}/xdpGroupDocTypes" id="searchForm" class="form-search">
    <s:select name="xdpGroupDocTypeId" list="availableXdpGroups" listKey="value" listValue="label"
        label="%{getText('xdpGroup')}"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i>
            <fmt:message key="button.search"/>
        </button>     
    
    <div id="actions" class="form-actions">
        <a class="btn btn-primary" href="<c:url value='/editXdpGroupDoc'/>" >
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/>
        </a>
        <a class="btn" href="<c:url value="/mainMenu"/>" >
            <i class="icon-ok"></i> <fmt:message key="button.done"/>
        </a>
    </div>

    <display:table name="xdpGroupDocs" class="table table-condensed table-striped table-hover" requestURI="" id="xdpGroupDocList" export="true" pagesize="25">
        <display:column property="id" sortable="true" href="editXdpGroupDoc" media="html"
            paramId="id" paramProperty="id" titleKey="xdpGroupDoc.id"/>
        <display:column property="id" media="csv excel xml pdf" titleKey="xdpGroupDoc.id"/>
        <display:column property="name" sortable="true" titleKey="xdpGroupDoc.name"/>        
        <display:column property="docFile" sortable="true" titleKey="xdpGroupDoc.docFile"/>
        <display:column property="docName" sortable="true" titleKey="xdpGroupDoc.docName"/>
        <display:column sortProperty="enabled" sortable="true" titleKey="xdpGroupDoc.enabled">
            <input type="checkbox" disabled="disabled" <c:if test="${xdpGroupDocList.enabled}">checked="checked"</c:if>/>
        </display:column>
        <display:column property="mgrNo" sortable="true" titleKey="xdpGroupDoc.mgrNo"/>
        <display:setProperty name="paging.banner.item_name"><fmt:message key="xdpGroupDocList.xdpGroupDoc"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="xdpGroupDocList.xdpGroupDocs"/></display:setProperty>

        <display:setProperty name="export.excel.filename"><fmt:message key="xdpGroupDocList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="xdpGroupDocList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="xdpGroupDocList.title"/>.pdf</display:setProperty>
    </display:table>
    </form>
</div>
