class EmailEditor {
  constructor(id) {
    unlayer.init({
      id: id,
      displayMode: "email",
     
      appearance: {
        //theme: 'dark',
        panels: {
          tools: {
            dock: "left"
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
  
  
$.ajax({
				type: "post",
				 dataType: 'JSON',
    data: { 
      loadProds: 1,
      htmldata: JSON.stringify(data) // look here!
    },
				url: "AddEmailTemplate",

				asynch: false,
				success: function(response)
				{
				
				}

				}) 
})
})


