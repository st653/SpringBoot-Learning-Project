# API-Endpoint URL
$apiUrl = "http://localhost:8080/kunden?name="

# Kundenname
$customerName = "Mercedes Normandie"

# URL-codieren des Kundennamens
$encodedCustomerName = [System.Web.HttpUtility]::UrlEncode($customerName)

# Vollständige URL mit dem 'name'-Parameter
$fullUrl = "$apiUrl$encodedCustomerName"

try {
    # Sende die POST-Anfrage ohne Body, nur mit Query-Parameter
    $response = Invoke-RestMethod -Uri $fullUrl -Method Post -ContentType "application/json"
    Write-Output "Kunde wurde angelegt: $($response)"
} catch {
    Write-Output "Fehler beim Anlegen des Kunden: $_"
}
