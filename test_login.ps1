# Test script to check login endpoint
# Test login with the test user credentials

$body = @{
    email = "joao@gmail.com"
    senha = "123456"
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri "http://localhost:8087/api/usuarios/login" -Method Post -Body $body -ContentType "application/json" -ErrorAction Stop
    Write-Host "Login successful!"
    Write-Host "Response: $response"
} catch {
    Write-Host "Login failed with error: $($_.Exception.Message)"
    Write-Host "Status: $($_.Exception.Response.StatusCode.value__)"
    Write-Host "Response: $($_.Exception.Response)"
}