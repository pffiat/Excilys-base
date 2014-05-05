<jsp:include page="/include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%-- 
<%@ taglib tagdir="/WEB-INF/tag" prefix="tags" %>  --%>

<section id="main">
	<h1 id="homeTitle">${pageWrapper.totalCount} computers found ${pageWrapper.nbOfLines} displayed</h1>
	<div id="actions" class="row">
		<form action="" method="GET">

			<div class="col-md-1">
				<input type="search" id="searchbox" name="search" value="" placeholder="Search name">
			</div>
			<div class="col-md-1">
					<select name="column">
						<option value="company.name">name of company</option>
						<option value="introduced">date of introduced</option>
						<option value="computer.name">name of computer</option>
					</select>
			</div>
			<div class="col-md-1">
					<select name="order">
						<option value=""></option>
							<option value="ASC">ASCENDING</option>
							<option value="DESC">DESCENDING</option>
					</select>
			</div>
			<div class="col-md-1">
				<input type="submit" id="searchsubmit"	value="Filter by name"	class="btn btn-primary">	
			</div>
			<div class="col-md-1">
			</div>
			
			<c:forEach begin="1" end="4" var="i">
			<div class="col-md-1">
			<a class="btn btn-info" id=${i} href="?lignes=${i}">${i*5}</a>
			</div>
			</c:forEach>
			
			<div class="col-md-1">
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-1">
				<a class="btn btn-success" id="add" href="AjouterComputerServlet">Add Computer</a>
			</div>			 
			</form>
	</div>

		<table class="table table-striped">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<th>Computer Name</th>
					<th>Introduced Date</th>
					<!-- Table header for Discontinued Date -->
					<th>Discontinued Date</th>
					<!-- Table header for Company -->
					<th>Company</th>
					<th>Update</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
							
				<c:forEach var="computer" items="${pageWrapper.list}">
					<tr>
						<td><a href="#">${computer.name}</a></td>
						<td>${computer.introduced}</td>
						<td>${computer.discontinued}</td>
						<td>${computer.company}</td>
						<td><a class="btn btn-warning" id="update" href="EditComputerServlet?id=${computer.id}&name=${computer.name}&introduced=${computer.introduced}&discontinued=${computer.discontinued}&comp_id=${computer.company_id}&comp_name=${computer.company}">Update</a></td>
						<td><a class="btn btn-danger" id="delete" href="DeleteComputerServlet?id=${computer.id}">Delete</a></td>
					</tr>		
				</c:forEach>

				
			</tbody>
		</table>
		<div id="pages">	<%-- 
		<tags:pagination /> --%>
			<c:forEach begin="1" end="${pageWrapper.nbOfBouton}" var="i">
					<a class="btn btn-success" id=${i} href="?page=${i}">${i}</a>
			</c:forEach>
		</div>
</section>

<jsp:include page="/include/footer.jsp" />
