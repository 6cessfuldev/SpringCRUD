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

	<form action="/board/register" method="post" enctype="multipart/form-data">
	
		<label for="title" class="form-label">Title</label>
		<div class="input-group mb-3">
		  <input type="text" name="title" class="form-control" placeholder="title" aria-label="title" >
		</div>
		
		<label for="writer" class="form-label">writer</label>
		<div class="input-group mb-3">
		  <input type="text" name="writer" class="form-control" value="${user.id}" aria-label="writer" readonly>
		</div>
		
		<label for="content" class="form-label">content</label>
		<div class="input-group mb-3">
		  <textarea name="content" rows="3" cols="130"></textarea>
		</div>
		
		<div class="mb-3">
		  <label for="formFileMultiple" class="form-label">이미지 첨부파일</label>
		  <input class="form-control" type="file" id="formFileMultiple" name="files" style="display: none" multiple>
		  <button type="button" id="trigger" class="btn btn-outline-primary btn-block d-block">Files Upload</button>
		</div>
		
		<div class="col-12" id="fileZone">
		
		</div>
		
	    <button type="submit" class="btn btn-primary" id="regBtn">Confirm</button>
	</form>
</div>
<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
<jsp:include page="../includes/footer.jsp"></jsp:include>