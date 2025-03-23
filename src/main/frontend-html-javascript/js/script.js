// URL deines Backends
const articleBackendUrl = "http://localhost:8080/artikel";
const customerBackendUrl = "http://localhost:8080/kunden";

// Warte bis das HTML-Dokument vollständig geladen wurde
document.addEventListener("DOMContentLoaded", function () {
    loadArticleSummary();
    loadCustomerSummary();
    loadHeader();
    loadFooter();
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

// Lade die Artikelübersicht
function loadArticleSummary() {
    fetch(articleBackendUrl)
        .then(response => response.json())
        .then(data => {
            document.getElementById("totalArticles").textContent = data.length;

            const list = document.getElementById("articleList");
            list.innerHTML = ""; // Liste leeren

            data.forEach(article => {
                const listItem = document.createElement("li");
                listItem.innerHTML = `<strong>${article.articleName}</strong>: ${article.stock}`;
                list.appendChild(listItem);
            });
        })
        .catch(error => console.error("Fehler beim Laden der Artikel:", error));
}

// Lade die Kundenübersicht
function loadCustomerSummary() {
    fetch(customerBackendUrl)
        .then(response => response.json())
        .then(data => {
            document.getElementById("totalCustomers").textContent = data.length;
        })
        .catch(error => console.error("Fehler beim Laden der Kunden:", error));
}
