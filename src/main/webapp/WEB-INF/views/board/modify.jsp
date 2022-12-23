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
	<h1>Register</h1> <br>

	<form action="/board/modify" method="post">
	<input type="hidden" name="bno" value="${board.bno}">
	
	<label for="title" class="form-label">Title</label>
	<div class="input-group mb-3">
	  <input type="text" name="title" class="form-control" value="${board.title}" aria-label="title" >
	</div>
	
	<label for="writer" class="form-label">writer</label>
	<div class="input-group mb-3">
	  <input type="text" name="writer" class="form-control" value="${user.id}" aria-label="writer" readonly>
	</div>
	
	<label for="content" class="form-label">content</label>
	<div class="input-group mb-3">
	  <textarea name="content" rows="3" cols="130">${board.title }</textarea>
	</div>
	
    	<button type="submit" class="btn btn-primary">Confirm</button>

	</form>
</div>


<jsp:include page="../includes/footer.jsp"></jsp:include>