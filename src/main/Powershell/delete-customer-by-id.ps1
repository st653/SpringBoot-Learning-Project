# API-Endpoint URL
$apiUrl = "http://localhost:8080/kunden/"
$articleId = 1

# Vollständige URL mit der Artikel-ID
$fullUrl = "$apiUrl$articleId"

# Sende die GET-Anfrage
$response = Invoke-RestMethod -Uri $fullUrl -Method Delete -ContentType "application/json"

# Ausgabe der Antwort
Write-Output "$response"