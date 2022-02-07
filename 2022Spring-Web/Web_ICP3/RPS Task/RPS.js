//This is a javascript script for our website.

//initializing veriabels for our buttons and the result screen.
let results = document.getElementById("results");
let rock = document.getElementById("rock");
let paper = document.getElementById("paper");
let scissors = document.getElementById("scissors");

//calling our clear function when page is open.
clear();

//This is a simple clear function that sets our result text blank.
function clear() {
    results.innerHTML = "";
}

//This function returns to us a random number accourding to the max value passed. It uses Math.floor() and Math.random() to give us our number.
function getRandomInt(max) {
    return Math.floor(Math.random() * max);
}

//This is an event listener for our rock button, once pressed it will call our game function, passing it 0 which represents rock.
rock.addEventListener('click', function () {
    game(0);
});

//This is an event listener for our paper button, once pressed it will call our game function, passing it 1 which represents paper.
paper.addEventListener('click', function () {
    game(1);
});

//This is an event listener for our scissors button, once pressed it will call our game function, passing it 2 which represents scissors.
scissors.addEventListener('click', function () {
    game(2);
});

//This is our game function, which takes in a number that represents which hand was chosen by the player.
//Once called, the function will get a random hand for the computer by calling our getRandomInt() function. 
//When the computers number is chosen, the function will then compair using if statements and come up with an outcome.
//Once an outcome is revealed it will be displayed onto the result screen.
function game(num){
    ai = getRandomInt(3);
    if(ai == 0){
        document.getElementById("computer").src ="Images/Rock.png";}
    else if (ai == 1){
        document.getElementById("computer").src ="Images/Paper.png";}
    else if (ai == 2){
        document.getElementById("computer").src ="Images/Scissors.png";}

    if(num == ai){
        results.innerHTML = "Draw!";
    }
    else if(num==0 && ai == 1){
        results.innerHTML = "You loss! Paper beats rock!";
    }
    else if(num==0 && ai == 2){
        results.innerHTML = "You Win! Rock beats scissors!";
    }
    else if(num==1 && ai == 0){
        results.innerHTML = "You Win! Paper beats rock!";
    }
    else if(num==1 && ai == 2){
        results.innerHTML = "You loss! Scissors beats paper!";
    }
    else if(num==2 && ai == 0){
        results.innerHTML = "You loss! Rock beats scissors!";
    }
    else if(num==2 && ai == 1){
        results.innerHTML = "You Win! Scissors beats paper!";
    }
}