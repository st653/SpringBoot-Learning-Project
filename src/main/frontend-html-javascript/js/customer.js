

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