
define(function(require, exports, module) {
	
	require('validate');
	require('bootstrap');
	require('layer');
	
	$("#e_btn").click(function(){
		let email_val = $("#email").val();
		if(email_val==null||email_val==""||email_val==" "){
			layer.msg('请先输入邮箱', function(){});
		}else{
			let count = 60;
   			const countDown = setInterval(() => {
   				if (count == 0) {
          			$('#e_btn').val('重新发送').removeAttr('disabled');
          			$('#e_btn').addClass('btn-success');
          		clearInterval(countDown);
        		} else {
          			$('#e_btn').attr('disabled', true);
          			$('#e_btn').removeClass('btn-success');
          			$('#e_btn').val(count + '秒');
        		}
        		count-=1;
      		}, 1000);
   			   			
			$.ajax({
				type:"POST",
        			url:"/account/edit",
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
	
	$("#f_email").validate({
        debug : true,//只验证不提交
        rules :{
        	email: {
        		required: true,
        		email:true,
            
        	},
        	code: {
        		required: true,
        		equalTo:"#n_code"
        	}
        },
        messages: {
        	email: {
        		required: "请输入电子邮箱",
        		email:"邮箱格式不正确",
        		
        	},
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
            //$(b).popover('destroy');
        },
        submitHandler: function(form){
        	$.ajax({ 
        		type: "POST",
        		url: "/account/editEmail",
        		data: $(form).serialize(),
        		success: function(data){
					if(data.code==101){
						layer.msg('邮箱已被占用', function(){});
					}
					if(data.code==200){
						layer.msg('恭喜，修改成功', {icon: 6});
						$("#code").val("");
					}
                },
                error: function(XMLHttpRequest,textStatus,errorThrown){
                	layer.msg('服务器通讯错误', {icon: 5});
                },
        	});
        } 
    });
	
	exports.init=function(){
		
	}
});