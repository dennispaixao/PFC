let btn_finaliza = document.querySelectorAll("#finalizarItem");
let situacao = document.querySelectorAll("#st");
let id = document.querySelectorAll("#idItem");
let tdSit = document.querySelectorAll("#tdSit");
let btn_fimOrc = document.querySelector("#FinalizarOrcamento");
btn_fimOrc.style.display = "none";
let idOrc = document.querySelector("#idOrc").value;

let xhr = new XMLHttpRequest();

const atualiza_btn = (sit, i) => {
    switch (parseInt(sit)) {
        case 3:
            btn_finaliza[i].value = "finalizar";
            btn_finaliza[i].style.background = "green";
            tdSit[i].textContent = "pendente";
            break;
        case 4:
            btn_finaliza[i].value = "desfazer";
            btn_finaliza[i].style.background = "red";
            tdSit[i].textContent = "finalizado";
    }
};

for (let i = 0; i < btn_finaliza.length; i++) {
    btn_finaliza[i].style.color = "white";
    atualiza_btn(situacao[i].value, i);
    btn_finaliza[i].addEventListener("click", () => {
    
        xhr.open('POST', 'AlterarStatusItemOrcamentoAjax?id=' + id[i].textContent);
        xhr.onload = function () {
            if (xhr.status === 200) {
                console.log(xhr.responseText);
                situacao[i].value = xhr.responseText;
                atualiza_btn(situacao[i].value, i);
                checkFinaliza();
            } else {
                alert("erro:" + xhr.status);
            }
        };
        xhr.send();
    });
}

const checkFinaliza = () => {
    let completados = true;
    for (let i = 0; i < situacao.length; i++) {
        if (parseInt(situacao[i].value) !== 4) {
            completados = false;
        }
    }
    if (completados){ btn_fimOrc.style.display = "inline";}
    else{btn_fimOrc.style.display = "none";}
};
checkFinaliza();

alert(situacao[0].value);

btn_fimOrc.addEventListener("click",()=>{
      xhr.open('POST', 'FinalizarOrcamentoAjax?idOrc='+idOrc);
      xhr.onload = function () {
            if (xhr.status === 200){
                if(xhr.responseText === "ok"){
                    alert(xhr.responseText);
                    window.location.href = "index.jsp";
                }else{
                    alert(xhr.responseText);
                    window.location.href = "ControllerFactory?acao=GerenciarPagamento&id="+idOrc;
                }
                 
            } else {
                alert("erro:" + xhr.status);
            }
        };
     xhr.send();
});
