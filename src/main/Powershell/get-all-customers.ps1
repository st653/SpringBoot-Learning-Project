# API-Endpoint URL
$apiUrl = "http://localhost:8080/kunden"

# Sende die GET-Anfrage
$response = Invoke-RestMethod -Uri $apiUrl -Method Get -ContentType "application/json"

# Ausgabe der Antwort
Write-Output "Gefundene Kunden:"
$response | ForEach-Object {
    Write-Output "ID: $($_.id), Name: $($_.name)"
}