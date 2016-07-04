<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="xdpSystemList.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>

<div class="span10">
    <h2><fmt:message key="xdpSystemList.heading"/></h2>

    <fmt:message key="xdpSystemList.message"/>
    
    <div id="actions" class="form-actions">
        <a class="btn" href="<c:url value="/mainMenu"/>" >
            <i class="icon-ok"></i> <fmt:message key="button.done"/>
        </a>
    </div>    

    <display:table name="xdpSystems" class="table table-condensed table-striped table-hover" requestURI="" id="xdpSystemList" export="true" pagesize="25">
        <display:column property="id" sortable="true" titleKey="xdpSystem.id"  media="html" />      
        <display:column property="id" media="csv excel xml pdf" titleKey="xdpSystem.id"/>
        <display:column property="name" href="editXdpSystem" paramId="id" paramProperty="id" sortable="true" titleKey="xdpSystem.name"/>
        <display:column property="filePath" sortable="true" titleKey="xdpSystem.filePath"/>        
        <display:setProperty name="paging.banner.item_name"><fmt:message key="xdpSystemList.xdpSystem"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="xdpSystemList.xdpSystems"/></display:setProperty>

        <display:setProperty name="export.excel.filename"><fmt:message key="xdpSystemList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="xdpSystemList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="xdpSystemList.title"/>.pdf</display:setProperty>
    </display:table>
</div>
