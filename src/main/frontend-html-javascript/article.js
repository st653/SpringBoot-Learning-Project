document.getElementById("articleButton").addEventListener("click", loadArticles);

function loadArticles() {
    fetch("http://localhost:8080/artikel")
        .then(response => response.json())
        .then(data => {
            document.getElementById("articleList").innerHTML =
                data.map(a => `<p>${a.name} - ${a.price}€</p>`).join("");
        })
        .catch(error => console.error("Fehler beim Laden:", error));
}
