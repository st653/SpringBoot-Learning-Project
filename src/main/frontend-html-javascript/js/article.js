// URL article Backend
const articleBackendUrl = "http://localhost:8080/artikel";

// Warte bis das HTML-Dokument vollständig geladen wurde
document.addEventListener("DOMContentLoaded", function () {
    loadHeader();
    loadFooter();
    loadArticles();
});

//Lade den Header
function loadHeader() {
    fetch("../components/header.html")
        .then(response => response.text())
        .then(data => {
            document.querySelector("header").innerHTML = data;
        })
        .catch(error => console.error("Fehler beim Laden des Headers:", error));
}

// Lade den Footer
function loadFooter() {
    fetch("../components/footer.html")
        .then(response => response.text())
        .then(data => {
            document.querySelector("footer").innerHTML = data;
        })
        .catch(error => console.error("Fehler beim Laden des Footers:", error));
}

function loadArticles() {
    fetch(articleBackendUrl)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById("articlesTable").getElementsByTagName("tbody")[0];
            tableBody.innerHTML = "";  // Clear existing rows

            // Füge jede Artikelzeile zur Tabelle hinzu
            data.forEach(a => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${a.articleName}</td>
                    <td>${a.price} €</td>
                    <td>${a.stock}</td>
                    <td>${a.weight} Kg</td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error("Fehler beim Laden:", error));
}

document.getElementById('newArticleForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Verhindert das Standard-Formularverhalten

    const articleName = document.getElementById('articleName').value;
    const articlePrice = document.getElementById('articlePrice').value;
    const articleStock = document.getElementById('articleStock').value;
    const articleWeight = document.getElementById('articleWeight').value;

    // Beispiel für eine Ausgabe (kann durch eine API-Anfrage ersetzt werden)
    console.log('New Article Added:');
    console.log('Name: ' + articleName);
    console.log('Description: ' + articlePrice);
    console.log('Stock: ' + articleStock);
    console.log('Weight: ' + articleWeight);

    // Hier kannst du dann den Artikel an eine API senden (z.B. mit Fetch oder Axios)

    // Optional: Formular zurücksetzen nach dem Absenden
    document.getElementById('newArticleForm').reset();
});

