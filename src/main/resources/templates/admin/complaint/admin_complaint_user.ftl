
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
			         	<input type="text" class=" form-control" id="nickname" name="nickname" placeholder="请输入要查询的用户昵称" autocomplete="off"/>
                </div>
                <div class="form-group col-md-4">
			         <select name="handle" id="handle" class="form-control selectpicker " title="请选择要查询的处理状态">
				    	<option value="0" >未处理</option>
				    	<option value="1" >已处理</option>
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
	
<!-- 模态框（Modal） -->
	<div class="modal fade " id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5>发送整改通知</h5>
			<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button> -->
				</div>
				<div class="modal-body">
			         <form class="form-horizontal" role="form" id="user_editform">
			         	  <input type="hidden" id="userId" name="userId"/>
					      <div class="form-group">
					    		<label for="userNick" class="col-sm-2 control-label">整改用户:</label>
					    		<div class="col-sm-5">
					    			<input type="text" class="form-control" id="userNick" name="userNick" readonly placeholder="请输入用户昵称"/>
					    		</div>
					      </div>
					      <div class="form-group">
					    		<label for="remarks" class="col-sm-2 control-label">通知内容:</label>
					    		<div class="col-sm-5">
					    			<textarea id="content" class="form-control" name="content" placeholder="请输入通知内容"  style="height: 100px;width: 300px;resize: none;"></textarea>
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
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <table class="table table-hover table-responsive" id="complaint_user_table"></table>
    </div>
</div>

<script type='text/javascript' src='/assets/vendors/Chart.js/dist/Chart.min.js'></script>
<script type='text/javascript'>
$(function() {
	$('#complaint_user_table').bootstrapTable('destroy');
	$('#complaint_user_table').bootstrapTable({
	    url: "/admin/complaint/user/list",
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
	        field: "target_user.nickname",
	        title: "被举报用户昵称",
	        align: "center",
	        valign: "middle"
	    },{
	    	field: "created",
	        title: "举报时间",
	        align: "center",
	        valign: "middle",
	        sortable:true,
	        formatter: dateFormatter,
	    },{
	    	field: "remarks",
		    title: "备注说明",
		    align: "center",
		    valign: "middle"
	    },{
	    	field: "genre",
	        title: "投诉理由",
	        align: "center",
	        valign: "middle",
	       // sortable:true,
	        formatter: GenreFormatter,
		},{
			field: "complaintCount_user",
		    title: "被投诉次数",
		    align: "center",
		    valign: "middle"
		},{
			field: "handle",
		    title: "是否处理",
		    align: "center",
		    valign: "middle",
		    sortable:true,
		    formatter: HandleFormatter,
	    },{
	        field: 'target_user.id',
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
	//处理类型格式
	function HandleFormatter(value,row,index){
			let result= null;
			switch(value){
				case true:
					result="已处理";
					break;
				case false:
					result="未处理";
					break;
			}
			return result;
		}
	//举报理由格式
	function GenreFormatter(value,row,index){
			let result= null;
			switch(value){
				case 0:
					result="政治敏感";
					break;
				case 1:
					result="色情暴力";
					break;
				case 2:
					result="发布广告、诈骗等";
					break;
				case 3:
					result="其他";
					break;
			}
			return result;
		}
	//操作相关格式
	function actionFormatter(value, row, index) {
		//console.log(index);
	    var id = value;
	    let result = "";
	    result += "<a href='javascript:void(0)' class='btn btn-xs green' onclick=\'ShowViewById("+id+")'\ title='查看'><span class='glyphicon glyphicon-search'></span></a>";
	    result += "<a href='javascript:void(0)' id="+index+" class='btn btn-xs blue' onclick=\"EditViewById(this,"+ id +")\" title='发送通知'  data-toggle='modal' data-target='#myModal'><span class='glyphicon glyphicon-pencil'></span></a>";
	    //result += "<a href='javascript:void(0)' id="+index+" class='btn btn-xs red' onclick=\'DeleteById(this,"+id+")'\ title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
	    return result;
	}
	//table数据请求参数
	function queryParam(params){
		var user_nickname = $("input[name='nickname']").val();
		var handle = $("#handle").selectpicker('val');
		var param = {
				limit : this.limit, // 每页显示数量
				offset : this.offset, // SQL语句起始索引
		        pageNumber : this.pageNumber,
		        pageSize : this.pageSize,
		        sort : params.sort,//排序
		        order : params.order,
		};
		if(name!=null&&name!=''){
			param['user_nickname'] = user_nickname;
		}
		if(handle!=null&&handle!=''&&typeof(handle)!="undefined"){
			param['handle'] = handle;
		}
		//console.log(param);
		return param;
	}
});
//查询按钮，通过获取查询条件作为参数让table重新发送请求获取数据，进行刷新
function reloadTable() {
	//console.log(11);
	$("#complaint_user_table").bootstrapTable('refresh');
}
// 重置按钮，重置所有查询条件
function reloadAll() {
	$("input[name=nickname]").val("");
	$('select[name=handle]').selectpicker('val',['noneSelectedText'])//回到初始状态
	$("select[name=handle]").selectpicker('refresh');//刷新
}
//根据字符获取value值
function findValByStr(str){
	let result=null
	if(str == '未处理'){
		result = 0;
		return result;
	}
	if(str == '已处理'){
		result = 1;
		return result;
	}
}
//查看操作
function ShowViewById(id){
	//console.log(id);
	window.location.href="/ta/"+id;
} 
//修改操作
function EditViewById(obj,id){
	let index = $(obj).attr("id");
	let th_td = $("tr[data-index = '"+index+"']").find('td')
	let user_nickname = th_td.eq(1).text();
	//let handle = findValByStr(th_td.eq(6).text());
	//let weight = th_td.eq(5).text();
	//居中弹出模态框
	$('#myModal').on('show.bs.modal', function(){
	    var $this = $(this);
	    var $modal_dialog = $this.find('.modal-dialog');
	    $this.css('display', 'block');
	    $modal_dialog.css({'margin-top': Math.max(0, ($(window).height() - $modal_dialog.height()) / 2) });
	    
	});
	$("#myModal").modal({backdrop: false, keyboard: false});
	
	$("#userId").val(id);
	$("#userNick").val(user_nickname);
	//$('select[name=editHandle]').selectpicker('val',handle);
	
	//修改请求
	$('#modal-btn').unbind("click").click(function(){
		var formdata = new FormData();
		formdata.append("Id",$("#userId").val());
		formdata.append("content",$("#content").val());
		formdata.append("type",0);
		//console.log(formdata);
		$.ajax({ 
    		type: "POST",
    		url: '/admin/complaint/edit',
    		data: formdata,
    		dataType: 'JSON',
            processData: false,// 不加会报错	
            contentType: false,// 不加会报错
    		success: function(data){
    			$('#myModal').modal('hide');
    			if(data.code ==102){
    				layer.msg(data.msg, function(){});
    			}
    			if(data.code ==103){
    				layer.msg(data.msg, {icon: 6});
    			}
    			$("#complaint_user_table").bootstrapTable('refresh');
    		},
    		error: function(XMLHttpRequest,textStatus,errorThrown){
            	layer.close(layer);
            	layer.msg('服务器通讯错误', {icon: 5});
            },
		});
	});
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
