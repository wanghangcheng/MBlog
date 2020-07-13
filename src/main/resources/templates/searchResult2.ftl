<!DOCTYPE html>
<html lang="en" class="app">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>博文</title>
    <meta name="keywords" content="MGblog, MGblog,博客,社区,摄影,旅游,艺术,娱乐">
    <meta name="description" content="MGblog, 轻松分享你的兴趣. 便捷的文字、图片发布,扁平化的响应式设计,美观、大气,是您记录生活的最佳选择">
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
                    <div class="col-xs-12 col-md-9 side-left">

 					<#function active ord >
                    	<#if (RequestParameters.ord?default('')==ord)>
                    		<#return 'class="active"'>
                    	<#else>
                    		<#return ''>
                    	</#if>
                    </#function>

<div class="shadow-box">
	<!-- tab -->
	<div class="filter">
		<ul class="">
			<li>
				<a href="JavaScript:void(0)">
					<i class="fa fa-newspaper-o"></i>当前搜索：${RequestParameters.q}
				</a>
			</li>
			<li>
				<a href="/search?q=${RequestParameters.q}">
					<i class="fa fa-newspaper-o"></i>文章
				</a>
			</li>
			<li>
				<a href="/search/user?q=${RequestParameters.q}">
					<i class="fa fa-newspaper-o"></i>用户
				</a>
			</li>
		</ul>
	</div>
	<!-- tab end -->
	<!-- tab panes -->
	<div class="stream-list p-stream">
	
	<#if userList ?has_content >
		<#list userList as item>
		<div class="stream-item" style="display: flex;justify-content: space-around;width:725px;">
			<div class="users" style="height:70px;width:70px;">
				<a href="/ta/${item.id}">
	                <div class="ava">
	    				<img class="img-circle" src="${item.avatar}"/>
	                </div>
		        </a>
			</div>
			<div class="info" style="width:500px;margin-left:10px" >
				<h3><strong style="font-weight: 500;">${item.nickname?default('未设置昵称')}</strong></h3>
				<h5>${item.sign?default('这个人很懒，没有个性签名。')}</h5>
			</div>
			<div style="width:200px">
				<h5>发表文章数：${item.articleCount}</h5>
				<h5>发表评论数：${item.commtentCount}</h5>
			</div>
		</div>
		
		</#list>
	<#else>
		<div class="stream-item" id="loop-10">
			无相关记录
		</div>
	</#if>
		
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
        	 	window.location.href='?q=${RequestParameters.q?default('')}&page='+api.getCurrent();
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
                    <!-- right sidebar-->
                    <div class="col-xs-12 col-md-3 side-right hidden-xs hidden-sm">
<div class="widget-box shadow-box">
	<div class="title">
		<h3><i class="fa fa-users "></i> 热门用户</h3>
	</div>
	<ul id="hotuser" class="hotusers">

        <img src="/assets/images/spinner.gif">

	</ul>
</div>

<div class="widget-box shadow-box">
	<div class="title">
		<h3>
		<i class="fa fa-area-chart"></i> 热门文章
			<a href="?ord=hottest" class="rrh-refresh"><!--<i class="fa fa-refresh"></i>-->查看更多</a>
		</h3>
		
	</div>
	<ul class="box-list" id="hots">
		<li class="text-center"><img src="/assets/images/spinner.gif"></li>
	</ul>
</div>

<div class="widget-box shadow-box">
	<div class="title">
		<h3><i class="fa fa-bars"></i> 最新发布
		<a href="?ord=newest" class="rrh-refresh">查看更多</a></h3>
	</div>
	<ul class="box-list" id="latests">
        <li class="text-center"><img src="/assets/images/spinner.gif"></li>
	</ul>
</div>


<script type="text/javascript">
var hot_li_template = '<li><div class="li-out"><span class="last"><i>{0}</i></span><span class="name"><a  href="{1}">{2}</a></span><span class="nums">{3}</span></div></li>'
var latest_li_template = '<li><div class="li-out"><span class="name"><a  href="{1}">{2}</a></span><span class="nums">{3}</span></div></li>'

var hotUser_li_template = '<li class=""><a  href="{1}"><img src="{0}" class="imguser"/></a></li>'

seajs.use('sidebox', function (sidebox) {
	sidebox.init({
        latestUrl : '/api/latests',
    	hotUrl : '/api/hots',
		hotTagUrl : '/api/hot_tags',
		hotUserUrl:'/api/hotusers',

    	maxResults :6,
        // callback
        onLoadHot : function (i, data) {
        	var url = '/view/' + data.id+'.html';
      		var item = jQuery.format(hot_li_template, i + 1, url, data.title, data.hits);
      		return item;
        },
        onLoadLatest : function (i, data) {
        	var url = '/view/' + data.id + '.html';
      		var item = jQuery.format(latest_li_template, i + 1, url, data.title, data.hits);
      		return item;
        },
        onLoadHotUser : function (i, data) {
        	var url = '/ta/' + data.id;	
      		var item = jQuery.format(hotUser_li_template,data.avatar,url,data.name, data.fans);
      		return item;
        }
	});
});
</script>                    </div>
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
