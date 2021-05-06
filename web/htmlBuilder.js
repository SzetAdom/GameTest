function setup(){
    const isAdmin = localStorage.getItem("isAdmin");
    if(isAdmin !== null){
        if(isAdmin == "true"){
            callAdminPage(); 
        }else{
            callUserPage();
        }
    }
    getJoke();
}

//DONE, gets joke on page load
function getJoke(){
    //clearLocalStorage(); Not sure why it was here
    var xmlhttp = new XMLHttpRequest();
    var url = "https://official-joke-api.appspot.com/jokes/programming/random";
    
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
    
    xmlhttp.onreadystatechange = function(){
        if(this.readyState === 4 && this.status === 200){
            var myArr = JSON.parse(this.responseText);
            //console.log(myArr);
            document.getElementById("joke").innerHTML += 
                    "<b>Daily joke: </b>" + 
                    myArr[0].setup + " " + myArr[0].punchline;
        }
    };
}

//DONE
function clearContent(){    
    var header = document.getElementsByClassName("header")[0];
    var body = document.getElementsByTagName("body")[0];
    body.innerHTML = "<header class=\"header\">" + header.innerHTML + "</header>";
}
//DONE
function logOut(){
    clearContent();
    setupLogin();
    clearLocalStorage();
}

//DONE
function setupLogin(){
    document.getElementsByTagName("body")[0].innerHTML += `
        <div id="login">
            
            <div>
                <h1>Rate our games</h1>
                <h4>Your opinion matters to us</h4>
            </div>
            <div class="login-input">
                <label>Login code: </label>
                <input id="passIN" type="text" name="pass" value="" />  
                <br>
                <input type="button" value="Bejelentkezés" name="submitPass" onclick="login()" />
            </div>
        </div>`;
}

//DONE
function setupAdminAside(){
    document.getElementsByTagName("body")[0].innerHTML += `
        <aside id="adminAside">
            <ul>
                <li>
                    <input type="button" value="Choose game" name="chooseGame" onclick="chooseGame(0)" />   
                </li>
                <li>
                    <input type="button" value="Add new game" name="addNewGame" onclick="newGame()" />
                </li>
                <li>
                    <input type="button" value="Choose Tester" name="chooseTester" onclick="chooseTester(0)" />
                </li>
                <li>
                    <input type="button" value="Add new Tester/Admin" name="addNewTester" onclick="newTester()" />
                </li>
                <li>
                    <input type="button" value="Check charts" name="checkCharts" onclick="checkCharts()" />
                </li>
                <li>
                    <input type="button" value="Add Progress" name="addProgress" onclick="addProgress()" />   
                </li>
                <li>
                    <input type="button" value="Check progress" name="checkProgress" onclick="checkProgress()" />
                </li>
                <li>
                    <input type="button" value="Add Review" name="addReview" onclick="listAllGame(0)" />
                </li>
                <li>
                    <input type="button" value="Log out" name="logOut" onclick="logOut()" />
                </li>
            </ul>
        </aside>`;
        
}


//TODO: fill it up with data, once model+controller ready
function chooseGame(countIn){
    if(countIn < 0) var count = 0;
    else var count = countIn;
    var max = 0;
    clearContent();
    setupAdminAside();
    var request = {"task" : "getAllGame"};
    $.ajax({
        url:"GameController",
        type:"POST",
        data: request,
        success: function(response){
            max = response.result.length;
            if(count > response.result.length - 10) count = response.result.length-10;
            document.getElementsByTagName("body")[0].innerHTML += `
                <main class="adminMain">            
                    <div id="chooseGame" count="`+count+`" max="`+max+`"   >
                        <h2>Choose a game to check out its global statistics</h2>
                        <table>
                            <thead>
                                <tr>
                                    <td>Title</td>
                                    <td>Developer</td>
                                    <td>Release Date</td>
                                    <td>Price</td>
                                </tr>                        
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                        <input type="button" id="last" onclick="chooseGame(`+(count-10)+`)" value="Previous 10"/>
                        <input type="button" id="next" onclick="chooseGame(`+(count+10)+`)" value="Next 10"/>
                    </div>
                </main>`;
            for(var i = count; i<count + 10; i++){
                document.getElementsByTagName("tbody")[0].innerHTML += 
                `<tr id="`+response.result[i].gameId+`" onclick="choosenGame(`+response.result[i].gameId+`)">
                    <td>` + response.result[i].name + `</td>
                    <td>` + response.result[i].dev + `</td>
                    <td>` + response.result[i].releaseDate + `</td>
                    <td>` + response.result[i].price + `</td>
                </tr>`;
            }
        },
        error: function(response){
            alert("Problem with the data processing");
            console.log(response);
        }
    });
}


