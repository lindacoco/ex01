<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="content">
	<div class="row">
	    <div class="col-sm-12">
	        <div class="box box-primary">
	           <div class="box-header">
	              <h3 class="box-title">UPDATE</h3>
	           </div>
	           
	           <form role="form" action="update" method="post">
		           <div class="box-body">
		              <div class="form-group">
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
		              </div><div class="form-group">
		                 <button type="submit" class="btn btn-primary">Submit</button>
		              </div>
		           </div>
	           </form>
	        </div>
	    </div>
	</div>
</div>

<%@ include file="../include/footer.jsp"%>