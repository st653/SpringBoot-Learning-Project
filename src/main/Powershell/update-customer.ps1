# API-Endpoint URL
$apiUrl = "http://localhost:8080/kunden/"
$articleId = 2

# Vollständige URL mit der Artikel-ID
$fullUrl = "$apiUrl$articleId"

# JSON-Daten für die Aktualisierung des Artikels
$jsonData = @{
    name = "Mercedes Hollandaise"
} | ConvertTo-Json -Depth 1 -Compress

# Sende die PUT-Anfrage
$response = Invoke-RestMethod -Uri $fullUrl -Method Put -Body $jsonData -ContentType "application/json"

# Ausgabe der Antwort
Write-Output "Antwort vom Server: $response"