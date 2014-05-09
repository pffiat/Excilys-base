<jsp:include
	page="/include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section id="main">

	<h1>Edit Computer</h1>

	<form action="EditComputer" method="POST">
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<%-- <input type="text" name="name"  value="${computer.name}"/> <span class="help-inline">Required</span> --%>
					<input class="error" data-validation-error-msg="The user name has to be an alphanumeric value between 2-20 characters" data-validation-length="2-20" data-validation="alphanumeric length" data-validation-allowing="-_ " name="name"  value="${computer.name}";"></input>
					<span class="help-inline">Required</span>
				</div>
			</div>

			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<%-- <input type="text" name="introduced" value="${computer.introduced}"/> <span class="help-inline">YYYY-MM-DD</span> --%>
					<input class="has-help-txt error" data-validation-optional="true" data-validation-help="yyyy-mm-dd (Not allowing dates in the future or older than 120 years)" data-validation="birthdate" name="introduced" value="${computer.introduced}";"></input>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
		<%-- 			<input type="date" name="discontinued" value="${computer.discontinued}"/> <span
						class="help-inline">YYYY-MM-DD</span> --%>
						<input class="has-help-txt error" data-validation-optional="true" data-validation-help="yyyy-mm-dd (Not allowing dates in the future or older than 120 years)" data-validation="birthdate" name="discontinued" value="${computer.discontinued}";"></input>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<select name="company">
						<option value="${cpn.id}">${cpn.name}</option>
						<c:forEach var="company" items="${companies}">
							<option value="${company.id}">${company.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="Edit" class="btn btn-warning"> or <a
				href="dashboard.jsp" class="btn">Cancel</a>
		</div>
	</form>
</section>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.1.47/jquery.form-validator.min.js"></script>
<script>
$.validate({
modules : 'date'
});
</script>	

<jsp:include page="/include/footer.jsp" />