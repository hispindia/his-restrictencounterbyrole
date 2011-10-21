 <%--
 *  Copyright 2009 Society for Health Information Systems Programmes, India (HISP India)
 *
 *  This file is part of RestrictEncounterByRole module.
 *
 *  RestrictEncounterByRole module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  RestrictEncounterByRole module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with RestrictEncounterByRole module.  If not, see <http://www.gnu.org/licenses/>.
 *
--%> 
<%@ include file="/WEB-INF/template/include.jsp" %>

<openmrs:require privilege="Manage Role Restrictions" otherwise="/login.htm" redirect="/module/restrictbyenctype/restr.list" />

<spring:message var="pageTitle" code="restrictbyenctype.admin.restr.manage" scope="page"/>
<%@ include file="/WEB-INF/template/header.jsp" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/moduleResources/restrictbyenctype/styles/paging.css" />
<script type="text/javascript"  src="${pageContext.request.contextPath}/moduleResources/restrictbyenctype/scripts/paging.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/moduleResources/restrictbyenctype/scripts/jquery/js/jquery-1.4.2.min.js"></script>

<h2><spring:message code="restrictbyenctype.admin.restr.manage"/></h2>	

<br />
<c:forEach items="${errors.allErrors}" var="error">
	<span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><
</c:forEach>
<spring:bind path="restriction">
<c:if test="${not empty status.errorMessages }">
<div class="error">
<ul>
	<c:forEach var="error" items="${status.errorMessages}">
		<li>${error }</li>
	</c:forEach>
</ul>
</div>
</c:if>
</spring:bind>
<input type="button" value="<spring:message code='restrictbyenctype.restr.add'/>" onclick="javascript:window.location.href='restr.form'"/>

<br /><br />
<c:if test="${not empty listEncTypeRests}">
<form method="post" onsubmit="return false;" id="form">
<input type="button" onclick="checkValue()" value="<spring:message code='restrictbyenctype.restr.deleteselected'/>"/>
<span class="boxHeader"><spring:message code="restrictbyenctype.restr.list"/></span>
<div class="box">
<table cellpadding="5" cellspacing="0">
<tr>
	<th>#</th>
	<th><spring:message code="general.description"/></th>
	<th><spring:message code="restrictbyenctype.createdby"/></th>
	<th><spring:message code="restrictbyenctype.createdon"/></th>
	<th></th>
	
</tr>
<c:forEach items="${listEncTypeRests}" var="restr" varStatus="varStatus">
	<tr class='${varStatus.index % 2 == 0 ? "oddRow" : "evenRow" } <c:if test="${restr.voided}">voided</c:if>'>
		<td><c:out value="${(( pagingUtil.currentPage - 1  ) * pagingUtil.pageSize ) + varStatus.count }"/></td>	
		<td><a href="javascript:window.location.href='restr.form?restrictionId=${restr.roleRestrictionId}'">${restr.description }</a> </td>
		<td>${restr.creator.name }</td>
		<td><openmrs:formatDate date="${restr.dateCreated}" type="textbox" /></td>
		<td><input type="checkbox" name="encTypeRes" value="${restr.roleRestrictionId}"/></td>
	</tr>
</c:forEach>
</form>
<tr class="paging-container">
	<td colspan="9"><%@ include file="paging.jsp" %></td>
</tr>
</table>
</div>
</c:if>
<script>

function checkValue()
{
	var form = jQuery("#form");
	if( jQuery("input[type='checkbox']:checked",form).length > 0 ) 
		form.submit();
	else{
		alert("Please choose items for deleting");
		return false;
	}
}</script>
<%@ include file="/WEB-INF/template/footer.jsp" %>
