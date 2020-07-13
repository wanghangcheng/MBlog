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
<!-- 在线客服 -->
<link rel="stylesheet" media='all' href="/assets/Online/css/zzsc.css" />
<script type="text/javascript" src="/assets/Online/js/main.js"></script>

<script type="text/javascript" src="/assets/vendors/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript" src="/assets/vendors/layer/layer.js"></script>

<script type="text/javascript" src="/assets/js/sea.js"></script>
<script type="text/javascript" src="/assets/js/sea.config.js"></script>

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

<!-- Header END -->
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
            <li><a class="active" href="$base/home">动态</a></li>
        </ul>
    </div>
    <!-- tab panes -->
    <div class="stream-list p-stream">
		<#list pageInfo.content as item>
            <div class="stream-item" id="loop-${item.id}">
                <div class="blog-rank">
                    <div class="user" title=${item.title}">
                        <a href="${item.url}">
    			<img class="img-circle" src="${item.avatar}"/>
                        </a>
                    </div>
                </div>
                <div class="summary">
                    <h2 class="title" href = "">${item.title}</h2>
                    <div class="excerpt wordbreak">
                         	${item.content}
                    </div>
                    </h2>
                    <div class="foot-block clearfix">
                        <div class="author">
                            <time>${item.created?string('yyyy-MM-dd HH:mm:ss')}</time>
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
<!-- 在线客服 -->
<#include "/include/right_online.ftl">
<a href="#" class="site-scroll-top"></a>

<script type="text/javascript">
    seajs.use('main', function (main) {
        main.init();
    });
</script></body>
</html>
