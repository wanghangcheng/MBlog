<!DOCTYPE html>
<html lang="en" class="app">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>我的主页 - MGblog</title>
    <meta name="keywords" content="MGblog,MGblog,博客,社区,摄影,旅游,艺术,娱乐"/>
    <meta name="description" content="MGblog, 轻松分享你的兴趣. 便捷的文字、图片发布,扁平化的响应式设计,美观、大气,是您记录生活的最佳选择"/>
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

<script type="text/javascript" src="/assets/vendors/layer/layer.js"></script>

<script type="text/javascript" src="/assets/js/sea.js"></script>
<script type="text/javascript" src="/assets/js/sea.config.js"></script>
<script type="text/javascript" src="/assets/vendors/bootstrap/js/bootstrap.min.js"></script>
<!-- Favicons -->
<link rel="apple-touch-icon-precomposed" href="http://mtons.com/dist/images/logo.png"/>
<link rel="shortcut icon" href="http://mtons.com/dist/images/logo.png"/>

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
    <div class="wrap" id="wrap">
        <div class="container">
            <div class="row">
                <div class="main clearfix">
<!-- left -->
<#include "/include/home-left.ftl">
<!-- end-->
 <div class="col-xs-12 col-md-9 side-right">

<div class="shadow-box">
	<div class="filter">
		<ul class="">
			<li><a class="active" href="/home/favors">我的喜欢</a></li>
		</ul>
	</div>
	<!-- tab panes -->
	<div class="stream-list">
	<#list favorList as item>
		<div class="stream-item" id="loop-${item.article.id}">
				<div class="p-rank clearfix">
					<div class="users">
						<a href="/ta/${item.user.id}.html">
							<div class="ava">
    				<img class="img-circle" src="${item.user.avatar}"/>
							</div>
							<div class="info">
								<strong>${item.user.nickname}</strong>
								<time>${item.created?string('yyyy-MM-dd HH:mm:ss')}</time>
							</div>
						</a>
					</div>
					<div class="counts">
						<span class="act"><i class="praise_icon"></i>${item.article.like}</span>
						<span class="act"><i class="comment_icon"></i>${item.article.comment}</span>
					</div>
				</div>
				<div class="summary">
					<a href="/view/${item.article.id}.html">
						<div class="title"><h2>${item.article.title}</h2></div>
						<div class="excerpt wordbreak hidden-xs">
							<#if item.article.content?length lt 50>
								${item.article.content}
							<#else>
								${item.article.content[0..50]}...
							</#if>
						</div>
					</a>
					<!--前端图片显示样式-->

					<div class="foot-block clearfix">
						<ul class="tags">
						</ul>
                        <div class="pull-right hidden-xs">
                            <a class="act" href="javascript:void(0);" data-evt="unfavor" data-id="${item.article.id}">取消喜欢</a>
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

<script type="text/javascript">
$(function() {
	$('a[data-evt=unfavor]').click(function () {
		var id = $(this).attr('data-id');

		layer.confirm('确定取消喜欢吗?', {
            btn: ['确定','取消'], //按钮
            shade: false //不显示遮罩
        }, function(){
			jQuery.getJSON('/account/unfavor', {'id': id}, function (ret) {
				
				if (ret.code ==0) {
					layer.msg(ret.msg, function(){});
					windows.location.href='/login.html';
				}
				if (ret.code ==1) {
					layer.msg(ret.msg, {icon: 1});
					$('#loop-' + id).fadeOut(1000,function(){
						$('#loop-' + id).remove();
					});
				}	
			});

        }, function(){

        });
	});
})
</script>
                    </div>
                </div>
            </div>
        </div>
    </div>
<footer>
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
             <li><a href="JavaScript:void(0)" target="_blank" title="Tons社区">Tons社区</a></li>
        </ul>
    </div>

</footer>

<a href="#" class="site-scroll-top"></a>

<script type="text/javascript">
    seajs.use('main', function (main) {
        main.init();
    });
</script></body>
</html>
