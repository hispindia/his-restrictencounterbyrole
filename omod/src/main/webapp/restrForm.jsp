<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<openmrs:require privilege="Manage Role Restrictions" otherwise="/login.htm" redirect="/module/restrictbyenctype/restr.list" />

<h2><spring:message code="restrictbyenctype.admin.restr.manage"/></h2>

<c:forEach items="${errors.allErrors}" var="error">
	<span class="error"><spring:message code="${error.defaultMessage}" text="${error.defaultMessage}"/></span><
</c:forEach>
<spring:bind path="restriction">
<c:if test="${not empty  status.errorMessages}">
<div class="error">
<ul>
<c:forEach items="${status.errorMessages}" var="error">
    <li>${error}</li>   
</c:forEach>
</ul>
</div>
</c:if>
</spring:bind>
<form method="post" class="box">
<table>
	<tr>
		<td><spring:message code="general.description"/></td>
		<td>
			<spring:bind path="restriction.description">
				<input type="text" name="${status.expression }" value="${status.value}" size="35" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td valign="top"><spring:message code="restrictbyenctype.restr.role"/></td>
		<td><spring:bind path="restriction.role">
			<select name="${status.expression }" >
				<option value=""><spring:message code="restrictbyenctype.pleaseselect"/></option>
				<c:forEach items="${roles}" var="role">
					<option value="${role.role }"
						<c:if test="${role.role == status.value}"> selected</c:if>
					>${role.role} </option>
				</c:forEach>
			</select>
			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>	
	<tr>
		<td valign="top"><spring:message code="restrictbyenctype.restr.encountertypes"/></td>
		<td><spring:bind path="restriction.encTypes">
			<select name="${status.expression }" multiple="multiple">
				<c:forEach items="${listEncTypes}" var="encType">
					<option value="${encType.encounterTypeId }"
						<c:forEach items="${status.value}" var="rEncType">
							<c:if test="${encType.encounterTypeId == rEncType.encounterTypeId}"> selected</c:if>
						</c:forEach>
					>${encType.name}</option>
				</c:forEach>
			</select>
			<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	
	<c:if test="${!(restriction.creator == null)}">
		<tr>
			<td><spring:message code="general.createdBy" /></td>
			<td>
				${restriction.creator} -
				<openmrs:formatDate date="${restriction.dateCreated}" type="java.util.Date" />
			</td>
		</tr>
	</c:if>
	
	<tr>
		<td><spring:message code="general.retired"/></td>
		<td>
			<spring:bind path="restriction.voided">
				<openmrs:fieldGen type="java.lang.Boolean" formFieldName="${status.expression}" val="${status.editor.value}" parameters="isNullable=false" />
				<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
			</spring:bind>
		</td>
	</tr>
	
	<c:if test="${ restriction.voided == true }">
		<tr>
			<td><spring:message code="general.retiredBy"/></td>
			<td>
					${restriction.voidedBy} -
						<openmrs:formatDate date="${restriction.dateVoided}" type="textbox" />
			</td>
		</tr>
	</c:if>
</table>
<br />
<input type="submit" value="<spring:message code="general.save"/>">
<input type="button" value="<spring:message code="general.cancel"/>" onclick="javascript:window.location.href='restr.list'">
</form>
<%@ include file="/WEB-INF/template/footer.jsp" %>