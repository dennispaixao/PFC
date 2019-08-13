let btn_finaliza = document.querySelectorAll("#finalizarItem");
let situacao = document.querySelectorAll("#st");
let id = document.querySelectorAll("#idItem");
let tdSit = document.querySelectorAll("#tdSit");
let btn_entrega = document.querySelector("#IniciarEntrega");
btn_entrega.style.display = "none";
let idOrc = document.querySelector("#idOrc").value;

let xhr = new XMLHttpRequest();

const atualiza_btn = (sit, i) => {
    switch (parseInt(sit)) {
        case 1:
            btn_finaliza[i].value = "finalizar";
            btn_finaliza[i].style.background = "green";
            tdSit[i].textContent = "pendente";
            break;
        case 2:
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
        if (parseInt(situacao[i].value) === 1) {
            completados = false;
        }
    }
    if (completados){ btn_entrega.style.display = "inline";}
    else{btn_entrega.style.display = "none";}
};

checkFinaliza();

btn_entrega.addEventListener("click",()=>{
      xhr.open('POST', 'LiberarEntregaOrcamentoAjax?idOrc='+idOrc);
      xhr.onload = function () {
            if (xhr.status === 200){
                alert(xhr.responseText);
              
            } else {
                alert("erro:" + xhr.status);
            }
        };
     xhr.send();
});