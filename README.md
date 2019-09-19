### Opis funkcjonalości
Przyjazne Szachy są platformą umożliwiającą przeprowadzenie partii szachów z innym użytkownikiem lub botem zarówno niezarejestrowanemu użytkownikowi jak i osobie posiadającej konto. Użytkownik może wybrać grę rankingową lub nierankingową, po czym system dobiera przeciwnika. Aby w komfortowych warunkach zaznajamiać się z zasadami rozgrywki przewidziana jest możliwość potyczki z komputerowym przeciwnikiem. Bot szacuje sytuację w meczu i wybiera ruch spośród możliwych wariacji, które wyznaczył. Zastosowanie silnika szachowego ma pomóc graczowi we własnym rozwoju oraz ewolucji siły gry samego bota. Aplikacja będzie także wyposażona w bazę danych, gromadzącą wiedzę na temat historii rozegranych meczy oraz kont zarejestrowanych użytkowników. Gracz rozgrywając mecze zdobywa punkty ELO, będące miarą jego zaawansowania.

### Inne
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
