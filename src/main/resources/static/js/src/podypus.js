console.log("Podypus is now mining for buttcoins...");

/* Subscribe to the channel with the corresponding href */
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


/* Send a POST request to the podypus server */
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

/* Render channel contents incl. episode list */
let showChannel = (event) => {
    event.preventDefault();
    let channel_id = {"channel_id": event.target.dataset['channelId']};
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(channel_id),
        dataType: "html",
        url: "channel",
        success: function (res) {
            console.log(res)
            document.getElementById("podypus-container").innerHTML = res;
            addEpisodeListeners();
        },
        error: function (res) {
            console.log("ERROR");
            console.log(res);
        },
        done: function (res) {
        }
    })
}

/*Takes all fields with class name podypus-episode in the Index.html->fragments/Search.html and adds listeners*/
function addEpisodeListeners() {
    var elements = document.getElementsByClassName("podypus-episode");
    for(var i = 0; i < elements.length; i++){
        elements.item(i).addEventListener("click", makePlayer);
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
function makeAudio(url, title, episode_id, image_url) {
    var container = document.createElement("div");
    container.setAttribute("class", "playerContent");

    var div = document.createElement("div");
    div.setAttribute("class", "left");
    div.setAttribute("id", "imageHolder");
    container.appendChild(div);

    var right = document.createElement("div");
    right.setAttribute("class", "right");

    var top = document.createElement("div")
    top.setAttribute("class", "top");
    var titleElement = document.createElement("h3");
    titleElement.innerHTML = title;
    top.appendChild(titleElement);
    right.appendChild(top);
    container.appendChild(right);

    var bottom = document.createElement("div");
    bottom.setAttribute("id", "mediaPlayerBox");
    bottom.setAttribute("class", "bottom");

    var audio = document.createElement("audio");
    audio.setAttribute("id", "mediaPlayer");
    audio.setAttribute("name", "media");
    audio.setAttribute("autoplay", "true");
    audio.setAttribute("controls", "");
    audio.setAttribute("src", url);
    var src = document.createElement("src");
    src.setAttribute("type", "audio/mpeg");
    audio.appendChild(src);
    bottom.appendChild(audio);
    container.appendChild(bottom);

    let playerNode = document.getElementById("podypus-player");
    while(playerNode.firstChild) {
        playerNode.removeChild(playerNode.firstChild);
    }

    playerNode.appendChild(container);

    console.log(image_url);
    if(image_url != undefined) {
        var el = document.getElementById("imageHolder");
        el.style.backgroundImage = "url(" + image_url + ")";
    }
}

/*Event makes the first element on the page clickable for the player*/
let makePlayer = (event) => {
    event.preventDefault();
    url = event.currentTarget.dataset['episodeUrl'];
    title = event.currentTarget.dataset['episodeTitle'];
    image_url = event.currentTarget.dataset['episodeImageUrl'];
    id = event.currentTarget.dataset['episodeId'];
    makeAudio(url, title, id, image_url);
}

/* Event Listeners */
document.addEventListener('DOMContentLoaded', function () {
    /* Search event listeners */
    document.getElementById("nav-channels").addEventListener("click", showSubscriptions);
    document.getElementById("nav-search").addEventListener("click", showSearch);
})

