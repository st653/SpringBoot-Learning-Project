// URL deines Backends
const backendUrl = "http://localhost:8080/artikel";

// Funktion, um Artikel vom Backend abzurufen
function fetchArtikel() {
    fetch(backendUrl, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((response) => {
        if (!response.ok) {
            throw new Error("Fehler beim Abrufen der Artikel");
        }
        return response.json();
    })
        .then((data) => {
        // Artikel in die HTML-Seite einfügen
        const artikelList = document.getElementById("artikel-list");
        artikelList.innerHTML = ""; // Liste leeren

        data.forEach((artikel) => {
            console.log("Artikel:", artikel); // Prüfen, was in jedem Artikel steckt
            const listItem = document.createElement("li");
            listItem.textContent = `ID: ${artikel.id}, Name: ${artikel.articleName}, Preis: ${artikel.price} €, Bestand: ${artikel.stock}, Gewicht: ${artikel.weight} kg`;
            artikelList.appendChild(listItem);
        });
    })
        .catch((error) => {
        console.error("Fehler:", error);
    });
}

// Artikel beim Laden der Seite abrufen
document.addEventListener("DOMContentLoaded", fetchArtikel);

