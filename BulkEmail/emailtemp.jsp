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
 <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/milligram/1.3.0/milligram.min.css'>
 <link rel="stylesheet" href="unplayer/style.css">
</head>
<body>
      
    <!-- Main content -->
    <%
    
    String tempname=request.getParameter("tempname");
    %>
      
    <ul>
  <li><button id="save_html_btn">Save HTML</button></li>
</ul><div id="editor-container"></div>
          
          
         
        <!-- /.col-->
     
    <!-- /.content -->
  


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
<script src='https://editor.unlayer.com/embed.js'></script>

<!-- Page specific script -->
<script src='https://editor.unlayer.com/embed.js'></script>
<!--<script  src="unplayer/script.js"></script>-->
<script>
class EmailEditor {
	  constructor(id) {
		    unlayer.init({
		      id: id,
		      displayMode: "email",
		      customJS: [
		    	    'https://examples.unlayer.com/examples/simple-custom-tool/custom.js',
		    	  ],
		      appearance: {
		        //theme: 'dark',
		        panels: {
		          tools: {
		            dock: "left",
		            social: {
		                properties: {
		                  containerPadding: {
		                    value: "10px"
		                  }
		                }
		              }
		          }
		        }
		      }
		    });
		  }



		  exportHtml(callback) {
			unlayer.exportHtml(callback);
		}
		}

		const editor = new EmailEditor('editor-container');

		const saveHTMLBtn = document.getElementById('save_html_btn');

		saveHTMLBtn.addEventListener('click',e => {
		  editor.exportHtml(d => console.log(d));
		  
		  
		  unlayer.exportHtml(function(data) {
		  var json = data.design; // design json
		  var html = data.html; // final html
		  var d='<%=tempname%>';
		$.ajax({
						type: "post",
						 dataType: 'JSON',
		    data: { 
		      loadProds: 1,
		      htmldata: JSON.stringify(data), // look here!
		      tempname:d
		    },
						url: "AddEmailTemplate",

						asynch: false,
						success: function(jsonObj)
						{
							//alert(jsonObj.message);
							 if(jsonObj.message.includes("Successfully"))
							 {
								 
								 //$('#leadform')[0].reset();
								 alert(jsonObj.message);
								 window.location="createTemp.jsp"
							  } 
							 else
								 {
							//document.getElementById("err").innerHTML =response;
							
							alert(jsonObj.message);
							 window.location="createTemp.jsp"
								 }
						},
		    error: function(XMLHttpRequest, textStatus, errorThrown) { 
		        alert("Status: " + textStatus);
		        alert("Error: " + errorThrown); 
		    } 
						}) 
		})
		})


</script>
</body>
</html>
