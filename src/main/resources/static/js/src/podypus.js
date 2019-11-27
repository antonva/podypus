//Keep SQL database informed on the playback position of the player client side.
let updatePlaybackPos = (event) => {
    let obj = {
        "id": event.currentTarget.dataset['episodeId'],
        "pos": event.currentTarget.currentTime,
        "ended": event.currentTarget.ended
    }
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        url: "update-playback-pos",
        data : JSON.stringify(obj),
        success: function(res) {

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

let getPlaybackPos = (id) => {
    let obj = {
        "id": id
    };
    let pos = 0;

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: false,//Terrible hack, don't try this at home kids
        url: "get-playback-pos",
        data : JSON.stringify(obj),
        success: function(res) {
            pos = res.pos;
        },
        error: function(res) {
            console.log("error");
            console.log(res)
        },
        done: function(res) {
            console.log("DONE");
            console.log(res)
        },
    });
    return pos;
};

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


/* Perform an AJAX search on podypus server */
let performSearch = (event) => {
    event.preventDefault();
    var queryElem = document.getElementById("searchTxt");
    var termobj = { term: queryElem.value };
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
};

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
    let channel_id = {"channel_id": event.currentTarget.dataset['channelId']};
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(channel_id),
        dataType: "html",
        url: "channel",
        success: function (res) {
            document.getElementById("podypus-container").innerHTML = res;
            addEpisodeListeners();
            let episodeTable = $('#table_episode').DataTable({ // Stillingar á töflu
                scrollY: 380,
                paging: false,
                order: [[3, 'desc']] // Raða eftir release date
            });
            $('#playedCheck').change(function() { // Check box til að filtera út spilað
                if (this.checked) {
                    $.fn.dataTable.ext.search.push(
                        function(settings, data, dataIndex) {
                            return $(episodeTable.row(dataIndex).node()).attr('data-episode-played') === 'false';
                        }
                    );
                    episodeTable.draw();
                } else {
                    $.fn.dataTable.ext.search.pop();
                    episodeTable.draw();
                }
            });
        },
        error: function (res) {
            console.log("ERROR");
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

/*Creates the audio player container in html document*/
function makeAudio(url, title, episode_id, image_url) {
    var bool = false;
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

    var audio = new Audio(url);
    audio.autoplay = true;
    audio.controls = true;
    audio.id = "mediaPlayer";
    audio.currentTime = getPlaybackPos(episode_id);
    audio.setAttribute("name", "media");
    audio.setAttribute("data-episode-id", episode_id);

    audio.addEventListener("timeupdate", updatePlaybackPos);

    var sleepInput = document.createElement("input");
    sleepInput.setAttribute("type", "number");
    sleepInput.setAttribute("class", "sleepVar");
    sleepInput.setAttribute("name", "sleepVar");
    sleepInput.placeholder = "Sleep(min)";

    var checkSleep = document.createElement("input");
    checkSleep.setAttribute("type", "checkbox");
    checkSleep.setAttribute("class", "boolSleep");
    checkSleep.setAttribute("name", "sleepBool");
    checkSleep.setAttribute("id", "checkSleep")

    /*Check if user wants to use sleep timer otherwise remove*/
    checkSleep.addEventListener("change", (event) => {
        if(event.target.checked) {sleepInput.addEventListener("input", setSleep);}
        else{sleepInput.removeEventListener("input", setSleep);}
    });

    var src = document.createElement("src");
    src.setAttribute("type", "audio/mpeg");
    audio.appendChild(src);
    bottom.appendChild(audio);
    bottom.appendChild(checkSleep);
    bottom.appendChild(sleepInput);
    container.appendChild(bottom);

    let playerNode = document.getElementById("podypus-player");
    while(playerNode.firstChild) {
        playerNode.removeChild(playerNode.firstChild);
    }

    playerNode.appendChild(container);

    /*Set image if its available otherwise use CSS fallback background defined in the css*/
    if(image_url != undefined) {
        var el = document.getElementById("imageHolder");
        el.style.backgroundImage = "url(" + image_url + ")";
    }
}

let setSleep = (event) => {
    /*Set sleep timer then clear intervals and checkbox*/
    var value = event.currentTarget.value;
    if(value < 1) return;
    else {
        var timer = setInterval(() => {
            document.getElementById("mediaPlayer").pause();
            clearInterval(timer);
            document.getElementById("checkSleep").checked = false;
        }, value*60*1000)
    }
}

/*Event makes the first element on the page clickable for the player*/
let makePlayer = (event) => {
    event.preventDefault();
    url = event.currentTarget.dataset['episodeUrl'];
    title = event.currentTarget.dataset['episodeTitle'];
    id = event.currentTarget.dataset['episodeId'];
    image_url = document.getElementById('podypus-episode-img-' + id).src;
    makeAudio(url, title, id, image_url);
}

/* Event Listeners */
document.addEventListener('DOMContentLoaded', function () {
    /* Search event listeners */
    document.getElementById("nav-channels").addEventListener("click", showSubscriptions);
    document.getElementById("nav-search").addEventListener("click", showSearch);
})

