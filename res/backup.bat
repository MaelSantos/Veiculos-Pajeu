@set password = "admin"
"C:\Program Files\PostgreSQL\9.4\bin\"pg_dump.exe --host localhost --port 5432 --username "postgres" --password "admin" --format custom --blobs --verbose --file "C:\Users\maels\Desktop\teste.backup" "Veiculos Pajeu"
pause

