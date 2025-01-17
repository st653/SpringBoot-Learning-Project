# API-Endpoint URL
$apiUrl = "http://localhost:8080/kunden"

# JSON-Body für den POST-Request
$jsonData = @{
    name = "Maximus Musterkunde"
} | ConvertTo-Json -Depth 1

try {
    # Sende die POST-Anfrage
    $response = Invoke-RestMethod -Uri $apiUrl -Method Post -Body $jsonData -ContentType "application/json"
    Write-Output "Antwort des Servers: $($response)"
} catch {
    Write-Output "Fehler beim Anlegen des Kunden: $_"
}