//TODO: fill it with data
function chooseTester(countIn){
    if(countIn < 0) var count = 0;
    else var count = countIn;
    var max = 0;
    clearContent();
    setupAdminAside();
    var request = {"task" : "getAllUser"}
    $.ajax({
        url:"UserController",
        type:"POST",
        data: request,
        success: function(response){
            if(count + 75 > response.result.length) count = response.result.length - 60;
            document.getElementsByTagName("body")[0].innerHTML += `
        <main class="adminMain">
            <div id="chooseTester">
                <h2>Choose a tester to check out his/her overall statistics</h2>
                <table>
                    <tbody>
                        
                    </tbody>
                </table>                
            </div> 
            <input type="button" id="last" onclick="chooseTester(`+(count-60)+`)" value="Previous 60"/>
            <input type="button" id="next" onclick="chooseTester(`+(count+60)+`)" value="Next 60"/>
        </main>
        `;
        for(var i = 0; i < 12; i++){ 
            document.getElementsByTagName("tbody")[0].innerHTML += "<tr></tr>";
            for(var j = 0; j < 5;j++){
                document.getElementsByTagName("tr")[i].innerHTML 
                += `<td id="`+response.result[count].userId+`" class="`+response.result[count].isAdmin+`" onclick="choosenTester(`+response.result[count].userId+`)">`+response.result[count].username+`</td>`;
                count++;
            }
        }
            
        },
        error: function(response){
            alert("Problem with the data processing");
            console.log(response);
        }
    });
}

