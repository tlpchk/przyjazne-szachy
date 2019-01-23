Aby zainstalować tę aplikację, uruchom następujące polecenia:

    git clone https://github.com/tlpchk/przyjazne-szachy.git
    cd przyjazne-szachy/client
    npm install
	npm install bootstrap
	npm install jquery popper.js --save
	npm install --save angularx-social-login
Aby uruchomić serwer, przejdź do folderu server i uruchom:

    ./mvnw spring-boot:run
    
Klient będzie znajdował się pod adresem:

    localhost:4200
    
    
Deploy na Heroku:

    mvn clean heroku:deploy
    heroku open
