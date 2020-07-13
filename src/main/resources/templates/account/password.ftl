<!DOCTYPE html>
<html lang="en" class="app">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>修改用户信息</title>
    <meta name="keywords" content="MBlog, MBlog,博客,社区,摄影,旅游,艺术,娱乐">
    <meta name="description" content="MBlog, 轻松分享你的兴趣. 便捷的文字、图片发布,扁平化的响应式设计,美观、大气,是您记录生活的最佳选择">

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

<link rel='stylesheet' media='all' href="/assets/vendors/baguette/baguetteBox.min.css"/>

<script type="text/javascript" src="/assets/js/jquery.min.js"></script>
<script type="text/javascript" src="/assets/js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="/assets/js/utils.js"></script>

<script type="text/javascript" src="/assets/vendors/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript" src="/assets/vendors/layer/layer.js"></script>

<script type="text/javascript" src="/assets/js/sea.js"></script>
<script type="text/javascript" src="/assets/js/sea.config.js"></script>

<!-- Favicons -->
<link rel="apple-touch-icon-precomposed" href="http://mtons.com/dist/images/logo.png"/>
<link rel="shortcut icon" href="http://mtons.com/dist/images/logo.png"/>

<script type="text/javascript" src="/assets/vendors/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="/assets/vendors/validate/messages_zh.min.js"></script>

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

<!-- Login dialog BEGIN -->
<div id="loginalert" style="display: none; top: 0px;" class="fade in">
    <div class="pd20 loginpd">
        <h3><i class="closealert fr" data-dismiss="modal" aria-label="Close"></i><div class="clear"></div></h3>
        <div class="loginwrap">
            <div class="loginh">
                <div class="fl">会员登录</div>
                <div class="fr">还没有账号<a id="sigup_now" href="/reg">立即注册</a></div>
            </div>
            <h3><span class="login_warning" id="login_warning" style="display: none;">用户名或密码错误</span><div class="clear"></div></h3>
            <form action="" method="post" id="login_form">
                <div class="logininput">
                    <input type="text" id="alt_un" name="username" class="loginusername" value="" placeholder="用户名">
                    <input type="password" id="alt_pw" name="password" class="loginuserpasswordp" placeholder="密码">
                </div>
                <div class="loginbtn">
                    <div class="loginsubmit fl"><input type="button" value="登录" id="alt_login" class="btn"></div>
                    <!--
                    <div class="fr" style="margin:26px 0 0 0;"><a href="##">忘记密码?</a></div>
                    -->
                    <div class="clear"></div>
                </div>
            </form>
        </div>
    </div>
    <div class="thirdlogin">
        <div class="pd50">
            <h4>用第三方帐号直接登录</h4>
            <ul>
                <li id="sinal"><a href="/oauth/callback/call_weibo">微博帐号注册</a></li>
                <li id="qql" style="margin-right: 0px;"><a href="/oauth/callback/call_qq">QQ帐号注册</a></li>
            </ul>
            <div class="clear"></div>
        </div>
    </div>
</div>
<!-- Login dialog END -->

<#include "/include/header.ftl">

<!-- Header END -->
    <!--.site-main -->
    <div class="wrap" id="wrap">
        <div class="container">
            <div class="row">
                <div class="main clearfix">
                    <div class="col-xs-12 col-md-12">

<style>
	.popover-content{
		width:140px;
	}
</style>

<div class="panel panel-default stacked">
	<div class="panel-heading">
		<ul class="nav nav-pills account-tab">
			<li><a href="profile">基本信息</a></li>
			<li><a href="avatar">修改头像</a></li>
			<li class="active"><a href="password">修改密码</a></li>
		</ul>
	</div>
	<div class="panel-body">
		<div id="message">

		</div>
		<div class="tab-pane active" id="passwd">
			<form id="pw" action="" method="post" class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-lg-3" for="password">当前密码</label>
					<div class="col-lg-4">
						<input type="password" class="form-control" name="oldPassword" maxlength="18" placeholder="请输入当前密码" data-required>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-3" for="password">新密码</label>
					<div class="col-lg-4">
						<input type="password" class="form-control" id="password" name="password" placeholder="请输入新密码" maxlength="18" data-required>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-3" for="password2">确认密码</label>
					<div class="col-lg-4">
						<input type="password" class="form-control" name="password2" data-required placeholder="请再输入一遍新密码" maxlength="18" data-conditional="confirm" data-describedby="message" data-description="passwd">
					</div>
				</div>
				<div class="form-group">
					<div class="text-center">
					<button type="submit" class="btn btn-primary">提交</button>
					</div>
				</div><!-- /form-actions -->
			</form>
		</div>
	</div><!-- /panel-content -->
</div><!-- /panel -->

<script type="text/javascript">
$(function () {
	
	$("#pw").validate({
            debug : true,//只验证不提交
            rules :{
              oldPassword: {
                required: true
              },
              password: {
                required: true,
                minlength: 5
              },
              password2:{
              	required: true,
              	minlength: 5,
              	equalTo: "#password"
              },
            },
            messages: {
              oldPassword: {
                required: "请输入原密码",
              },
              password: {
                required: "请输入新密码",
                minlength: "密码长度不能小于 5"
              },
              password2: {
                required: "请确认新密码",
                minlength: "密码长度不能小于 5",
                equalTo:"两次密码输入不一致"
              },
            },
             errorPlacement:function(error,element) {  
            	element.popover('destroy');
                element.popover({
                    content:error[0].innerHTML
                });
                element.click();
                element.closest('div').removeClass('has-success').addClass('has-error');
             },
             success: function(a, b){
             
                $(b).parent().removeClass('has-error').addClass('has-success');
                //$(b).popover('destroy');
             },
             //验证通过后执行这里，发送数据到后端
            submitHandler: function(form){
            	var layer1 = layer.msg('正在修改密码...', {
            		  		icon: 16,
            		 		shade: 0.5,
            		  		time: 0,
            			});
            	
            	$.ajax({ 
            		type: "POST",
            		url: "/account/password/change",
            		data: $("#pw").serialize(),
            		success: function(data){
                    	layer.close(layer1);
                    	if(data.code==300){
    						layer.msg('旧密码不正确，修改失败', function(){});
    					}
    					if(data.code==200){
    						layer.msg('密码修改成功', {icon: 6});
    						$('#pw')[0].reset(); 
    					}
                    },
                    error: function(XMLHttpRequest,textStatus,errorThrown){
                    	layer.close(layer1);
                    	layer.msg('服务器通讯错误', {icon: 5});
                    },
            	});
            	
            }
        });
});
</script>
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
				<li><a href="#">关于我们</a></li><!-- /about -->
				<li><a href="#">联系我们</a></li><!-- /joinus -->
				<li><a href="#">常见问题</a></li><!-- /faqs -->
			</ul>
		</div>
	</div>
	<div class="container mode-link">
        <h3 class="t-h3">友情链接</h3>
       <ul class="list-inline">
             <li><a href="JavaScript:void(0)" target="_blank" title="Tons社区"></a></li>
        </ul>
        <ul class="list-inline">
             <li><a href="JavaScript:void(0)" target="_blank" title="Tons社区">Tons社区</a></li>
        </ul>
        <ul class="list-inline">
             <li><a href="JavaScript:void(0)" target="_blank" title="Tons社区"></a></li>
        </ul>
    </div>

</footer>

<a href="#" class="site-scroll-top"></a>

<script type="text/javascript">
	//seajs模块化解决方案
    seajs.use('main', function (main) {
        main.init();
    });
</script></body>
</html>
