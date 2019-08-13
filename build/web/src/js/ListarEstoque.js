
let search = location.search.toString();
let id = search.split('=');
id = id[1];
let link;
switch(id){  
    
    case "1": link = 'ListarEstoqueGeral.js'; break; 
    case "2": link = 'ListarEstoqueAbaixo.js'; break; 
    case "3": link = 'ListarEstoqueRazoavel.js'; break; 
    default: link = 'ListarEstoqueGeral.js';

}



document.writeln("<script type='text/javascript' src='src/js/"+link+"'></script>");
