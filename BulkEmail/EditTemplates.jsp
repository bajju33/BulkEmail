<%@page import="com.bulkmail.dao.DaoFactory"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Bulk Email</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
    <link rel="icon" href="images/seatech-bk.png" type="image/x-icon">
  

  <link rel="stylesheet" href="dist/css/adminlte.min.css">
  <!-- summernote -->
  <link rel="stylesheet" href="plugins/summernote/summernote-bs4.min.css">
  <!-- CodeMirror -->
  <link rel="stylesheet" href="plugins/codemirror/codemirror.css">
  <link rel="stylesheet" href="plugins/codemirror/theme/monokai.css">
    
   <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css"/>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.6.5/css/buttons.dataTables.min.css"/>
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
  <!-- Navbar -->
 <%@ include file="sidebar.jsp"%>
              <%@ include file="header.jsp"%>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
    </section>
                               
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-12">
         <div class="panel-body">
  <div class="row">
    <form role="form" name="leadform" id="leadform" >
       
           
                   <input type="hidden" id="id" name="id" value="<%= request.getParameter("id")%>" >
          <div class="card card-outline card-info">
             
            <!-- /.card-header -->
            <div class="card-body">
            <label>Template</label>
            <%
            DaoFactory dao=DaoFactory.getDao("");
            String template=dao.getTemplate(request.getParameter("id")); 
            %>
              <textarea class="form-control" rows="4"  cols="100" name="template" id="template" >
               <%=template%>
              </textarea>
              <button type="button" class="btn btn-primary btn-sm" onclick="SetVariable('Var1')" value="Var1">Var1</button>
                <button type="button" class="btn btn-primary btn-sm" onclick="SetVariable('Var2')" value="Var2">Var2</button>
                  <button type="button" class="btn btn-primary btn-sm" onclick="SetVariable('Var3')" value="Var3">Var3</button>
                    <button type="button" class="btn btn-primary btn-sm" onclick="SetVariable('Var4')" value="Var4">Var4</button>
                    <button type="button" class="btn btn-primary btn-sm" onclick="SetVariable('Var5')" value="Var5">Var5</button>
                    <button type="button" class="btn btn-primary btn-sm" onclick="SetVariable('Var6')" value="Var6">Var6</button>
                    <button type="button" class="btn btn-primary btn-sm" onclick="SetVariable('Var7')" value="Var7">Var7</button>
                    <button type="button" class="btn btn-primary btn-sm" onclick="SetVariable('Var8')" value="Var8">Var8</button>
                    <button type="button" class="btn btn-primary btn-sm" onclick="SetVariable('Var9')" value="Var9">Var9</button>
            <span style="color: red;" id="temperr"> </span>
            </div>
            <div class="col-md-3">
           <button type="button" class="btn btn-primary btn-sm" onclick="Update()">Update</button>
         <span style="color: red;" id="err"> </span>
                                                   <span style="color: green;" id="success"> </span>
         </div>
 
          </div> </form>
          
          </div>
          </div>
         
        </div>
        <!-- /.col-->
      </div>
      
      <!-- ./row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <!-- <footer class="main-footer">
    <div class="float-right d-none d-sm-block">
      <b>Version</b> 3.1.0-rc
    </div>
    <strong>Copyright &copy; 2014-2020 <a href="https://adminlte.io">AdminLTE.io</a>.</strong> All rights reserved.
  </footer> -->

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<!-- jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>
<!-- Summernote -->
<script src="plugins/summernote/summernote-bs4.min.js"></script>
<!-- CodeMirror -->
<script src="plugins/codemirror/codemirror.js"></script>
<script src="plugins/codemirror/mode/css/css.js"></script>
<script src="plugins/codemirror/mode/xml/xml.js"></script>
<script src="plugins/codemirror/mode/htmlmixed/htmlmixed.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>
 <script type="text/javascript" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.5/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.5/js/buttons.flash.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.5/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.5/js/buttons.print.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<!-- Page specific script -->
<script>
   $(function () {
    // Summernote
    $('#template').summernote({
        height: 200,
        toolbar: [
            [ 'style', [ 'style' ] ],
            [ 'font', [ 'bold', 'italic', 'underline', 'strikethrough', 'superscript', 'subscript', 'clear'] ],
            [ 'fontname', [ 'fontname' ] ],
            [ 'fontsize', [ 'fontsize' ] ],
            [ 'color', [ 'color' ] ],
            [ 'para', [ 'ol', 'ul', 'paragraph', 'height' ] ],
            [ 'table', [ 'table' ] ],
            [ 'insert', [ 'link'] ]
        ]
    });

    // CodeMirror
    CodeMirror.fromTextArea(document.getElementById("codeMirrorDemo"), {
      mode: "htmlmixed",
      theme: "monokai"
    });
  }) 
  
  
  $(document).ready(function() {
      $('#dataTables-example').DataTable({
              responsive: true,
              ordering: false
      });
  });
  
  
  function SetVariable(v)
  {
	  var temp=document.leadform.template.value;
	  //alert(temp);
	  //document.leadform.template.value =temp+"<"+v+">";
	
	  $('#template').summernote('editor.insertText', "{"+v+"}");
  }
  
  function Update()
  {
	  
	  try{
		  var flag=0;
	  document.getElementById("temperr").innerHTML ="";
	  document.getElementById("err").innerHTML ="";	  
	  document.getElementById("success").innerHTML ="";	  
	  
		if ($('#template').summernote('code')=="" || $('#template').summernote('code')==null) {
		   	document.getElementById("temperr").innerHTML = " Please Enter Template ";
		   	document.leadform.template.value = "";
		   	document.leadform.template.focus();
		   	flag=1;
		   	}
		
		if(flag==0)
		{
		//var str ="tempname="+document.leadform.tempname.value+"&template="+$('#template').summernote('code').replace(';&lt','<').replace('&gt;', '>');
		//alert(str);
		$.ajax({
			type: "post",
			data: $("#leadform").serialize(),
			url: "UpdateTemplate",

			asynch: false,
			success: function(response)
			{
				 
					 
					 document.getElementById("success").innerHTML = response; 
			}

			}) 
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
