
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
	            <div class="form-group col-md-3">
			         	<input type="text" class=" form-control" id="nickname" name="nickname" placeholder="请输入要查询的作者" autocomplete="off"/>
                </div>
                 <div class="form-group col-md-3">
			         	<input type="text" class=" form-control" id="artTitle" name="artTitle" placeholder="请输入要查询的文章标题" autocomplete="off"/>
                </div>
	            <div class="form-group col-md-3">
			            <!--指定 date标记-->
			            <div class='input-group date' id='beginDateTime'>
			                <input type='text' class="form-control" placeholder="请选择开始日期" autocomplete="off" name='beginDateTime' disabled="true"/>
			                <span class="input-group-addon">
			                    <span class="glyphicon glyphicon-calendar"></span>
			                </span>
			            </div>
                </div> 
	            <div class="form-group col-md-3">
			            <!--指定 date标记-->
			            <div class='input-group date' id='endDateTime'>
			                <input type='text' class="form-control" placeholder="请选择结束日期 " autocomplete="off" name='endDateTime' disabled="true"/>
			                <span class="input-group-addon">
			                    <span class="glyphicon glyphicon-calendar"></span>
			                </span>
			            </div>
                </div>
                <div class="form-group col-md-3">
                   <button type="button" class="btn btn-primary ml-2" onclick="reloadTable()">查询</button>
                   <button type="button" class="btn btn-primary ml-2" onclick="reloadAll()">重置</button>
                </div>                              
	          </div>
	        </form>
	       <!--  <div  class="alert alert-danger text-center col-8  offset-2 py-5" role="alert">
			   没有符合查询匹配条件的信息被找到!
			</div> -->
		</div>
	</div>
<!-- 模态框（Modal） -->
	<div class="modal fade " id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5>修改文章状态</h5>
			<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button> -->
				</div>
				<div class="modal-body">
			         <form class="form-horizontal" role="form" id="editform">
			         	  <input type="hidden" id="artId" name="artId"/>
					      <div class="form-group">
							    <label for="state" class="col-sm-2 control-label">置顶状态:</label>
							    <div class="col-sm-5">
							    	<select name="edittop" id="edittop" class="form-control selectpicker " title="请选择操作">
								    	<option value="0" >未置顶</option>
								    	<option value="1" >已置顶</option>
							         </select>
							    </div>
						  </div>
						  <div class="form-group">
							    <label for="role" class="col-sm-2 control-label">加精状态:</label>
							    <div class="col-sm-5">
							    	<select name="editcream" id="editcream" class="form-control selectpicker" title="请选择操作">
								    	<option value="0" >未加精</option>
								    	<option value="1" >已加精</option>
							         </select>
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
        <table class="table table-hover table-responsive" id="article_table"></table>
    </div>
</div>

