# API-Endpoint URL
$apiUrl = "http://localhost:8080/artikel"
$articleId = 1

# Vollständige URL mit der Artikel-ID
$fullUrl = "$apiUrl/$articleId"

# JSON-Daten für die Aktualisierung des Artikels
$jsonData = @{
    articleName = "Laptop"
    price = 1100.00
    stock = 15
    weight = 1.8
} | ConvertTo-Json -Depth 1 -Compress

# Sende die PUT-Anfrage
$response = Invoke-RestMethod -Uri $fullUrl -Method Put -Body $jsonData -ContentType "application/json"

# Ausgabe der Antwort
Write-Output "Antwort vom Server: $response"