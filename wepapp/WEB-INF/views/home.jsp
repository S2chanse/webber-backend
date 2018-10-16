<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	window.onload = function() {
		  $("#editorFileUpload").on('submit',function(e){
			  e.preventDefault();
			  e.stopPropagation();
			 
			  var formData = new FormData($("#editorFileUpload")[0]);
			  console.log(formData);
			$.ajax({
				url:"/api/template/update",
				type:"POST",
				enctype: "multipart/form-data",
				data:formData,
				processData : false,
	            contentType : false,
				success:function(data){
					alert(data);
					console.log(data);
				},
				error:function(xhr){
					alert('error: '+xhr.status);
					console.log(xhr);
				}
				
			});
		})
		;  
		
		  $("#getUserList").click('on',function(){
			  $.ajax({
					url:"/api/auth/123123",
					type:"GET",
					success:function(data){
						alert(data);
						console.log(data);
						for(i=0;i<data.length;i++){
							var trTag=$("<tr></tr>");
							var tdTag=$("<td></td>").append($("<a></a>").attr("href","/go/detailInfo?nickname="+data[i].nickname).html(data[i].nickname));
							trTag.append(tdTag);
							$("#userList").append(trTag);
						}
					},
					error:function(xhr){
						alert('error: '+xhr.status);
						console.log(xhr);
					}
					
				});
		 
		  });
	/*  	 $("#editorFileUpload").ajaxForm({
             success : function(result) {                
                     alert(result.message);
             },
             error:function(){
            	 alert(2);
             }
     });  */
	}
	

</script>
</head>
<body>
<form  id="editorFileUpload" method="post" enctype="multipart/form-data">
	<div>
	    <label for="fname">이름</label>
	        :
	    <input type="text" name="nickname" id="fname" value="C.m.A" />
	</div>
	 <div>
	    <label for="femail">이메일</label>
	    :
	    <input type="text" name="htmlCont" id="femail" value="" />
	    <input type="text" name="cssCont" id="femail2" value="" />
	    <input type="file" name="file"/>
	</div>
	<button>oke</button>
</form>

<button class="button" id="getUserList"> getuser</button>
<!-- <img src="/tImg/profilePhoto/testUser01/Chrysanthemum.jpg"/>-->
<!-- <img src="/tImg/templatephoto/C.m.A/Desert.jpg" alt="사진2"/> -->

<table id="userList">

</table>
<!--  <form id="fileForm" action="fileUpload" method="post"

        enctype="multipart/form-data">

        <input type="file" id="fileUp" name="fileUp"/><br/><br/>

        <input type="file" id="fileUp2" name="fileUp2"/><br/><br/>

          

        아이디 : <input type="text" name="id" />

        비밀번호 : <input type="password" name="pw" /><br/><br/>

        <input type="button" value="전송하기" o-nClick="fileSubmit();">

    </form>

 

<script>

    function fileSubmit() {

        var formData = new FormData($("#fileForm")[0]);

        console.log(formData);

        alert(formData);

        $.ajax({

            type : 'post',

            url : '/fileUpload',

            data : formData,

            processData : false,

            contentType : false,

            success : function(html) {

                alert("파일 업로드하였습니다.");

            },

            error : function(error) {

                alert("파일 업로드에 실패하였습니다.");

                console.log(error);

                console.log(error.status);

            }

        });

    }

</script> -->
</body>
</html>