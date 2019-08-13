//função usada para realizar a busca e montar a tabela de itens de produtos a serem add
const listar = () => {
    //instanciando objeto do XMLHttpRequest
    let xhr = new XMLHttpRequest();
    //caminho da servlet  e atributo
    xhr.open('GET', 'BuscarListaOrcamentosAjax?situacao=' + 3);
    //configurações do Request
    xhr.onload = function () {
        let tbody = document.querySelector("#tbodyOrcamentos");
        tbody.textContent = "";
        //se tudo ocorrer bem como proceder
        if (xhr.status === 200) {
            let orcamentos = xhr.responseText;
            //transforma a resposta de String com formato JSON para objeto JSON
            orcamentos = JSON.parse(orcamentos);

            for (let i = 0; i < orcamentos.length; i++) {
                let tr = document.createElement("tr");

                let tdId = document.createElement("td");
                tdId.textContent = orcamentos[i].id;
                tr.appendChild(tdId);

                let tdCliente = document.createElement("td");
                tdCliente.textContent = orcamentos[i].nomeCliente;
                tr.appendChild(tdCliente);

                let tdDataInsercao = document.createElement("td");
                tdDataInsercao.textContent = orcamentos[i].dataInsercao;
                tr.appendChild(tdDataInsercao);

                let tdSituacao = document.createElement("td");
                tdSituacao.textContent = orcamentos[i].estado;
                tr.appendChild(tdSituacao); 
                
                let tdFerramentas = document.createElement("td");
 
                let a = document.createElement("a");
                a.setAttribute("href","ControllerFactory?acao=GerenciarOrcamentoEntrega&id="+orcamentos[i].id);
                
                let imgVer = document.createElement("img");
                imgVer.setAttribute("src", "img/editar.png");
                imgVer.setAttribute("alt", "ver orcamento");
                imgVer.setAttribute("title", "ver orcamento");   
                a.appendChild(imgVer);       
                tdFerramentas.appendChild(a);
                tr.appendChild(tdFerramentas);
                tbody.appendChild(tr);
 
            }
        } else {
            alert('Request failed.  Returned status of ' + xhr.status);
        }
        console.log(xhr.responseText);
    };

    //executa o request    
    xhr.send();
};


const createRequisitionAjax = (metodo, url, strParams) => {
    let xhr = new XMLHttpRequest();
    xhr.open(metodo, url + '?situacao=' + strParams);
    let retorno = "";
    xhr.onload = function () {
        //se tudo ocorrer bem como proceder
        if (xhr.status === 200) {
            listar(); 
            retorno = "xhr.responseText";
           
        } else {
            alert('Request failed.  Returned status of ' + xhr.status);
            console.log(xhr.status);
        }
    };  
   //executa o request   
   xhr.send();
   return retorno;
   
};
 

 listar();
