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
  <link rel="stylesheet" href="dist/css/adminlte.min.css">
  <!-- overlayScrollbars -->

    </head>
    <body class="hold-transition sidebar-mini layout-fixed">
    
 <%--    <%SalesTrackBean be=(SalesTrackBean)session.getAttribute("login");
    System.out.println("id on dashboard  "+be.getId());
    int id1=be.getId(); --%>
 
<div>
      <div class="wrapper">
     <%@ include file="header.jsp"%>
      
              <%@ include file="sidebar.jsp"%>
        <!-- /#wrapper -->
                 <%
String TotalAgent="0";
  String ActiveAgent="0";  %>
            <div class="content-wrapper">
                        <section class="content">
                   
                            <%                                
  ResultSet result=null;
  
  DaoFactory dao=DaoFactory.getDao("");  

%>
        
             
                        
      <div class="container-fluid">
      <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">Dashboard</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            
          </div><!-- /.col -->
        </div>
         
 
   <!-- <div class="table-responsive" id="dataagent">
                                         <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                            <thead>
                                               <tr>
                                       <th> Agent Name</th>
			                          <th>Login Time </th>
			                          <th>Address</th>
			                           <th>Tracking Time</th>
			                             <th>Assigned Location</th>
			                             <th>Covered Location</th>
			                          
                                    </tr>
                                    </thead>
                                    </table>
                                    </div>  -->
          <br>
           <!-- <div class="card card-danger">
              <div class="card-header">
                <h3 class="card-title">Account Chart</h3>

                <div class="card-tools">
                  <button type="button" class="btn btn-tool" data-card-widget="collapse">
                    <i class="fas fa-minus"></i>
                  </button>
                  <button type="button" class="btn btn-tool" data-card-widget="remove">
                    <i class="fas fa-times"></i>
                  </button>
                </div>
              </div>
              <div class="card-body">
                <canvas id="donutChart" style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
              </div>
              /.card-body
            </div> -->
            
            
          <!-- ./col -->
          
          <!-- ./col -->
          
          <!-- ./col -->
      
            
            <!-- /.card -->
          </div>
          </section>
                            </div>
                        </div> 
                        
                        <!-- /.col-lg-6 -->
                        <!-- /.row -->
                   
                    <!-- /.row -->
                </div>
                <!-- /.container-fluid -->
        
                                
                                <!-- /.panel-body -->
                             
 
        <!-- jQuery -->
          <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
<script src="plugins/jquery/jquery.min.js"></script>
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

<script>



var donutChartCanvas = $('#donutChart').get(0).getContext('2d')
var donutData        = {
  labels: [
      'Total Agents',
      ' Login',
  ],
  datasets: [
    {
      data: [<%=TotalAgent%>,<%=ActiveAgent%>],
      backgroundColor : ['#00a65a', '#f39c12'],
    }
  ]
}
var donutOptions     = {
  maintainAspectRatio : false,
  responsive : true,
}
//Create pie or douhnut chart
// You can switch between pie and douhnut using the method below.
var donutChart = new Chart(donutChartCanvas, {
  type: 'doughnut',
  data: donutData,
  options: donutOptions
})

                </script>
    </body>
</html>