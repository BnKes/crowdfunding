<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/main.css">
	<link rel="stylesheet" href="${APP_PATH }/jquery/pagination/pagination.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 流程管理</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<%@include file="/WEB-INF/jsp/common/top.jsp"%>
			</li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<%@include file="/WEB-INF/jsp/common/menu.jsp" %>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input class="form-control has-success" type="text" placeholder="请输入查询条件">
    </div>
  </div>
  <button type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>

<button id="uploadPrcDefBtn" type="button" class="btn btn-primary" style="float:right;" ><i class="glyphicon glyphicon-upload"></i> 上传流程定义文件</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
          
            <form id="deployForm" action="" method="POST" enctype="multipart/form-data">
          		<input id="processDefFile" type="file" style="display:none" name="processDefFile">
          	</form>
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
                  <th>流程名称</th>
                  <th>流程版本</th>
                  <th>流程定义Key</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody>
                 
              </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="6" align="center">
						<ul id="Pagination" class="pagination">
 
						</ul>
					 </td>
				 </tr>
			  </tfoot>
            </table>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>

    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH}/jquery/layer/layer.js"></script>
	<script src="${APP_PATH}/jquery/pagination/jquery.pagination.js"></script>
	<script src="${APP_PATH }/jquery/jquery-form/jquery-form.min.js"></script>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
			    queryPageUser(0);
			    showMenu();
            });
            
            
            $("#uploadPrcDefBtn").click(function(){
                  	$("#deployForm")[0].reset();//二次提交前重置表单
            	$("#processDefFile").click();
            });
            
    
            $("#processDefFile").change(function(){
        		var options = {
          			url:"${APP_PATH}/process/deploy.do",
        			beforeSubmit : function(){
        				loadingIndex = layer.msg('数据正在部署中', {icon: 6});
                		return true ; //必须返回true,否则,请求终止.
        			},
        			success : function(result){
               			layer.close(loadingIndex);
               			if(result.success){
               				layer.msg(result.message, {time:1000, icon:6});
               				queryPageUser(0);//异步刷新后，表单需要重置，否则二次上传失败，个人猜测是change状态没有改变
               				/* window.location.href="${APP_PATH}/process/index.htm"; */
               			}else{
               				layer.msg(result.message, {time:1000, icon:5, shift:6});
               			}	
               		}	
           		};
           		
           		$("#deployForm").ajaxSubmit(options); //异步提交
              
           		return ; 
            	
            });
            
            var jsonObj = {
            		"pageno" : 1,
            		"pagesize" : 10 
            	};
             
            
            function deleteProcess(id,name){
            	layer.confirm("确认要删除["+name+"]流程吗?",  {icon: 3, title:'提示'}, function(cindex){
            		layer.close(cindex);
            		$.ajax({
                		type : "POST",
                		data : {
                			"id" : id
                		},
                		url : "${APP_PATH}/process/doDelete.do",
                		beforeSend : function() {   
                			return true ;
                		},
                		success : function(result){
                			if(result.success){
                				layer.msg("删除成功", {icon: 1});
                				queryPageUser(0);
                     			}else{
                     				layer.msg("删除失败", {time:1000, icon:5, shift:6}); 
                     			}
                     		},
                     		error : function(){
                     			layer.msg("删除失败", {time:1000, icon:5, shift:6}); 
                     		}
                     	});
            }, function(cindex){
                layer.close(cindex);
            });            	           	
          }
            
            var loadingIndex = -1 ;
            function queryPageUser(pageIndex){     //参数默认pageIndex
            	jsonObj.pageno = pageIndex + 1;
            	$.ajax({
            		type : "POST",
            		data : jsonObj,
            		url : "${APP_PATH}/process/doIndex.do",
            		beforeSend : function(){
            			loadingIndex = layer.load(2, {time: 10*1000});
            			return true ;
            		},
            		success : function(result){
            			layer.close(loadingIndex);
            			if(result.success){
            				var page = result.page ;
            				var data = page.data ;
            				
            				var content = '';
            				
            				$.each(data,function(i,n){ 
            					content+='<tr>';
            					content+='  <td>'+(i+1)+'</td>';
            					content+='  <td>'+n.name+'</td>';
            					content+='  <td>'+n.version+'</td>';
            					content+='  <td>'+n.key+'</td>';
            					content+='  <td>';
               					content+='	  <button type="button" onclick="window.location.href=\'${APP_PATH}/process/showimg.htm?id='+n.id+'\'" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-eye-open"></i></button>';
            					content+='	  <button type="button" onclick="deleteProcess(\''+n.id+'\',\''+n.name+'\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
            					content+='  </td>';
            					content+='</tr>';
            				});
            				
            				
            				$("tbody").html(content);
            				// 创建分页
            				$("#Pagination").pagination(page.totalsize, {    //page.totalsize查询出的总条数
            					num_edge_entries: 1, 			//边缘页数                             
            					num_display_entries: 3, 		//主体页数			
            					callback: queryPageUser,		//回调函数，回调自己
            					items_per_page: 10, //每页显示页数       //与自己设置的pagesize一致
            					current_page:(page.pageno-1),          //当前页，0代表第1页
            					prev_text : "上一页",                   //“前一页”分页按钮上显示的文字
            					next_text : "下一页"			//“下一页”分页按钮上显示的文字
            				});
            				
            			}else{
            				layer.msg(result.message, {time:1000, icon:5, shift:6});
            			}
            		},
            		error : function(){
            			layer.msg("加载数据失败!", {time:1000, icon:5, shift:6});
            		}
            	});
            }
        </script>
        <script type="text/javascript" src="${APP_PATH }/script/menu.js"></script>
  </body>
</html>
