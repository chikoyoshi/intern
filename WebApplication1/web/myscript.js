/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function error(str){
    window.alert(str);
}

function check_delete() {
    var flag = 0;
    var buf_id = document.forms.delete.delete_id.value;
    var str_id = buf_id.match(/[0-9]{4}/g);
    
    if (buf_id == "" ){
        str = "未入力欄があります.";
        flag = 1;
    } else if (buf_id != str_id){
        str = "使用できない文字があります.";
        flag = 1;
    }     
    
    if (flag == 1){
        window.alert(str);
        return false;
    }
    
    if(window.confirm("ID:" + buf_id +'\nを消去します.')){
        return true;
    } else {
        return false;
    }    
    
}

function check_update() {
    var flag = 0;
    var str2 = "";
    var buf_id = document.forms.update.update_id.value;
    var buf_name = document.forms.update.update_name.value;
    var buf_tel = document.forms.update.update_tel.value;
    var str_id = buf_id.match(/[0-9]{4}/g);
    var str_name = buf_name.match(/[0-9a-zA-Z]{1,10}/g);
    var str_tel = buf_tel.match(/[0-9]{10,11}/g);
    
    if (buf_id == "" || buf_name == "" || buf_tel == ""){
        str = "未入力欄があります.";
        flag = 1;
    } 
    if (buf_id != str_id){
        str2 += "idの入力が不正です.\n";
        document.getElementById("update_id").style.backgroundColor = "red";
        flag = 2;
    } else {
        document.getElementById("update_id").style.backgroundColor = "white";
    }
    if (buf_name != str_name){
        str2 += "nameの入力が不正です.\n";
        document.getElementById("update_name").style.backgroundColor = "red";
        flag = 2;
    } else {
        document.getElementById("update_name").style.backgroundColor = "white";
    }
    if (buf_tel != str_tel){
        str2 += "telの入力が不正です.\n";
        document.getElementById("update_tel").style.backgroundColor = "red";
        flag = 2;
    } else {
        document.getElementById("update_tel").style.backgroundColor = "white";
    }  
    if (flag != 1) {
        str = "ID:" + buf_id + "  Name:" + buf_name + "   Tel:" + buf_tel + "\n を更新します.";
    }         
    
    if (flag == 1){
        window.alert(str);
        return false;
    } else if (flag == 2){
        window.alert(str2);
        str2 = "";
        return false;
    } else {
        if (window.confirm(str)){
            return true;
        } else {
            return false;
        }
    }
}

function check_entry() {
    var flag = 0;
    var err = 0;
    var str2 = "";
    var buf_id = document.forms.entry.new_id.value;
    var buf_name = document.forms.entry.new_name.value;
    var buf_tel = document.forms.entry.new_tel.value;
    var str_id = buf_id.match(/[0-9]{4}/g);
    var str_name = buf_name.match(/[0-9a-zA-Z]{1,10}/g);
    var str_tel = buf_tel.match(/[0-9]{10,11}/g);

    //未入力項目があるか
    if (buf_id == "" || buf_name == "" || buf_tel == ""){
        str = "未入力欄があります.";
        flag = 1;
    }
    if (buf_id != str_id){
        str2 += "idの入力が不正です.\n";
        document.getElementById("new_id").style.backgroundColor = "red";
        flag = 2;
    } else {
        document.getElementById("new_id").style.backgroundColor = "white";
    }
    if (buf_name != str_name){
        str2 += "nameの入力が不正です.\n";
        document.getElementById("new_name").style.backgroundColor = "red";
        flag = 2;
    } else {
        document.getElementById("new_name").style.backgroundColor = "white";
    }
    if (buf_tel != str_tel){
        str2 += "telの入力が不正です.\n";
        document.getElementById("new_tel").style.backgroundColor = "red";
        flag = 2;
    } else {
        document.getElementById("new_tel").style.backgroundColor = "white";
    }
    if (flag != 1) {
        str = "ID:" + buf_id + "  Name:" + buf_name + "   Tel:" + buf_tel + "\n を登録します.";
    }

    if (flag == 1){
        window.alert(str);
        return false;
    } else if (flag == 2) {
        window.alert(str2);
        str2 = "";
        return false;
    } else {
        if (window.confirm(str)){
            return true;
        }else{
            return false;
        }
    }
}