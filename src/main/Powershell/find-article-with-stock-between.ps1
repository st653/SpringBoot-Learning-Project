# Minimal- und Maximalbestand
$minStock = 5
$maxStock = 15

$apiUrl = "http://localhost:8080/artikel/bestand-bereich?minStock=$minStock&maxStock=$maxStock"

try
{
    # Sende die GET-Anfrage
    $response = Invoke-RestMethod -Uri $apiUrl -Method Get -ContentType "application/json"

    # Prüfe, ob Artikel gefunden wurden
    if ($response -and $response.Count -gt 0)
    {
        Write-Output "Gefundene Artikel mit Bestand zwischen $minStock und $maxStock :"
        $response | ForEach-Object {
            Write-Output "ID: $( $_.id ), Name: $( $_.articleName ), Preis: $( $_.price ), Lager: $( $_.stock ), Gewicht: $( $_.weight )"
        }
    }
    else
    {
        Write-Output "Keine Artikel mit Bestand zwischen $minStock und $maxStock gefunden."
    }
}
catch {
    # Fehlerbehandlung
    Write-Output "Fehler bei der Abfrage des Endpunkts für Artikel im Bestandsbereich: $_"
}