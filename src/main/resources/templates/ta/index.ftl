<!DOCTYPE html>
<html lang="en-US">

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>${user.nickname?default('')}</title>
<meta property="mtons:mblog" content="2.2.1">

<script src="/assets/vendors/pace/pace.min.js"></script>
<link href="/assets/vendors/pace/themes/pace-theme-minimal.css" rel="stylesheet" />

<link rel='stylesheet' media='all' href='/assets/vendors/font-awesome/css/font-awesome.min.css'/>
<link rel="stylesheet" media='all' href="/assets/vendors/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" media='all' href="/assets/vendors/animate/animate.min.css">
<link rel='stylesheet' media='all' href='/assets/css/style.css'/>
<link rel='stylesheet' media='all' href='/assets/css/layout.css'/>
<link rel='stylesheet' media='all' href='/assets/css/plugins.css'/>
<link rel='stylesheet' media='all' href='/assets/css/addons.css'/>
<link rel="stylesheet" href="/assets/vendors/bootstrap_select/bootstrap-select.min.css">

<link rel='stylesheet' media='all' href="/assets/vendors/baguette/baguetteBox.min.css"/>

<script type="text/javascript" src="/assets/js/jquery.min.js"></script>
<script type="text/javascript" src="/assets/js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="/assets/js/utils.js"></script>

<script type="text/javascript" src="/assets/vendors/layer/layer.js"></script>

<script type="text/javascript" src="/assets/js/sea.js"></script>
<script type="text/javascript" src="/assets/js/sea.config.js"></script>
<script type="text/javascript" src="/assets/vendors/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/assets/vendors/bootstrap_select/bootstrap-select.js"></script>
<script type="text/javascript" src="/assets/vendors/bootstrap_select/defaults-zh_CN.min.js"></script>

<!-- Favicons -->
<link rel="apple-touch-icon-precomposed" href="/assets/images/logo.png"/>
<link rel="shortcut icon" href="/assets/images/logo.png"/>

<script type="text/javascript">
    var _base_path = '$!{base}';

    window.app = {
        base: '',
        LOGIN_TOKEN: '$!{profile.id}'
    };
	
	window.UEDITOR_HOME_URL = '/assets/vendors/ueditor/';
</script>
</head>
<body>

