<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="../includes/header.jsp"></jsp:include>

<style>
	.form-wrapper{
		width:50%;
		padding:100px;
	}
</style>

<div class="form-wrapper">
	<h1>Modify</h1> <br>

	<form action="/board/modify" method="post" enctype="multipart/form-data">
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
	
	<div class="form-group">
		<ul id="ul-img-group">
			<c:forEach items="${fList}" var="fvo"> 
				<li data-uuid="${fvo.uuid}">
					<button type="button" class="fileDelBtn btn btn-sm btn-danger py-0" onclick="clickDelBtn(this)">x</button>
					<c:choose>
						<c:when test="${fvo.file_type > 0}">
							<div>
								<img alt="image" src="/upload/${fn:replace(fvo.save_dir, '\\','/')}/${fvo.uuid}_th_${fvo.file_name}">
							</div>
						</c:when>
						<c:otherwise>
							<div>
								<img alt="not image" src="#">
							</div>
						</c:otherwise>
					</c:choose>
					<div class="ms-2 me-auto">
						<div class="fw-bold">${fvo.file_name}</div>
						${fvo.reg_at}
					</div>
					<span class="badge bg-secondary rounded-pill">${fvo.file_size} bytes</span>
				</li>
			</c:forEach>
		</ul>
	</div>
	
	<div class="mb-3">
	  <label for="formFileMultiple" class="form-label">이미지 첨부파일</label>
	  <input class="form-control" type="file" id="formFileMultiple" name="files" style="display: none" multiple>
	  <button type="button" id="trigger" class="btn btn-outline-primary btn-block d-block">Files Upload</button>
	</div>
	
	<div class="col-12" id="fileZone"></div>
	
	
	<!-- 파일 수정 입력 -->

   	<button type="submit" class="btn btn-primary" id="regBtn">Confirm</button>

	</form>
</div>

<script type="text/javascript" src="/resources/js/boardModify.js"></script>
<jsp:include page="../includes/footer.jsp"></jsp:include>