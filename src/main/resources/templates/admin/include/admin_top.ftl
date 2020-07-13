<!-- top navigation -->
        <div class="top_nav">
            <div class="nav_menu">
                <nav>
                    <div class="nav toggle">
                        <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                    </div>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="">
                            <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown"
                               aria-expanded="false">
                                <img src="${Session.loginInfo.avatar}" alt="">${Session.loginInfo.nickname}
                                <span class=" fa fa-angle-down"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-usermenu pull-right">
                            	<li><a href="/home/index.html"> 我的主页</a></li>
                                <li><a href="/account/profile"> 账户信息</a></li>
                                <li><a href="/public/logout"><i class="fa fa-sign-out pull-right"></i> 退出登录</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
        <!-- /top navigation -->