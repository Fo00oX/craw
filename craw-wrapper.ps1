if (!(docker ps -f status=running | Select-String -Pattern "craw-cli")) {
  echo "It seems there is no craw container running :("
  $input = Read-Host "Do you want to start it? [y/n] "
  while("y", "n" -notcontains $input) {
    $input = Read-Host "Do you want to start it? [y/n] "
  }
  switch ($input) {
    "y" { ./start.ps1 }
    "n" { exit 1 }
  }
}

docker exec -t craw-cli craw-cli $args 
