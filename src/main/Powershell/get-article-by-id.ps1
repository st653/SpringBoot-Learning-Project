# API-Endpoint URL
$apiUrl = "http://localhost:8080/artikel/"
$articleId = 1

# Vollständige URL mit der Artikel-ID
$fullUrl = "$apiUrl$articleId"

# Sende die GET-Anfrage
$response = Invoke-RestMethod -Uri $fullUrl -Method Get -ContentType "application/json"

# Ausgabe der Antwort
Write-Output "Gefundener Artikel: $response"
