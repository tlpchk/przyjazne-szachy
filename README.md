Aby zainstalować aplikację i uruchomić lokalnie, uruchom następujące polecenia:

    git clone https://github.com/tlpchk/przyjazne-szachy.git
    git checkout LOCALHOST_FOR_TESTING
    cd przyjazne-szachy/client
    npm install
    
Przed uruchomieniem aplikacji lokalnie należy zbudować bazę skryptem SQL(MySQL) który znajduje się w

    \przyjazne-szachy\server\src\main\resources\database.sql
    
Następnie w pliku

    C:\git\przyjazne-szachy\server\src\main\resources\application.properties
    
 należy zmienić hasło i uzytkownika do lokalnej bazy danych
	
Aby uruchomić serwer, przejdź do folderu `server` i uruchom:
    
    ./mvnw spring-boot:run


Aby uruchomić klienta, przejdź do folderu `client` i uruchom:

    npm start
    
Klient będzie znajdował się pod adresem:

    localhost:4200
    
