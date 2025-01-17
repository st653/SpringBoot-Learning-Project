# API-Endpoint URL
$apiUrl = "http://localhost:8080/kunden"

# JSON-Daten für die Aktualisierung des Artikels
$jsonData = @{
    id = 1
    name = "Mercedes Hollandaise"
} | ConvertTo-Json -Depth 1 -Compress

try {
    # Sende die PUT-Anfrage
    $response = Invoke-RestMethod -Uri $apiUrl -Method Put -Body $jsonData -ContentType "application/json"
    # Ausgabe der Antwort
    Write-Output "Antwort vom Server: $response"
} catch {
    # Ausgabe der Fehlermeldung
    Write-Output "Fehler: $_"
}