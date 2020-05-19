<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<style>
  #displayImg img{
      width:200px;
      height: 150px;
   
  }
  .imgDiv{
    float:left;
    width:200px;
    position: relative;
  }
  .imgDiv .checkbox{
    position:absolute;
    top:0;
    right:0;
  }
  .addedDiv{
    float:left;
    width:200px;
    position: relative;
  }
  .addedDiv img{
     width:200px;
     height: 150px;
  }
  .addedDiv .checkbox{
    position:absolute;
    top:0;
    right:0;
  }
  #cancel{
    clear:both;
  }
</style>
<div class="content">
	<div class="row">
	    <div class="col-sm-12">
	        <div class="box box-primary">
	           <div class="box-header">
	              <h3 class="box-title">UPDATE</h3>
	           </div>
	            <div class="box-body text-right">
	                 <a href="${pageContext.request.contextPath }/sboard/listPage?page=${cri.page}&searchType=${cri.searchType}&keyword=${cri.keyword}&">ToList</a>
	           </div>
	           <form role="form" action="updatePage" method="post" enctype="multipart/form-data">
		           <div class="box-body">
		              <div class="form-group">
		              <input type="hidden" name="searchType" value="${cri.searchType }">
		               <input type="hidden" name="keyword" value="${cri.keyword }">
		              <input type="hidden" name="page" value="${cri.page }">
		              <input type="hidden" name="bno" value="${board.bno }">
		               <input type="hidden" name="viewcnt" value="${board.viewcnt }">
		                  <label>title</label>
		                  <input type="text" name="title" class="form-control" value="${board.title }">
		              </div>
		              <div class="form-group">
		                  <label>Content</label>
		                  <textarea rows= "5" cols="30" class="form-control" name="content">${board.content }</textarea>
		              </div>
		              
		              <div class="form-group">
		                  <label>Writer</label>
		                   <input type="text" name="writer" class="form-control" readonly="readonly" value="${board.writer }">
		              </div>
		              <div class="form-group">
		                 <label>Image Files</label>
		                 <input type="file" name="imgfiles" class="form-control" multiple="multiple" id="file">
		              </div>
		               <div class="form-group" id="displayImg">
		                 <c:forEach var="file" items="${board.files }">
			                 <div class="imgDiv"><img src="displayFile?filename=${file }">
			                    <input type="checkbox" class="checkbox" name="delfiles" value="${file }">
			                 </div>
		                 </c:forEach>
		                 
		              </div class="form-group" >
		              <div id="previewBox"></div>
		              
		              <div class="form-group" id="cancel">
		                 <button type="submit" class="btn btn-primary">Submit</button>
		              </div>
		           </div>
	           </form>
	        </div>
	    </div>
	</div>
</div>
<script>
    $(".checkbox").change(function(){
    	if($(this).is(":checked")){
    		
    	   $(this).prev().css("opacity","0.5");
    	   
    	 }else{
    		 
    	   $(this).prev().css("opacity","1");
    	}
  })
  
  $(document).on("change",".checkbox",function(){
	  if($(this).is(":checked")){
  		
   	   $(this).prev().css("opacity","0.5");
   	   
   	 }else{
   		 
   	   $(this).prev().css("opacity","1");
   	}
  })
  $("#file").change(function(){
    	 // var file = $(this)[0]; //$(this)[0] : javascript객체를 의미  제이쿼리의 0번째 정보는 자바스크립트가 가지고 있음
	      
    	  var file = $(this)[0].files; //배열
    	  $("#previewBox") .empty();  //비우기 
    	  for(var i=0; i<file.length; i++){
    		  
    		  var reader = new FileReader();
        	  reader.readAsDataURL(file[i]);
        	  reader.onload = function(e){ //.addEventListener("load",function(){}) - 여러개 이벤트를 넣을 수 있음 
        		  var $addedDiv = $("<div class='addedDiv'>");
        		  var $img = $("<img>").attr("src",e.target.result); // == reader.result 같은 값이 넘어온다 
        		  var $checkbox = $("<input type='checkbox' class='checkbox' name='imgfiles' value='${file }'>");
        		  
        		  $addedDiv.append($img);
        		  $addedDiv.append($checkbox);
        		  
        		  $("#previewBox").append($addedDiv);
        		
        	  }
    	  }
    	  
    	  
    	 
      })
</script>
<%@ include file="../include/footer.jsp"%>