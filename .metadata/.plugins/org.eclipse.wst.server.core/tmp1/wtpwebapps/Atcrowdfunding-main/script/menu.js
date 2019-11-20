function showMenu(){
	var href = window.location.href ; //http://localhost:8888/Atcrowdfunding-main/user/index.htm
	var host = window.location.host ;//localhost:8888
	var index = href.indexOf(host);//7
	var path = href.substring(index + host.length); // /Atcrowdfunding-main/user/index.htm
	var contextPath = "${APP_PATH}"; //${APP_PATH}
	var pathAddress = path.substring(contextPath.length);// ding-main/user/index.htm
	
	var alink = $(".list-group a[href*='"+pathAddress+"']");//取出class="list-group"里面<a href*与pathAddress匹配成功的标签
	
	alink.css("color","red");//设为红色
	
	alink.parent().parent().parent().removeClass("tree-closed");//alink 的父父父标签，即<li class="list-group-item去掉tree-closed，即不关闭
	alink.parent().parent().show();
}