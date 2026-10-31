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
                 <form role="form" name="userform" id="userform" >
                 
                            <div class="form-group">
                          <label>UserName <span style="color:#ff0000">*</span> </label>
                         <input class="form-control" placeholder="Enter Username" name ="name" id="name">
                           <span style="color: red;" id="nameerr"> </span>  
                             </div>
                             
                             <div class="form-group">
                          <label>Email <span style="color:#ff0000">*</span> </label>
                         <input class="form-control" placeholder="Enter Email" name ="emailid" id="emailid">
                           <span style="color: red;" id="emailerr"> </span>  
                             </div>
                             
                             
                             <div class="form-group">
                          <label>Password <span style="color:#ff0000">*</span> </label>
                         <input class="form-control" placeholder="Enter password" name ="password" id="password">
                           <span style="color: red;" id="passworderr"> </span>  
                             </div>
                             
                             <div class="form-group">
                          <label>Contact <span style="color:#ff0000">*</span> </label>
                         <input class="form-control" placeholder="Enter Contact" name ="contact" id="contact">
                           <span style="color: red;" id="contacterr"> </span>  
                             </div>
                                                    
                                                 <div class="form-group" id ="sheduleId">
                                                   <label class="form-control-label" for="input-last-name">ValidityDate <span style="color:#ff0000">*</span></label>
                                                  <input type="text"  class="form-control" id="ValidityDate" name="ValidityDate"></input>
                                                   <span style="color: red;" id="ValidityDateerr"> </span>
                                                 </div>
                                                 
                 
                                                <div class="form-group">
       <button type="button" class="btn btn-primary btn-danger" onclick="Validation()" style="margin-top:5px;">Submit</button>
                                                 
                 
                                                 <span style="color: red;" id="err"> </span>
                                                   <span style="color: green;" id="success"> </span>
                                                </div>
                                                
                                          </form>
                                         </div>
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

<script> 
     instance = new dtsel.DTS('input[name="ValidityDate"]',  {
    	 direction: 'BOTTOM',
     	  dateFormat: "yyyy-mm-dd",
     	 
     	});
     
     
     
     function Validation()
     {

     try
     {
   	  var flag=0;
   	  
   	  
   	var email=$("#emailid").val();
   	var password=$("#password").val();
	  var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	  
		var passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&#])[A-Za-z\d@$!%*?&#]{8,}$/;

   		
   	document.getElementById("nameerr").innerHTML ="";
     document.getElementById("emailerr").innerHTML ="";
     document.getElementById("passworderr").innerHTML ="";
     document.getElementById("contacterr").innerHTML ="";
      document.getElementById("ValidityDateerr").innerHTML = "";
     

     //FIRST NAME VALIDATION
     if (document.userform.name.value=="" || document.userform.name.value==null) {
     document.getElementById("nameerr").innerHTML = "Enter User Name";
     document.userform.name.value = "";
     document.userform.name.focus();
 	flag=1;
     }
     
     
     if (!emailRegex.test(email)) {
    	 document.getElementById("emailerr").innerHTML = "Please enter a valid email address";
         document.userform.emailid.focus();
     	flag=1;
     } 
     
     if (!passwordRegex.test(password)) {
    	 document.getElementById("passworderr").innerHTML = "Please Enter Password ";
        // document.userform.password.value = "";
         document.userform.password.focus();
     	flag=1; 	 
     }
    
     
      if (document.userform.contact.value=="") {
          document.getElementById("contacterr").innerHTML = "Please Enter User ContactNo";
         //document.userform.contact.value = "";
         // document.userform.BrandName.focus();
     	flag=1;
          } 
      
          
     ////HotelImages
    
   
       if (document.userform.ValidityDate.value=="" || document.userform.ValidityDate.value==null) {
    	     document.getElementById("ValidityDateerr").innerHTML = "Please Select Schedule Date";
    	     //document.userform.HotelImages.value = "";
    	    // document.userform.HotelImages.focus();
    	 	flag=1;
    	     }
     
     
      if(flag==0)                       
          
  	 {
   	   
         	 // var datastring = $("#userform").serialize();
         	 // alert(datastring);
               
              var data=$("#userform").serialize();
              
              

              $.ajax({
        	        type:"post",
        	        //contentType: "application/json",
        	         data: data,//send data directly
        	        url: "AddUserServlet",
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
        	    	   document.getElementById("emailerr").innerHTML = "EmailID Already Exist";
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