//função usada para realizar a busca e montar a tabela de itens de produtos a serem add
const listar = (busca) => {

    //instanciando objeto do XMLHttpRequest
    let xhr = new XMLHttpRequest();

    //caminho da servlet  e atributo
    xhr.open('GET', 'BuscarListaEstoqueAjax?busca=' + busca);

    //configurações do Request
    xhr.onload = function () {
        let tbody = document.querySelector("#tbodyOrcamentos");
        tbody.textContent = "";
        tbody.style.fontSize = "18px";
        //se tudo ocorrer bem como proceder
        if (xhr.status === 200) {

            let produtos = xhr.responseText;
            //transforma a resposta de String com formato JSON para objeto JSON
            produtos = JSON.parse(produtos);
            console.log(produtos);
            
            for (let i = 0; i < produtos.length; i++) {
                let tr = document.createElement("tr");
                
                if(produtos[i].qtEstoque < 0){
                  tr.style.background = "#ff6666";    
                }else if(produtos[i].qtEstoque >= produtos[i].estoqueRaz){
                    tr.style.background = "lightgreen";   
                    
                } 
                
                

                let idProd = produtos[i].id;

                let tdId = document.createElement("td");
                tdId.style.padding = "8px";
                tdId.textContent = idProd;
                tr.appendChild(tdId);

                let tdProduto = document.createElement("td");
                tdProduto.textContent = produtos[i].nome;
                tr.appendChild(tdProduto);

                let tdFornecedor = document.createElement("td");
                tdFornecedor.textContent = produtos[i].fornecedor;
                tr.appendChild(tdFornecedor);

                let tdQuantidade = document.createElement("td");
                tdQuantidade.style.textAlign = "center";
                let campoQtEstoque = document.createElement("input");
                campoQtEstoque.value = produtos[i].qtEstoque;

                campoQtEstoque.style.width = "60px";
                campoQtEstoque.style.fontSize = "20px";
                tdQuantidade.appendChild(campoQtEstoque);

                tr.appendChild(tdQuantidade);

                let tdRazoavel = document.createElement("td");
                let campoEstRaz = document.createElement("input");
                campoEstRaz.style.width = "60px";
                campoEstRaz.style.fontSize = "20px";
                campoEstRaz.value = produtos[i].estoqueRaz;
                tdRazoavel.appendChild(campoEstRaz);
                tr.appendChild(tdRazoavel);


                campoQtEstoque.addEventListener("change", () => {
                    let xhr = new XMLHttpRequest();
                    xhr.open("POST", 'AlterarQtEstoqueAjax?id='+idProd+"-"+ campoQtEstoque.value);
                    xhr.onload = function () {
                        //se tudo ocorrer bem como proceder
                        if (xhr.status === 200) {
                            listar("");
                            alert(xhr.responseText);
                        } else {
                            console.log('Request failed.  Returned status of ' + xhr.status);
                        }
                    };
                    xhr.send();
                });
                
                campoEstRaz.addEventListener("change", () => {
                    let xhr = new XMLHttpRequest();
                    xhr.open("POST", 'AlterarPadraoEstoqueAjax?id='+idProd+"-"+ campoEstRaz.value);
                    xhr.onload = function () {
                        //se tudo ocorrer bem como proceder
                        if (xhr.status === 200) {
                            listar("");
                            alert(xhr.responseText);
                        } else {
                            console.log('Request failed.  Returned status of ' + xhr.status);
                        }
                    };
                    xhr.send();
                });
                
                
                //insere item na lista
                tbody.appendChild(tr);

            }

        } else {
            alert('Request failed.  Returned status of ' + xhr.status);
        }

    };

    //executa o request    
    xhr.send();
};


let q = document.querySelector("#q");
q.addEventListener("keyup", (q) => {
    listar(q.target.value);
});




listar("");
