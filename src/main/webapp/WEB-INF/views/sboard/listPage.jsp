<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<div class="content">
	<div class="row">
	    <div class="col-sm-12">
	        <div class="box box-primary">
	           <div class="box-header">
	              <h3 class="box-title">Search Board LIST- 검색까지 가능하도록</h3>
	            
	           </div>
	          
	           <div class="box-body">
	                <select name="searchType" id="searchType"> 
	                    
	                   <option value="n" ${cri.searchType ==null?'selected':''}>------</option>
	                   <option value="t" ${cri.searchType =='t'?'selected':''}>Title</option>
	                   <option value="c" ${cri.searchType =='c'?'selected':''}>Content</option>
	                   <option value="w" ${cri.searchType =='w'?'selected':''}>Writer</option>
	                   <option value="tc" ${cri.searchType =='tc'?'selected':''}>Title or Content</option>
	                   <option value="cw" ${cri.searchType =='cw'?'selected':''}>Content or Writer</option>
	                   <option value="tcw" ${cri.searchType =='tcw'?'selected':''}>Title or Content or Writer</option>
	                   
	                </select>
	                <input type="text" name="keyword" id="keywordInput" value = ${cri.keyword }>
	                <button id="btnSearch">Search</button>
	                <button id="btnRegister">New Board</button>
	           </div>
	            <div class="box-body">
	               <table class="table table-borderd">
	                 <tr>
	                   <th style="width:10px;">BNO</th>
	                   <th>TITLE</th>
	                   <th>WRITER</th>
	                   <th>REGDATE</th>
	                   <th style="width:40px;">VIEWCNT</th>
	                 </tr>
	                 <c:forEach var="board" items="${list }">
	                    <tr>
	                       <td>${board.bno }</td>
	                       <td><a href="${pageContext.request.contextPath }/sboard/readPage?bno=${board.bno}&page=${cri.page}&searchType=${cri.searchType}&keyword=${cri.keyword}">
	                       ${board.title } </a> [${board.replycnt}] </td>
	                       <td>${board.writer }</td>
	                       <td><fmt:formatDate value="${board.regdate }" pattern="yyyy-MM-dd hh:mm"/></td>
	                       <td><span class="badge bg-red">${board.viewcnt }</span></td>
	                    </tr>
	                 </c:forEach>
	               </table>
	           </div>
	            <!--  박스 끝나느 부분  -->
	           <div class="box-footer">
	             <%--   --%>
	              <!--  부트 스트랩으로 사용  -->
	              <div class='text-center'>
	                   <ul class="pagination">
	                      <c:if test="${pageMaker.prev == true }">
	                          <li><a href="listPage?page=${pageMaker.startPage -1 }&searchType=${cri.searchType}&keyword=${cri.keyword}">&laquo;</a></li>
	                       </c:if>
	                      <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
	                         <li class="${pageMaker.cri.page == idx?'active':''}"><a href="listPage?page=${idx}&searchType=${cri.searchType}&keyword=${cri.keyword}"> ${idx }</a></li>
	                       </c:forEach>
	                       <!--  언제나 나오는 게 아니니까  -->
	                       <c:if test="${pageMaker.next == true }">
	                          <li><a href="listPage?page=${pageMaker.endPage +1 }&searchType=${cri.searchType}&keyword=${cri.keyword}">&raquo;</a></li>
	                       </c:if>
	                   </ul>
	               </div>
	           </div>
	        </div>
	    </div>
	</div>
</div>
<script>
    $("#btnSearch").click(function(){
    	var searchType = $("#searchType").val();
    	var keyword = $("#keywordInput").val();
    	location.href="listPage?searchType="+searchType+"&keyword="+keyword;
    })
    $("#btnRegister").click(function(){
    	location.href="register";
    })

</script>

<%@ include file="../include/footer.jsp"%>













