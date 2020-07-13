
<!DOCTYPE html>
<html lang="en" class="app">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>修改用户邮箱</title>
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
<!--
<script type="text/javascript" src="/assets/js/jquery.min.js"></script>
<script type="text/javascript" src="/assets/js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="/assets/js/utils.js"></script>

<script type="text/javascript" src="/assets/vendors/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript" src="/assets/vendors/layer/layer.js"></script>
<script type="text/javascript" src="/assets/vendors/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="/assets/vendors/validate/messages_zh.min.js"></script>
-->

<script type="text/javascript" src="/assets/js/sea.js"></script>
<script type="text/javascript" src="/assets/js/sea.config.js"></script>

<!-- Favicons -->
<link rel="apple-touch-icon-precomposed" href="http://mtons.com/dist/images/logo.png"/>
<link rel="shortcut icon" href="http://mtons.com/dist/images/logo.png"/>

<style>
	.popover-content{
		width:140px;
	}
</style>
<script type="text/javascript">
    var _base_path = '$!{base}';

    window.app = {
        base: '',
        LOGIN_TOKEN: '$!{profile.id}'
    };
	
	window.UEDITOR_HOME_URL = '/assets/vendors/ueditor/';
</script>
</head>

<#include "/include/header.ftl">

<!-- Header END -->
    <!--.site-main -->
    <div class="wrap" id="wrap">
        <div class="container">
            <div class="row">
                <div class="main clearfix">
                    <div class="col-xs-12 col-md-12">

<div class="panel panel-default stacked">
	<div class="panel-heading">
		<ul class="nav nav-pills account-tab">
			<li><a href="profile">基本信息</a></li>
			<li><a href="avatar">修改头像</a></li>
			<li><a href="password">修改密码</a></li>
		</ul>
	</div>
	<div class="panel-body">
		<div id="message">

		</div>
		<div class="tab-pane active" id="profile">
			<form id="f_email" action="" method="get" class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-lg-3" style="width:35%;">邮箱</label>
					<div class="col-lg-4">
						<input id="email" type="text" class="form-control" name="email" value="${Session.loginInfo.email}">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-3" style="width:35%;">验证码</label>
					<div class="col-lg-4">
						<div style="display:inline-block; padding-right:10px;width:78%;float:left;">
        					<input maxlength="64" class="form-control border" id="code" name="code" value="" placeholder="邮箱验证码" type="text" data-required data-describedby="message">
        				</div>
        				<div style="display:inline-block;width:22%;float:right;">
        					<input id="e_btn" name="" type="button" class="btn btn-success border" value="发送">
        				</div>
        					<input id="n_code" value="3333" type="hidden" style ="display:block;">
					</div>
				</div>
				<div class="form-group">
					<div class="text-center">
						<button type="submit" class="btn btn-primary">发送</button>
					</div>
				</div><!-- /form-actions -->
			</form>
		</div>
	</div><!-- /panel-content -->
</div><!-- /panel -->

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
             <li><a ></a></li>
        </ul>
        <ul class="list-inline">
             <li><a href="JavaScript:void(0)" target="_blank" title="Tons社区">Tons社区</a></li>
        </ul>
        <ul class="list-inline">
        <li><a ></a></li>
        </ul>
    </div>

</footer>

<a href="#" class="site-scroll-top"></a>

<script type="text/javascript">

	//seajs模块化解决方案
    seajs.use('account.email', function (a) {
        a.init();
    });
</script>
</body>
</html>
