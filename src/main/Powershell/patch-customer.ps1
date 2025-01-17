# API-Endpoint URL
$apiUrl = "http://localhost:8080/kunden"

# JSON-Daten für die Teilaktualisierung des Artikels (nur die Felder, die aktualisiert werden sollen)
$jsonData = @{
    id = 1
    name = "Jane Austin"
} | ConvertTo-Json -Depth 1 -Compress

try {
    # Sende die PATCH-Anfrage
    $response = Invoke-RestMethod -Uri $apiUrl -Method Patch -Body $jsonData -ContentType "application/json"
    # Ausgabe der Antwort
    Write-Output "Antwort vom Server: $response"
} catch {
    # Ausgabe der Fehlermeldung
    Write-Output "Fehler: $_"
}