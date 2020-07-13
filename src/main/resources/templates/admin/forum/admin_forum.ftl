
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
			         	<input type="text" class=" form-control" id="name" name="name" placeholder="请输入要查询的版块名称" autocomplete="off"/>
                </div>
                <div class="form-group col-md-4">
			         <select name="status" id="status" class="form-control selectpicker " title="请选择要查询的板块状态">
				    	<option value="0" >已屏蔽的板块</option>
				    	<option value="1" >已显示的板块</option>
			         </select>
                </div>
                
                <div class="form-group col-md-3">
                   <button type="button" class="btn btn-primary ml-2" onclick="reloadTable()">查询</button>
                   <button type="button" class="btn btn-primary ml-2" onclick="reloadAll()">重置</button>
                   <button type="button" class="btn btn-success ml-2" onclick="insertForum()">新增</button>
                </div>                              
	          </div>
	        </form>
		</div>
	</div>
	
<!-- 模态框（Modal） -->
	<div class="modal fade " id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5>修改栏目信息</h5>
			<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button> -->
				</div>
				<div class="modal-body">
			         <form class="form-horizontal" role="form" id="editform">
			         	  <input type="hidden" id="forumId" name="forumId"/>
			         	  <div class="form-group">
					    		<label for="username" class="col-sm-2 control-label">栏目名称:</label>
					    		<div class="col-sm-5">
					    			<input type="text" class="form-control" id="forumname" name="forumname" placeholder="请输入版块名称"/>
					    		</div>
					      </div>
					      <div class="form-group">
							    <label for="state" class="col-sm-2 control-label">栏目状态:</label>
							    <div class="col-sm-5">
							    	<select name="stats" id="stats" class="form-control selectpicker " title="请选择要更改的版块状态">
								    	<option value="0" >屏蔽该版块</option>
								    	<option value="1" >显示该版块</option>
							         </select>
							    </div>
						  </div>
						  <div class="form-group">
					    		<label for="email" class="col-sm-2 control-label">栏目权重:</label>
					    		<div class="col-sm-5">
					    			<input type="text" class="form-control" id="weight" name="weight" placeholder="请输入要修改的权重"/>
					    		</div>
					      </div>
					      <div class="form-group">
					      		<div class="col-sm-5">
					      			<a>注：权重越低，显示越靠前<br>（1为首页不可填）</a>
					      		</div>
					      </div>
						  <div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">
								关闭
							</button>
							<button type="button" class="btn btn-primary" id="modal-btn">
								提交更改
							</button>
						  </div>
		             </form>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<!--新增模态框（Modal） -->
	<div class="modal fade " id="newModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5>新增栏目信息</h5>
			<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button> -->
				</div>
				<div class="modal-body">
			         <form class="form-horizontal" role="form" id="newform">
			         	  <!-- <input type="hidden" id="forumId" name="forumId"/> -->
			         	  <div class="form-group">
					    		<label for="f_n_name" class="col-sm-2 control-label">栏目名称:</label>
					    		<div class="col-sm-5">
					    			<input type="text" class="form-control" id="f_n_name" name="f_n_name" placeholder="请输入栏目名称"/>
					    		</div>
					      </div>
					      <div class="form-group">
					    		<label for="f_n_code" class="col-sm-2 control-label">栏目标识:</label>
					    		<div class="col-sm-5">
					    			<input type="text" class="form-control" id="f_n_code" name="f_n_code" placeholder="请输入栏目英文标识"/>
					    		</div>
					      </div>
						  <div class="form-group">
					    		<label for="f_n_weight" class="col-sm-2 control-label">栏目权重:</label>
					    		<div class="col-sm-5">
					    			<input type="text" class="form-control" id="f_n_weight" name="f_n_weight" placeholder="请输入栏目数字权重"/>
					    		</div>
					      </div>
					      <div class="form-group">
					      		<div class="col-sm-5">
					      			<a>注：标识与权重，请勿与已有重复<br>新增栏目前台默认不显示</a>
					      		</div>
					      </div>
						  <div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">
								关闭
							</button>
							<button type="button" class="btn btn-success" id="newmodal-btn">
								保存
							</button>
						  </div>
		             </form>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <table class="table table-hover table-responsive" id="forum_table"></table>
    </div>
</div>

