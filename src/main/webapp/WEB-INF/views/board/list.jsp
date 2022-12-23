<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../includes/header.jsp"></jsp:include>

<style>
	.form-wrapper{
		width: 1000px;
		height: 800px;
		padding: 80px;
	}
</style>

<div class="form-wrapper">
	<h1>list</h1> <br>

	<table class="table">
	   <thead>
	    <tr>
	      <th scope="col">bno</th>
	      <th scope="col">title</th>
	      <th scope="col">writer</th>
	      <th scope="col">regDate</th>
	    </tr>
	  </thead>
	  <tbody>
	    
	    <c:forEach items="${list}" var="board">
	    <tr>
	    	<td>${board.bno}</td>
	    	<td><a href="/board/detail?bno=${board.bno}">${board.title}</a></td>
	    	<td>${board.writer}</td>
	    	<td>${board.registerDate}</td>
	    </tr>
	    
	    </c:forEach>
	  </tbody>
	</table>
	<c:if test="${pgh.prev}"><a href="/board/list?pageNo=${pgh.startPage-1}&qty=${pgh.pgvo.qty}">prev</a></c:if>
	<c:forEach begin="${pgh.startPage}" end="${pgh.endPage}" var="i">
		<a href="/board/list?pageNo=${i}&qty=${pgh.pgvo.qty}">${i}</a>
	</c:forEach>
	<c:if test="${pgh.next}"><a href="/board/list?pageNo=${pgh.endPage+1}&qty=${pgh.pgvo.qty}">next</a></c:if>
	
</div>

<jsp:include page="../includes/footer.jsp"></jsp:include>