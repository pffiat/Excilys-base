<jsp:include
	page="/include/header.jsp" />
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<section id="main">

	<h1><spring:message code="Add.Computer" text="" /></h1>

	<form action="AddComputer" method="POST">
		<fieldset>
			<div class="clearfix">
				<label for="name"><spring:message code="name.of.company" text="" />:</label>
				<div class="input">
					<%-- <input type="text" name="name"  value="${computer.name}"/> <span class="help-inline">Required</span> --%>
					<input class="error" data-validation-error-msg="The user name has to be an alphanumeric value between 2-20 characters" data-validation-length="2-20" data-validation="alphanumeric length" data-validation-allowing="-_ " name="name"  value=""></input>
					<span class="help-inline"><spring:message code="Required" text="" /></span>
				</div>
			</div>

			<div class="clearfix">
				<label for="introduced"><spring:message code="date.of.introduced" text="" />:</label>
				<div class="input">
					<%-- <input type="text" name="introduced" value="${computer.introduced}"/> <span class="help-inline">YYYY-MM-DD</span> --%>
					<input class="has-help-txt error" data-validation-optional="true" data-validation-help="<spring:message code="ymdLong" text="" />" data-validation="birthdate" name="introduced" value=""></input>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued"><spring:message code="Discontinued.Date" text="" />:</label>
				<div class="input">
		<%-- 			<input type="date" name="discontinued" value="${computer.discontinued}"/> <span
						class="help-inline">YYYY-MM-DD</span> --%>
						<input class="has-help-txt error" data-validation-optional="true" data-validation-help="<spring:message code="ymdLong" text="" />" data-validation="birthdate" name="discontinued" value=""></input>
				</div>
			</div>
			<div class="clearfix">
				<label for="company"><spring:message code="Company" text="" />:</label>
				<div class="input">
					<select name="company_id">
						<option value="0">--</option>
						<c:forEach var="company" items="${companies}">
							<option value="${company.id}">${company.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="<spring:message code="Add.Computer" text="" />" class="btn btn-primary"> <spring:message code="or" text="" /> <a
				href="Dashboard" class="btn"><spring:message code="Cancel" text="" /></a>
		</div>
	</form>
</section>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.1.47/jquery.form-validator.min.js"></script>
<script> $.validate(); </script>

<jsp:include page="/include/footer.jsp" />