<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="xdpDocTypeList.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>

<div class="span10">
    
    <h2><fmt:message key="xdpDocTypeList.heading"/></h2>
    <fmt:message key="xdpDocTypeList.message"/>

    <div id="actions" class="form-actions">
        <a class="btn btn-primary" href="<c:url value='/editXdpDocType'/>" >
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/>
        </a>
        <a class="btn" href="<c:url value="/mainMenu"/>" >
            <i class="icon-ok"></i> <fmt:message key="button.done"/>
        </a>
    </div>

    <display:table name="xdpDocTypes" class="table table-condensed table-striped table-hover" requestURI="" id="xdpDocTypeList" export="true" pagesize="25">
        <display:column property="id" sortable="true" href="editXdpDocType" media="html"
            paramId="id" paramProperty="id" titleKey="xdpDocType.id"/>
        <display:column property="id" media="csv excel xml pdf" titleKey="xdpDocType.id"/>
        <display:column property="name" sortable="true" titleKey="xdpDocType.name"/>
        <display:column sortable="true" titleKey="xdpDocType.xdpProcess.code" maxLength="20">
            ${xdpDocTypeList.xdpProcess.code}<c:if test="${xdpDocTypeList.xdpProcess.enabled=='false'}"><fmt:message key="label.disabled"/></c:if>
        </display:column>
        <display:column property="sortNo" sortable="true" titleKey="xdpDocType.sortNo"/>              
        <display:column property="ruleFile" sortable="true" titleKey="xdpDocType.ruleFile"/>
        <display:column property="ruleName" sortable="true" titleKey="xdpDocType.ruleName"/>
        <display:column property="templateFile" sortable="true" titleKey="xdpDocType.templateFile"/>
        <display:column property="templateName" sortable="true" titleKey="xdpDocType.templateName"/>
        <display:column sortProperty="enabled" sortable="true" titleKey="xdpDocType.enabled">
            <input type="checkbox" disabled="disabled" <c:if test="${xdpDocTypeList.enabled}">checked="checked"</c:if>/>
        </display:column>
        <display:setProperty name="paging.banner.item_name"><fmt:message key="xdpDocTypeList.XdpDocType"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="xdpDocTypeList.XdpDocTypes"/></display:setProperty>

        <display:setProperty name="export.excel.filename"><fmt:message key="xdpDocTypeList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="xdpDocTypeList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="xdpDocTypeList.title"/>.pdf</display:setProperty>
    </display:table>
</div>
