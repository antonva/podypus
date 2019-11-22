console.log("Podypus is now mining for buttcoins...");





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
            document.getElementById("ply").addEventListener("click", makePlayer);
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
/*Event makes the first element on the page clickable for the player*/
let makePlayer = (event) => {
    event.preventDefault();
    var element = document.getElementById("ply");
    var href = element.getAttribute("href");

    var playerDom = document.createElement("div");
    playerDom.setAttribute("th:insert", "fragments/player.html :: player");
    document.body.appendChild(playerDom);
    location = location;
}

/* Event Listeners */
document.addEventListener('DOMContentLoaded', function () {
    /* Search event listeners */
    document.getElementById("searchBtn").addEventListener("click", performSearch)
})

