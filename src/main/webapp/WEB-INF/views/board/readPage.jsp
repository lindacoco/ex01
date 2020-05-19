<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>


<script>
   $(function(){
	   $("button#btnMod").click(function(){
		   location.href="${pageContext.request.contextPath }/board/updatePage?bno=${board.bno }&page=${cri.page}";
	   })
	   $("button#btnDel").click(function(){
		   location.href="${pageContext.request.contextPath }/board/removePage?bno=${board.bno }&page=${cri.page}";
	   })
   })

</script>
<div class="content">
	<div class="row">
	    <div class="col-sm-12">
	        <div class="box box-primary">
	           <div class="box-header">
	              <h3 class="box-title">Read</h3>
	            
	           </div>
	          
	           <div class="box-body text-right">
	                 <a href="${pageContext.request.contextPath }/board/listPage?page=${cri.page}">ToList</a>
	           </div>
	            <div class="box-body">
	            <div class="form-group">
		                  <label>No</label>
		                  ${board.bno }
		              </div>
	               <div class="form-group">
		                  <label>title</label>
		                  <input type="text" name="title" class="form-control" value="${board.title }" readonly="readonly">
		              </div>
		              <div class="form-group">
		                  <label>Content</label>
		                  <textarea rows= "5" cols="30" class="form-control" name="content" readonly="readonly">${board.content }</textarea>
		              </div>
		              <div class="form-group">
		                  <label>Writer</label>
		                   <input type="text" name="writer" class="form-control" value="${board.writer }" readonly="readonly">
		              </div><div class="form-group">
		                 <%-- <a href="${pageContext.request.contextPath }/board/update"><button type="submit" class="btn btn-warning">Modify</button></a> --%>
		                 <button type="submit" class="btn btn-warning" id="btnMod">Modify</button>
		                 <button type="submit" class="btn btn-danger" id="btnDel">Remove</button>
		              </div>
	           </div>
	        </div>
	    </div>
	</div>
</div>



<%@ include file="../include/footer.jsp"%>