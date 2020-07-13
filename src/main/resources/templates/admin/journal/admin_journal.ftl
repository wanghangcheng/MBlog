
<!DOCTYPE html>
<html lang="en" class="app">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MBlog - 后台管理</title>

    <!-- Bootstrap -->
    <link href="/assets/vendors/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/vendors/bootstrap_table/bootstrap-table.css	" rel="stylesheet">
    <link href="/assets/vendors/bootstrap_select/bootstrap-select.min.css" rel="stylesheet">
    <link href="/assets/vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet">
   	<!-- table拖拽 -->
    <!-- <link href="/assets/vendors/bootstrap_table/bootstrap-table-reorder-rows.css" rel="stylesheet"> -->
    
    <!-- Font Awesome -->
    <link href="/assets/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="/assets/vendors/treeTable/themes/vsStyle/treeTable.min.css" rel="stylesheet"/>
	<!-- LayUI -->
	<link rel='stylesheet' media='all' href='/assets/css/layout.css'/>
    <!-- Custom Theme Style -->
    <link href="/assets/admin/css/custom.min.css" rel="stylesheet">

    <!-- jQuery -->
    <script src="/assets/js/jquery-3.2.1.min.js"></script>
    <!-- Bootstrap -->
    <script src="/assets/vendors/bootstrap/js/bootstrap.min.js"></script>
    <script src="/assets/vendors/bootstrap_table/bootstrap-table.js"></script>
    <script src="/assets/vendors/bootstrap_table/bootstrap-table-zh-CN.js"></script>
    <!-- table拖拽 -->
    <!-- <script src="/assets/vendors/bootstrap_table/bootstrap-table-reorder-rows.js"></script>
    <script src="/assets/vendors/bootstrap_table/jquery.tablednd.js"></script> -->
    
    <script src="/assets/vendors/bootstrap_select/bootstrap-select.min.js"></script>
    <script src="/assets/vendors/bootstrap_select/defaults-zh_CN.min.js"></script>
    <script src="/assets/vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
    <script src="/assets/vendors/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
    
    <script src='/assets/vendors/validate/jquery-validate.js'></script>
    <!-- FastClick -->
    <script src="/assets/vendors/fastclick/lib/fastclick.js"></script>

    <script src="/assets/vendors/layer/layer.js"></script>
    <script src="/assets/vendors/treeTable/jquery.treeTable.min.js"></script>

    <script type="text/javascript">
        window.UEDITOR_HOME_URL = '/assets/vendors/ueditor/';
    </script>
 
</head>
<body class="nav-md">
<div class="container body">
    <div class="main_container">
    <!-- left menu -->   
	<#include "/admin/include/admin_menu.ftl">
    <!-- admin top -->
	<#include "/admin/include/admin_top.ftl">
        <!-- page content -->
        <div class="right_col" role="main">
            <div>
<div class="clearfix">
</div>
<div class="container-fluid">
	   <div class="row px-3">
	        <form  class="col-12">
	          <div class="form-row">
	          	<div class="form-group col-md-4">
			         	<input type="text" class=" form-control" id="username" name="username" placeholder="请输入要查询的用户名" autocomplete="off"/>
                </div>
                <div class="form-group col-md-4">
			         <select name="type" id="type" class="form-control selectpicker " title="请选择要查询的操作">
				    	<option value="0" >新增</option>
				    	<option value="1" >删除</option>
				    	<option value="2" >修改</option>
			         </select>
                </div>
                <div class="form-group col-md-3">
                   <button type="button" class="btn btn-primary ml-2" onclick="reloadTable()">查询</button>
                   <button type="button" class="btn btn-primary ml-2" onclick="reloadAll()">重置</button>
                </div>                              
	          </div>
	        </form>
		</div>
	</div>
	
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <table class="table table-hover table-responsive" id="journal_table"></table>
    </div>
</div>

