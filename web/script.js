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
                if(response.isAdmin === "true" && response.id != 0){
                    //alert("Admin vagy");                     
                    localStorage.setItem("id", response.id);
                    localStorage.setItem("isAdmin", response.isAdmin);
                    callAdminPage(); 
                }
                else{
                    if(response.isAdmin === "false" && response.id != 0){
                        //alert("Nem vagy admin");
                       localStorage.setItem("id", response.id);
                       localStorage.setItem("isAdmin", response.isAdmin);
                       callUserPage();
                    } else {
                        alert("Wrong key!");  
                    }   
                }
            }
            //console.log(response);
        },
        error: function(response){
            clearLocalStorage();
            alert("Problem with the data processing");
            console.log(response);
        }
    });
}

//DONE, load admin page after login
function callAdminPage(){
    clearContent();
    setupAdminAside();
    chooseGame(0);
}

function callUserPage(){
    //TODO 
    clearContent();
    setupUserAside();
    addProgress();
    
}
//PREPARE THE CONTROLLER FOR THIS
function newUser(isAdmin){
    var request = {"task" : "userCreate", "isAdmin" : isAdmin}; 
    $.ajax({
        url:"UserController",
        type:"POST",
        data: request,
        success: function(response){
            if(response.result !== "")alert("The key of the new user: " + response.result);
            else alert("Unsuccessful user creation");
        },
        error: function(response){
            alert("Problem with the data processing");
            console.log(response);
        }
    });
}

function addGame(){
    if(checkNewGameData()){
        var name = document.getElementById("nameIN").value;
        var desc = document.getElementById("descIN").value;
        var dev = document.getElementById("devIN").value;
        var date = document.getElementById("dateIN").value;
        var price = document.getElementById("priceIN").value;
        var request = {"task" : "gameCreate", "name" : name, "description" : desc, "dev" : dev, "releaseDate" : date, "price" : price};
        console.log(request);
        $.ajax({
            url:"GameController",
            type:"POST",
            data: request,
            success: function(response){
                if(response.result === true){
                    alert("New game has been added to the database");
                    clearContent();
                    chooseGame();
                }
                else alert("Unsuccesful game registering");
            },
            error: function(response){
                alert("Problem with the data processing");
                console.log(response);
            }
        });
    }
    else alert("The form isn't filled right");
}

function getScoreDisrubution(){
    const request = {"task" : "getScoreDistribution"};
    $.ajax({
            url:"ReviewController",
            type:"GET",
            data: request,
            success: function(response){
                makeScoreDisrubutionPlot(response.result);
            },
            error: function(response){
                response = {score: [1,2,3,4,5], numberOf: [9,7,25,42,18]};
                makeScoreDisrubutionPlot(response);
            }
        });
}
function getReviewsOverTime(){
    const request = {"task" : "getReviewsOverTime"};
    $.ajax({
            url:"ReviewController",
            type:"GET",
            data: request,
            success: function(response){
                makeReviewsOverTimePlot(response.result);
            },
            error: function(response){
                response = {year: [2020,2021,2021,2021,2021], month: [12,1,2,3,4], numberOf: [0,24,21,36,20]}
                makeReviewsOverTimePlot(response);
                //alert("Problem with database connection");
                //console.log(response);
            }
        });
}
function getTestersOverTime(){
    const request = {"task" : "getTestersOverTime"};
    $.ajax({
            url:"UserController",
            type:"GET",
            data: request,
            success: function(response){
                makeTestersOverTimePlot(response.result);
            },
            error: function(response){
                response = {year: [2021,2021,2021], month: [3,4,5], numberOf: [0,101,7]};
                makeTestersOverTimePlot(response);
            }
        });
}
function getGenderDistribution(){
    const request = {"task" : "getGenderDistribution"};
    $.ajax({
            url:"GenderController",
            type:"GET",
            data: request,
            success: function(response){
                makeGenderDisrubutionPlot(response.result);
            },
            error: function(response){
                alert("Database connection failed")
            }
        });
}

function cumSum(a) {
    let result = [a[0]];

    for(let i = 1; i < a.length; i++) {
      result[i] = result[i - 1] + a[i];
    }

    return result;
};
//DONE
function checkNewGameData(){
    if(
        document.getElementById("nameIN").value.length < 2
        || document.getElementById("descIN").value.length < 4
        || document.getElementById("devIN").value.length < 2
    )   return false;
    else return true;
}


//DONE, clear login data, on load and after log out
function clearLocalStorage(){    
    localStorage.removeItem("id");
    localStorage.removeItem("isAdmin");
}
function showLabel(){
    
    var x = document.getElementsByClassName("achievementsAdd");
    for (let i = 0; i < x.length; i++) {
        x[i].style.display = "inline";
    }
    x = document.getElementsByClassName("progressAdd");
    for (let i = 0; i < x.length; i++) {
        x[i].style.display = "inline";
    }
}
function loadAchievementsForGame(){
    const gameId = document.getElementsByTagName("select")[0].value
    const request = {"task" : "getAllAchievementByGame", "id": gameId};
    $.ajax({
        url:"AchievementController",
        type:"GET",
        data: request,
        success: function(response){
            const achievementsOfGame = document.querySelector("#achievementsOfGame")
            while (achievementsOfGame.childElementCount > 1) {
                achievementsOfGame.removeChild(achievementsOfGame.lastChild);
            }
            response.result.forEach(function(achievement){
                const select = document.createElement("option")
                select.value = achievement.achievementId
                select.textContent = achievement.descriptionOfAchievment
                achievementsOfGame.append(select)
            });
            showLabel();
        },
        error: function(response){
            alert("Database connection failed")
        }
    });
    
}
function saveAchievement(){
    const achievementsOfGame = document.querySelector("#achievementsOfGame")
    const achievementId = achievementsOfGame.value;
    const request = {"task" : "linkAchievementUser", "achiId": achievementId, "userId": localStorage.getItem("id")};
    $.ajax({
        url:"AchievementUserController",
        type:"POST",
        data: request,
        success: function(response){
            alert("Save successful")
        },
        error: function(response){
            alert("Database connection failed")
        }
    });
}
function saveProgress(){
    const minutesIN = document.querySelector("#minutesIN").value;
    const gameId = document.querySelector("#games").value;
    const request = {"task" : "addStatistics", "gameId": gameId, "userId": localStorage.getItem("id"), "minutes": minutesIN};
    $.ajax({
        url:"StatisticsController",
        type:"POST",
        data: request,
        success: function(response){
            alert("Save successful")
        },
        error: function(response){
            alert("Database connection failed")
        }
    });
}