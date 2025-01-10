# API-Endpoint URL
$apiUrl = "http://localhost:8080/artikel"

# JSON-Daten für den neuen Artikel
$jsonData = @{
    articleName = "Bildschirm"
    price = 500.00
    stock = 45
    weight = 3.00
} | ConvertTo-Json -Depth 1

# Sende die POST-Anfrage
$response = Invoke-RestMethod -Uri $apiUrl -Method Post -Body $jsonData -ContentType "application/json"

# Ausgabe der Antwort
Write-Output "Artikel wurde erstellt: $response"
