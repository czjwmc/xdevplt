<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="userProfile.title"/></title>
    <%-- <meta name="menu" content="UserMenu"/> --%>
    <%-- Start: Modified by wx Ver0.0.1. --%>
    <s:if test="#parameters.from[0] == 'list'">
        <meta name="menu" content="AdminMenu"/>
    </s:if>
    <s:else>
        <meta name="menu" content="MainMenu"/>
    </s:else>
    <%-- End: Modified by wx Ver0.0.1. --%>
</head>

<c:set var="delObject" scope="request"><fmt:message key="userList.user"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="userProfile.heading"/></h2>
    <c:choose>
        <c:when test="${param.from == 'list'}">
            <p><fmt:message key="userProfile.admin.message"/></p>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="userProfile.message"/></p>
        </c:otherwise>
    </c:choose>
</div>
<div class="span7">
    <s:form name="userForm" action="saveUser" method="post" validate="true" cssClass="well form-horizontal" autocomplete="off">
        <s:hidden key="user.id"/>
        <s:hidden key="user.version"/>
        <input type="hidden" name="from" value="${param.from}"/>

        <c:if test="${cookieLogin == 'true'}">
            <s:hidden key="user.password"/>
            <s:hidden key="user.confirmPassword"/>
        </c:if>
        <s:if test="user.version == null">
            <input type="hidden" name="encryptPass" value="true"/>
        </s:if>

        <%-- Start: Added by wx Ver0.0.1. --%>
        <c:if test="${not empty user.id}">
        <fieldset class="control-group">
            <label class="control-label"><fmt:message key="user.id"/>:</label>
            <div class="controls readonly">
                <s:property value="user.id"/>
            </div>
        </fieldset>
        </c:if>
        <%-- End: Added by wx Ver0.0.1. --%>

        <%-- Start: Modified by wx Ver0.0.1 for remain admin user. --%>
        <%-- <s:textfield key="user.username" required="true"/> --%>
        <c:choose>
        <c:when test="${user.username == 'admin'}">
            <fieldset class="control-group">
                <label class="control-label"><fmt:message key="user.username"/>:</label>
                <div class="controls readonly">
                    <s:property value="user.username"/>
                </div>
                <s:hidden key="user.username"/>
            </fieldset>
        </c:when>
        <c:otherwise>
            <s:textfield key="user.username" required="true" maxlength="50"/>
        </c:otherwise>
        </c:choose>
        <%-- End: Modified by wx Ver0.0.1 for remain current admin user. --%>

        <c:if test="${cookieLogin != 'true'}">
            <s:password key="user.password" showPassword="true" required="true"
                        onchange="passwordChanged(this)"/>

            <s:password key="user.confirmPassword" required="true"
                        showPassword="true" onchange="passwordChanged(this)"/>
        </c:if>

        <s:textfield key="user.passwordHint" maxlength="50"/> <%-- Modified by wx Ver0.0.1. --%>
        <s:textfield key="user.lastName" required="true"/>
        <s:textfield key="user.firstName" required="true"/>
        <s:textfield key="user.phoneNumber" maxlength="50" required="false"/> <%-- Modified by wx Ver0.0.1. --%>
        <s:textfield key="user.email" maxlength="100"/> <%-- Modified by wx Ver0.0.1. --%>
        <s:textfield key="user.website" maxlength="100"/> <%-- Modified by wx Ver0.0.1. --%>

        <fieldset>
            <legend class="accordion-heading">
                <a data-toggle="collapse" href="#collapse-address"><fmt:message key="user.address.address"/></a>
            </legend>
            <div id="collapse-address" class="accordion-body collapse">
                <s:textfield key="user.address.address" maxlength="150"/>
                <s:textfield key="user.address.city" maxlength="50"/>
                <s:textfield key="user.address.province" maxlength="100"/>
                <s:textfield key="user.address.postalCode" maxlength="15"/>
                <s:set name="country" value="user.address.country" scope="page"/>
                <fieldset class="control-group">
                    <label for="user.address.country" class="control-label"> <%-- Modified by wx Ver0.0.1. --%>
                        <fmt:message key="user.address.country"/>:
                    </label>

                    <div class="controls">
                        <appfuse:country name="user.address.country" prompt="" default="${country}"/>
                    </div>
                </fieldset>
            </div>
        </fieldset>
