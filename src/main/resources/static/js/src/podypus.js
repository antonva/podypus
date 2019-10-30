console.log("Podypus is now mining for buttcoins...");





let performSearch = () => {
    let queryElem = document.getElementById("searchTxt")
    let term = queryElem.textContent

    xhr = new XMLHttpRequest();
    xhr.open('POST', '')
    alert("Foo")
}

/* Event Listeners */
document.addEventListener('DOMContentLoaded', function () {
    /* Search event listeners */
    document.getElementById("searchBtn").addEventListener("click", performSearch)
})

