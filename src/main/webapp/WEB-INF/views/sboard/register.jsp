<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<style>
  #previewBox img{
    width:200px;
    height: 180px;
  }
</style>
<div class="content">
	<div class="row">
	    <div class="col-sm-12">
	        <div class="box box-primary">
	           <div class="box-header">
	              <h3 class="box-title">REGISTER</h3>
	           </div>
	           
	           <form role="form" action="register" method="post" enctype="multipart/form-data">
		           <div class="box-body">
		              <div class="form-group">
		                  <label>title</label>
		                  <input type="text" name="title" class="form-control" placeholder="Enter title">
		              </div>
		              <div class="form-group">
		                  <label>Content</label>
		                  <textarea rows= "5" cols="30" class="form-control" name="content"></textarea>
		              </div>
		              <div class="form-group">
		                  <label>Writer</label>
		                   <input type="text" name="writer" class="form-control" placeholder="Enter writer" value="${Auth }" readonly="readonly">
		              </div>
		              <div class="form-group">
		                 <label>Image Files</label>
		                 <input type="file" name="imgfiles" class="form-control" multiple="multiple" id="file">
		              </div>
		              <div class="form-group" id="previewBox" style="height:300px;">
							
						</div>
		                <div class="box-footer">
		                 <button type="submit" class="btn btn-primary">Submit</button>
		              </div>
		           </div>
	           </form>
	        </div>
	    </div>
	</div>
</div>
<script>
      $("#file").change(function(){
    	 // var file = $(this)[0]; //$(this)[0] : javascript객체를 의미  제이쿼리의 0번째 정보는 자바스크립트가 가지고 있음
	      
    	  var file = $(this)[0].files; //배열
    	  $("#previewBox") .empty();  //비우기 
    	  for(var i=0; i<file.length; i++){
    		  
  
    		  var reader = new FileReader();
        	  reader.readAsDataURL(file[i]);
        	  reader.onload = function(e){ //.addEventListener("load",function(){}) - 여러개 이벤트를 넣을 수 있음 
        	
        		  var $img = $("<img>").attr("src",e.target.result); // == reader.result 같은 값이 넘어온다 
        		  $("#previewBox").append($img);
        	  }
    	  }
    	  
    	  
    	 
      })
   
  </script>
<%@ include file="../include/footer.jsp"%>