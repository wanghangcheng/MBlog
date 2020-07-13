<!DOCTYPE html>
<html lang="en-US">

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>${articleInfo.title}</title>
    <meta name="keywords" content="mtons, ">
    <meta name="description" >

<script src="/assets/vendors/pace/pace.min.js"></script>
<link href="/assets/vendors/pace/themes/pace-theme-minimal.css" rel="stylesheet" />

<link rel='stylesheet' media='all' href='/assets/vendors/font-awesome/css/font-awesome.min.css'/>
<link rel="stylesheet" media='all' href="/assets/vendors/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/assets/vendors/bootstrap_select/bootstrap-select.min.css">
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
<script type="text/javascript" src="/assets/vendors/bootstrap_select/bootstrap-select.js"></script>
<script type="text/javascript" src="/assets/vendors/bootstrap_select/defaults-zh_CN.min.js"></script>
    
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
                    <div class="shadow-box">
                        <h1 class="post-title">${articleInfo.title}</h1>
                        <div class="clearfix post-other">
            <span class="pull-left author">
                <a class="author-name" href="/ta/${articleInfo.user.id}" target="_blank">${articleInfo.user.nickname}</a>
            </span>
                            <time class="pull-left time">${articleInfo.createTime?string('yyyy-MM-dd')}</time>
                            <span class="pull-left time">浏览: ${articleInfo.hits}</span>
                            <ul class="tags">
                            	<#list articleInfo.tags as tname>
								<li>
									<a class="tag tag-sm" href="/tag/ord=${tname}">${tname}</a>	
								</li>
								</#list>
                            </ul>
                            <span class="pull-right action-box">
                            	<a class="btn btn-danger btn-xs" href="javascript:void(0);" onclick="EditViewById(${articleInfo.id});">举报</a>
                            </span>
                        </div>
                        <div class="post-frame">
                            <div class="post-content">
                           	${articleInfo.content}
                            </div>
                            <div class="post-footer">
                                <div class="tip">分享到：</div>
                                <div class="shares">
                                    <!-- Share Button BEGIN -->
                                    <div class="bdsharebuttonbox bdshare-button-24">
                                        <a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
                                        <!-- <a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a> -->
                                        <a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
                                        <a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
                                    </div>
                                    <script>window._bd_share_config = {
                                        "common": {
                                            "bdSnsKey": {"tsina": "3554307689"},
                                            "bdText": "",
                                            "bdMini": "2",
                                            "bdMiniList": false,
                                            "bdPic": "",
                                            "bdStyle": "1",
                                            "bdSize": "24"
                                        }, "share": {}
                                    };
                                    with (document)0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];</script>
                                    <style>
                                        .bdshare-button-24 a, .bdshare-button-24 .bds_more {
                                            background-image: url("/assets/images/btn/icons_0_24.png");
                                            border-radius: 4px;
                                        }

                                        .bdshare-button-style1-24 a, .bdshare-button-style1-24 .bds_more {
                                            padding-left: 24px;
                                        }
                                    </style>
                                    <!-- Share Button END -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- post/end -->

                    <div id="chat" class="chats shadow-box">
                        <div class="chat_other">
                            <h4>全部评论: <i id="chat_count"></i> 条</h4>
                        </div>
                        <ul id="chat_container" class="its">
    
						</ul>
                        <div id="pager" class="text-center" style="height: 30px;"></div>
                        <div class="cbox-wrap">
                            <div class="cbox-title">我有话说: <span id="chat_reply" style="display:none;">@<i id="chat_to"></i></span>
                            </div>
                            <div class="cbox-post">
                                <div class="cbox-input">
                                    <textarea id="chat_text" rows="3" placeholder="请输入评论内容"></textarea>
                                    <input type="hidden" value="0" name="chat_pid" id="chat_pid">
                                </div>
                                <div class="cbox-ats clearfix">
                                    <div class="ats-func">
                                        <ul class="func-list">
                                            <li class="list-b">
                                                <a href="javascript:void(0);" class="join" id="c-btn"><i class="fa fa-smile-o fa-2"></i></a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="ats-issue">
                                        <button id="btn-chat" class="btn btn-success btn-sm bt">发送</button>
                                    </div>
                                </div>
                            </div>
                            <div class="phiz-box" id="c-phiz" style="display:none">
                                <div class="phiz-list" view="c-phizs"></div>
                            </div>
                        </div>
                    </div>
                
                </div>
                <!-- 举报功能模态框 -->
				<div class="modal fade " id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5>举报详情</h5>
							<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button> -->
								</div>
								<div class="modal-body">
							         <form class="form-horizontal" role="form" id="editform">
							         	  <input type="hidden" id="artId" name="artId"/>
							         	  <div class="form-group">
									    		<label for="username" class="col-sm-2 control-label">文章标题:</label>
									    		<div class="col-sm-5">
									    			<input type="text" class="form-control" id="art_title" name="art_title" readonly value="${articleInfo.title}" />
									    		</div>
									      </div>
									      <div class="form-group">
											    <label for="type" class="col-sm-2 control-label">举报理由:</label>
											    <div class="col-sm-5">
											    	<select name="type" id="type" class="form-control selectpicker" >
   														<option data-hidden="true" disable>请选择举报类型</option>
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
	
                <!-- right sidebar-->
                <div class="col-xs-12 col-md-3 side-right hidden-xs hidden-sm">
                    <ul class="list-group about-user">
                        <li class="list-group-item user-card" >
                            <div class="ava">
                                <a href="/ta/${articleInfo.user.id}">
    						<img class="img-circle" src='${articleInfo.user.avatar}'/>
                                </a>
                            </div>
                            <div class="user-info">
                                <div class="nk mb10">${articleInfo.user.nickname}</div>
                                <div class="mb6">
                                    <a class="btn btn-success btn-xs" href="javascript:void(0);" data-id="${articleInfo.user.id}" rel="follow">+ 关注</a>
                                </div> 
                            </div>
                        </li>

                        <li class="list-group-item">
                            <div class="user-datas">
                                <ul>
                                    <li><strong>${articleInfo.user.articleCount}</strong><span>发布</span></li>
                                    <li class="noborder"><strong>${articleInfo.user.commtentCount}</strong><span>评论</span></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                    <ul class="list-group">
                        <li class="list-group-item">
                            <a class="btn btn-success btn-sm" href="javascript:void(0);" data-id="${articleInfo.id}" rel="favor">喜欢</a>
                            <strong id="favors">${articleInfo.like}</strong> 喜欢
                        </li>
                    </ul>
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
			<a href="" class="rrh-refresh"><i class="fa fa-refresh"></i>换一批</a>
		</h3>
		
	</div>
	<ul class="box-list" id="hots">
		<li class="text-center"><img src="/assets/images/spinner.gif"></li>
	</ul>
