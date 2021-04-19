Obiectele (8 obiecte si clase factory care nu stiu daca se pun ca si obiecte):
    -Cont
    --Cont de economii
    -Card
    --Visa
    --Mastercard
    -Tranzactie
    --Client
    --Adresa client
Actiuni (10 actiuni):
    -Creare client
    -Creare cont normal
    -Creare card
    -Creare cont de economii
    -Inchidere cont
    -Creare tranzactie
    -Afisare toate tranzactiile unui client
    -Afisare conturi pentru o persoana
    -Afisare date persoana
    -Depozitare suma in cont
    -Afisare sold total client


Etapa I:
1. Definirea sistemului: sa se creeze o lista pe baza temei alese cu cel puțin 10 acțiuni/interogări care se pot face în cadrul sistemului și o lista cu cel puțin 8 tipuri de obiecte.
2. Implementare: sa se implementeze în limbajul Java o aplicație pe baza celor definite la punctul. Aplicația va conține:
- clase simple cu atribute private / protected și metode de acces
- cel puțin 2 colecții diferite capabile să gestioneze obiectele definite anterior (List, Set, Map, etc.) dintre care cel puțin una sa fie sortata – se vor folosi array-uri uni-/bidimensionale in cazul in care nu se parcurg colectiile pana la data checkpoint-ului.
- utilizare moștenire pentru crearea de clase adiționale și utilizarea lor în cadrul colecțiilor;
- cel puțin o clasa serviciu care sa expună operațiile
- o clasa main din care sunt făcute apeluri către servicii


Etapa II:
Extindeți proiectul din prima etapa prin realizarea persistentei utilizând fișiere.
1. Se vor realiza fișiere de tip CSV (comma separated values) pentru cel puțin 4 dintre clasele definite in prima etapa. Fiecare coloana din fisier este separata de virgula.
Exemplu: nume,prenume,varsta
- se vor realiza servicii singleton generice pentru scrierea și citirea din fișiere
- la pornirea programului se vor încărca datele din fișiere utilizând serviciile create
2. Realizarea unui serviciu de audit: se va realiza un serviciu care sa scrie într-un fișier de tip CSV de fiecare data când este executata una dintre acțiunile descrise in prima etapa. Structura fișierului: nume_actiune, timestamp


Etapa III:
Înlocuiți serviciile realizate în etapa a II-a cu servicii care sa asigure persistenta utilizând baza de date folosind JDBC.
- sa se realizeze servicii care sa expună operații de tip create, read, update, delete pentru cel puțin 4 dintre clasele definite
- sa se realizeze o interfață grafica în care sa fie expuse cel puțin 5 dintre acțiunile definite inițial. Interfața va avea cel puțin 2 ecrane diferite care sa permită navigarea intre ele. Se va utiliza Swing sau JSP pentru realizarea interfeței grafice. Se pot utiliza si alte framework-uri, dar ar trebui discutate inainte, la laborator.
- se va adăuga în fișierul exportat de serviciul de audit încă o coloana: thread_name, reprezentand numele thread-ului care a apelat metoda.
