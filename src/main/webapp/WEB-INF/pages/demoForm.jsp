<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="demoDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='demoDetail.heading'/>"/>
    <meta name="menu" content="AdminMenu"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="demoList.demo"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="demoDetail.heading"/></h2>
    <fmt:message key="demoDetail.message"/>
</div>

<div class="span7">
    <s:form id="demoForm" action="saveDemo" method="post" validate="true" cssClass="well form-horizontal">
        <s:hidden key="demo.id"/>
        <s:hidden key="demo.version"/>
        <s:hidden key="demo.createUser.id"/>
        <s:hidden key="demo.createDate"/>
        <s:hidden key="demo.updateUser.id"/>
        <s:hidden key="demo.updateDate"/>

        <c:if test="${not empty demo.id}">
        <fieldset class="control-group">
            <label class="control-label"><fmt:message key="demo.id"/>:</label>
            <div class="controls readonly">
                <s:property value="demo.id"/>
            </div>
        </fieldset>
        </c:if>
        <s:textfield key="demo.name" required="true" maxlength="50" />
        <s:textfield key="demo.price" required="false" maxlength="10" style="text-align: right" 
            value="%{demo.price!=null?getText('currency.format',{demo.price}):''}"/>
        <s:textfield key="demo.qty" required="false" maxlength="7" style="text-align: right" 
            value="%{demo.qty!=null?getText('number.format',{demo.qty}):''}"/>
        <s:textfield key="demo.total" required="false" maxlength="22" style="text-align: right" 
            value="%{demo.total!=null?getText('currency.format',{demo.total}):''}" readonly="true"/>
        <s:textfield key="demo.discountRate" required="false" maxlength="6" style="text-align: right" 
            value="%{demo.discountRate!=null?getText('discount.format',{demo.discountRate}):''}"/>
        <s:textfield key="demo.percent" required="false" maxlength="10" style="text-align: right" 
            value="%{demo.percent!=null?getText('percent.format',{demo.percent}):''}"/>
        <s:textfield key="demo.date" required="false" maxlength="10" size="10" title="date" datepicker="true"/>
        <s:textfield key="demo.datetime" required="false" maxlength="19" size="19" title="date" datepicker="true"/>
        <s:textarea key="demo.description" required="false" maxlength="200" cssClass="textarea large"/>
        <%-- <s:checkbox key="demo.enabled" theme="css_xhtml"/> --%>
        <fieldset class="control-group">
            <label class="control-label"><fmt:message key="demo.enabled"/>:</label>
            <div class="controls">
                <label class="checkbox inline">
                    <s:checkbox key="demo.enabled" theme="simple" fieldValue="true"/>
                </label>
            </div>
        </fieldset>

        <!-- todo: change this to read the identifier field from the other pojo -->
        <%-- <s:select name="demo.createUser.id" list="createUserList" listKey="id" listValue="id"></s:select> --%>
        <!-- todo: change this to read the identifier field from the other pojo -->
        <%-- <s:select name="demo.updateUser.id" list="updateUserList" listKey="id" listValue="id"></s:select> --%>

        <c:if test="${not empty demo.id}">
        <fieldset class="control-group">
            <label class="control-label"><fmt:message key="demo.createUser"/>:</label>
            <div class="controls readonly">
                <s:property value="demo.createUser.fullName"/>
                <c:if test="${demo.createUser.enabled=='false'}">(<fmt:message key="label.disabled"/>)</c:if>
            </div>
        </fieldset>
        <fieldset class="control-group">
            <label class="control-label"><fmt:message key="demo.createDate"/>:</label>
            <div class="controls readonly">
                <s:date name="demo.createDate" format="%{getText('datetime.format')}" />
            </div>
        </fieldset>
        <fieldset class="control-group">
            <label class="control-label"><fmt:message key="demo.updateUser"/>:</label>
            <div class="controls readonly">
                <s:property value="demo.updateUser.fullName"/>
                <c:if test="${demo.updateUser.enabled=='false'}">(<fmt:message key="label.disabled"/>)</c:if>
            </div>
        </fieldset>
        <fieldset class="control-group">
            <label class="control-label"><fmt:message key="demo.updateDate"/>:</label>
            <div class="controls readonly">
                <s:date name="demo.updateDate" format="%{getText('datetime.format')}" />
            </div>
        </fieldset>
        </c:if>

        <div id="actions" class="form-actions">
            <s:submit type="button" cssClass="btn btn-primary" method="save" key="button.save" 
                onclick="onConvert()" theme="simple">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </s:submit>
            <c:if test="${not empty demo.id}">
                <s:submit type="button" cssClass="btn btn-warning" method="delete" key="button.delete"
                    onclick="if(confirmMessage(msgDelConfirm)){onConvert();return true;}else{return false;}" theme="simple">
                    <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
                </s:submit>
            </c:if>
            <s:submit type="button" cssClass="btn" method="cancel" key="button.cancel" theme="simple">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </s:submit>
        </div>
    </s:form>
</div>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['demoForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>

<script type="text/javascript" src="<c:url value="/scripts/accounting.js"/>"></script>
<script type="text/javascript">
var onConvert;
jQuery(document).ready(function($){
    $("#demoForm_demo_price").blur(function(){
        var str = $("#demoForm_demo_price").val();
        if (str != '') {
            $("#demoForm_demo_price").val(formatMoney(unformat(str)));
        }
        changeAmount();
    });
    $("#demoForm_demo_qty").blur(function(){
        var str = $("#demoForm_demo_qty").val();
        if (str != '') {
            $("#demoForm_demo_qty").val(formatNumber(unformat(str)));
        }
        changeAmount();
    });
    function changeAmount() {
        var strPrice = $("#demoForm_demo_price").val();
        var strQty = $("#demoForm_demo_qty").val();
        if (strPrice != '' && strQty != '') {
            var numPrice = unformat(strPrice);
            var numQty = unformat(strQty);
            var numTotal = numPrice.mul(numQty);
            $("#demoForm_demo_total").val(formatMoney(numTotal));
        } else {
            $("#demoForm_demo_total").val('');
        }
    }
    $("#demoForm_demo_discountRate").blur(function(){
        var str = $("#demoForm_demo_discountRate").val();
        if (str != '') {
            $("#demoForm_demo_discountRate").val(formatDiscount(unformat(str)));
        }
    });
    $("#demoForm_demo_percent").blur(function(){
        var str = $("#demoForm_demo_percent").val();
        if (str != '') {
            $("#demoForm_demo_percent").val(formatPercent(unformat(str)));
        }
    });
    onConvert = function() {
        convertDiscountRate();
        convertPercent();
    }
    function convertDiscountRate() {
        var str = $("#demoForm_demo_discountRate").val();
        if (str != '') {
            $("#demoForm_demo_discountRate").val(unformat(str).div(100));
        }
    }
    function convertPercent() {
        var str = $("#demoForm_demo_percent").val();
        if (str != '') {
            $("#demoForm_demo_percent").val(unformat(str).div(100));
        }
    }
});
</script>
