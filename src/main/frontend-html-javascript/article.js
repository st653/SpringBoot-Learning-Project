document.getElementById("articleButton").addEventListener("click", loadArticles);

// Warte bis das HTML-Dokument vollständig geladen wurde
document.addEventListener("DOMContentLoaded", function () {
    loadHeader();
});

//Lade den Header
function loadHeader() {
    fetch("header.html")
        .then(response => response.text())
        .then(data => {
            document.querySelector("header").innerHTML = data;
        })
        .catch(error => console.error("Fehler beim Laden des Headers:", error));
}

function loadArticles() {
    fetch("http://localhost:8080/artikel")
        .then(response => response.json())
        .then(data => {
            document.getElementById("articleList").innerHTML =
                data.map(a => `<p>${a.name} - ${a.price}€</p>`).join("");
        })
        .catch(error => console.error("Fehler beim Laden:", error));
}
