<!-- Header BEGIN -->
<div class="top-wrap">
    <nav class="navbar navbar-inverse" role="navigation">
<!--[if lt IE 9]>
<div class="alert alert-danger alert-dismissible fade in" role="alert" style="margin-bottom:0">
      <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
      <strong>您正在使用低版本浏览器，</strong> 在本页面的显示效果可能有差异。
        建议您升级到
        <a href="http://www.google.cn/intl/zh-CN/chrome/" target="_blank">Chrome</a>
        或以下浏览器：
        <a href="www.mozilla.org/en-US/firefox/‎" target="_blank">Firefox</a> /
        <a href="http://www.apple.com.cn/safari/" target="_blank">Safari</a> /
        <a href="http://www.opera.com/" target="_blank">Opera</a> /
        <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie" target="_blank">Internet Explorer 9+</a>
</div>
<![endif]-->
    	<div class="container">
    		<div class="navbar-header">
    			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
    				<span class="sr-only"></span>
    				<span class="icon-bar"></span>
    				<span class="icon-bar"></span>
    				<span class="icon-bar"></span>
    			</button>
    			<a class="navbar-brand logo" href="/index"><img src="/assets/images/logo/logo_full.png"></a>
    		</div>
    		<div id="navbar" class="navbar-collapse collapse">
    			<ul class="nav navbar-nav">
                    
                    <#function active code >
                    	<#if Request.servletPath?contains('/forum/'+code) >
                    		<#return 'class="active"'>
                    	<#else>
                    		<#return ''>
                    	</#if>
                    </#function>
                    
    				<#list Application.FORUM_LIST as item>
						<li ${active(item.code)}>
							<a href="/forum/${item.code}" nav="${item.name}">${item.name}</a>
						</li>
    				</#list>
					
    			</ul>
    			<div id="_search_box" class="search-box navbar-left hidden-xs hidden-sm">
    				<form class="navbar-form" method="get" action="/search">
    					<input type="text" class="form-control" name="q" placeholder="搜索文章或作者..">
						<button class="search-btn" type="submit"><i class="fa fa-search"></i></button>
    				</form>
    			</div>
    			
    			<ul class="nav navbar-nav navbar-right sign">
					<#if Session.loginInfo?exists>
					<li class="dropdown">
                        <a href="/article/create" class="publish"><i class="fa fa-magic"></i> 写文章</a>
					</li>
    				<li class="dropdown">
    					<a href="#" class="ava dropdown-toggle" data-toggle="dropdown">
    						<img class="img-circle" src='${Session.loginInfo.avatar}'>
    					</a>
    					<ul class="dropdown-menu" role="menu">
    		                <li>
    		                	<a href="/account/profile" class="ava">
   									<img class="img-circle" src='${Session.loginInfo.avatar}'/>
    		                		<span>${Session.loginInfo.nickname}</span>
    		                	</a>
    		               	</li>
    		                <li class="divider"></li>
    		                <#if Session.loginInfo?exists>
	                    		<li data="home">
	    							<a href="/home/index.html" nav="home">我的主页</a>
	    						</li>
	    						<li class="divider"></li>
                   			</#if>
							<#if Session.loginInfo.power.role == 2>
								<li><a href="/admin/home">后台管理</a></li>
								<li class="divider"></li>
							</#if>
    		                	<li><a href="/public/logout">退出</a></li>
    		              </ul>
    				</li>
    				<#else>
    					<ul class="nav navbar-nav navbar-right sign">
    						<li><a href="/login.html" class="signin">登录</a></li>
    						<li><a href="/register.html" class="signup">注册</a></li>
    					</ul>
    				</#if>
    			</ul>
    		</div>
    	</div>
    </nav>
</div>
<script type="text/javascript">

	seajs.use('header', function (a) {
        //a.init();
    });
</script>