<%@page import="com.bulkmail.bean.LoginBean"%>
<%@page import="java.sql.ResultSet"%>

 <% LoginBean sidebean=(LoginBean)session.getAttribute("login"); 
   String name_u_u="NA";
   try{
	   name_u_u=sidebean.getName();
   }
catch(Exception e) 
   {
	name_u_u="NA";
	 RequestDispatcher dd = request.getRequestDispatcher("Login.jsp");
	 dd.forward(request, response);
   } 
   %> 

<div>
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="" class="brand-link">
    
      <!-- <img src="images/logo-new.png" alt="SEATECH Logo" class="brand-image img-circle elevation-3" style="opacity: .8;background-color:white"> -->
      <span class="brand-text font-weight-light">Bulk Email</span>
    </a>
    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <!-- SidebarSearch Form -->
      <!-- Sidebar Menu -->
      
      
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
               <li class="nav-item">
            <a href="dashboard.jsp" class="nav-link">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                Dashboard
              </p>
            </a>
          </li>
          
            <!-- <li class="nav-item">
            <a href="agentAccount.jsp" class="nav-link">
              
              <i class="nav-icon fas fa-users-cog"></i>
              <p>
                Users
                                                
              </p>
            </a>
            </li> -->
            <%
            if(sidebean.getRole()==0)
            {
            	%>
            <li class="nav-item">
            <a href="addUser.jsp" class="nav-link">
              
              <i class="nav-icon fas fa-users-cog"></i>
              <p>
                Add User
                                                
              </p>
            </a>
            </li>
            
            <li class="nav-item">
            <a href="addSenderMail.jsp" class="nav-link">
              
              <i class="nav-icon fas fa-users-cog"></i>
              <p>
                Add SenderMail
                                                
              </p>
            </a>
            </li>
            
            <li class="nav-item">
            <a href="assignSenderMail.jsp" class="nav-link">
              
              <i class="nav-icon fas fa-users-cog"></i>
              <p>
                Assign SenderMail
                                                
              </p>
            </a>
            </li>
            <%} %>
            <li class="nav-item">
            <a href="mailer.jsp" class="nav-link">
              
              <i class="nav-icon fas fa-users-cog"></i>
              <p>
                Mailer
                                                
              </p>
            </a>
            </li>
            <li class="nav-item">
            <a href="mailReport.jsp" class="nav-link">
              
              <i class="nav-icon fas fa-users-cog"></i>
              <p>
                MailReport
                                                
              </p>
            </a>
            </li>
            <li class="nav-item">
            <a href="createTemp.jsp" class="nav-link">
              
              <i class="nav-icon fas fa-users-cog"></i>
              <p>
                Templates
                                                
              </p>
            </a>
            </li>
            
          <li class="nav-item">
          </li>
          
        </ul>
      </nav>
      <!-- /.sidebar-menu -->
      
      
    </div>
    <!-- /.sidebar -->
  </aside> 
  

                                        <!-- /.col-lg-6 (nested) -->
                                    </div>
                                    <!-- /.row (nested) -->
                                    

                                    
                              
       
        <!-- jQuery -->
      