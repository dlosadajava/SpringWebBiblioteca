

function ShowEditInputIsbn() {
    document.getElementById('isbn').readOnly = false;
   
  } 
  function ShowEditInputTitolo() {
    
    document.getElementById('titolo').readOnly = false;
    
  } 
  
  function ShowEditInputDescrizione() {
    
    document.getElementById('descrizione').readOnly = false;
    
  } 
  
  function ShowEditInputGenere() {
  
   document.getElementById("genere").disabled = false;
   
  } 
  
  function ShowEditInputAutore() {
   
    document.getElementById('autore').disabled = false;
  } 
  
   function ShowEditInputNumeroPagina() {
   
    document.getElementById('numero_pagine').readOnly = false;
  } 
  
  function EnableAllField() {
	  //document.getElementById('isbn').readOnly = false;
	  //document.getElementById('titolo').readOnly = false;
	  //document.getElementById('descrizione').readOnly = false;
   document.getElementById("genere").disabled = false;
     document.getElementById('autore').disabled = false;
    //document.getElementById('numero_pagine').readOnly = false;
    
    
  } 
  
  //validacion
  //body onLoad
  
  function fieldRed(){
	  //ISBN
	   var isbn=document.getElementById("isbn");
    // alert(isbn);
    if(isbn.value==""){//8806218034
		 
		 isbn.style.borderColor = "red"; 
	}
	
	
	//titolo
	
	var t= document.getElementById('titolo');
	if(t.value==null){
		t.style.borderColor = "red"; 
	}
	
    
    //descrizione    
    var d=document.getElementById('descrizione');
    if(d.value==""){
		d.style.borderColor="red";
	}
     
     //numero
     var n=document.getElementById('numero_pagine');
    if(n.value==""){
		n.style.borderColor="red";
	}
	
	//genere
	var g_=document.getElementById("genere");
	if(g.value==""){
		g.style.borderColor="red";
	}
	
	//autore
	//validacion del select option multiple autore
     var l = document.getElementById("autore").options;
     var cont=-1;//comienza en -1 para quitarle el primer opcion, <option disabled selected value> -- select an autore -- </option>
     for(var i=0;i<l.length;i++){
		 if(l[i].selected){
			cont++; 
		 }
	 }
	 
	 if(cont==0){
		  alert("Name must be filled out");
    	document.getElementById("autore").style.borderColor = "red"; 
	 }
    // alert(cont);
	
  }
  

  
  