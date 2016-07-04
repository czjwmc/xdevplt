<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="xdpGroupDocTypeList.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>

<div class="span10">
    <h2><fmt:message key="xdpGroupDocTypeList.heading"/></h2>    

    <fmt:message key="xdpGroupDocTypeList.message"/>
    <form method="post" action="${ctx}/xdpGroupDocTypes" id="searchForm" class="form-search">
<div id="search" class="input-append" >
    <s:select name="xdpGroupDocTypeId" list="availableXdpGroups" listKey="value" listValue="label"class="input-medium search-query"/>             
    <button id="button.search" class="btn" type="submit">
        <i class="icon-search"></i>
        <fmt:message key="button.search"/>
    </button>    
</div> 


    <div id="actions" class="form-actions">
        <a class="btn btn-primary" href="<c:url value='/editXdpGroupDocType'/>" >
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/>
        </a>
        <a class="btn" href="<c:url value="/mainMenu"/>" >
            <i class="icon-ok"></i> <fmt:message key="button.done"/>
        </a>
    </div>
    <display:table name="xdpGroupDocTypes" class="table table-condensed table-striped table-hover" requestURI="" id="xdpGroupDocTypeList" export="true" pagesize="25">
        <display:column property="id" sortable="true"  media="html" titleKey="xdpGroupDocType.id"/>         
        <display:column property="id" media="csv excel xml pdf" titleKey="xdpGroupDocType.id"/>
        <display:column property="name" href="editXdpGroupDocType" paramId="id" paramProperty="id" sortable="true" titleKey="xdpGroupDocType.name" maxLength="15"/>

        <display:column sortable="true" titleKey="xdpGroupDocType.xdpGroup.name" maxLength="20">
            ${xdpGroupDocTypeList.xdpGroup.name}<c:if test="${xdpGroupDocTypeList.xdpGroup.enabled=='false'}"><fmt:message key="label.disabled"/></c:if>
        </display:column>
        <display:column sortable="true" titleKey="xdpGroupDocType.xdpProcess.code" maxLength="20">
            ${xdpGroupDocTypeList.xdpProcess.code}<c:if test="${xdpGroupDocTypeList.xdpProcess.enabled=='false'}"><fmt:message key="label.disabled"/></c:if>
        </display:column>
        <display:column property="sortNo" sortable="true" titleKey="xdpGroupDocType.sortNo"/>       
        <display:column property="docNum" sortable="true" titleKey="xdpGroupDocType.docNum"/>
        <display:column sortable="true"   href="filedownload?id=${xdpGroupDocTypeList.id}"  paramId="id" paramProperty="id" titleKey="xdpGroupDocType.ruleName">${xdpGroupDocTypeList.ruleName}</display:column>                           
        <display:column sortable="true"   href="templateFiledownload?id=${xdpGroupDocTypeList.id}" paramId="id" paramProperty="id" titleKey="xdpGroupDocType.templateName"  >${xdpGroupDocTypeList.templateName}</display:column>
        <display:column sortProperty="enabled" sortable="true" titleKey="xdpGroupDocType.enabled">
            <input type="checkbox" disabled="disabled" <c:if test="${xdpGroupDocTypeList.enabled}">checked="checked"</c:if>/>
        </display:column> 
        <display:setProperty name="paging.banner.item_name"><fmt:message key="xdpGroupDocTypeList.xdpGroupDocType"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="xdpGroupDocTypeList.xdpGroupDocTypes"/></display:setProperty>

        <display:setProperty name="export.excel.filename"><fmt:message key="xdpGroupDocTypeList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="xdpGroupDocTypeList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="xdpGroupDocTypeList.title"/>.pdf</display:setProperty>
    </display:table>
</form>
</div>

    
