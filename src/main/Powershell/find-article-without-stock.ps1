$apiUrl = "http://localhost:8080/artikel/ohne-Bestand"

try
{
    # Sende die GET-Anfrage
    $response = Invoke-RestMethod -Uri $apiUrl -Method Get -ContentType "application/json"

    # Ausgabe der Antwort
    Write-Output "Gefundene Artikel mit leerem Bestand:"
    $response | ForEach-Object {
        Write-Output "ID: $( $_.id ), Name: $( $_.articleName ), Preis: $( $_.price ), Lager: $( $_.stock ), Gewicht: $( $_.weight )"
    }
}
catch {
    # Fehlerbehandlung
    Write-Output "Fehler beim Anlegen des Artikels: $_"
}