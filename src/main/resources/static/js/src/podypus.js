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
            addListeners();
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

/*Takes all fields with id ply from backend and adds listeners to all of them*/
function addListeners() {
    var elements = document.querySelectorAll("[id='ply']");
    for(var i = 0; i < elements.length; i++){
        elements[i].addEventListener("click", makePlayer);
        elements[i].href = elements[i].getAttribute("href");
    }
}

/*Creates the player container in html*/
/*
              <div class="playerContent">
                <div class="left"></div>
                <div class="right">
                    <div class="top">
                        <h2>demosong</h2>
                        <p>demo artist</p>
                    </div>
                </div>
                <div id="mediaPlayerBox" class="bottom">
                    <audio id="mediaPlayer" name="media" _autoplay="false" controls=""><source type="audio/mpeg"></audio>
                </div>
            </div>
*/
function makeAudio(url) {
    var container = document.createElement("div");
    container.setAttribute("class", "playerContent");

    var div = document.createElement("div");
    div.setAttribute("class", "left");
    container.appendChild(div);

    var right = document.createElement("div");
    right.setAttribute("class", "right");

    var top = document.createElement("div")
    top.setAttribute("class", "top");
    var h2 = document.createElement("h2");
    h2.innerHTML = "demosong";
    top.appendChild(h2);
    var p = document.createElement("p");
    p.innerHTML = "demotext";
    top.appendChild(p);
    right.appendChild(top);
    container.appendChild(right);

    var bottom = document.createElement("div");
    bottom.setAttribute("id", "mediaPlayerBox");
    bottom.setAttribute("class", "bottom");

    var audio = document.createElement("audio");
    audio.setAttribute("id", "mediaPlayer");
    audio.setAttribute("name", "media");
    audio.setAttribute("_autoplay", "false");
    audio.setAttribute("controls", "");
    audio.setAttribute("src", url);
    var src = document.createElement("src");
    src.setAttribute("type", "audio/mpeg");
    audio.appendChild(src);
    bottom.appendChild(audio);
    container.appendChild(bottom);

    document.body.appendChild(container);
}

/*Event makes the first element on the page clickable for the player*/
let makePlayer = (event) => {
    event.preventDefault();
    makeAudio(event.currentTarget.href);
}

/* Event Listeners */
document.addEventListener('DOMContentLoaded', function () {
    /* Search event listeners */
    document.getElementById("searchBtn").addEventListener("click", performSearch)
})