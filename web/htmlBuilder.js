function setup(){
    const isAdmin = localStorage.getItem("isAdmin");
    if(isAdmin != null){
        if(isAdmin){
            localStorage.setItem("isAdmin", true)
            callAdminPage(); 
        }else{
            localStorage.setItem("isAdmin", false)
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
                `<tr id="`+response.result[i].gameId+`" onclick="alert('TODO')">
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
                += `<td id="`+response.result[count].userId+`" class="`+response.result[count].isAdmin+`" onclick="alert(`+response.result[count].userId+`)">`+response.result[count].username+`</td>`;
                count++;
            }
        }
            
        },
        error: function(response){
            alert("Problem with the data processing");
            console.log(response);
        }
    });
    /*document.getElementsByTagName("body")[0].innerHTML += `
        <main class="adminMain">
            <div id="chooseTester">
                <h2>Choose a tester to check out his/her overall statistics</h2>
                <table>
                    <tr>
                        <td id="player1" onclick="alert('TODO')">Player1</td>
                        <td id="player2" onclick="alert('TODO')">Player2</td>
                        <td id="player3" onclick="alert('TODO')">Player3</td>
                        <td id="player4" onclick="alert('TODO')">Player4</td>
                    </tr>
                    <tr>
                        <td id="player5" onclick="alert('TODO')">Player5</td>
                        <td id="player6" onclick="alert('TODO')">Player6</td>
                    </tr>
                </table>                
            </div>            
        </main>
`;*/
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
    main.class = "adminMain";
    body.append(main);
    
    const div = document.createElement("div");
    div.id = "charts";
    main.append(div);
    
    const title = document.createElement("h2");
    title.textContent = "Charts";
    div.append(title);
    
    const plot1 = document.createElement("div");
    plot1.id = "plot1"
    div.append(plot1)
    const data = [{
        x: [1,2,3,4,5],
        y: [30,60,80,140,90],
        type: 'bar',
        
        
      }
    ];
    const layout = {
        height: "5%",
        width : "10%"
             
    }

    Plotly.newPlot('plot1', data, layout);
}


function setupUserAside(){
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
                    <input type="button" value="Log out" name="logOut" onclick="logOut()" />
                </li>
            </ul>
        </aside>`;
        
}

function addProgress(){
    clearContent();
    setupUserAside();
    document.getElementsByTagName("body")[0].innerHTML += `
         <main class='adminMain'>
            <div id="newGame">
                <h2>Add progress to the desired game</h2>
                <ul>
                    <li>
                        <label>Game: </label> 
                        <select name="games" id="games">
                        <option disabled selected value> -- select an option -- </option>
                        <option value="cargame2">Car Game 3</option>
                        <option value="cargame3">Car Game 4</option>
                        </select>
                        <br/>
                    </li>
                    <li>
                        <input type="button" value="Select" name="SelectGame" onclick="showLabel()" />
                    </li>
                    
                    <li>
                       <label class="achievementsAdd">Achievement: </label> 
                        <select name="Achievements" class="achievementsAdd">
                        <option disabled selected value> -- select an option -- </option>
                        <option value="nagyongyors">Naagyon gyors</option>
                        <option value="meggyorsabb">Méggyorsabb</option>
                        </select>
                        <br/>
                    </li>
                    <li>
                        <input type="button" value="Add" name="AddGame" onclick="alert('Itt majd elmentődik')" class="achievementsAdd"/>
                    </li>
                    
                </ul>
            </div>
        </main>
`;
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
                        <tr id="progressID"">
                            <td>Car game 3</td>
                            <td>Lajos</td>
                            <td>2021-04-11</td>
                            <td>2021-05-01</td>
                            <td>69</td>
                        </tr>  
                        <tr id="progressID" ">
                            <td>Car game 4</td>
                            <td>Lajos2</td>
                            <td>2020-11-31</td>
                            <td>2021-01-11</td>
                            <td>420</td>
                        </tr> 
                    </tbody>
                </table>
            </div>

        </main>
`;
}

