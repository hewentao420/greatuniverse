var xmlhttp=null;
 function getHttpRequest() 
 {
   try
   {
    xmlhttp=new ActiveXObject('Msxml2.XMLHTTP');
   }
   catch(e)
   {
        try
          {
                xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
          }
          catch(e1)
          {
              xmlhttp=new XMLHttpRequest();
          }
       
   }
 }
 

 
function httpGet(url,method,data)
{
 getHttpRequest();
 xmlhttp.open(method,url +"?"+data,true);
 xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
 xmlhttp.setRequestHeader("Content-Length",data.length);
 xmlhttp.onreadystatechange=callback;
 xmlhttp.send(null);
}

function callback()
{
     if(xmlhttp.readyState==4)
     {  
       if(xmlhttp.status==200)
       {     
           var xmlDoc=xmlhttp.responseText;
           var data=eval(xmlDoc);
           document.getElementById("showout").innerHTML+=data[0].userId+","+data[0].title+","+data[0].description+","+data[0].url_small+"<br>";
           document.getElementById("showout").innerHTML+=data[1].userId+","+data[1].title+","+data[1].description+","+data[1].url_small+"<br>";
       }
       else
       {
           document.getElementById("showout").innerHTML="AJAX Server error!"; 
       }      
     }
}