<script type='text/javascript' src='/assets/vendors/Chart.js/dist/Chart.min.js'></script>
<script type='text/javascript'>
$(function() {
	$('#journal_table').bootstrapTable('destroy');
	$('#journal_table').bootstrapTable({
	    url: "/admin/journal/list",
	    contentType:"application/x-www-form-urlencoded; charset=UTF-8",
	    method:'post',
	    dataType:'json',
	    sidePagination : "server",//服务器分页
	    //toolbar : '#toolbar', //工具按钮用哪个容器
	    sortName : 'created',//初始化的时候排序的字段
		sortOrder: "desc", //排序方式
	    search : false, //是否显示表格搜索
	    showFooter : false, //显示底部，默认不显示
	    cache: false, //不使用缓存
	    striped: true,//设置为 true 会有隔行变色效果
	    pagination: true, //显示分页
	   // showPaginationSwitch : true, //显示切换分页
	    pageNumber : 1, //初始化加载第一页，默认第一页,并记录
	    pageSize: 8, //每页的行数
	    pageList: [8,15, 20, 25, 30], //自定义每页的行数
	    queryParams:queryParam,
	    showRefresh: false, //刷新按钮
	    //singleSelect : true, // 设置为true将禁止多选
		clickToSelect : true, // 是否启用点击选中行
		//uniqueId :'ID',//给每行设置唯一标识
		onClickRow:function (row,$element) {//点击修改行背景色
                    $('.info').removeClass('info');
                    $($element).addClass('info');
		}, 
	    columns: [{
	        title: "序号",
	        align: "center",
	        valign: "middle",
	       	formatter: function (value, row, index) {
	               return index+1;
	           },
	    },{
	        field: "source_user.nickname",
	        title: "操作人昵称",
	        align: "center",
	        valign: "middle"
	    },{
	    	field: "source_user.username",
	        title: "操作人用户名",
	        align: "center",
	        valign: "middle",
	    },{
	    	field: "content",
		    title: "操作内容",
		    align: "center",
		    valign: "middle"
	    },{
	    	field: "created",
	        title: "操作时间",
	        align: "center",
	        valign: "middle",
	        sortable:true,
	        formatter: dateFormatter,
		},{
			field: "type",
		    title: "执行的操作",
		    align: "center",
		    valign: "middle",
		    formatter: TypeFormatter,
	    },
	    ],
	    formatNoMatches: function () {
	    	return '无符合条件的记录';
	    },
	    onLoadSuccess: function(){    
	    	$(".dropdown-item").css("margin","3px");
	    //	$("span[class='btn-group dropdown dropup']").append('&nbsp;&nbsp;<input id="pageSize" name="pageSize" value="'+tempLeft+'" style="text-align:center;width:30px" οnkeydοwn="keyup_submit(event);"/>&nbsp;&nbsp;');
        },
	});
	//日期格式化方法
	 function dateFormatter(value,row,index){  
        var oDate = new Date(value),  
        oYear = oDate.getFullYear(),  
        oMonth = oDate.getMonth()+1,  
        oDay = oDate.getDate(),  
        oHour = oDate.getHours(),  
        oMin = oDate.getMinutes(),  
        /*oSen = oDate.getSeconds(), */  
        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay)+' '+getzf(oHour)+':'+getzf(oMin);//最后拼接时间  
        return oTime;  
    };  
    //补0操作  
    function getzf(num){  
        if(parseInt(num) < 10){  
            num = '0'+num;  
        }  
        return num;  
    }  
	//操作格式
	function TypeFormatter(value,row,index){
			let result= null;
			switch(value){
				case 0:
					result="新增";
					break;
				case 1:
					result="删除";
					break;
				case 2:
					result="修改";
					break;
			}
			return result;
		}
	//table数据请求参数
	function queryParam(params){
		var username = $("input[name='username']").val();
		var type = $("#type").selectpicker('val');
		var param = {
				limit : this.limit, // 每页显示数量
				offset : this.offset, // SQL语句起始索引
		        pageNumber : this.pageNumber,
		        pageSize : this.pageSize,
		        sort : params.sort,//排序
		        order : params.order,
		};
		if(username!=null&&username!=''){
			param['username'] = username;
		}
		if(type!=null&&type!=''&&typeof(type)!="undefined"){
			param['type'] = type;
		}
		//console.log(param);
		return param;
	}
});
//查询按钮，通过获取查询条件作为参数让table重新发送请求获取数据，进行刷新
function reloadTable() {
	//console.log(11);
	$("#journal_table").bootstrapTable('refresh');
}
// 重置按钮，重置所有查询条件
function reloadAll() {
	$("input[name=username]").val("");
	$('select[name=type]').selectpicker('val',['noneSelectedText'])//回到初始状态
	$("select[name=type]").selectpicker('refresh');//刷新
}
</script>
            </div>
        </div>
        <!-- /page content -->
<!-- 尾部 -->
        <#include "/admin/include/admin_footer.ftl">
    </div>
    <!-- Custom Theme Scripts -->
    <script src="/assets/admin/js/custom.min.js"></script>
    <script src="/assets/admin/js/app.data.js"></script>
</body>
</html>
