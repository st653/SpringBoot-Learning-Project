# API-Endpoint URL
$apiUrl = "http://localhost:8080/artikel"

# JSON-Daten für den neuen Artikel
$jsonData = @{
    articleName = "Laptop"
    price = 500.00
    stock = 12
    weight = 2.00
} | ConvertTo-Json -Depth 1

try {
    # Sende die POST-Anfrage
    $response = Invoke-RestMethod -Uri $apiUrl -Method Post -Body $jsonData -ContentType "application/json"
    # Ausgabe der Antwort
    Write-Output "Antwort des Servers: $response"
} catch {
    # Fehlerbehandlung
    Write-Output "Fehler beim Anlegen des Artikels: $_"
}