function choosenTester(idIN){
    clearContent();
    setupAdminAside();
    document.getElementsByTagName("body")[0].innerHTML += `
        <main class="adminMain">
            <div id="choosenTester">
                <div id="choosenLeft">
                    <h2 id="userName"></h2>
                    <h4 id="userKey"></h4>
                    <p id="userID"></p>
                    <p id="userBirth"></p>
                    <p id="userGender"></p>
                    <p id="userAdmin"></p>
                    <p>All achievements</p>
                    <ul id="gameList">
                        
                    </ul>
                    <p>All reviews</p>
                    <ul id="reviewList">
                    </ul>
                </div>
                <div id="choosenRight">
                    <input type="button" id="changeAdmin" value="Change admin property" onclick="changeAdmin(`+idIN+`)"/>
                    <input type="button" id="deleteUser" value="Delete user" onclick="deleteUser(`+idIN+`)"/>
                </div>
            </div>
        </main>
`;
    var request = {"task" : "getUser", "id" : idIN};
    $.ajax({
        url:"UserController",
        type:"POST",
        data: request,
        success: function(response){
            document.getElementById("userName").innerHTML = "Username: "+response.username;
            document.getElementById("userKey").innerHTML = "Key: "+response.key;
            document.getElementById("userID").innerHTML = "ID: "+response.id;
            document.getElementById("userBirth").innerHTML = "Born at: "+response.birthDate;
            if(response.gender === "genderId: 1, nameOfGender: Male") document.getElementById("userGender").innerHTML = "Sex: Male";
            else if(response.gender === "genderId: 2, nameOfGender: Female") document.getElementById("userGender").innerHTML = "Sex: Female";
            else document.getElementById("userGender").innerHTML = "Sex: Other";
            if(response.isAdmin === "false") document.getElementById("userAdmin").innerHTML = "Type: <b>Tester</b>";
            else document.getElementById("userAdmin").innerHTML = "<b>Admin</b>";
        },
        error: function(response){            
            alert("Problem with the data processing");
            console.log(response);
        }
    });
    var request = {"task" : "userListGames", "id" : idIN};
    $.ajax({
        url:"UserController",
        type:"POST",
        data: request,
        success: function(response){
            //console.log(response);
            for(var i = 0; i < response.result.length; i++){
                document.getElementById("gameList").innerHTML += "<li>"+response.result[i].name+'</li><ul id="'+response.result[i].id+'"></ul>';                
                request = {"task" : "getAllAchievementByUserGame", "id" : idIN ,"gameId" : response.result[i].id};
                $.ajax({
                    url:"AchievementController",
                    type:"POST",
                    data: request,
                    success: function(res){               
                        for(var j = 0; j<res.result.length; j++){
                            document.getElementById(res.result[j].gameId.gameId).innerHTML += "<li>"+res.result[j].descriptionOfAchievment+"</li>";
                        }
                    },
                    error: function(res){            
                        alert("Problem with the data processing");
                        console.log(res);
                    }
                });
                
            }
        },
        error: function(response){            
            alert("Problem with the data processing");
            console.log(response);
        }
    });
    var request = {"task" : "reviewListbyUser", "id" : idIN};
    $.ajax({
        url:"ReviewController",
        type:"POST",
        data: request,
        success: function(response){
            for(var i=0; i<response.result.length;i++){
                document.getElementById("reviewList").innerHTML += "<li>On " + response.result[i].gameName 
                    + ": " + response.result[i].comment 
                    + ". (" + response.result[i].score + ")</li>";
            }
        },
        error: function(response){            
            alert("Problem with the data processing");
            console.log(response);
        }
    });
}

