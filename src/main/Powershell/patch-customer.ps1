# API-Endpoint URL
$apiUrl = "http://localhost:8080/kunden/"
$customerId = 2

# Vollständige URL mit der Artikel-ID
$fullUrl = "$apiUrl$customerId"

# JSON-Daten für die Teilaktualisierung des Artikels (nur die Felder, die aktualisiert werden sollen)
$jsonData = @{
    name = "Jane Austin"
} | ConvertTo-Json -Depth 1 -Compress

# Sende die PATCH-Anfrage
$response = Invoke-RestMethod -Uri $fullUrl -Method Patch -Body $jsonData -ContentType "application/json"

# Ausgabe der Antwort
Write-Output "Antwort vom Server: $response"
