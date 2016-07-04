<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="navbarMenu.vm" permissions="rolesAdapter">
<div class="nav-collapse collapse">
<ul class="nav">
    <c:if test="${empty pageContext.request.remoteUser}">
        <li class="active">
            <a href="<c:url value="/login"/>"><fmt:message key="login.title"/></a>
        </li>
    </c:if>
    <menu:displayMenu name="MainMenu"/>
    <%-- <menu:displayMenu name="UserMenu"/> Deleted by wx Ver0.0.1. --%>
    <menu:displayMenu name="AdminMenu"/>
    <menu:displayMenu name="Logout"/>   
    <!--XdpGroupDoc-START-->
    <menu:displayMenu name="XdpGroupDocMenu"/>
    <!--XdpGroupDoc-END-->
</ul>
</div>
</menu:useMenuDisplayer>
