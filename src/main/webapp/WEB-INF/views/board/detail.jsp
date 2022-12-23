<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../includes/header.jsp"></jsp:include>

<style>
	.wrapper{
		width: 1000px;
		height: 800px;
		padding: 80px;
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
	<div class="btn-group" role="group" aria-label="Basic example">
	<c:if test="${user.id eq board.writer}">
	  <button type="button" class="btn btn-secondary" onclick='location.href="/board/modify?bno=${board.bno}"'>수정</button>
	  <button type="button" class="btn btn-danger" onclick='location.href="/board/remove?bno=${board.bno}'>삭제</button>
	</c:if>
	  <button type="button" class="btn btn-primary" onclick="location.href='/board/list'">리스트</button>
	</div>

	
</div>

<script type="text/javascript">

console.log("Test");
const rs = '<c:out value="${result}" />';
console.log(rs);
if(rs == '0'){
	alert("이미 존재하는 아이디입니다.");
}

</script>

<jsp:include page="../includes/footer.jsp"></jsp:include>