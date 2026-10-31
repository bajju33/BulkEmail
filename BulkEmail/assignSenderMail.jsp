<%@page import="com.bulkmail.bean.SenderIdBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bulkmail.dao.DaoFactory"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
    <title>Bulk Email</title>
       <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <!-- Favicon icon -->
	     <link rel="icon" href="images/seatech-bk.png" type="image/x-icon">
     <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <link rel="icon" href="images/seatech-bk.png" type="image/x-icon">
  <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
  <!-- Ionicons -->
    <link rel="stylesheet" href="dtsel-master/dtsel.css">
  <link rel="stylesheet" href="dist/css/adminlte.min.css">
  
   <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css"/>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.6.5/css/buttons.dataTables.min.css"/>
  <!-- overlayScrollbars -->

    </head>
    <body class="hold-transition sidebar-mini layout-fixed">
    
 <%--    <%SalesTrackBean be=(SalesTrackBean)session.getAttribute("login");
    System.out.println("id on dashboard  "+be.getId());
    int id1=be.getId(); --%>
 
      <div class="wrapper">
                        <%@ include file="sidebar.jsp"%>
                         <%@ include file="header.jsp"%>                                        
    <div class="content-wrapper">
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
          </div>
        </div>
      </div>
    </div>      
    
    
      <div class="container-fluid">
    <div class="col-sm-8 mx-auto">
    
    <div class="card card-primary">
          <div class="card-header">
                <h3 class="card-title">UserAdd </h3>
                
              </div>
              <%
              DaoFactory daoFactory=DaoFactory.getDao("");
              ArrayList<LoginBean> Aruser=daoFactory.getAllUserList();
              ArrayList<SenderIdBean> ArSender=daoFactory.getActiveSenderMail();
              %>
                 <form role="form" name="userform" id="userform" >
                 
                            <div class="form-group">
                          <label>Username <span style="color:#ff0000">*</span> </label>
                         <select class="form-control" name ="username" id="username">
                         <option value="0">Select Username</option>
                         <%for(LoginBean loginBean:Aruser){ %>
                             <option value="<%=loginBean.getId()%>"><%=loginBean.getName()%></option>
                         <%} %>
                         </select>
                           <span style="color: red;" id="usernameerr"> </span>  
                             </div>
                             
                             <div class="form-group">
                          <label>SenderMail <span style="color:#ff0000">*</span> </label>
                         <select class="form-control" name ="sendermailid" id="sendermailid">
                         <option value="0">Select SenderMail</option>
                         <%for(SenderIdBean senderIdBean:ArSender){ %>
                             <option value="<%=senderIdBean.getId()%>"><%=senderIdBean.getName()%></option>
                         <%} %>
                         </select>
                           <span style="color: red;" id="senderiderr"> </span>  
                             </div>
                             
                             
                                                <div class="form-group">
       <button type="button" class="btn btn-primary btn-danger" onclick="Assign()" style="margin-top:5px;">Add</button>
                                                 
                 
                                                 <span style="color: red;" id="err"> </span>
                                                   <span style="color: green;" id="success"> </span>
                                                </div>
                                                
                                          </form>
                                         </div>
                                        </div>
                                        <div class="table-responsive">
                                        <table class="table table-striped table-bordered table-hover" id="dataTables-example">                                  
                                                    
                                           <thead>
                                           
                                           <tr>
                                                    <th>Username  </th> 
                                                     <th>SenderEmail</th>
                                                    
                                                    <!--  <th>Status</th> -->
                                                      
                                                </tr>
                                                </thead>  
                                                <tbody>
                                                <%
                                                DaoFactory da=DaoFactory.getDao("");
                                                ArrayList<SenderIdBean> Ar=da.getAssignUserSenderMail();
                                                String tt="";
                                                for(SenderIdBean senderIdBean:Ar)
                                                {
                                                	try{
                                                %>
                                                <tr>
                                                <td><%=senderIdBean.getUsername() %></td>
                                                <td><%=senderIdBean.getSender_email() %></td>
                                                
                                               <%--  <td><button type="button" class="btn btn-primary btn-danger" onClick="window.open('maildata.jsp?id=<%=rs.getInt("id") %>');" >Report</button></td> --%>
                                                </tr>
                                                <%
                                                }
                                                catch(Exception e)
                                                {
                                                	e.printStackTrace();
                                                }
                                                }
                                                	%>
                                                
                                                
                                                </tbody>
                                                </table>
                                                </div>
                                        </div>
                </div>
                </div>
          <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->

<!-- Bootstrap 4 -->
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- ChartJS -->
<script src="plugins/chart.js/Chart.min.js"></script>
<script src="dist/js/adminlte.min.js"></script>
<!-- Sparkline -->
<!-- AdminLTE App -->
<script src="dist/js/demo.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="dtsel-master/dtsel.js"></script>
 <script type="text/javascript" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>

<script> 
   
     
     
$(document).ready(function() {
    $('#dataTables-example').DataTable({
            responsive: true,
            ordering: false
    });
});

     
     function Assign()
     {

     try
     {
   	  var flag=0;
   	  
   	  
	  

   		
   	document.getElementById("usernameerr").innerHTML ="";
     document.getElementById("senderiderr").innerHTML ="";
     

     //FIRST NAME VALIDATION
     if (document.userform.username.value=="0") {
     document.getElementById("usernameerr").innerHTML = "Select User Name";
     document.userform.username.value = "";
     document.userform.username.focus();
 	flag=1;
     }
     if (document.userform.sendermailid.value=="0") {
         document.getElementById("senderiderr").innerHTML = "Select Sender MailId";
         document.userform.sendermailid.value = "";
         document.userform.sendermailid.focus();
     	flag=1;
         }
     
     //sendermailid
     
     
    
     
      if(flag==0)                       
          
  	 {
   	   
         	 // var datastring = $("#userform").serialize();
         	 // alert(datastring);
               
              var data=$("#userform").serialize();
              
              

              $.ajax({
        	        type:"post",
        	        //contentType: "application/json",
        	         data: data,//send data directly
        	        url: "AssignSenderMailServlet",
        	        asynch: false,
        	        success: function(response) {
        	          //  window.location.href="dashboard";
        	          
        	       if(response.includes("SuccessFully"))
		 	 {
          	  alert(response);
          	  window.location.reload();
		 	 }
        	       else
        	    	   {
        	    	   document.getElementById("senderiderr").innerHTML = "EmailID Already Assign";
        	    	   }
        	           
        	           
        	        } 

        	    });
  	    	 }
 }
 catch(err)
 {
 alert(err);
 }
 }	
     
     
     
     </script>
     
     
    </body>
</html>