function choosenGame(idIN){
    clearContent();
    setupAdminAside();
    document.getElementsByTagName("body")[0].innerHTML += `
        <main class="adminMain">
            <div id="choosenGame">
                <div id="choosenLeft">
                    <h2 id="gameName"></h2>
                    <p id="gameID"></p>
                    <p id="releaseDate"></p>
                    <p id="descGame"></p>
                    <p id="devGame"></p>
                    <p id="price"></p>
                    <p>All achievements</p>
                    <ul id="achiList">                        
                    </ul>
                    <p>All reviews</p>
                    <ul id="reviewList">
                    </ul>
                </div>
                <div id="choosenRight">
                    <input type="button" id="updateGame" value="Update the elements of the game" onclick="updateGame(`+idIN+`)"/>
                    <input type="button" id="addAchi" value="Add a new achievement" onclick="setupAddAchievement(`+idIN+`)"/>
                    <input type="button" id="deleteGame" value="Delete game" onclick="deleteGame(`+idIN+`)"/>
                </div>
            </div>
        </main>
`;
    var request = {"task" : "getGame", "id" : idIN};
    $.ajax({
        url:"GameController",
        type:"POST",
        data: request,
        success: function(response){
            response = response.result.split("[",2);
            response = response[1].split(",",8);
            //console.log(response);
            document.getElementById("gameName").innerHTML = response[1];
            document.getElementById("gameID").innerHTML = "<b>ID: </b>"+response[0];
            document.getElementById("releaseDate").innerHTML = "Release date: "+response[4];
            document.getElementById("descGame").innerHTML = "Description: "+response[2];
            document.getElementById("devGame").innerHTML = "Dev: "+response[3];
            document.getElementById("price").innerHTML = "Price: "+response[5];
        },
        error: function(response){            
            alert("Problem with the data processing");
            console.log(response);
        }
    });
    request = {"task" : "getAllAchievementByGame", "id" : idIN};
    $.ajax({
        url:"AchievementController",
        type:"POST",
        data: request,
        success: function(response){
            for(var i=0; i < response.result.length; i++){
                document.getElementById("achiList").innerHTML += "<li>"+response.result[i].descriptionOfAchievment + " ( " + response.result[i].achievementType.nameOfAchievementType + " )</li>";
            }
        },
        error: function(response){            
            alert("Problem with the data processing");
            console.log(response);
        }
    });
    request = {"task" : "reviewListbyGame", "id" : idIN};
    $.ajax({
        url:"ReviewController",
        type:"POST",
        data: request,
        success: function(response){
            for(var i=0; i < response.result.length; i++){
                document.getElementById("reviewList").innerHTML += "<li>"+response.result[i].comment 
                    +" ( "+response.result[i].score+" ) by "+response.result[i].username +"</li>";
                
                 
            }
        },
        error: function(response){            
            alert("Problem with the data processing");
            console.log(response);
        }
    });
}
function updateGame(idIN){
    clearContent();
    setupAdminAside();
    document.getElementsByTagName("body")[0].innerHTML += `
        <main class='adminMain'>
            <div id="updateGame">
                <h2>Register the new datas to the database</h2>
                <ul>
                    <li>
                        <label>Name: </label>
                        <input id="nameIN" type="text" name="name" value="" />
                        <br/>
                    </li>
                    <li>
                        <label>Description: </label>
                        <br/>
                        <textarea id="descIN"  name="desc" placeholder="A small description of the game"/></textarea>
                    
                    </li>
                    <li>
                        <label id="devLBL">Developer: </label>
                        <input id="devIN" type="text" name="dev" value="" />
                    </li>
                    <li>
                        <label>Release date: </label>
                        <input id="dateIN" type="date" name="date" value="" />
                    </li>
                    <li>
                        <label>Price: </label>
                        <input id="priceIN" type="number" name="price" value="60" min="0" max="120"/>
                    </li>
                    <li>
                        <input type="button" value="Register the new game" name="updateGame" onclick="updateTheGame(`+idIN+`)" />
                    </li>
                </ul>
            </div>
        </main>
    `;
    var request = {"task" : "getGame", "id" : idIN};
    $.ajax({
        url:"GameController",
        type:"POST",
        data: request,
        success: function(response){
            response = response.result.split("[",2);
            response = response[1].split(", ",8);
            console.log(response);
            document.getElementById("nameIN").value = response[1];
            document.getElementById("dateIN").value = response[4];
            document.getElementById("descIN").value = response[2];
            document.getElementById("devIN").value = response[3];
            document.getElementById("priceIN").value = response[5];
        },
        error: function(response){            
            alert("Problem with the data processing");
            console.log(response);
        }
    });
}
function updateTheGame(idIN){
    var request = {"task" : "updateGame", "gameId" : idIN, "description" : document.getElementById("descIN").value,
        "dev" : document.getElementById("devIN").value, "releaseDate" : document.getElementById("dateIN").value,
        "price" : document.getElementById("priceIN").value, "name" : document.getElementById("nameIN").value};
    console.log(request);
    $.ajax({
        url:"GameController",
        type:"POST",
        data: request,
        success: function(response){
          if(response.result === true){
                alert("Succesful updating");
                chooseGame(0);
          }
          else{
              alert("Unsuccesful updating");
                chooseGame(0);
          }
        },
        error: function(response){            
            alert("Problem with the data processing");
            console.log(response);
        }
    });
}
function deleteGame(idIN){
    var request = {"task" : "setGameInactive", "id" : idIN};
    $.ajax({
        url:"GameController",
        type:"POST",
        data: request,
        success: function(response){
            chooseGame(0);
            alert("Succesfully deleted the game");
        },
        error: function(response){            
            alert("Problem with the data processing");
            console.log(response);
        }
    });
}
function setupAddAchievement(idIN){    
    clearContent();
    setupAdminAside();
    document.getElementsByTagName("body")[0].innerHTML += `
        <main id="addAchiMain">
        <h2>Add a new achievement to the game</h2>
        <ul id="addAchiUL">  
            
            <li>
                <label>Description: </label>
                <br/>
                <textarea id="descIN"  name="desc" placeholder="A small description of the achievement"/></textarea>

            </li>
            <li>
                <label>Prerequisite: </label>
                <input id="preIN" class="numIN" type="number" name="preIN" value="1" />
            </li>
            <li>
                <label>Type: </label>
                <input id="typeIN" class="numIN" type="number" name="typeIN" value="1" min="1" max="5"/>
            </li>
            <li>
                <input id="newAchiBTN" type="button" value="Register the new achievement" name="newAchi" onclick="addAchievement(`+idIN+`)" />
            </li>
        </ul>
        </main>
`;
    
}
function addAchievement(idIN){
    if(document.getElementById("typeIN").value < 1 || document.getElementById("typeIN").value > 5){
        alert("Wrong input");
        return;
    }else{
        var desc = document.getElementById("descIN").value;
        var typeId = document.getElementById("typeIN").value;
        var preIN = document.getElementById("preIN").value;
        if(preIN <= 0) preIN = null;
        var request = {"task" : "createAchievement", "gameId" : idIN, "prerequisite" : preIN, "description" : desc, "typeId" : typeId};
        console.log(request);
        $.ajax({
            url:"AchievementController",
            type:"POST",
            data: request,
            success: function(response){
                if(response.result === true) alert("Succesfully added the new achievement");
                else alert("Unsuccesful achievement registering");
                chooseGame(0);
            },
            error: function(response){
                alert("Problem with the data processing");
                console.log(response);
            }
        });
    }
}
function changeAdmin(idIN){
    var isAdmin = document.getElementById("userAdmin").innerHTML;
    if(isAdmin === "<b>Admin</b>"){
        var request = {"task" : "disableAdmin", "id" : idIN};
        $.ajax({
            url:"UserController",
            type:"POST",
            data: request,
            success: function(response){
                if(response.result === "true") alert("Succesfully promoted to admin");
                else alert("Succesfully demoted to tester");
                choosenTester(idIN);
            },
            error: function(response){
                alert("Problem with the data processing");
                console.log(response);
            }
        });
    }
    else{
        var request = {"task" : "enableAdmin", "id" : idIN};
        $.ajax({
            url:"UserController",
            type:"POST",
            data: request,
            success: function(response){
                if(response.result === "true") alert("Succesfully demoted to tester");
                else alert("Succesfully promoted to admin");
                choosenTester(idIN);
            },
            error: function(response){
                alert("Problem with the data processing");
                console.log(response);
            }
        });
    }
}