<#include "/include/header.ftl">

	<!--.site-main -->
	<div class="wrap">
		<div class="profile">
			<div class="container">
				<div class="avatar animated fadeInDown">
   					 <img class="img-circle" src="${user.avatar?default('S')}"/>
				</div>
				<h1>${user.nickname?default('未设置昵称')}</h1>

				<h2>${user.sign?default('这个人很懒，没有个性签名。')}</h2>
				<a class="btn btn-white" href="javascript:void(0);" data-id="${user.id}" rel="follow">+ 关注</a>
				<a class="btn btn-white" href="javascript:void(0);" onclick="ComplaintUserById(${user.id});">投诉</a>
			</div>
		</div>

		<nav id="profile-navigation" class="profile-navbar">
			<div class="container">
				<div class="content">
					<ul class="nav navbar-nav">
						<li class="active">
							<a href="javascript:void(0);)">TA的文章</a>
						</li>
					</ul>
				</div>
			</div>
		</nav>
		<!-- 举报功能模态框 -->
				<div class="modal fade " id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5>投诉详情</h5>
							<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button> -->
								</div>
								<div class="modal-body">
							         <form class="form-horizontal" role="form" id="editform">
							         	  <input type="hidden" id="userId" name="userId"/>
							         	  <div class="form-group">
									    		<label for="name" class="col-sm-2 control-label">用户昵称:</label>
									    		<div class="col-sm-5">
									    			<input type="text" class="form-control" id="nick_name" name="nick_name" readonly value="${user.nickname}" />
									    		</div>
									      </div>
									      <div class="form-group">
											    <label for="type" class="col-sm-2 control-label">投诉理由:</label>
											    <div class="col-sm-5">
											    	<select name="type" id="type" class="form-control selectpicker">
   														<option data-hidden="true" disable>请选择投诉类型</option>
												    	<option value="0" >政治敏感</option>
												    	<option value="1" >色情暴力</option>
												    	<option value="2" >发布广告、欺诈等</option>
												    	<option value="3" >其他</option>
											         </select>
											    </div>
										  </div>
										  <div class="form-group">
									    		<label for="remarks" class="col-sm-2 control-label">补充说明:</label>
									    		<div class="col-sm-5">
									    			<textarea id="remarks" class="form-control" name="remarks" placeholder="其他信息补充"  style="height: 100px;width: 300px;resize: none;"></textarea>
									    		</div>
									      </div>
										  <div class="modal-footer">
											<button type="button" class="btn btn-default" data-dismiss="modal">
												关闭
											</button>
											<button type="button" class="btn btn-danger" id="modal-btn">
												举报
											</button>
										  </div>
						             </form>
								</div>
							</div><!-- /.modal-content -->
						</div><!-- /.modal-dialog -->
					</div><!-- /.modal -->
		<div class="container">
			<div class="row">
				<div class="main clearfix">
					<div class="col-xs-12 col-md-12">
						<div class="shadow-box mt20">
							<div class="stream-list">
							<#list pageInfo.content as item>
								<div class="stream-item" id="loop-${item.id}">
									<div class="blog-rank">
										<div class="votes #if(1 > 0) plus #end">
											${item.like}<small>喜欢</small>
										</div>
										<div class="views hidden-xs">
											${item.hits}<small>浏览</small>
										</div>
									</div>
									<div class="summary">
										<h2 class="title"><a href="/view/${item.id}.html">${item.title}</a></h2>

										<div class="excerpt wordbreak hidden-xs">
											<#if item.content?length lt 100>
												${item.content}
												<#else>
												${item.content[0..100]}...
											</#if>
										</div>

										<!--前端图片显示样式-->

										<div class="foot-block clearfix">
											<div class="author">
												<time>${item.createTime}</time>
											</div>
											<ul class="tags">
												<#list item.tags as tname>
												<li>
													<a class="tag tag-sm" href="/tag/ord=${tname}">${tname}</a>	
												</li>
												</#list>
											</ul>
											<div class="pull-right hidden-xs">
												<span class="act">评论 (<i>${item.comment}</i>)</span>
												<span class="act">喜欢 (<i>${item.like}</i>)</span>
											</div>
										</div>
									</div>
								
								</div>
							</#list>
							</div>
						</div>
				<div class="text-center clr">
				<script type="text/javascript" src="/js/jquery.pagination.js"></script>
				<link rel='stylesheet' media='all' href='/css/pagination.css'/>
				<script>
					$(function(){
						
						$('#pageBox').pagination({
							 current:${pageInfo.number+1},
				    		 pageCount: ${pageInfo.totalPages},
				    		 jump: true,
				    		 callback: function (api) {
				        	 	window.location.href='?page='+api.getCurrent();
				    		}
						});
						
					});
				</script>
				<ul class="m-style M-box" id="pageBox">
				<!--
				    <li class="active"><a href="javascript:void(0);"><span>1</span></a></li>
				-->
				</ul>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
	</div>
	</div>
<footer >
	<div class="footer-nav">
		<div class="container">
			<ul class="about-ul list-inline clearfix">
				<li><a href="/about">关于我们</a></li>
				<li><a href="/joinus">联系我们</a></li>
				<li><a href="/faqs">常见问题</a></li>
			</ul>
		</div>
	</div>
	<div class="container mode-link">
        <h3 class="t-h3">友情链接</h3>
        <ul class="list-inline">
            <li><a href="JavaScript:void(0)" target="_blank" title="Tons社区">Tons社区</a></li>
        </ul>
    </div>

</footer>

<a href="#" class="site-scroll-top"></a>

<script type="text/javascript">
    seajs.use('main', function (main) {
        main.init();
    });
    function ComplaintUserById(id){
    	//console.log(id);
    	$("#myModal").modal("show");
    	//修改请求
    	$('#modal-btn').unbind("click").click(function(){
    		var formdata = new FormData();
    		formdata.append("userId",id);
    		formdata.append("type",$('select[name=type]').selectpicker('val'));
        	formdata.append("remarks",$("#remarks").val());
    		//console.log(formdata);
    		$.ajax({ 
        		type: "POST",
        		url: '/ta/complaint',
        		data: formdata,
        		dataType: 'JSON',
                processData: false,// 不加会报错	
                contentType: false,// 不加会报错
        		success: function(data){
        			$('#myModal').modal('hide');
        			if(data.code ==100){
        				layer.msg(data.msg, function(){});
        			}
        			if(data.code ==101){
        				layer.msg(data.msg, {icon: 6});
        				$("#remarks").val("");
        				$('select[name=type]').selectpicker('val',['noneSelectedText'])//回到初始状态
        				$("select[name=type]").selectpicker('refresh');//刷新
        			}
        		},
        		error: function(XMLHttpRequest,textStatus,errorThrown){
                	layer.close(layer1);
                	layer.msg('服务器通讯错误', {icon: 5});
                },
    		});
    	});
    }
</script>
</body>
</html>