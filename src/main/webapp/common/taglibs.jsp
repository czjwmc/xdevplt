<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://struts-menu.sf.net/tag-el" prefix="menu" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.appfuse.org/tags/struts" prefix="appfuse" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="datePattern"><fmt:message key="date.format"/></c:set>

<%-- Start: Added by wx Ver0.0.1. --%>
<c:set var="dateFormat">{0,date,<fmt:message key="date.format"/>}</c:set>
<c:set var="datetimePattern"><fmt:message key="datetime.format"/></c:set>
<c:set var="datetimeFormat">{0,date,<fmt:message key="datetime.format"/>}</c:set>
<c:set var="timePattern"><fmt:message key="time.format"/></c:set>
<c:set var="timeFormat">{0,date,<fmt:message key="time.format"/>}</c:set>
<c:set var="currencyPattern"><fmt:message key="currency.pattern"/></c:set>
<c:set var="currencyFormat"><fmt:message key="currency.format"/></c:set>
<c:set var="numberPattern"><fmt:message key="number.pattern"/></c:set>
<c:set var="numberFormat"><fmt:message key="number.format"/></c:set>
<c:set var="discountPattern"><fmt:message key="discount.pattern"/></c:set>
<c:set var="discountFormat"><fmt:message key="discount.format"/></c:set>
<c:set var="percentPattern"><fmt:message key="percent.pattern"/></c:set>
<c:set var="percentFormat"><fmt:message key="percent.format"/></c:set>
<%-- End: Added by wx Ver0.0.1. --%>