function deleteUser(idIN){
    var request = {"task" : "setUserInactive", "id" : idIN};
    $.ajax({
        url:"UserController",
        type:"POST",
        data: request,
        success: function(response){
            if(response.result === true) alert("Succesfully deleted user");
            else alert("Could not delete the user");
            chooseTester(0);
        },
        error: function(response){
            alert("Problem with the data processing");
            console.log(response);
        }
    });
}

//TODO: connect to db
function newTester(){
    clearContent();
    setupAdminAside();
    document.getElementsByTagName("body")[0].innerHTML += `
        <main class="adminMain">
            <div id="newUser">
                <h2>Register new Tester or create a new Admin</h2>
                <ul>
                    <li>
                        <input type="button" value="Create new user" name="newUser" onclick="newUser(false)" />
                    </li>
                    <li>
                        <input type="button" value="Create new admin" name="newAdmin" onclick="newUser(true)" />
                    </li>
                </ul>
            </div>
        </main>
`;
}
//TODO: connect to db
function newGame(){
    clearContent();
    setupAdminAside();
    document.getElementsByTagName("body")[0].innerHTML += `
        <main class='adminMain'>
            <div id="newGame">
                <h2>Register a new game to the database</h2>
                <ul>
                    <li>
                        <label>Name: </label>
                        <input id="nameIN" type="text" name="name" value="" />
                        <br/>
                    </li>
                    <li>
                        <label>Description: </label>
                        <br/>
                        <textarea id="descIN"  name="desc" placeholder="A small description of the game"/></textarea>
                    
                    </li>
                    <li>
                        <label id="devLBL">Developer: </label>
                        <input id="devIN" type="text" name="dev" value="" />
                    </li>
                    <li>
                        <label>Release date: </label>
                        <input id="dateIN" type="date" name="date" value="" />
                    </li>
                    <li>
                        <label>Price: </label>
                        <input id="priceIN" type="number" name="price" value="60" min="0" max="120"/>
                    </li>
                    <li>
                        <input type="button" value="Register the new game" name="newGame" onclick="addGame()" />
                    </li>
                </ul>
            </div>
        </main>
`;
}

