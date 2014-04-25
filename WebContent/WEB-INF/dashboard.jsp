<jsp:include page="/include/header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section id="main">
	<h1 id="homeTitle">${nbOfPc} computers found ${nbDisplayed} displayed</h1>
	<div id="actions">
		<form action="" method="GET">
			<input type="search" id="searchbox" name="search"
				value="" placeholder="Search name">
			<input type="submit" id="searchsubmit"
				value="Filter by name"
				class="btn btn-primary">
			</form>
			<c:forEach begin="1" end="4" var="i">
			<a class="btn btn-success" id=${i} href="?lignes=${i}">${i*5}</a>
			</c:forEach>
		<a class="btn btn-success" id="add" href="AjouterComputerServlet">Add Computer</a>
	</div>

		<table class="computers zebra-striped">
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
							
				<c:forEach var="computer" items="${computers}">
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
		<div id="pages">
			<c:forEach begin="1" end="${nbOfBouton}" var="i">
					<a class="btn btn-success" id=${i} href="?page=${i}">${i}</a>
			</c:forEach>
		</div>
</section>

<jsp:include page="/include/footer.jsp" />
