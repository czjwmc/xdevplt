<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="xdpGroupList.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>

<div class="span10">
    <h2><fmt:message key="xdpGroupList.heading"/></h2>
    <fmt:message key="xdpGroupList.message"/>

    <div id="actions" class="form-actions">
        <a class="btn btn-primary" href="<c:url value='/editXdpGroup'/>" >
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/>
        </a>
        <a class="btn" href="<c:url value="/mainMenu"/>" >
            <i class="icon-ok"></i> <fmt:message key="button.done"/>
        </a>
    </div>

    <display:table name="xdpGroups" class="table table-condensed table-striped table-hover" requestURI="" id="xdpGroupList" export="true" pagesize="25">
        <display:column property="id" sortable="true"  media="html" titleKey="xdpGroup.id"/>
        <display:column property="id" media="csv excel xml pdf" titleKey="xdpGroup.id"/>
        <display:column property="name" sortable="true" titleKey="xdpGroup.name" href="editXdpGroup" paramId="id" paramProperty="id"/>
        <display:column property="code" sortable="true" titleKey="xdpGroup.code"/>
        <display:column property="email" sortable="true" titleKey="xdpGroup.email"/>
        <display:column sortProperty="enabled" sortable="true" titleKey="xdpGroup.enabled">
            <input type="checkbox" disabled="disabled" <c:if test="${xdpGroupList.enabled}">checked="checked"</c:if>/>
        </display:column>
        <display:column property="enabled" titleKey="xdpGroup.enabled" media="csv xml excel pdf"/>
        <display:setProperty name="paging.banner.item_name"><fmt:message key="xdpGroupList.xdpGroup"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="xdpGroupList.xdpGroups"/></display:setProperty>

        <display:setProperty name="export.excel.filename"><fmt:message key="xdpGroupList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="xdpGroupList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="xdpGroupList.title"/>.pdf</display:setProperty>
    </display:table>
</div>
