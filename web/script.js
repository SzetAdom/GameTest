//login btn function
function login(){    
    var id = document.getElementById("passIN").value;
    var request = {"task" : "login", "key" : id};
    $.ajax({
        url:"UserController",
        type:"POST",
        data: request,
        success: function(response){
            if(response.result){
                alert(response.result);
            }
            else{
                if(response.isAdmin === "true"){
                    //alert("Admin vagy");                     
                    localStorage.setItem("id", response.id);
                    localStorage.setItem("isAdmin", response.isAdmin);
                    callAdminPage(); 
                }
                else{
                    //alert("Nem vagy admin");
                    localStorage.setItem("id", response.id);
                    localStorage.setItem("isAdmin", response.isAdmin);
                    callUserPage();
                }
            }
            //console.log(response);
        },
        error: function(response){
            clearLocalStorage();
            alert("Hiba történt az adatfeldolgozásban");
            console.log(response);
        }
    });
}

//DONE, load admin page after login
function callAdminPage(){
    clearContent();
    setupAdminAside();
    chooseGame();
}

function callUserPage(){
    //TODO 
    clearContent();
    setupUserAside();
    addProgress();
    
}

//DONE, clear login data, on load and after log out
function clearLocalStorage(){    
    localStorage.removeItem("id");
    localStorage.removeItem("isAdmin");
}
function showLabel(){
    
   var x = document.getElementsByClassName("achievementsAdd");
   var i;
    for (i = 0; i < x.length; i++) {
    x[i].style.display = "inline";
   
}
}