<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<style>
  #displayImg img{
      width:200px;
      height: 150px;
   
  }
</style>
<script>
   $(function(){
	   $("button#btnMod").click(function(){
		   location.href="${pageContext.request.contextPath }/sboard/updatePage?bno=${board.bno }&page=${cri.page}&searchType=${cri.searchType}&keyword=${cri.keyword}";
	   })
	   $("button#btnDel").click(function(){
		   var cc = confirm("삭제하시겠습니까?");
		   if(cc){
		   location.href="${pageContext.request.contextPath }/sboard/removePage?bno=${board.bno }&page=${cri.page}&searchType=${cri.searchType}&keyword=${cri.keyword}";
		   }
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
	                 <a href="${pageContext.request.contextPath }/sboard/listPage?page=${cri.page}&searchType=${cri.searchType}&keyword=${cri.keyword}">ToList</a>
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
		              <div class="form-group" id="displayImg">
		                 <c:forEach var="file" items="${board.files }">
		             
		                    <p><img src="displayFile?filename=${file }"></p>
		                 </c:forEach>
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
<div class="content">
<div class="row">
   <div class="col-xs-12">
      <div class="box box-success">
         <div class="bax-header">
           <h3 class="box-title">ADD NEW REPLY</h3>
         </div>
         <div class="box-body">
           <label>writer</label>
           <input type="text" class="form-control" placeholder="User id" id="newReplyWriter" name="replyer" value="${Auth }" readonly="readonly">
           <label>Reply text</label>
           <input type="text" class="form-control" placeholder="text" id="newReplyText" name="replytext">
           
         </div>
         <div class="box-footer">
            <button class="btn btn-primary" id="btnReplyAdd">Reply</button>
         </div>
      </div>
      <ul class="timeline">
        <li class="time-label" id="repliesDiv">
           <span class="bg-green">Replies List <span id="replyCount">[${board.replycnt}]</span></span>
        </li>
      </ul>
      <div class="text-center">
         <ul id="pagination" class="pagination pagination-sm no-margin"></ul>
      </div>
   </div>
</div>
</div>

<div id="modifyModal" class="modal modal-primary fade" role="dialog">
   <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
           <button type="button" class="close" data-dismiss="modal">&times;</button>
           <h4 class="modal-title">22</h4>
          
        </div>
        <div class="modal-body">
           <p>
             <input type="text" id="replytext" class="form-control">
           </p>
        </div>
        <div class="modal-footer">
           <button type="button" class="btn btn-info" id="btnModSave">Modify</button>
        </div>
      </div>
   </div>

</div>






<script id="template" type="text/x-handlebars-template">
{{#each list}}
<li class="replyLi" data-rno="{{rno}}">
   <i class ="fa fa-comments bg-blue"></i>
   <div class="timeline-item">
   
      <span class="time">
         <i class="fa fa-clock-o"></i>{{dateHelper regdate}}
      </span>
      <h3 class="timeline-header"><strong>{{rno}}</strong> - {{replyer}}</h3>
      <div class="timeline-body">{{replytext}}</div>
      <div class="timeline-footer">
         <a class="btn btn-primary btn-xs btnMod" data-toggle="modal" data-target="#modifyModal" data-rno="{{rno}}" data-text="{{replytext}}">Modify</a>
          <a class="btn btn-primary btn-xs btnDel"  data-rno="{{rno}}">Delete</a>
      </div>
   </div>

</li>
{{/each}}
</script>


<script>
var currentPage = 1; 
function getPageList(page ){
	
	
	Handlebars.registerHelper("dateHelper",function(value){
    	var d = new Date(value);
    	var year = d.getFullYear();
    	var month = d.getMonth()+1;
    	var day = d.getDate();
    	
    	var gg = d.getDay();
    	var week = ["일","월","화","수","목","금","토"];
    	return year + "/" + month +"/"+day + week[gg];
    	
    });
	 var bno =  ${board.bno };
	var replytext = $("#replytext").val();
    
   $.ajax({
	   url:"${pageContext.request.contextPath}/replies/"+bno+"/"+page,
	   type:"get",
	   dataType:"json",
	   success:function(res){
		   
		  console.log(res);
		  // $("#list").empty();
		   $(".replyLi").remove();
	       var source = $("#template").html();
		   var func = Handlebars.compile(source);
		   $(".timeline").append(func(res));
		 
		   $("#pagination").empty();
		   
		   for(var i = res.pageMaker.startPage; i<=res.pageMaker.endPage; i++){
			   var $li = $("<li>");
			   if(i == currentPage){
				   $li.addClass("active");
			   }
			    var $a = $("<a>").html(i);
			    $li.append($a);
			   $("#pagination").append($li);
		   }
		  // console.log(res.pageMaker.totalCount);
		   $("#replyCount").html("["+res.pageMaker.totalCount+"]"); 
	   }
   })
}
    $("#repliesDiv").click(function(){
    	   //타임라인에 엠프티 넣으면 초록 버튼도 사라져서 이렇게 클래스를 넣고 자기 자신을 없앰 
    	 
    	getPageList(1);
    })
    
     $(document).on("click","#pagination li",function(){
    	 
        	//getPageList(번호);
           //클릭한 번호만 알면 됨 
           var no = $(this).text(); //text써도 됨  html하면 안에 a 코드가 있어서 안됨 
           // alert(no);
          // $(this).addClass("dark");
           currentPage = no;
           getPageList(no);
        })
        
     //댓글 수정 버튼 누르면 팝업창 뜨기
      $(document).on("click",".btnMod",function(){
    	   var rno= $(this).attr("data-rno");
           var replytext= $(this).attr("data-text");
    	   
    	   $("#modifyModal").find(".modal-title").html(rno);  // == $(".modPopup .mod-rno").text(rno);
    	   $("#modifyModal").find(".form-control").val(replytext);
         })
         
     //팝업창에서 수정 버튼 눌렀을 때
      $(document).on("click","#btnModSave",function(){
	        var rno = $(this).parents("#modifyModal").find("h4").text();
	       // alert(rno);
	        var text = $(this).parents("#modifyModal").find(".form-control").val();
	       // alert(text);
	        
	        var json= JSON.stringify({"rno":rno, "replytext":text});
	    	
	    	//서버주소 컨트롤러에 보면 비어있어서 replies/ 슬래시까지
	    	$.ajax({
	    		url:"${pageContext.request.contextPath}/replies/"+rno, //앞에 슬래시 붙이면 프로젝트 이름이 날아간다
	    	    type : "put",
	    	    headers:{"Content-Type":"application/json"},
	    	    data:json, 
	    	    dataType:"text", // 단순한 스트링은 json아님, 객체를 보낼 때만 json
	    	    success:function(res){
	    	    	console.log(res);
	    	    	if(res =="SUCCESS"){
	    	    		alert("댓글이 수정되었습니다");
	    	    		$("#modifyModal").modal('hide');
	    	    		//리스트 갱신하는 기능
	    	    		getPageList(currentPage);
	    	    	}
	    	    }
	    	
	    	})
	     })   
         
     //댓글 삭제버튼 눌렀을 때 
     $(document).on("click",".btnDel",function(){   //btn.btn-primary.btn-xs.btnDel 이런식으로 구분함 내가 준 클래스 btnDel 
        	var no = $(this).attr("data-rno");
       
        	 $.ajax({
           	   url:"${pageContext.request.contextPath}/replies/"+no,
           	   type:"delete",
           	   dataType:"text", //돌아올 타입은 있어서
           	   success:function(res){
           		   console.log(res);
           		   if(res == "SUCCESS"){
           			 alert("삭제되었습니다");  
           		    //리스트 갱신 
           		     getPageList(currentPage);
           		    
           		   }
           	     }
           })
        })
       
        
        
        //댓글 등록
        $("#btnReplyAdd").click(function(){
    	//댓글 등록 게시글 번호 등 정보를 가져와야함
    	var bno =  ${board.bno };
    	var replyer = $("#newReplyWriter").val();
    	var replytext= $("#newReplyText").val();
    	
    	var json= JSON.stringify({"bno":bno, "replyer":replyer,"replytext":replytext});
    	//서버주소 컨트롤러에 보면 비어있어서 replies/ 슬래시까지
    	$.ajax({
    		url:"${pageContext.request.contextPath}/replies/", //앞에 슬래시 붙이면 프로젝트 이름이 날아간다
    	    type : "post",
    	    headers:{"Content-Type":"application/json"},
    	    data:json, 
    	    //vo가 받아야하는데 그 안에 이름이 정해져 있어서 앞부분 이름을 그대로 적어줘야한다.. 가 이제까지방식
    	    
    	    //키,값, 키값으로 보내는게 get이라 415에러가 난다.
    	    //컨트롤러 받는 부분에서 requestbody로 받는 경우에는 다른 형태로 보내주어야한다
    	    //1. 프로그램에서 보냈듯 application/json 를 헤더에 보내줘야한다.
    	    //2. 보내는 data는 json string으로 변형해서 보내야 한다. "{"bno:bno"}" 함수제공한다
    	    //1. headers:{"Content-Type":"application/json"},
    	    //2. JSON.stringify({"bno":bno, "replyer":replyer,"replytext":replytext})
    	    dataType:"text", // 단순한 스트링은 json아님, 객체를 보낼 때만 json
    	    success:function(res){
    	    	console.log(res);
    	    	if(res =="success"){
    	    		alert("댓글이 등록되었습니다");
    	    		$("#newReplyWriter").val("");
    	        	$("#newReplyText").val("");
    	    		//리스트 갱신하는 기능
    	    		getPageList(1);
    	    	}
    	    }
    	
    	})

    	
    })

</script>
<%@ include file="../include/footer.jsp"%>