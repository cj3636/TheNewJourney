$path=".\blockstates\*.json"
#With Default system encoding
Get-ChildItem $path -Recurse | foreach{    
    (Get-Content $_.FullName).ToLower() | Out-File $_.FullName
}

#Or with specified encoding    
Get-ChildItem $path -Recurse | foreach{    
(Get-Content $_.FullName -Encoding Unicode).ToLower() | 
    Out-File $_.FullName -Encoding Unicode
}
#Test
Get-ChildItem $path -Recurse | foreach{
    Write-Host "`n File ($_.FullName): `n" -ForegroundColor DarkGreen
    Get-Content $_.FullName    
}