</div>

<div class="widget-box shadow-box">
	<div class="title">
		<h3><i class="fa fa-bars"></i> 最新发布
		<a href="" class="rrh-refresh">查看更多</a></h3>
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
        	var url = '/view/' + data.id;
      		var item = jQuery.format(hot_li_template, i + 1, url, data.title, data.hits);
      		return item;
        },
        onLoadLatest : function (i, data) {
        	var url = '/view/' + data.id;
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
</script>                </div>
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
    function EditViewById(id){
    	//console.log(id);
    	$("#myModal").modal("show");
    	//修改请求
    	$('#modal-btn').unbind("click").click(function(){
    		var formdata = new FormData();
    		formdata.append("artId",id);
    		formdata.append("type",$('select[name=type]').selectpicker('val'));
        	formdata.append("remarks",$("#remarks").val());
    		//console.log(formdata);
    		$.ajax({ 
        		type: "POST",
        		url: '/article/complaint',
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

</script>
<script type="text/plain" id="chat_template">
    <li id="chat{5}">
        <a class="avt fl" target="_blank" href="/ta/{0}">
            <img src="{1}">
        </a>
        <div class="chat_body">
            <h5>
                <div class="fl"><a class="chat_name" href="/ta/{0}">{2}</a><span>{3}</span></div>
                <div class="fr reply_this"><a href="javascript:void(0);" onclick="goto('{5}', '{2}')">[回复]</a></div>
                <div class="clear"></div>
            </h5>
            <div class="chat_p">
                <div class="chat_pct">{4}</div> {6}
            </div>
        </div>
        <div class="clear"></div>
        <div class="chat_reply"></div>
    </li>
</script>

<script type="text/javascript">
    function goto(pid, user) {
        document.getElementById('chat_text').scrollIntoView();
        $('#chat_text').focus();
        $('#chat_text').val('');
        $('#chat_to').text(user);
        $('#chat_pid').val(pid);

        $('#chat_reply').show();
    }
    var container = $("#chat_container");
    var template = $('#chat_template')[0].text;
    seajs.use('comment', function (comment) {
        comment.init({
            load_url: '/comment/list/${articleInfo.id}',
            post_url: '/comment/submit',
            toId: ' ${articleInfo.id}',
            onLoad: function (i, data) {
				
                var content = ContentRender.wrapItem(data.content);
                //console.log(data.content);
                var quoto = '';
                if (data.target) {
                    var pat = data.target;
                  //  console.log(pat.content);
                    var pcontent = ContentRender.wrapItem(pat.content);
                    quoto = '<div class="quote"><a href="/ta/' + pat.author.id + '">@' + pat.author.nickname + '</a>: ' + pcontent + '</div>';
                }
                var item = jQuery.format(template,
                        data.author.id,
                        data.author.avatar,
                        data.author.nickname,
                        data.created,
                        content,
                        data.id, quoto);
                return item;
            }
        });
    });

    seajs.use('phiz', function (phiz) {
        $("#c-btn").jphiz({
            base: '/assets',
            textId: 'chat_text',
            lnkBoxId: 'c-lnk',
            phizBoxId: 'c-phiz'
        });
    });

</script>
</body>
</html>