function checkCharts(){
    clearContent();
    setupAdminAside();
    const body = document.querySelector("body");
    
    const main = document.createElement("main");
    main.id = "adminChartsMain";
    body.append(main);
    
    const div = document.createElement("div");
    div.id = "charts";
    main.append(div);
    
    const title = document.createElement("h2");
    title.textContent = "Charts";
    div.append(title);
    
    const table = document.createElement("table");
    div.append(table);
    const tr1 = document.createElement("tr");
    const td1 = document.createElement("td");
    const td2 = document.createElement("td");
    tr1.append(td1, td2);
    const tr2 = document.createElement("tr");
    const td3 = document.createElement("td");
    const td4 = document.createElement("td");
    tr2.append(td3, td4);
    table.append(tr1,tr2);
    
    const plot1 = document.createElement("div");
    plot1.id = "plot1";
    td1.append(plot1);
    const plot2 = document.createElement("div");
    plot2.id = "plot2";
    td2.append(plot2);
    const plot3 = document.createElement("div");
    plot3.id = "plot3";
    td3.append(plot3);
    const plot4 = document.createElement("div");
    plot4.id = "plot4";
    td4.append(plot4);

    getScoreDisrubution();
    getReviewsOverTime();
    getTestersOverTime();
    getGenderDistribution();
}
function makeScoreDisrubutionPlot(response){
    const data = [{
        x: response.score,
        y: response.numberOf,
        type: 'bar'
      }];
    const layout = {
        width: 600,
        height: 350,
        margin: 0,
        title: "Rating score distribution"
    };
    Plotly.newPlot('plot1', data, layout);
}
function makeReviewsOverTimePlot(response){
    const data = [{
        x: [response.year, response.month].reduce((a, b) => a.map((v, i) => v + '-' + b[i])),
        y: cumSum(response.numberOf),
        type: 'line'
      }];
    const layout = {
        width: 600,
        height: 350,
        margin: 0,
        title: "Total review count over time"
             
    };
    Plotly.newPlot('plot2', data, layout);
}

function makeTestersOverTimePlot(response){
    const data = [{
        x: [response.year, response.month].reduce((a, b) => a.map((v, i) => v + '-' + b[i])),
        y: cumSum(response.numberOf),
        type: 'line'
      }];
    const layout = {
        width: 600,
        height: 350,
        margin: 0,
        title: "Total user count over time"
             
    };
    Plotly.newPlot('plot3', data, layout);
}

function makeGenderDisrubutionPlot(response){
    const data = [{
        x: response.gender,
        y: response.numberOf,
        type: 'bar'
      }];
    const layout = {
        width: 600,
        height: 350,
        margin: 0,
        title: "Gender distribution"
    };
    Plotly.newPlot('plot4', data, layout);
}

