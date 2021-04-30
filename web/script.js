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
                    callAdminPage(response.id);  
                    localStorage.setItem("id", response.id);
                    localStorage.setItem("isAdmin", response.isAdmin);
                }
                else{
                    //alert("Nem vagy admin");
                    callUserPage(response.id);  
                    localStorage.setItem("id", response.id);
                    localStorage.setItem("isAdmin", response.isAdmin);
                }
            }
            //console.log(response);
        },
        error: function(response){
            alert("Hiba történt az adatfeldolgozásban");
            console.log(response);
        }
    });
}

function callAdminPage(id){
    //TODO
}

function callUserPage(id){
    //TODO 
}

//joke filler
function getJoke(){
    localStorage.removeItem("id");
    localStorage.removeItem("isAdmin");
    var xmlhttp = new XMLHttpRequest();
    var url = "https://official-joke-api.appspot.com/jokes/programming/random";
    
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
    
    xmlhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var myArr = JSON.parse(this.responseText);
            //console.log(myArr);
            document.getElementById("joke").innerHTML += 
                    "<b>Daily joke: </b>" + 
                    myArr[0].setup + " " + myArr[0].punchline;
        }
    };
}