<script type='text/javascript' src='/assets/vendors/Chart.js/dist/Chart.min.js'></script>
<script type='text/javascript'>
$(function() {
	$('#forum_table').bootstrapTable('destroy');
	$('#forum_table').bootstrapTable({
	    url: "/admin/forum/list",
	    contentType:"application/x-www-form-urlencoded; charset=UTF-8",
	    method:'post',
	    dataType:'json',
	    sidePagination : "server",//服务器分页
	    //toolbar : '#toolbar', //工具按钮用哪个容器
	    sortName : 'id',//初始化的时候排序的字段
		sortOrder: "asc", //排序方式
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
	        field: "name",
	        title: "版块名称",
	        align: "center",
	        //width : "300",
	        valign: "middle"
	    },{
	        field: "code",
	        title: "版块标识",
	        align: "center",
	        valign: "middle"
	    },{
	    	field: "articleCount",
		    title: "该板块下文章数",
		    align: "center",
		    valign: "middle"
		},{
	    	field: "status",
	        title: "版块状态",
	        align: "center",
	        valign: "middle",
	        sortable:true,
	        formatter: statusFormatter,
	    },{
	        field: "weight",
	        title: "版块权重",
	        align: "center",
	        valign: "middle",
	        sortable:true,
	    },{
	        field: 'id',
	        title: '操作',
	        align: 'center',
	        valign: 'middle',
	        formatter: actionFormatter,
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
	//板块状态格式
	function statusFormatter(value,row,index){
			let result= null;
			switch(value){
				case true:
					result="已显示的版块";
					break;
				case false:
					result="已屏蔽的版块";
					break;
			}
			return result;
		}
	//操作相关格式
	function actionFormatter(value, row, index) {
		//console.log(index);
	    var id = value;
	    let result = "";
	    if(id==1){
	    	return "<a>不可操作<a>";
		}
	    //result += "<a href='javascript:void(0)' class='btn btn-xs green' onclick=\'ShowViewById("+id+")'\ title='查看'><span class='glyphicon glyphicon-search'></span></a>";
	    result += "<a href='javascript:void(0)' id="+index+" class='btn btn-xs blue' onclick=\"EditViewById(this,"+ id +")\" title='编辑'  data-toggle='modal' data-target='#myModal'><span class='glyphicon glyphicon-pencil'></span></a>";
	    result += "<a href='javascript:void(0)' id="+index+" class='btn btn-xs red' onclick=\'DeleteById(this,"+id+")'\ title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
	    return result;
	}
	//table数据请求参数
	function queryParam(params){
		var name = $("input[name='name']").val();
        var status = $("#status").selectpicker('val');
		var param = {
				limit : this.limit, // 每页显示数量
				offset : this.offset, // SQL语句起始索引
		        pageNumber : this.pageNumber,
		        pageSize : this.pageSize,
		        sort : params.sort,//排序
		        order : params.order,
		};
		if(name!=null&&name!=''){
			param['name'] = name;
		}
		if(status!=null&&status!=''&&typeof(status)!="undefined"){
			param['status'] = status;
		}
		//console.log(param);
		return param;
	}
});
//查询按钮，通过获取查询条件作为参数让table重新发送请求获取数据，进行刷新
function reloadTable() {
	//console.log(11);
	$("#forum_table").bootstrapTable('refresh');
}
// 重置按钮，重置所有查询条件
function reloadAll() {
	$("input[name=name]").val("");
	$('select[name=status]').selectpicker('val',['noneSelectedText'])//回到初始状态
	$("select[name=status]").selectpicker('refresh');//刷新
	
}
//新增栏目
function insertForum(){
	//居中弹出模态框
	$('#newModal').on('show.bs.modal', function(){
	    var $this = $(this);
	    var $modal_dialog = $this.find('.modal-dialog');
	    $this.css('display', 'block');
	    $modal_dialog.css({'margin-top': Math.max(0, ($(window).height() - $modal_dialog.height()) / 2) });
	    
	});
	$("#newModal").modal({backdrop: false, keyboard: false});
	
	//新增请求
	$('#newmodal-btn').unbind("click").click(function(){
		var formdata = new FormData();
		formdata.append("f_n_name",$("#f_n_name").val());
		formdata.append("f_n_code",$("#f_n_code").val());
		formdata.append("f_n_weight",$("#f_n_weight").val());
		//console.log(formdata);
		$.ajax({ 
    		type: "POST",
    		url: '/admin/forum/insert',
    		data: formdata,
    		dataType: 'JSON',
            processData: false,// 不加会报错	
            contentType: false,// 不加会报错
    		success: function(data){
    			$('#newModal').modal('hide');
    			if(data.code ==101){
    				layer.msg(data.msg, function(){});
    			}
    			if(data.code ==102){
    				layer.msg(data.msg, function(){});
    			}
    			if(data.code ==103){
    				layer.msg(data.msg, function(){});
    			}
    			if(data.code ==100){
    				layer.msg(data.msg, {icon: 6});
    				$("input[name=f_n_name]").val("");
    				$("input[name=f_n_code]").val("");
    				$("input[name=f_n_weight]").val("");
    			}
    			$("#forum_table").bootstrapTable('refresh');
    		},
    		error: function(XMLHttpRequest,textStatus,errorThrown){
            	layer.close(layer);
            	layer.msg('服务器通讯错误', {icon: 5});
            },
		});
	});
}
//根据字符获取value值
function findValByStr(str){
	let result=null
	if(str == '已屏蔽的版块'){
		result = 0;
		return result;
	}
	if(str == '已显示的版块'){
		result = 1;
		return result;
	}
}
//查看操作
/* function ShowViewById(id){
	//console.log(id);
	window.location.href="/ta/"+id;
} */
//修改操作
function EditViewById(obj,id){
	let index = $(obj).attr("id");
	let th_td = $("tr[data-index = '"+index+"']").find('td')
	let forumname = th_td.eq(1).text();
	let status = findValByStr(th_td.eq(4).text());
	let weight = th_td.eq(5).text();
	//居中弹出模态框
	$('#myModal').on('show.bs.modal', function(){
	    var $this = $(this);
	    var $modal_dialog = $this.find('.modal-dialog');
	    $this.css('display', 'block');
	    $modal_dialog.css({'margin-top': Math.max(0, ($(window).height() - $modal_dialog.height()) / 2) });
	    
	});
	$("#myModal").modal({backdrop: false, keyboard: false});
	
	$("#forumId").val(id);
	$("#forumname").val(forumname);
	$('select[name=stats]').selectpicker('val',status);
	$("#weight").val(weight);
	
	//修改请求
	$('#modal-btn').unbind("click").click(function(){
		var formdata = new FormData();
		formdata.append("forumId",id);
		formdata.append("name",$("#forumname").val());
		formdata.append("stats",$('select[name=stats]').selectpicker('val'));
		formdata.append("weight",$("#weight").val());
		//console.log(formdata);
		$.ajax({ 
    		type: "POST",
    		url: '/admin/forum/edit',
    		data: formdata,
    		dataType: 'JSON',
            processData: false,// 不加会报错	
            contentType: false,// 不加会报错
    		success: function(data){
    			$('#myModal').modal('hide');
    			if(data.code ==101){
    				layer.msg(data.msg, function(){});
    			}
    			if(data.code ==102){
    				layer.msg(data.msg, function(){});
    			}
    			if(data.code ==104){
    				layer.msg(data.msg, function(){});
    			}
    			if(data.code ==105){
    				layer.msg(data.msg, function(){});
    			}
    			if(data.code ==103){
    				layer.msg(data.msg, {icon: 6});
    				$("input[name=forumname]").val("");
    				$("input[name=weight]").val("");
    				$('select[name=stats]').selectpicker('val',['noneSelectedText'])//回到初始状态
    				$("select[name=stats]").selectpicker('refresh');//刷新
    				
    			}
    			$("#forum_table").bootstrapTable('refresh');
    		},
    		error: function(XMLHttpRequest,textStatus,errorThrown){
            	layer.close(layer);
            	layer.msg('服务器通讯错误', {icon: 5});
            },
		});
	});
}
//删除操作
function DeleteById(obj,id){
	let index = $(obj).attr("id");
	//console.log($("tr[data-index = '"+index+"']"));
	layer.confirm('确定删除该版块吗?', {
        btn: ['确定','取消'], //按钮
        shade: false //不显示遮罩
    }, function(){
		 jQuery.getJSON('/admin/forum/delete' ,{'id':id }, function (ret) {
			if (ret.code ==101) {
				layer.msg(ret.msg, function(){});
			} 
			if (ret.code ==103) {
				layer.msg(ret.msg, function(){});
			} 
			if (ret.code ==100) {
				layer.msg(ret.msg, {icon: 1});
				$("tr[data-index = '"+index+"']").fadeOut(1000,function(){
					$("tr[data-index = '"+index+"']").remove();
				});
			} 
		});
    }, function(){
		
    }
    );
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
