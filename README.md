Aby zainstalować tę aplikację, uruchom następujące polecenia:

    git clone https://github.com/tlpchk/przyjazne-szachy.git
    mvn clean install

Aby uruchomić aplikację, przejdź do folderu server i uruchom: 

    ./mvnw spring-boot:run
    
Klient będzie znajdował się pod adresem:

    localhost:8080
    
    
Deploy na Heroku:

    mvn clean heroku:deploy
    heroku open
