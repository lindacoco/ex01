<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="content">
	<div class="row">
	    <div class="col-sm-12">
	        <div class="box box-primary">
	           <div class="box-header">
	              <h3 class="box-title">REGISTER</h3>
	           </div>
	           
	           <form role="form" action="register" method="post">
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
		                   <input type="text" name="writer" class="form-control" placeholder="Enter writer">
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