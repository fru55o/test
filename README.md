# test
Piattaforma Eclipse
Compilatore Maven (compatibilità con versione 1.8 richiesta)

Il test è costituito dalle seguenti Classi
1) LdapSearch.java
2) SearchUsers.java
3) Modello User.java 
4) PropertyManager

Funzionalità:
Modello utente con informazioni di autenticazione:  uid; name; surname;
LdapSearch classe utilizzata per effettuare la ricerca dei parametri inseriti all'iterno del server LDAP, inoltre la stessa classe è invocata per effettuare la connessione.
PropertyManager legge tutte le proprietà del file main\resources\META-INF\searchUsers.properties facendo delle GET
 

Nota:
durante la fase di preparazione è emersa la possibilità di utilizzare il singleton: gestire soltanto un’unica istanza della classe stessa essere creata all’interno del programma. funzionalità scelta per ovviare a problematiche di login simultaneo che potrebbero mandare in errore l'esecuzione.
Per la richiesta compatibilità con versione 1.8 è stato utilzzato Maven https://maven.apache.org 

INFO:
la richiesta prevede l'utilizzo di un file esterno
main\resources\META-INF\searchUsers.properties
in cui vanno inseriti i valori URL credenziali server LDAP
per mancanza di tempo l'obiettivo di ottenere un file esterno non è stato ottenuto.
La soluzione proposta sarebbe stata, conoscendo il sistema operativo destinatario usare uno script sh o un batch che esternamente effettuava la sincronizzazione dei dati.
