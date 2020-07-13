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
	
	<#if articleList ?has_content >
		<#list articleList as item>
		<div class="stream-item" id="loop-10">
	    <div class="summary">
	        <a href="/view/${item.id}.html">
	            <div class="title"><h2>${item.title}</h2></div>
	            <!--<div class="excerpt wordbreak hidden-xs">$!{row.summary} </div>-->
	        </a>
	        <!--前端图片显示样式-->
	    </div>
	       
	    <div class="p-rank clearfix" >
	        <div class="users" >
	            <a href="/ta/${item.user.id}">
	                <div class="ava">
	    				<img class="img-circle" src="${item.user.avatar}"/>
	                </div>
	            </a>
	            <div class="info">
	                <strong> ${item.user.nickname}</strong>
	                <time> ${item.createTime?string('yyyy-MM-dd')}</time>
	                <time> 
						<#assign ct = (.now?long-item.createTime?long)/1000>
						<#if ct gte 31104000>${(ct/31104000)?int}年前
							<#t><#elseif ct gte 2592000>${(ct/2592000)?int}个月前
							<#t><#elseif ct gte 86400*2>${(ct/86400)?int}天前
							<#t><#elseif ct gte 86400>昨天
							<#t><#elseif ct gte 3600>${(ct/3600)?int}小时前
							<#t><#elseif ct gte 60>${(ct/60)?int}分钟前
							<#t><#elseif ct gte 0>${ct?int}秒前
						</#if>
						
					</time>
	            </div>
	
	        </div>
	       <div class="counts">
	            <span class="act"><i class="praise_icon"></i>${item.like}</span>
	            <span class="act"><i class="comment_icon"></i>${item.comment}</span>
	        </div>
	
	        <div class="foot-block clearfix">
	            <ul class="tags">
	            <#list item.tags as tname>
				<li>
					<a class="tag tag-sm" href="/tag/ord=${tname}">${tname}</a>	
				</li>
				</#list>
	            </ul>
	        </div>
	
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
