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

	<form action="/member/join" method="post">
	
	<label for="id" class="form-label">ID</label>
	<div class="input-group mb-3">
	  <input type="text" name="id" class="form-control" placeholder="ID" aria-label="id" >
	</div>
	
	<label for="pw" class="form-label">Password</label>
	<div class="input-group mb-3">
	  <input type="password" name="pw" class="form-control" placeholder="Password" aria-label="pw">
	</div>
	
	<label for="email" class="form-label">Your Email</label>
	<div class="input-group mb-3">
	  <input type="text" class="form-control" placeholder="Email" aria-label="Email">
	</div>
	
	<label for="name" class="form-label">Your Name</label>
	<div class="input-group mb-3">
	  <input type="text" name="name" class="form-control" placeholder="name" aria-label="name">
	</div>
	
	<label for="Age" class="form-label">Your Age</label>
	<div class="input-group mb-3">
	  <input type="number" name="age" class="form-control" placeholder="Age" aria-label="age">
	</div>
	
	<label for="home" class="form-label">Your Home</label>
	<div class="input-group mb-3">
	  <input type="text" name="home" class="form-control" placeholder="Home" aria-label="home">
	</div>
	
    	<button type="submit" class="btn btn-primary">Confirm</button>

	</form>
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