function setupUserAside(){
    if(localStorage.getItem("isAdmin") === "true"){
        setupAdminAside();
        return;
    }
    document.getElementsByTagName("body")[0].innerHTML += `
        <aside id="userAside">
            <ul>
                <li>
                    <input type="button" value="Add Progress" name="addProgress" onclick="addProgress()" />   
                </li>
                <li>
                    <input type="button" value="Check progress" name="checkProgress" onclick="checkProgress()" />
                </li>
                <li>
                    <input type="button" value="Add Review" name="addReview" onclick="listAllGame(0)" />
                </li>
                <li>
                    <input type="button" value="Log out" name="logOut" onclick="logOut()" />
                </li>
            </ul>
        </aside>`;
        
}

function addProgress(idIN){
    clearContent();
    setupUserAside();
    var request = {"task" : "getAllGame"};
    $.ajax({
        url:"GameController",
        type:"POST",
        data: request,
        success: function(response){
           // max = response.result.length;
            //if(count > response.result.length - 10) count = response.result.length-10;
            document.getElementsByTagName("body")[0].innerHTML += `
         <main class='adminMain'>
            <div id="newGame">
                <h2>Add progress to the desired game</h2>
                <ul>
                    <li>
                        <label>Game: </label> 
                        <select name="games" id="games">
                        <option disabled selected value> -- select an option -- </option>
                        
                        </select>
                        <br/>
                    </li>
                    <li>
                        <input type="button" value="Select" name="SelectGame" onclick="loadAchievementsForGame()" />
                    </li>
                    
                    <li>
                       <label class="achievementsAdd" >Achievement: </label> 
                        <select name="Achievements" class="achievementsAdd", id="achievementsOfGame">
                        <option disabled selected value> -- select an option -- </option>
                        </select>
                        <br/>
                    </li>
                    <li>
                        <input type="button" value="Add" name="AddGame" onclick="saveAchievement()" class="achievementsAdd"/>
                    </li>
                    
                    <li>

                        <label class="progressAdd" id="devLBL">Minutes played: </label>
                        <input id="minutesIN" type="number" min="0" name="Minutes played" value="" class="progressAdd"/>
                        <br/>
                    </li>
                    <li>
                        <input type="button" value="Add" name="AddGame" onclick="saveProgress()" class="progressAdd"/>
                    </li>
                    
                </ul>
            </div>
        </main>
`;
            for(var i = 0; i<response.result.length; i++){
                document.getElementsByTagName("select")[0].innerHTML += 
                `<option value="`+response.result[i].gameId+`">`+response.result[i].name+`</option>
                
                `;
            }
        },
        error: function(response){
            alert("Problem with the data processing");
            console.log(response);
        }
    }); 
}

