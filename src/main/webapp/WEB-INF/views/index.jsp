<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="./includes/header.jsp"></jsp:include>

<img src="/resources/source/christmas.jpg" height="800px" />

<script type="text/javascript">
	const rs = "<c:out value='${result}' />";
	if(rs!=null){
		if(rs != "0"){
			alert("새 글이 등록되었습니다.");
		}else=={
			alert("글이 등록되지 않았습니다.");
		}
	}
</script>

<jsp:include page="./includes/footer.jsp"></jsp:include>