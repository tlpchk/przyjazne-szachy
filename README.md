Aby zainstalować aplikację, uruchom następujące polecenia:

    git clone https://github.com/tlpchk/przyjazne-szachy.git
    cd przyjazne-szachy/client
    npm install
	
Aby uruchomić serwer, przejdź do folderu `server` i uruchom:
    
    ./mvnw spring-boot:run

lub
    
    bash ./server.sh

Aby uruchomić klienta, przejdź do folderu `client` i uruchom:

    sudo npm start
    
Klient będzie znajdował się pod adresem:

    localhost:4200
    
Deploy na Heroku:

    mvn clean heroku:deploy
    heroku open
