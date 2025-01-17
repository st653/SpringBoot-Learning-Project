# API-Endpoint URL
$apiUrl = "http://localhost:8080/artikel"
$articleId = 1

# JSON-Daten für die Aktualisierung des Artikels
$jsonData = @{
    id = $articleId
    articleName = "Laptop"
    price = 1100.00
    stock = 15
    weight = 1.8
} | ConvertTo-Json -Depth 1 -Compress

# Sende die PUT-Anfrage
try
{
    $response = Invoke-RestMethod -Uri $apiUrl -Method Put -Body $jsonData -ContentType "application/json"
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