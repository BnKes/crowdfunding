<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
    
    input[type=checkbox] {
        width:18px;
        height:18px;        
    }
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 分类管理</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
				<%@ include file="/WEB-INF/jsp/common/top.jsp" %>
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
			<div class="tree">
				<jsp:include page="/WEB-INF/jsp/common/menu.jsp"></jsp:include>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据矩阵</h3>
			  </div>
			  <div class="panel-body">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th>名称</th>
                  <th >商业公司</th>
                  <th >个体工商户</th>
                  <th >个人经营</th>
                  <th >政府及非营利组织</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>营业执照副本</td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                </tr>
                <tr>
                  <td>税务登记证</td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                </tr>
                <tr>
                  <td>组织机构代码证</td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                </tr>
                <tr>
                  <td>单位登记证件 </td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                </tr>
                <tr>
                  <td>法定代表人证件</td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                </tr>
                <tr>
                  <td>经营者证件 </td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                </tr>
                <tr>
                  <td>手执身份证照片</td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                  <td><input type="checkbox"></td>
                </tr>
              </tbody>
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
				showMenu();
            });
        </script>
        <script type="text/javascript" src="${APP_PATH }/script/menu.js"></script>
  </body>
</html>
