
let btnProduzir = document.querySelector("#produzir");
let idOrc = document.querySelector("#idOrc").value;


btnProduzir.addEventListener("click", ()=>{
 let xhr = new XMLHttpRequest();  
    xhr.open('POST', 'AprovarOrcamentoAjax?id='+idOrc);
    xhr.onload = function() {
        if (xhr.status === 200) {
        alert(xhr.responseText);

        } 
        
        else {
            alert('Request failed.  Returned status of ' + xhr.status);
        }
    };
    
    xhr.send(); 

});


