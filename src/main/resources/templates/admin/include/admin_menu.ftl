 <style>
 
 	.menu_a{
 		margin-left: 30px;
    	font-size: 10px;
    }
 </style>
 <div class="col-md-3 left_col">
            <div class="left_col scroll-view">
                <div class="navbar nav_title" style="border: 0;">
                    <a href="/admin/home" class="site_title"><span>MBlog</span></a>
                </div>

                <div class="clearfix"></div>

                <br/>

                <!-- sidebar menu -->
                <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                    <div class="menu_section">
                        <h3>系统菜单</h3>
                        <ul class="nav side-menu">
                            <li><a href="/admin/home"><i class="fa fa-home"></i> Home</a>
                            </li>
                                        <li><a href="/admin/article/list" nav="2"><i class="fa fa-clone icon-xlarge"></i>文章管理</a></li>
                                        <li><a href="/admin/users/list" nav="3"><i class="fa fa-user icon-xlarge"></i>用户管理</a></li>
                                        <li><a><i class="fa fa-comments-o icon-xlarge"></i>投诉管理</a>
                                        	<ul class="nav collapse">  
												<li><a href="/admin/complaint/article/list" class="menu_a "><i class="fa fa-clone icon-xlarge"></i>文章投诉</a></li>  
                        						<li><a href="/admin/complaint/user/list" class="menu_a "><i class="fa fa-user icon-xlarge"></i>用户投诉</a></li>  
                    						</ul>  
                                        </li>
                                        <li><a href="/admin/forum/list" nav="5"><i class="fa fa-tags icon-xlarge"></i>栏目管理</a></li>
                                        <li><a href="/admin/journal/list" nav="6"><i class="fa fa-sun-o icon-xlarge"></i>操作日志</a></li>
                                       <!--  <li><a href="/admin/roles/list" nav="7"><i class="fa fa fa-registered icon-xlarge"></i>角色管理</a></li>
                                        <li><a href="/admin/authMenus/list" nav="8"><i class="fa fa-reorder icon-xlarge"></i>菜单管理</a></li> -->
                                        <li><a href="JavaScript:void(0);" style="pointer-events: none;"><i class="fa fa-link"></i>友情链接</a></li>

                        </ul>
                    </div>
                </div>
                <!-- /sidebar menu -->
            </div>
        </div>