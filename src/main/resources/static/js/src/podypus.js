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
    let queryElem = document.getElementById("searchTxt")
    let termobj = { term: queryElem.value }
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "html",
        url: "search",
        data : JSON.stringify(termobj),
        success: function(res) {
            $("searchresults").html(res);
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

/* Event Listeners */
document.addEventListener('DOMContentLoaded', function () {
    /* Search event listeners */
    document.getElementById("searchBtn").addEventListener("click", performSearch)
})

