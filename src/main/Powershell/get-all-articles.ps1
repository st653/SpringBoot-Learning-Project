# API-Endpoint URL
$apiUrl = "http://localhost:8080/artikel"

# Sende die GET-Anfrage
$response = Invoke-RestMethod -Uri $apiUrl -Method Get -ContentType "application/json"

# Ausgabe der Antwort
Write-Output "Gefundene Artikel:"
$response | ForEach-Object {
    Write-Output "ID: $($_.id), Name: $($_.articleName), Preis: $($_.price), Lager: $($_.stock), Gewicht: $($_.weight)"
}