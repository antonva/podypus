console.log("Podypus is now mining for buttcoins...");




let subscribeToChannel = (event) => {
    event.preventDefault();
    let url =  { url: event.target.getAttribute("href") };
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        url: "subscribe",
        data : JSON.stringify(url),
        success: function(res) {
            console.log("SUCCESS")
            console.log(res)
        },
        error: function(res) {
            console.log("error")
            console.log(res)
        },
        done: function(res) {
            console.log("DONE")
            console.log(res)
        },
    })
}

let performSearch = (event) => {
    event.preventDefault();
    var queryElem = document.getElementById("searchTxt")
    var termobj = { term: queryElem.value }
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "html",
        url: "search",
        data : JSON.stringify(termobj),
        success: function(res) {
            console.log(res)
            document.getElementById("search-results").innerHTML = res;
            let subscribeBtns = document.getElementsByClassName("subscribeBtn")
            for (let i = 0; i < subscribeBtns.length; i++) {
                subscribeBtns.item(i).addEventListener("click", subscribeToChannel);
            }
        },
        error: function(err) {
            console.log("ERROR");
            console.log(err);
        },
        done: function(eve) {
            console.log("DONE");
        }

    })

    /* Stop site navigation*/
}

/* Replaces the podypus-container div contents with a list of subscribed channels.
   All events on subscribed channels need to be registered here.
 */
let showSubscriptions = (event) => {
    event.preventDefault();
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "list",
        success: function(res) {
            document.getElementById("podypus-container").innerHTML = res;
            let subscribeBtns = document.getElementsByClassName("podypus-channel")
            for (let i = 0; i < subscribeBtns.length; i++) {
                subscribeBtns.item(i).addEventListener("click", showChannel);
            }
        },
        error: function(res) {
            console.log("ERROR")
        },
        done: function(res) {
            console.log("DONE")
        },
    })
}

/* Replaces the podypus-container div contents with a search view
   All events on the search view need to be registered here.
 */
let showSearch = (event) => {
    event.preventDefault();
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "search",
        success: function(res) {
            document.getElementById("podypus-container").innerHTML = res;
            document.getElementById("searchBtn").addEventListener("click", performSearch)
        },
        error: function(res) {
            console.log("ERROR")
        },
        done: function(res) {
            console.log("DONE")
        }
    })
}

let showChannel = (event) => {
    event.preventDefault();
    let channel_id = { "channel_id": event.target.dataset['channelId']};
    console.log(channel_id)
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(channel_id),
        dataType: "html",
        url: "channel",
        success: function(res) {
            console.log(res)
            document.getElementById("podypus-container").innerHTML = res;
        },
        error: function(res) {
            console.log("ERROR");
            console.log(res);
        },
        done: function(res) {}
    })
}

/* Event Listeners */
document.addEventListener('DOMContentLoaded', function () {
    /* Search event listeners */
    document.getElementById("nav-channels").addEventListener("click", showSubscriptions)
    document.getElementById("nav-search").addEventListener("click", showSearch)
})

