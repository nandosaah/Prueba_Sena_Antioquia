var patron = new Array(2,4)
var patron2 = new Array(1,3,3,3,3)
function mascara(d,sep,pat,nums){
    if(d.valant != d.value){
        val = d.value
        largo = val.length
        val = val.split(sep)
        val2 = ''
        for(r=0;r<val.length;r++){
            val2 += val[r]	
        }
        if(nums){
            for(z=0;z<val2.length;z++){
                if(isNaN(val2.charAt(z))){
                    letra = new RegExp(val2.charAt(z),"g")
                    val2 = val2.replace(letra,"")
                }
            }
        }
        val = ''
        val3 = new Array()
        for(s=0; s<pat.length; s++){
            val3[s] = val2.substring(0,pat[s])
            val2 = val2.substr(pat[s])
        }
        for(q=0;q<val3.length; q++){
            if(q ==0){
                val = val3[q]
            }
            else{
                if(val3[q] != ""){
                    val += sep + val3[q]
                }
            }
        }
        d.value = val
        d.valant = val
    }
}

function disabledField(campoD, campoFile){
    val = campoFile.value
    if (campoFile.value.toString().length > 0){
        document.getElementById(campoD).disabled = false;
    }else{
        document.getElementById(campoD).disabled = true;
    }
}

function mostrarField(campoDisplay){
    document.getElementById(campoDisplay).style.display = 'block';
}


function focusField(){
    window.alert("entro al foco");
    var h = window.innerHeight;
    var w = window.innerHeight;
    window.scrollTo(w,h);
}

function consultarTipoFact(campoD){
    window.alert("entro a la funcion js "+campoD);
    document.getElementById(campoD).click();
    window.alert("paso por js "+campoD);
}

function consultarTipoFactura(campoD){
    var submitbtn =  document.getElementById(campoD);
    if (submitbtn){
        submitbtn.click();
    }
}
function desaparecerObj(id){
    obj=getElement(id);
    if(!obj) return;
		
    if(obj.style.display=='none') obj.style.display='';
    else obj.style.display='none';
    return false;
} // desaparecerObj