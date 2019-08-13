
let search = location.search.toString();
let id = search.split('=');
id = id[1];
let link;
switch(id){  
    
    case "1": link = 'ListarOrcamentosAprovados.js'; break; 
    case "2": link = 'ListarOrcamentosProducao.js'; break; 
    case "3": link = 'ListarOrcamentosEntrega.js'; break; 
    case "4": link = 'ListarOrcamentosFinalizados.js'; break; 
    default: link = 'ListarOrcamentosAprovados.js';

}

document.writeln("<script type='text/javascript' src='src/js/"+link+"'></script>");