<script type='text/javascript' src='/assets/vendors/Chart.js/dist/Chart.min.js'></script>
<script type='text/javascript'>
$(function() {
	$('#article_table').bootstrapTable('destroy');
	$('#article_table').bootstrapTable({
	    url: "/admin/article/list",
	    contentType:"application/x-www-form-urlencoded; charset=UTF-8",
	    method:'post',
	    dataType:'json',
	    sidePagination : "server",//服务器分页
	    //toolbar : '#toolbar', //工具按钮用哪个容器
	    sortName : 'create_time',//初始化的时候排序的字段
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
	        field: "title",
	        title: "文章标题",
	        align: "center",
	        width : "300",
	        valign: "middle"
	    },{
	        field: "user.nickname",
	        title: "文章作者",
	        align: "center",
	        valign: "middle"
	    },{
	    	 field: "forum.name",
	         title: "文章板块",
	         align: "center",
	         valign: "middle"
	    },{
	        field: "hits",
	        title: "文章热度",
	        align: "center",
	        valign: "middle",
	        sortable:true,
	    },{
	        field: "createTime",
	        title: "发表时间",
	        align: "center",
	        valign: "middle",
	        sortable:true,
	    }, {
	    	field: "cream",
	        title: "是否加精",
	        align: "center",
	        valign: "middle",
	        sortable:true,
	        formatter:CreamFormatter,
	    }, {
	    	field: "top",
	        title: "是否置顶",
	        align: "center",
	        valign: "middle",
	        sortable:true,
	        formatter:TopFormatter,
	    }, {
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
        }
	});
	function actionFormatter(value, row, index) {
		//console.log(index);
	    var id = value;
	    var result = "";
	    result += "<a href='javascript:void(0)' class='btn btn-xs green' onclick=\'ShowViewById("+id+")'\ title='查看'><span class='glyphicon glyphicon-search'></span></a>";
	    result += "<a href='javascript:void(0)' id="+index+" class='btn btn-xs blue' onclick=\"EditViewById(this,"+ id +")\" title='编辑'  data-toggle='modal' data-target='#myModal'><span class='glyphicon glyphicon-pencil'></span></a>";
	    result += "<a href='javascript:void(0)' id="+index+" class='btn btn-xs red' onclick=\'DeleteById(this,"+id+")'\ title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
	    return result;
	}
	//置顶格式
	function TopFormatter(value,row,index){
		let result= null;
		switch(value){
			case 0:
				result="未置顶"	;
				break;
			case 1:
				result="已置顶"	;
				break;
		}
		return result;
	}
	//加精格式
	function CreamFormatter(value,row,index){
		let result= null;
		switch(value){
			case 0:
				result="未加精"	;
				break;
			case 1:
				result="已加精"	;
				break;
		}
		return result;
	}
	function queryParam(params){
		var nickname = $("input[name='nickname']").val();
		var artTitle = $("input[name='artTitle']").val();
        var beginDateTime = $("input[name='beginDateTime']").val();
        var endDateTime = $("input[name='endDateTime']").val();
		var param = {
				limit : this.limit, // 每页显示数量
				offset : this.offset, // SQL语句起始索引
		        pageNumber : this.pageNumber,
		        pageSize : this.pageSize,
		        sort : params.sort,//排序
		        order : params.order,//排序字段
		};
		if(nickname!=null&&nickname!=''){
			param['nickname'] = nickname;
		}
		if(artTitle!=null&&artTitle!=''){
			param['artTitle'] = artTitle;
		}
		if(beginDateTime!=null&&beginDateTime!=''){
			param['beginDateTime'] = beginDateTime;
		}
		if(endDateTime!=null&&endDateTime!=''){
			param['endDateTime'] = endDateTime;
		}
		//console.log(param);
		return param;
	}
	/* function keyup_submit(e){
	     var evt = window.event || e;
	      if (evt.keyCode == 13){
	          alert(111)
	            //回车事件
	          }
	} */
	//日历控件
	function DatePicker(beginSelector, endSelector) {
    $(beginSelector).datetimepicker(
        {
            language: "zh-CN",
            autoclose: 1,
            startView: 2,
            minView: 2,
            format: "yyyy-mm-dd",
            clearBtn: true,
            todayBtn: false,
            endDate: new Date()
        }).on('changeDate', function (ev) {
        	if (ev.date) {
                $(endSelector).datetimepicker('setStartDate', GMTToStr(ev.date))
            } else {
                $(endSelector).datetimepicker('setStartDate', null);
            } 
        });

    $(endSelector).datetimepicker(
        {
            language: "zh-CN",
            autoclose: 1,
            startView: 2,
            minView: 2,
            format: "yyyy-mm-dd",
            clearBtn: true,
            todayBtn: false,
            endDate: new Date()
        }).on('changeDate', function (ev) {
            if (ev.date) {
                $(beginSelector).datetimepicker('setEndDate', ev.date)
            } else {
                $(beginSelector).datetimepicker('setEndDate', new Date());
            }

        });
	}
	//处理日历控件问题，以及第二个控件的开始时间不大于第一个控件时间
	DatePicker("#beginDateTime", "#endDateTime");
	function GMTToStr(time){
	    let date = new Date(time);
	    let Str=(date.getFullYear()-1) + '-' +
	    (date.getMonth() + 1) + '-' + 
	    date.getDate();
	    return Str;
	}
});
//根据字符获取value值
function findValByStr(str){
	let result=null
	if(str == '未置顶'||str == '未加精'){
		result = 0;
		return result;
	}
	if(str == '已置顶'||str == '已加精'){
		result = 1;
		return result;
	}
}
//查询刷新
function reloadTable() {
	//console.log(11);
	$("#article_table").bootstrapTable('refresh');
}
//查看操作
function ShowViewById(id){
	//console.log(id);
	window.location.href="/view/"+id+".html";
}
//修改操作
function EditViewById(obj,id){
	let index = $(obj).attr("id");
	let th_td = $("tr[data-index = '"+index+"']").find('td')
	let cream = findValByStr(th_td.eq(6).text());
	let top = findValByStr(th_td.eq(7).text());
	$('#myModal').on('show.bs.modal', function(){
	    var $this = $(this);
	    var $modal_dialog = $this.find('.modal-dialog');
	    $this.css('display', 'block');
	    $modal_dialog.css({'margin-top': Math.max(0, ($(window).height() - $modal_dialog.height()) / 2) });
	    
	});
	$("#myModal").modal({backdrop: false, keyboard: false});
	$("#artId").val(id)
	$('select[name=edittop]').selectpicker('val',top);
	$('select[name=editcream]').selectpicker('val',cream);

	//修改请求
	$('#modal-btn').unbind("click").click(function(){
		var formdata = new FormData();
		formdata.append("artId",id);
		formdata.append("top",$('select[name=edittop]').selectpicker('val'));
		formdata.append("cream",$('select[name=editcream]').selectpicker('val'));
		//console.log(formdata);
		$.ajax({ 
    		type: "POST",
    		url: '/admin/article/edit',
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
    			if(data.code ==103){
    				layer.msg(data.msg, {icon: 6});
    			}
    			$("#article_table").bootstrapTable('refresh');
    		},
    		error: function(XMLHttpRequest,textStatus,errorThrown){
            	layer.close(layer1);
            	layer.msg('服务器通讯错误', {icon: 5});
            },
		});
	});
}
//删除操作
function DeleteById(obj,id){
	let index = $(obj).attr("id");
	//console.log($("tr[data-index = '"+index+"']"));
	layer.confirm('确定删除这篇文章吗?', {
        btn: ['确定','取消'], //按钮
        shade: false //不显示遮罩
    }, function(){
		 jQuery.getJSON('/admin/article/delete' ,{'id':id }, function (ret) {
			 console.log(ret);
			if(ret.code ==101){
				layer.msg(ret.msg, function(){});
			}
			/* if (ret.code ==0) {
				layer.msg(ret.msg, function(){});
				windows.location.href='/login.html';
			} */
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
//重置按钮，重置所有查询条件
function reloadAll() {
	$("input[name=nickname]").val("");
	$("input[name=artTitle]").val("");
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
