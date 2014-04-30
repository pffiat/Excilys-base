<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

			<c:forEach begin="1" end="${nbOfBouton}" var="i">
					<a class="btn btn-success" id=${i} href="?page=${i}">${i}</a>
			</c:forEach>
			
			
			
			
			<c:forEach begin="1" end="${nbOfBouton}" var="i">
					<a class="btn btn-success" id=${i} href="?page=${i}">${i}</a>
			</c:forEach>