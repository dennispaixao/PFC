
let pagar = document.querySelector("#pagar");
let id = document.querySelector("#idOrc").value;

pagar.addEventListener("click", () => {

    let qtPaga = document.querySelector("#qtpaga");
    let total = document.querySelector("#total");
    let pagamento = document.querySelector("#campoPagar");
    let troco = document.querySelector("#troco");

    _qtPaga = qtPaga.value.slice(8, qtPaga.length);
    _total = total.value.slice(9, total.length);

    _total = parseFloat(_total.replace(",", "."));
    _qtPaga = parseFloat(_qtPaga.replace(",", "."));
    let _pagamento = parseFloat(pagamento.value.replace(",", "."));
    
    if(!isNaN(_pagamento)){
    
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "InserirPagamentoAjax?valor=" + _pagamento + "&id=" + id);
        xhr.onload = function () {   
            if (xhr.status === 200){    
                
                let resposta = xhr.responseText.split(';');
                let _troco = parseFloat(resposta[1]);
                let _paga = parseFloat(resposta[0]);
                _troco = _troco.toString().replace(".",",");
                
                alert("o pagamento foi efetuado");
                qtPaga.value = "Pago R$"+ _paga;
                troco.value = "R$" + _troco;
                
             // qtPaga.value = "Pago: R$"+_pago;

            } else {
                alert('Request failed.  Returned status of ' + xhr.status);
                console.log(xhr.status);    
            }
        };   
        
    xhr.send();
}else{
    alert("campo pagamento precisa ser um numero");
}

}); 
