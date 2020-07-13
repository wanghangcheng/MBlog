<div class="col-xs-12 col-md-3 side-left">

		<ul class="list-group about-user">
    <li class="list-group-item user-card" >
        <div class="ava">
            <a href="/ta/${Session.loginInfo.id}">
    <img class="img-circle" src="${Session.loginInfo.avatar}"/>
            </a>
        </div>
        <div class="user-info">
            <div class="nk mb10">${Session.loginInfo.nickname}</div>
            <div class="mb6">
                <a class="btn btn-success btn-xs" href="/account/profile"><i class="fa fa-pencil"></i> 修改账户</a>
            </div>
        </div>
    </li>
</ul>
<nav class="navbar navbar-default shadow-box">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#home-navbar" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <span class="navbar-brand">导航</span>
        </div>
    </div>
    <div id="home-navbar" class="collapse navbar-collapse">
        <ul class="list-group user-nav first">
            <li class="list-group-item">
                <a href="/home/index"><i class="fa fa-feed"></i> 动态</a>
            </li>
            <li class="list-group-item">
                <a href="/home/posts"><i class="fa fa-file-text-o"></i> 我的文章</a>
            </li>
            <li class="list-group-item">
                <a href="/home/comments"><i class="fa fa-comment-o"></i> 我的评论</a>
            </li>
        </ul>

        <ul class="list-group user-nav">
            <li class="list-group-item">
                <a href="/home/favors"><i class="fa fa-heart-o"></i> 我的喜欢</a>
            </li>
            <li class="list-group-item">
                <a href="/home/follows"><i class="fa fa-eye"></i> 我的关注</a>
            </li>
            <li class="list-group-item">
                <a href="/home/fans"><i class="fa fa-users"></i> 我的粉丝</a>
            </li>
        </ul>

        <ul class="list-group user-nav">
            <li class="list-group-item">
                <a href="/home/notifies">
                    <i class="fa fa-bell-o"></i> 通知 <span class="label label-danger">0</span>
                </a>
            </li>
        </ul>
    </div>
</nav>
                    </div>