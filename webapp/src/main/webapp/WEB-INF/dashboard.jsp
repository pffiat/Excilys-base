<jsp:include page="/include/header.jsp" />    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<section id="main">
	<h1 id="homeTitle">${pageWrapper.totalCount} 
    <spring:message code="computers.found" text="" />   ${pageWrapper.nbOfLines} <spring:message code="displayed" text="" /> </h1>
   
    
	<div id="actions" class="row">
		<form action="" method="GET">

			<div class="col-md-1">
				<input type="search" id="searchbox" name="search" value="${pageWrapper.search}" placeHolder="<spring:message code="search"  />" >
			</div>
			<div class="col-md-1">
					<select name="column">
						<option value="name"><spring:message code="name.of.company" text="" /></option>
						<option value="introduced"><spring:message code="date.of.introduced"  text=""/></option>
						<option value="company.name"><spring:message code="name.of.computer"  text=""/></option>
					</select>
			</div>
			<div class="col-md-1">
					<select name="order">
							<option value=""></option>
							<option value="ASC"><spring:message code="ASCENDING"  text=""/></option>
							<option value="DESC"><spring:message code="DESCENDING" text="" /></option>
					</select>
			</div>
			<div class="col-md-1">
				<input type="submit" id="searchsubmit"	value="<spring:message code="filter" text="" />"	class="btn btn-primary">	
			</div>
			<div class="col-md-1">
			</div>
			
			<c:forEach begin="1" end="4" var="i">
			<div class="col-md-1">
			<a class="btn btn-info" href="?lignes=${i}" id="${i}" >${i*5}</a>
			</div>
			</c:forEach>
			
			<div class="col-md-1">
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-1">
				<a class="btn btn-success" id="add" href="AddComputer"><spring:message code="Add.Computer" text="" /></a>
			</div>			 
			</form>
	</div>

		<table class="table table-striped">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<th><spring:message code="Computer.Name"  text=""/></th>
					<th><spring:message code="Introduced.Date"  text=""/></th>
					<!-- Table header for Discontinued Date -->
					<th><spring:message code="Discontinued.Date"  text=""/></th>
					<!-- Table header for Company -->
					<th><spring:message code="Company"  text=""/></th>
					<th><spring:message code="Update" text="" /></th>
					<th><spring:message code="Delete" text="" /></th>
				</tr>
			</thead>
			<tbody>
							
				<c:forEach var="computer" items="${pageWrapper.list}">
					<tr>
						<td><a href="https://${pageWrapper.language}.wikipedia.org/wiki/${computer.name}">${computer.name}</a></td>
						<td>${computer.introduced}</td>
						<td>${computer.discontinued}</td>
						<td>${computer.company}</td>
						<td><a class="btn btn-warning" id="update" href="EditComputer?id=${computer.id}&name=${computer.name}&introduced=${computer.introduced}&discontinued=${computer.discontinued}&comp_id=${computer.company_id}&comp_name=${computer.company}"><spring:message code="Update" text="" /></a></td>
						<td><a class="btn btn-danger" id="delete" href="DeleteComputer?id=${computer.id}"><spring:message code="Delete" text="" /></a></td>
					</tr>		
				</c:forEach>

				
			</tbody>
		</table>
		<div id="pages">	<%-- 
		<tags:pagination /> --%>
			<c:forEach begin="1" end="${pageWrapper.nbOfBouton}" var="i">
					<a class="btn btn-success" id="${i}" href="?page=${i}">${i}</a>
			</c:forEach>
		</div>
</section>

<jsp:include page="/include/footer.jsp" />
