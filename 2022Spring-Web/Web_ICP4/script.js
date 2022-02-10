function getGithubInfo(user) {
    //1. Create an instance of XMLHttpRequest class and send a GET request using it.
    // The function should finally return the object(it now contains the response!)
    var xhttp = new XMLHttpRequest();
    const url = `https://api.github.com/users/${user}`;
    xhttp.open('GET', url, true)
    xhttp.onload = function() { 
        // Parse API data into JSON
        const data = JSON.parse(this.responseText);
        
        //if the response is successful show the user's details
        console.log(this.status);
        if (this.status == 200) {
            showUser(data);
            //else display suitable message
        } 
        else {
            noSuchUser(user);
        }
    }
    xhttp.send()
}

function showUser(user) {
    //2. set the contents of the h2 and the two div elements in the div '#profile' with the user content
    console.log(user);
    //Uses jQuery to change the information of the page with the information from the XMLHttpRequest.
    //Add the accounts name.
    $('h2','#profile').text(user.name);
    //Add the accounts avatar image.
    let image = user.avatar_url;
    let url = '<img src="'+image+'"style="width:100px"></img>'
    $('.avatar','#profile').html(url);
    //Add the accounts id and link to their Github page.
    let id = '<p>'+'User ID: '+user.id+'</p>'
    let link = '<a href='+user.html_url+'>'+user.html_url+'</a>'
    $('.information','#profile').html(id+link);
}

function noSuchUser(username) {
    //3. set the elements such that a suitable message is displayed
    $('h2','#profile').text("User: " + username + " not found");
}

$(document).ready(function () {
    $(document).on('keypress', '#username', function (e) {
        //check if the enter(i.e return) key is pressed
        if (e.which == 13) {
            //get what the user enters
            username = $(this).val();
            //reset the text typed in the input
            $(this).val("");
            //get the user's information and store the respsonse
            response = getGithubInfo(username);
        }
    })
});
