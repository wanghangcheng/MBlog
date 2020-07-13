
<!DOCTYPE html>
<html lang="en" class="app">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MBlog - 后台管理</title>

    <!-- Bootstrap -->
    <link href="/assets/vendors/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="/assets/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="/assets/vendors/treeTable/themes/vsStyle/treeTable.min.css" rel="stylesheet"/>

    <!-- Custom Theme Style -->
    <link href="/assets/admin/css/custom.min.css" rel="stylesheet">

    <!-- jQuery -->
    <script src="/assets/js/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="/assets/vendors/bootstrap/js/bootstrap.min.js"></script>
    <script src='/assets/vendors/validate/jquery-validate.js'></script>
    <!-- FastClick -->
    <script src="/assets/vendors/fastclick/lib/fastclick.js"></script>

    <script src="/assets/vendors/layer/layer.js"></script>
    <script src="/assets/vendors/treeTable/jquery.treeTable.min.js"></script>

    <script type="text/javascript">
        window.UEDITOR_HOME_URL = '/assets/vendors/ueditor/';
    </script>
</head>
<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <div class="col-md-3 left_col">
        <!-- admin_left  -->
        <#include "/admin/include/admin_menu.ftl">
        </div>

        <!-- top navigation -->
        <#include "/admin/include/admin_top.ftl">
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
            <div>

<div class="page-title">
    <div class="title_left">
    	<h2 class="display-4">欢迎来到MBLOG后台管理</h2>
        <h3>${Session.loginInfo.nickname}<small> 欢迎使用MBlog</small></h3>
    </div>
</div>
<div class="clearfix">
</div>
<div class="row">
    <div class="col-md-6 col-sm-6 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>系统状态</h2>
                <div class="clearfix"></div>
            </div>
            <div class="x_content" style="height: 231px;">
                <table class="table table-bordered">
                    <tr>
                        <td>内存消耗:</td>
                        <td>
						296.33M / 791M
                        </td>
                    </tr>
                    <tr>
                        <td style="width:120px;">操作系统:</td>
                        <td>Windows 10</td>
                    </tr>
                    <tr>
                        <td style="width:120px;">JDK版本:</td>
                        <td>1.8.0_171</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>

    <div class="col-md-6 col-sm-6 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>内存使用情况</h2>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <canvas id="canvas"></canvas>
            </div>
        </div>
    </div>
</div>
<script type='text/javascript' src='/assets/vendors/Chart.js/dist/Chart.min.js'></script>
<script>
	$(function () {
        var ctx = document.getElementById("canvas");
        var data = {
            labels: [
                "可用",
                "已用"
            ],
            datasets: [{
                data: [63, 100 - 63],
                backgroundColor: [
                    "#455C73",
                    "#9B59B6"
                ],
                hoverBackgroundColor: [
                    "#34495E",
                    "#B370CF"
                ]
            }]
        };

        var canvasDoughnut = new Chart(ctx, {
            type: 'doughnut',
            tooltipFillColor: "rgba(51, 51, 51, 0.55)",
            data: data
        });
    })
</script>
            </div>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <#include "/admin/include/admin_footer.ftl">
        <!-- /footer content -->
    </div>
    <!-- Custom Theme Scripts -->
    <script src="/assets/admin/js/custom.min.js"></script>
    <script src="/assets/admin/js/app.data.js"></script>
</body>
</html>
