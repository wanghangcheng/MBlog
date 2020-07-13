
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>注册 - MGblog</title>
    <meta name="keywords" content="MGblog,$!{site_keywords}">
    <meta name="description" content="$!{site_description}">
    <!-- v3 -->
    <link rel="stylesheet" href="/assets/vendors/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/login.css">
    <link rel="stylesheet" href="/assets/vendors/animate/animate.min.css">

    <!-- JavaScript -->
    <script type="text/javascript" src="/assets/js/jquery.min.js"></script>
    <script type="text/javascript" src="/assets/js/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="/assets/vendors/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/assets/vendors/validate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="/assets/vendors/validate/messages_zh.min.js"></script>
	 
    <!-- Favicons -->
    <link rel="apple-touch-icon-precomposed" href="http://MGblog.com/assets/images/logo/m10.png"/>
    <link rel="shortcut icon" href="http://MGblog.com/assets/images/logo/m.png"/>
	<script type="text/javascript" src="/assets/js/layer3.1/layer.js"></script>
	
</head>
<body class="sign">
<div class="login">
    <a href="/index">
        <img src="/assets/images/logo/m11.png" height="100" width="100">
    </a>
    <h1>MGblog, 轻松分享你的兴趣</h1>
    <a href="/login.html" class="signup-link gapps"><span>已经修改,返回登录</span></a>
    <hr>
    <form action="reg" method="post">
        <div id="message">
        <label for="id_name">邮箱:</label>
        <div id="id_name">
        	<input id="email" maxlength="64" class="form-control border" name="email" value="" placeholder="邮箱地址" type="text" data-required data-conditional="email" data-description="email" data-describedby="message">
        </div>
        <label for="id_code">邮箱验证码:</label>
        <div id="id_code" style="width:100%;overflow:hidden;"> 
        	<div style="display:inline-block; padding-right:8px;width:55%;float:left;">
        		<input maxlength="64" class="form-control border" id="code" name="code" value="" placeholder="邮箱验证码" type="text" data-required data-describedby="message">
        	</div>
            
        	<div style="display:inline-block;width:35%;float:right;margin-right: 22px;">
        		<input id="reg_btn" type="button" class="btn btn-success border" value="发送验证码" >
        	</div>
        	<input id="n_code" value="3333" type="hidden" style ="display:block;">
        </div>
        <label for="id_password">新密码:</label>
        <div id="id_password">
            <input id="password" class="form-control border" maxlength="18" name="password" placeholder="密码" type="password" data-required>
        </div>
        <label for="id_password2">确认密码:</label>
        <div id="id_password2">
            <input maxlength="18" class="form-control border" name="password2" placeholder="请再输入一次密码" type="password" data-required data-conditional="confirm" data-describedby="message" data-description="confirm">
        </div>
        <input type="submit" class="btn btn-success border" value="发送">
    </form>
</div>

<script type="text/javascript">
    $(function(){
    
		$("#reg_btn").click(function(){
			let email_val = $("#email").val();
			if(email_val==null||email_val==""||email_val==" "){
				layer.msg('请先输入邮箱', function(){});
			}else{
				let count = 60;
       			const countDown = setInterval(() => {
       				if (count == 0) {
              			$('#reg_btn').val('重新发送').removeAttr('disabled');
              			$('#reg_btn').addClass('btn-success');
              		clearInterval(countDown);
            		} else {
              			$('#reg_btn').attr('disabled', true);
              			$('#reg_btn').removeClass('btn-success');
              			$('#reg_btn').val(count + '秒');
            		}
            		count-=1;
          		}, 1000);
          	
				$.ajax({
					type:"POST",
        			url:"/email",
					data:'email='+email_val,
       				success:function(res){
            			if(res.code==100){
               				$("#n_code").val(res.data);
            			}
						if(res.code==200){
							layer.msg('验证失败', function(){});
						}
        			},
				});
			}
		});
		
       	$("form").validate({
            debug : true,
            rules :{
               password : {
                required: true,
                minlength: 5
              },
              password2 : {
                required: true,
                minlength: 5,
                equalTo: "#password"
              },
              email: {
                required: true,
                email: true
              },
              code: {
              	required: true,
              	equalTo:"#n_code"
              },
            },
            messages: {
              password: {
                required: "请输入登录密码",
                minlength: "密码长度不能小于 5"
              },
              password2: {
                required: "请重复输入密码",
                minlength: "密码长度不能小于 5",
                equalTo: "两次密码输入不一致"
              },
              email: "请输入正确的邮箱",
              code: {
              	required: "请输入验证码",
              	equalTo: "验证码不正确",
              }          
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
                $(b).popover('destroy');
             },
             //验证通过后执行这里，发送数据到后端
            submitHandler: function(form){
            	var layer1 = layer.msg('正在处理中...', {
            		  		icon: 16,
            		 		shade: 0.5,
            		  		time: 0,
            			});
            	
            	$.ajax({ 
            		type: "POST",
            		url: "/public/forgot/eidtPwd",
            		data: $(form).serialize(),
            		success: function(data){
                    	layer.close(layer1);
                    	if(data.code==100){
    						layer.msg('请输入您帐号的邮箱地址', function(){});
    					}
    					if(data.code==300){
    						console.log(data.msg);
    					}
    					if(data.code==400){
    						layer.msg('该邮箱没有绑定账号,无需修改,请先注册', {icon: 6});
    						setTimeout(function(){
    							window.location.href='/register.html';
    						},2000);
    					}
    					if(data.code==200){
    						layer.msg('修改成功，请使用新密码登录。正为你跳转登录页面', {icon: 6});
    						setTimeout(function(){
    							window.location.href='/login.html';
    						},2000);
    					}
                    },
                    error: function(XMLHttpRequest,textStatus,errorThrown){
                    	layer.close(layer1);
                    	layer.msg('服务器通讯错误', {icon: 5});
                    },
            	});
            	
            }
        });
    })
</script>


    <p class="small">
     <a href="http://www.blog.cn/" target="_blank">
    </p>

    <script type="text/javascript">
        //<!CDATA[
        var bodyBgs = [];
        bodyBgs[0] = "/assets/images/first/bg-1.jpg";
        bodyBgs[1] = "/assets/images/first/bg-2.jpg";
        bodyBgs[2] = "/assets/images/first/bg-3.jpg";
        bodyBgs[3] = "/assets/images/first/bg-4.jpg";
        bodyBgs[4] = "/assets/images/first/bg-5.jpg";
        bodyBgs[5] = "/assets/images/first/bg-9.jpg";
        var randomBgIndex = Math.round( Math.random() * 5 );
        //输出随机的背景图
        document.write('<style>body{background:url(' + bodyBgs[randomBgIndex] + ') no-repeat 100% 0}</style>');
        //]]>
    </script>
</body>
</html>

