# API-Endpoint URL
$apiUrl = "http://localhost:8080/artikel"
$articleId = 1

# JSON-Daten für die Teilaktualisierung des Artikels (nur die Felder, die aktualisiert werden sollen)
$jsonData = @{
    id = $articleId
    stock = 50
} | ConvertTo-Json -Depth 1 -Compress

try
{
    # Sende die PATCH-Anfrage
    $response = Invoke-RestMethod -Uri $apiUrl -Method Patch -Body $jsonData -ContentType "application/json"
}
catch
{
    #print status code and error message
    Write-Output "Statuscode: $($_.Exception.Response.StatusCode.value__)"
    Write-Output "Fehler beim Aktualisieren des Artikels: $_"
    exit
}

# Ausgabe der Antwort
Write-Output "Antwort vom Server: $response"
