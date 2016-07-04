<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="demoList.title"/></title>
    <meta name="heading" content="<fmt:message key='demoList.heading'/>"/>
    <meta name="menu" content="AdminMenu"/> 
</head>

<div>
    <h2><fmt:message key="demoList.heading"/></h2>

    <form method="get" action="${ctx}/demoes" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="demoList.message"/>

    <div id="actions" class="form-actions well">
        <a class="btn btn-primary" href="<c:url value='/editDemo'/>" >
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/>
        </a>
        <a class="btn" href="<c:url value="/mainMenu"/>" >
            <i class="icon-ok"></i> <fmt:message key="button.done"/>
        </a>
    </div>

    <display:table name="demoes" class="table table-condensed table-striped table-hover" requestURI="" id="demoList" export="true" pagesize="25" defaultsort="1" defaultorder="descending" sort="list" varTotals="totals">
        <display:column property="id" sortable="true" titleKey="demo.id" style="width:40px"/>
        <display:column property="name" sortable="true" titleKey="demo.name" href="editDemo" paramId="id" paramProperty="id" media="html" maxLength="20"/>
        <display:column property="name" titleKey="demo.name" media="csv excel xml pdf"/>
        <display:column property="price" sortable="true" titleKey="demo.price" format="${currencyFormat}" style="text-align:right" headerClass="headerRight"/>
        <display:column property="qty" sortable="true" titleKey="demo.qty" format="${numberFormat}" style="text-align:right" headerClass="headerRight" total="true"/>
        <display:column property="total" sortable="true" titleKey="demo.total" format="${currencyFormat}" style="text-align:right" headerClass="headerRight" total="true"/>
        <display:column property="discountRate" sortable="true" titleKey="demo.discountRate" format="${discountFormat}" style="text-align:right" headerClass="headerRight"/>
        <display:column property="percent" sortable="true" titleKey="demo.percent" format="${percentFormat}" style="text-align:right" headerClass="headerRight"/>
        <display:column property="date" sortable="true" titleKey="demo.date" format="${dateFormat}" maxLength="10"/>
        <display:column property="datetime" sortable="true" titleKey="demo.datetime" format="${datetimeFormat}" maxLength="19"/>
        <display:column sortProperty="enabled" sortable="true" titleKey="demo.enabled" style="padding-left: 15px" media="html">
            <input type="checkbox" disabled="disabled" <c:if test="${demoList.enabled}">checked="checked"</c:if>/>
        </display:column>
        <display:column property="enabled" titleKey="demo.enabled" media="csv xml excel pdf"/>
        <display:column property="createDate" sortable="true" titleKey="demo.createDate" format="${datetimeFormat}"/>
        <display:column property="updateDate" sortable="true" titleKey="demo.updateDate" format="${datetimeFormat}"/>

        <display:footer media="html">
        <tr>
            <td></td><td></td>
            <td style="text-align:right"><fmt:message key="label.total"/>:</td>
            <td style="text-align:right"><fmt:formatNumber value="${totals.column4}" pattern="${numberPattern}"/></td>
            <td style="text-align:right"><fmt:formatNumber value="${totals.column5}" pattern="${currencyPattern}"/></td>
            <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
        <tr>
        </display:footer>
        <display:caption media="csv xml excel pdf"><fmt:message key="demoList.heading"/></display:caption>
        <display:footer media="csv xml excel pdf">
            <fmt:message key="label.total"/>(<fmt:message key="demo.qty"/>): <fmt:formatNumber value="${totals.column4}" pattern="${numberPattern}"/>; 
            <fmt:message key="label.total"/>(<fmt:message key="demo.total"/>): <fmt:formatNumber value="${totals.column5}" pattern="${currencyPattern}"/>; 
        </display:footer>

        <display:setProperty name="paging.banner.item_name"><fmt:message key="demoList.demo"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="demoList.demoes"/></display:setProperty>

        <display:setProperty name="export.excel.filename"><fmt:message key="demoList.title"/>.xls</display:setProperty>
        <display:setProperty name="export.csv.filename"><fmt:message key="demoList.title"/>.csv</display:setProperty>
        <display:setProperty name="export.pdf.filename"><fmt:message key="demoList.title"/>.pdf</display:setProperty>
    </display:table>
</div>