function checkProgress(){
    clearContent();
    setupUserAside();
    
    document.getElementsByTagName("body")[0].innerHTML += `
        <main class="userMain">

            <div id="chooseGame">
                <h2>This is your progress history so far:</h2>
                <table>
                    <thead>
                        <tr>
                            <td>Game</td>
                            <td>username</td>
                            <td>First played</td>
                            <td>Last played</td>
                            <td>Total minutes</td>
                        </tr>                        
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>

        </main>
`;  var request = {"task" : "getAllStatisticsByUser", "id" : localStorage.getItem("id")};
    $.ajax({        
        url:"StatisticsController",
        type:"POST",
        data: request,
        success: function(response){
            for(var i=0; i<response.result.length;i++){
                var result = response.result[i];
                document.getElementsByTagName("tbody")[0].innerHTML += `
                    <tr id="progressID"">
                        <td>`+result[1]+`</td>
                        <td>`+result[2]+`</td>
                        <td>`+result[3]+`</td>
                        <td>`+result[4]+`</td>
                        <td>`+result[5]+`</td>
                    </tr>
                `;
            }
        },
        error: function(response){
            alert("Problem with the data processing");
            console.log(response);
        }
    });
}
/*
function listProgress(idIN){
    
    clearContent();
    setupAdminAside();
    var max=0;
    var request = {"task" : "statisticsListbyUser", "id":idIN};
    $.ajax({
        url:"StatisticsController",
        type:"POST",
        data: request,
        success: function(response){
            max = response.result.length;
            if(count > response.result.length - 10) count = response.result.length-10;
            document.getElementsByTagName("body")[0].innerHTML += `
                <main class="adminMain">            
                    <div id="chooseGame" count="`+count+`" max="`+max+`"   >
                        <h2>Choose a game to check out its global statistics</h2>
                        <table>
                            <thead>
                                <tr>
                                    <td>Title</td>
                                    <td>Developer</td>
                                    <td>Release Date</td>
                                    <td>Price</td>
                                </tr>                        
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                        <input type="button" id="last" onclick="chooseGame(`+(count-10)+`)" value="Previous 10"/>
                        <input type="button" id="next" onclick="chooseGame(`+(count+10)+`)" value="Next 10"/>
                    </div>
                </main>`;
            for(var i = count; i<count + 10; i++){
                document.getElementsByTagName("tbody")[0].innerHTML += 
                `<tr id="" onclick="">
                    <td>` + response.result[i].gameName + `</td>
                    <td>` + response.result[i].userName + `</td>
                    <td>` + response.result[i].firstPlayed + `</td>
                    <td>` + response.result[i].lastPlayed + `</td>
                    <td>` + response.result[i].minutes + `</td>
                </tr>`;
            }
        },
        error: function(response){
            alert("Problem with the data processing");
            console.log(response);
        }
    });
}
*/
function listAllGame(countIn){
    if(countIn < 0) var count = 0;
    else var count = countIn;
    //var max = 0;
    clearContent();
    setupUserAside();
    var request = {"task" : "getAllGame"};
    $.ajax({
        url:"GameController",
        type:"POST",
        data: request,
        success: function(response){
           // max = response.result.length;
            //if(count > response.result.length - 10) count = response.result.length-10;
            document.getElementsByTagName("body")[0].innerHTML += `
                <main class='adminMain'>
            <div id="newGame">
                <h2>Add an ovearll review to the desired game</h2>
                <ul>
                    <li>
                        <label>Game: </label> 
                        <select name="gamesIN" id="gamesIN">
                        <option disabled selected value> -- select an option -- </option>
                        
                        </select>
                        <br/>
                    </li>
                    <li>
                        <input type="button" value="Select" name="SelectGame" onclick="showLabel()" />
                    </li>
                    
                    <li>
                       <label class="achievementsAdd">Review: </label> 
                        <select id="scoreIN" name="Review" class="achievementsAdd">
                        <option disabled selected value> -- select an option -- </option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        </select>
                        <br/>
                    </li>
                    <li>
                        <textarea id="commentIN" class="achievementsAdd" placeholder="Write a comment..."></textarea>
                    </li>
                    <li>
                        <input type="button" value="Add" name="AddGame" onclick="addReview()" class="achievementsAdd"/>
                    </li>
                    
                </ul>
            </div>
        </main>`;
            for(var i = count; i<response.result.length; i++){
                document.getElementsByTagName("select")[0].innerHTML += 
                `<option value="`+response.result[i].gameId+`">`+response.result[i].name+`</option>
                
                `;
            }
        },
        error: function(response){
            alert("Problem with the data processing");
            console.log(response);
        }
    });
}

function addReview(){
    if(document.getElementById("gamesIN") <= 0
        || document.getElementById("scoreIN").value <= 0
        || document.getElementById("commentIN").value.length < 3){
        alert("Not enough input");
    }
    else{
        var request = {"task" : "addReview", "gameId" : document.getElementById("gamesIN").value,"userId" : localStorage.getItem("id"), "score" : document.getElementById("scoreIN").value, "comment" : document.getElementById("commentIN").value};
        $.ajax({
            url:"ReviewController",
            type:"POST",
            data: request,
            success: function(response){
                alert("Succesful review registering");
                checkProgress();
            },
            error: function(response){
                alert("Problem with the data processing");
                console.log(response);
            }
        });
    }
}