<c:choose>
    <%-- Modified by wx Ver0.0.1 for remain admin user. --%>
    <c:when test="${param.from == 'list' and user.username != 'admin'}">
        <fieldset class="control-group">
            <label class="control-label"><fmt:message key="userProfile.accountSettings"/></label>
            <div class="controls">
                <label class="checkbox inline">
                    <s:checkbox key="user.enabled" id="user.enabled" theme="simple" fieldValue="true"/>
                    <fmt:message key="user.enabled"/>
                </label>
                <label class="checkbox inline">
                    <s:checkbox key="user.accountExpired" id="user.accountExpired" theme="simple" fieldValue="true"/>
                    <fmt:message key="user.accountExpired"/>
                </label>
                <label class="checkbox inline">
                    <s:checkbox key="user.accountLocked" id="user.accountLocked" theme="simple" fieldValue="true"/>
                    <fmt:message key="user.accountLocked"/>
                </label>
                <br/>
                <label class="checkbox inline">
                    <s:checkbox key="user.credentialsExpired" id="user.credentialsExpired" theme="simple" fieldValue="true"/>
                    <fmt:message key="user.credentialsExpired"/>
                </label>
            </div>
        </fieldset>
        <fieldset class="control-group">
            <label for="userRoles" class="control-label"><fmt:message key="userProfile.assignRoles"/></label>
            <div class="controls">
                <select id="userRoles" name="userRoles" multiple="true">
                    <c:forEach items="${availableRoles}" var="role">
                    <option value="${role.value}" ${fn:contains(user.roles, role.label) ? 'selected' : ''}>${role.label}</option>
                    </c:forEach>
                </select>
            </div>
        </fieldset>
        <fieldset class="control-group">
            <label class="control-label"><fmt:message key="userProfile.assignXdpGroups"/></label>
            <div class="controls">
                <select id="relatedXdpGroups" name="relatedXdpGroups" multiple="true">
                    <c:forEach items="${availableXdpGroups}" var="xdpGroup">
                    <option value="${xdpGroup.value}" ${fn:contains(user.xdpGroups, xdpGroup.label) ? 'selected' : ''}>${xdpGroup.label}</option>
                    </c:forEach>
                </select>
            </div>
        </fieldset>
    </c:when>
    <c:otherwise>
        <fieldset class="control-group">
            <label class="control-label"><fmt:message key="user.roles"/>:</label>
            <div class="controls readonly">
                <s:iterator value="user.roleList" status="status">
                    <s:property value="label"/><s:if test="!#status.last">,</s:if>
                    <input type="hidden" name="userRoles" value="<s:property value="value"/>"/>
                </s:iterator>
            </div> 
            <s:hidden name="user.enabled" value="%{user.enabled}"/>
            <s:hidden name="user.accountExpired" value="%{user.accountExpired}"/>
            <s:hidden name="user.accountLocked" value="%{user.accountLocked}"/>
            <s:hidden name="user.credentialsExpired" value="%{user.credentialsExpired}"/>
            </fieldset>     
    </c:otherwise>
</c:choose>
        <%-- Start: Added by wx Ver0.0.1. --%>
        <c:if test="${not empty user.id}">
        <fieldset class="control-group">
            <label class="control-label"><fmt:message key="user.updateUser"/>:</label>
            <div class="controls readonly">
                <s:property value="user.updateUser"/>
            </div>
        </fieldset>
        </c:if>
        <s:hidden key="user.updateUser"/>
        <c:if test="${not empty user.id}">
        <fieldset class="control-group">
            <label class="control-label"><fmt:message key="user.updateDate"/>:</label>
            <div class="controls readonly">
                <s:date name="user.updateDate" format="%{getText('datetime.format')}" />
            </div>
        </fieldset>
        </c:if>
        <s:hidden key="user.updateDate"/>
        <%-- End: Added by wx Ver0.0.1. --%>

        <div id="actions" class="form-actions">
            <s:submit type="button" cssClass="btn btn-primary" method="save" key="button.save" 
                onclick="onConvert()" theme="simple"> <%-- Modified by wx Ver0.0.1. --%>
                <i class="icon-ok icon-white"></i>
                <fmt:message key="button.save"/>
            </s:submit>
            <%-- Modified by wx Ver0.0.1 for remain admin user. --%>
            <c:if test="${param.from == 'list' and not empty user.id and user.username != 'admin'}">
                <s:submit type="button" cssClass="btn btn-danger" method="delete" key="button.delete"
                    onclick="return confirmMessage(msgDelConfirm)" theme="simple">
                    <i class="icon-trash"></i>
                    <fmt:message key="button.delete"/>
                </s:submit>
            </c:if>
            <s:submit type="button" cssClass="btn" method="cancel" key="button.cancel" theme="simple">
                <i class="icon-remove"></i>
                <fmt:message key="button.cancel"/>
            </s:submit>
        </div>
    </s:form>
</div>

<c:set var="scripts" scope="request">
<script type="text/javascript">
    function passwordChanged(passwordField) {
        if (passwordField.name == "user.password") {
            var origPassword = "<s:property value="user.password"/>";
        } else if (passwordField.name == "user.confirmPassword") {
            var origPassword = "<s:property value="user.confirmPassword"/>";
        }

        if (passwordField.value != origPassword) {
            createFormElement("input", "hidden", "encryptPass", "encryptPass",
                    "true", passwordField.form);
        }
    }
</script>
</c:set>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['userForm']).focus();
    });
</script>
