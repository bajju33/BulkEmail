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
                <h3 class="card-title">Schedule Mail </h3>
                
              </div>
                 <form role="form" name="leadform" id="leadform" >
                 
                            <div class="form-group">
                          <label>EmailName <span style="color:#ff0000">*</span> </label>
                         <input class="form-control" placeholder="Enter EmailName" name ="emailName" id="emailName">
                           <span style="color: red;" id="emailerr"> </span>  
                             </div>
                             
                             <div class="form-group">
                          <label>Subject  <span style="color:#ff0000">*</span></label>
                         <input class="form-control" placeholder="Subject" name ="subject" id="subject">
                          <span style="color: red;" id="subjecterr"> </span>
                             </div>
                                                 
                                                   <%
          DaoFactory dao=DaoFactory.getDao(""); 
                                                   ArrayList<SenderIdBean> ArSender=dao.getAssignUserSender(bean.getId());
                  	try
                   	{
          	    	ResultSet result1=dao.getAllUserTemplate(bean.getId());
          	    
          %>
                                                  <div class="form-group">   
                                                   <label>Template <span style="color:#ff0000">*</span> </label>  
                                
                                                   <select class="form-control" name="templateid" id="templateid">
                                                    
                                                     <option value="0">--Select --</option>                                      
                            <% 
                 	     	while(result1.next()){
                            %>      
                                                                  
                           <option value=<%=result1.getString("id")%>><%=result1.getString("tempname")%></option>
                                                                                  
                           
                           
                           <%

                    }
		}
catch(Exception i)
{
System.out.println(i);
}		
		%>
		
		 </select>
		                      <span style="color: red;" id="temperr"> </span> 
		 
           </div>
                             
                                              <div class="form-group">
                                               <label>Upload File <span style="color:#ff0000">*</span></label>
                                                 <input type="file"  id="File" name="File">
                                      <span style="color: red;" id="CustFileerr"> </span>
                                                </div>
                                                    
                                                 <div class="form-group" id ="sheduleId">
                                                   <label class="form-control-label" for="input-last-name">ScheduleDate <span style="color:#ff0000">*</span></label>
                                                  <input type="text"  class="form-control" id="ValidityDate" name="ValidityDate"></input>
                                                   <span style="color: red;" id="ValidityDateerr"> </span>
                                                 </div>
                                                 
                                                 <div class="form-group">
                          <label>SenderMail <span style="color:#ff0000">*</span> </label>
                         <select class="form-control" name ="sendermailid" id="sendermailid">
                         <option value="0">Select SenderMail</option>
                         <%
                         System.out.print(ArSender.size());
                         
                         for(SenderIdBean senderIdBean:ArSender){ %>
                             <option value="<%=senderIdBean.getId()%>"><%=senderIdBean.getSender_email()%></option>
                         <%} %>
                         </select>
                           <span style="color: red;" id="senderiderr"> </span>  
                             </div>
                 
                                                <div class="form-group">
       <button type="button" class="btn btn-primary btn-danger" onclick="Validation()" style="margin-top:5px;">Submit</button>
                                                 
                  <a href="http://122.176.21.146/SampleFile.csv" download>Sample File</a>
                 
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
     	 showTime: true,
     	});
     
     
     
     function Validation()
     {

     try
     {
   	  var flag=0;
   		
   	  var file=document.leadform.File.value;
   	  var extension = file.substr(file.length-3,file.length);
   	  
   
     document.getElementById("emailerr").innerHTML ="";
     document.getElementById("subjecterr").innerHTML ="";
     document.getElementById("temperr").innerHTML ="";
     document.getElementById("ValidityDateerr").innerHTML = "";
     document.getElementById("CustFileerr").innerHTML = "";
     

     //FIRST NAME VALIDATION
     if (document.leadform.emailName.value=="" || document.leadform.emailName.value==null) {
     document.getElementById("emailerr").innerHTML = "Give Name To Email";
     document.leadform.emailName.value = "";
     document.leadform.emailName.focus();
 	flag=1;
     }
     
     else if (!/^[a-zA-Z\s]*$/.test(document.leadform.emailName.value)) {
         document.getElementById("emailerr").innerHTML = "Only  Characters Allowed";
               document.leadform.emailName.focus();
           	flag=1;
           }
    
     ////////////// HotelDetailse///
      if (document.leadform.subject.value=="" || document.leadform.subject.value==null) {
     document.getElementById("subjecterr").innerHTML = "Please Enter Subject ";
     document.leadform.subject.value = "";
     document.leadform.subject.focus();
 	flag=1;
     }   
     
      if (document.leadform.templateid.value=="0") {
          document.getElementById("temperr").innerHTML = "Please Select Template";
         document.leadform.templateid.value = "0";
         // document.leadform.BrandName.focus();
     	flag=1;
          } 
      
          
     ////HotelImages
    
       if (document.leadform.File.value=="" || document.leadform.File.value==null) {
     document.getElementById("CustFileerr").innerHTML = "Please Upload File";
     //document.leadform.HotelImages.value = "";
    // document.leadform.HotelImages.focus();
 	flag=1;
     }
       else if (extension!="csv")
          {
          document.getElementById("CustFileerr").innerHTML="Please Upload XLSX File only!";
          document.leadform.File.value = "";
          document.leadform.File.focus();
      	flag=1;
          }
   
       if (document.leadform.ValidityDate.value=="" || document.leadform.ValidityDate.value==null) {
    	     document.getElementById("ValidityDateerr").innerHTML = "Please Select Schedule Date";
    	     //document.leadform.HotelImages.value = "";
    	    // document.leadform.HotelImages.focus();
    	 	flag=1;
    	     }
     
       if (document.leadform.sendermailid.value=="0") {
           document.getElementById("senderiderr").innerHTML = "Select Sender MailId";
           document.leadform.sendermailid.value = "0";
           document.leadform.sendermailid.focus();
       	flag=1;
           }
       
     
      if(flag==0)                       
          
  	 {
   	   
         	 // var datastring = $("#leadform").serialize();
         	 // alert(datastring);
               var form = $('#leadform')[0];
              var data=new FormData(form);

                 $.ajax({
                     type: "POST",
                     enctype: 'multipart/form-data',
                     url: "MailSchedule",
                     data: data,
                     processData: false,
                     contentType: false,
                     cache: false,
                     timeout: 600000,
                     success: function (response) {
                     	if(response.includes("Successfully"))
               		  {
                     		$('#leadform')[0].reset();
                  		 document.getElementById("success").innerHTML = response; 
               		  }
               	  else
               		  {
               		  document.getElementById("err").innerHTML = response;  
               		  }
                     },
                     error: function (e) {


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