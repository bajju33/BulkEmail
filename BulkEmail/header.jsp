<%@page import="com.bulkmail.bean.LoginBean"%>
<%@page import="java.sql.ResultSet"%>


<nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="dashboard.jsp" class="nav-link"></a>
      </li>
    </ul>
    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
    <li class="nav-item">
        <a class="nav-link" data-widget="fullscreen" href="#" role="button">
          <i class="fas fa-expand-arrows-alt"></i>
        </a>
      </li>
      </ul>

<!--    NOTIFICATIONS  --> 


    <% LoginBean bean=(LoginBean)session.getAttribute("login"); 
   String name_u="NA";
   try{
	   name_u=bean.getName();
   }
catch(Exception e) 
   {
	name_u="NA";
	 RequestDispatcher dd = request.getRequestDispatcher("Login.jsp");
	 dd.forward(request, response);
   } 
   %> 
  
    
                         <ul class="nav navbar-right navbar-top-links">
                       
                        
      <!-- Messages Dropdown Menu -->
      <li class="nav-item dropdown">
        <a class="nav-link" data-toggle="dropdown" href="#">
       
                                <i class="fa fa-user fa-fw"></i> <%=name_u %> <b class="caret"></b>
                                <!--     <span>SuperAdmin</span> -->
        </a>
        <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
          <div class="dropdown-divider"></div>
        
          <a href="Login.jsp" class="dropdown-item">
            <i class="ti-layout-sidebar-left" ></i> Logout
          </a>
          
          <div class="dropdown-divider"></div>
                                       
                                          </div>
      </li>
      
    </ul>
  </nav>
