<%@ page session="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../include/pluginpage.jsp"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>처리품관리</title>

<style>

	body {
			text-align: center;
			scroll:no;
			overflow: hidden;
			color: black;
		}

	hr{
		width: 95%;
		margin: 10px 2.5% 2% 2.5%;
	}
	
	/* 버튼 */
	div > button{
		width: 110px;
		height: 35px;
		margin-left: 5px;
		border: 1px solid black; 
		color: firebrick;
	}
	
		
	div > button:hover {     
		background: firebrick;
		border: 1px solid firebrick;
		color: white; 
	}

	#contents{
		color: black;
	}
	
	.release_btn{
		margin-top: 9%;
	}
	
	.hogi{
		margin-bottom: 4%;
		font-size:15pt;
		font-weight:700;
	}
</style>



</head>

<body data-offset="60" data-target=".navbar">


<div id="wrap">

	
	<div id="body2">
		
	<div id="contents">
		<!-- <div style="color: black; font-size: 20px; padding-top: 2%;"> &lt;투입 LIST&gt; </div> -->
		<div style="color: black; font-size: 14px; padding-top: 5%; margin-left: 2.5%; text-align: left;"> <b style="font-size:15pt;">처리품관리</b> / <label style="font-size:15pt;">투입 LIST - 출고요청</label> </div>
		<hr>
		
		<div class="release_btn">
			<div class="hogi">침탄 5호기 <button id="hogi5_out" type="button"><b>출고요청</b></button> </div>
			<div class="hogi">침탄 6호기 <button id="hogi6_out" type="button"><b>출고요청</b></button> </div>
			<div class="hogi">침탄 7호기 <button id="hogi7_out" type="button"><b>출고요청</b></button> </div>
		</div>
	</div>
</div>
</div>
	<script>
	
	//로드

	
	
	
	
	
	$("#hogi5_out").on("click", function() {
	    // 1호기 출고요청
	    $.ajax({
	        url: "/transys/product/dayList/popup/insert",
	        type: "post",
	        dataType: "json",
	        data: { "device_code": "5" }, // device_code를 문자열로 설정
	        success: function(result) {
	            console.log("5호기 출고 요청 성공:", result); // 성공 여부 및 데이터 출력
	            window.close();
	        },
	        error: function(xhr, status, error) {
	            console.error("5호기 출고 요청 실패:", status, error); // 실패 시 오류 출력
	        }
	    });
	});
	
	$("#hogi6_out").on("click", function() {
	    // 2호기 출고요청
	    $.ajax({
	        url: "/transys/product/dayList/popup/insert",
	        type: "post",
	        dataType: "json",
	        data: { "device_code": "6" }, // device_code를 문자열로 설정
	        success: function(result) {
	            console.log("6호기 출고 요청 성공:", result); // 성공 여부 및 데이터 출력
	            window.close();
	        },
	        error: function(xhr, status, error) {
	            console.error("6호기 출고 요청 실패:", status, error); // 실패 시 오류 출력
	        }
	    });
	});
	
	$("#hogi7_out").on("click", function() {
	    // 3호기 출고요청
	    $.ajax({
	        url: "/transys/product/dayList/popup/insert",
	        type: "post",
	        dataType: "json",
	        data: { "device_code": "7" }, // device_code를 문자열로 설정
	        success: function(result) {
	            console.log("7호기 출고 요청 성공:", result); // 성공 여부 및 데이터 출력
	            window.close();
	        },
	        error: function(xhr, status, error) {
	            console.error("7호기 출고 요청 실패:", status, error); // 실패 시 오류 출력
	        }
	    });
	});


	</script>

</body>
</html>
