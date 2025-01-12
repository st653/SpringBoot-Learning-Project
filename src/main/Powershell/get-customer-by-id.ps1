# API-Endpoint URL
$apiUrl = "http://localhost:8080/kunden/"
$kundenId = 2

# Vollständige URL mit der Artikel-ID
$fullUrl = "$apiUrl$kundenId"

# Sende die GET-Anfrage
$response = Invoke-RestMethod -Uri $fullUrl -Method Get -ContentType "application/json"

# Ausgabe der Antwort
Write-Output "Gefundener Kunde: $response"
