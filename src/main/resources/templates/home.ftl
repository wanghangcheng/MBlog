<!DOCTYPE html>
<html lang="en" class="app">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>MBlog</title>
    <meta name="keywords" content="MGblog, MGblog,博客,社区,摄影,旅游,艺术,娱乐">
    <meta name="description" content="MGblog, 轻松分享你的兴趣. 便捷的文字、图片发布,扁平化的响应式设计,美观、大气,是您记录生活的最佳选择">
<meta property="mtons:mblog" content="2.2.1">

<script src="/assets/vendors/pace/pace.min.js"></script>
<link href="/assets/vendors/pace/themes/pace-theme-minimal.css" rel="stylesheet" />

<link rel='stylesheet' media='all' href='/assets/vendors/font-awesome/css/font-awesome.min.css'/>
<link rel="stylesheet" media='all' href="/assets/vendors/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" media='all' href="/assets/vendors/animate/animate.min.css">
<link rel="stylesheet" media='all' href="/assets/vendors/swiper/swiper.css">
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
<script type="text/javascript" src="/assets/vendors/swiper/swiper.min.js"></script>

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
<style>
	.recommend{
		display: inline;
	    width: 24px;
	    height: 23px;
	    border-radius: 4px;
	    border: 1px solid #ca0c16;
	    font-size: 14px;
	    -webkit-box-sizing: border-box;
	    box-sizing: border-box;
	    font-weight: 600;
	    text-align: center;
	    color: #ca0c16;
	    line-height: 23px;
	    margin-right: 4px;
	}
</style>
</head>
<body>
<!-- 头部 -->
<#include "/include/header.ftl">

    <!--.site-main -->
    <div class="wrap" id="wrap">
        <div class="container">
            <div class="row">
                <div class="main clearfix">
                    <!-- left -->
                    <div class="col-xs-12 col-md-9 side-left">
                    	<div class="swiper-container">
                    		<div class="swiper-wrapper" style="">
	                    		<img class="swiper-slide" src="/images/home/1.jpg"/>
								<img class="swiper-slide" src="/images/home/2.jpg"/>
								<img class="swiper-slide" src="/images/home/3.jpg"/>
								<img class="swiper-slide" src="/images/home/4.jpg"/>
                    		</div>
                    		<div class="swiper-pagination"></div><!--分页器（就是4个点点）-->
                    	</div>
						<div class="widget-box shadow-box" style="margin-top:20px;">
							<div class="title" style="margin-top:20px;margin-left:20px;">
								<h3>
									<i class="fa fa-star"></i> 精品文章
								</h3>
								
							</div>
							<ul class="box-list" id="cream">
								<!-- <li class="text-center"><img src="/assets/images/spinner.gif"></li> -->
								<#list AllList as item>
								<#if item.type=0>
		        					<li><div class="li-out_new"><span class="newname"><a style="text-decoration:none;" href="/view/${item.id}.html"><h2>${item.title}</h2></a></span><span class="over"> ${item.createTime?string('yyyy-MM-dd')}</span><span class="counts">
		            					 	<span class="act"><i class="praise_icon"></i>${item.like}</span>
		           						 	<span class="act"><i class="comment_icon"></i>${item.comment}</span>
		        							</span>
		        						</div>
		        					</li>
		        				<#elseif item.type!=1>
									<li><div class="li-out_new"><span class="newname"><a style="text-decoration:none;" href="{1}"><h2>无相关内容</h2></a></span><span class="over"> </span><span class="counts">
		        							</span>
		        						</div>
		        					</li>
								</#if>
	        					</#list>
							</ul>
						</div>
						<div class="widget-box shadow-box">
							<div class="title" style="margin-top:20px;margin-left:20px;">
								<h3>
									<i class="fa fa-fire"></i> 热门推荐
								</h3>
								
							</div>
							<ul class="box-list" id="recommend">
	        					<#list AllList as item>
								<#if item.type=1>
		        					<li><div class="li-out_new"><span class="newname"><a style="text-decoration:none;" href="/view/${item.id}.html"><h2>${item.title}</h2></a></span><span class="over"> ${item.createTime?string('yyyy-MM-dd')}</span><span class="counts">
		            					 	<span class="act"><i class="praise_icon"></i>${item.like}</span>
		           						 	<span class="act"><i class="comment_icon"></i>${item.comment}</span>
		        							</span>
		        						</div>
		        					</li>
		        				<#elseif item.type!=0>
									<li><div class="li-out_new"><span class="newname"><a style="text-decoration:none;" href="{1}"><h2>无相关内容</h2></a></span><span class="over"> </span><span class="counts">
		        							</span>
		        						</div>
		        					</li>
								</#if>
	        					</#list>
							</ul>
						</div>
						
					</div>
<script>
var mySwiper = new Swiper('.swiper-container', {//初始化Swiper
    autoplay: {//自动切换
       delay: 3000,
       stopOnLastSlide: false,
       disableOnInteraction: false,
     },
    navigation: {//前进后退
       nextEl: '.swiper-button-next',
       prevEl: '.swiper-button-prev',
     },
    pagination: {//分页器
       el: '.swiper-pagination',
       clickable :true,
    },
    loop : true,//循环
})
</script>
<div class="text-center clr">
</div>
<!-- right sidebar-->
<div class="col-xs-12 col-md-3 side-right hidden-xs hidden-sm">
<div class="widget-box shadow-box">
	<div class="title">
		<h3><i class="fa fa-users "></i> 热门用户</h3>
	</div>
	<ul id="hotuser" class="box-list">

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
var hot_li_template = '<li><div class="li-out"><span class="last"><i>{0}</i></span><span class="name"><a style="text-decoration:none;" href="{1}">{2}</a></span><span class="nums"><i class="fa fa-eye"></i>{3}</span></div></li>'
var latest_li_template = '<li><div class="li-out"><span class="name"><a style="text-decoration:none;" href="{1}">{2}</a></span><span class="nums">{3}</span></div></li>'

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
            <li><a href="http://www.mtons.com" target="_blank" title="Mtons社区">Mtons社区</a></li>
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
</script>
</body>
</html>
