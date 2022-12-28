<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="../includes/header.jsp"></jsp:include>

<style>
	.wrapper{
		width:50%;
		padding:100px;
	}
</style>

<div class="wrapper">
	<h1>detail</h1> <br>

	<table class="table">
		<tbody>
			<tr>
				<th>bno</th>
				<td>${board.bno}</td>
			</tr>
			<tr>
				<th>title</th>
				<td>${board.title}</td>
			</tr>
			<tr>
				<th>writer</th>
				<td>${board.writer}</td>
			</tr>
			<tr>
				<th>content</th>
				<td>${board.content}</td>
			</tr>				
			<tr>
				<th>read_count</th>
				<td>${board.read_count}</td>
			</tr>				
			<tr>
				<th>registerDate</th>
				<td>${board.registerDate}</td>
			</tr>
		</tbody>
	</table>
		<div class="form-group">
			<ul>
				<c:forEach items="${fList}" var="fvo"> 
					<li>
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
	<div class="btn-group" role="group" aria-label="Basic example">
	<c:if test="${user.id eq board.writer}">
	  <button type="button" class="btn btn-secondary" onclick='location.href="/board/modify?bno=${board.bno}"'>수정</button>
	  <button type="button" class="btn btn-danger" onclick='location.href="/board/remove?bno=${board.bno}"'>삭제</button>
	</c:if>
	  <button type="button" class="btn btn-primary" onclick="location.href='/board/list'">리스트</button>
	</div>
	
<!-- comment line -->
	<div class="container">
		<div class="input-group my-3">
			<span class="input-group-text" id="cmtWriter">${board.writer}</span>
			<input type="text" class="form-control" id="cmtText" placeholder="Test Add Comment">
			<button class="btn btn-success" id="cmtPostBtn" type="button">Post</button>
		</div>
		<ul class="list-group list-group-flush" id="cmtListArea">
			<li class="list-group-item d-flex justify-content-between aligh-items-start">
				<div class="ms-2 me-auto">
					<div class="fw-bold">Writer</div>
					content for Comment
				</div>
				<span class="badge bg-dark rounded-pill">modAt</span>
			</li>
		</ul>
	</div>	
	
</div>

<script type="text/javascript">

console.log("Test");
const rs = '<c:out value="${result}" />';
console.log(rs);
if(rs == '0'){
	alert("이미 존재하는 아이디입니다.");
}

const bnoVal = '<c:out value="${board.bno}"/>';
console.log(bnoVal);

</script>

<script type="text/javascript" src="/resources/js/boardComment.js"></script>

<script type="text/javascript">
	getCommentList(bnoVal);
</script>

<jsp:include page="../includes/footer.jsp"></jsp:include>