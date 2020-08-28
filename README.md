# Space-Travel-Application-Spring-Boot

Zadanie dla Kandydatów 
 
(sytuacja opisana w zadaniu jest hipotetyczna, w tej chwili nie budujemy takiego systemu...) 
 
Zostaliśmy poproszeni o stworzenie aplikacji do zarządzania turystycznymi lotami w kosmos. 
 
Klient chce, żeby system był dostępny przez przeglądarkę (standardowo używa najnowszego Chroma, ale nie wyklucza, że niektórzy użytkownicy będa korzystać z innych przeglądarek, w tym IE).  
 
System ma przechowywać dane w bazie danych SQL lub NoSQL. Wymogiem jest stworzenie architektury, w której w przeglądarce uruchamia się klienta w javascript komunikującego się przez REST z serwerem implementującym logikę biznesową i zarządzającym połączeniem z bazą. W ramach realizowanej usługi klient otrzyma źródła, dlatego wszystkie nazwy i komentarze powinny być po angielsku.  
Funkcjonalności 
1.	Zarządzanie turystami 
a.	Lista turystów 
b.	Dodawanie turysty 
c.	Usuwanie turysty 
d.	Edycja turysty 
i.	Dodawanie lotu (wybór z istniejących w systemie lotów, uwaga na liczbę miejsc) 
ii.	Usuwanie lotu 
2.	Zarządzanie lotami 
a.	Lista lotów 
b.	Dodawanie lotu 
c.	Usuwanie lotu 
d.	Edycja lotu 
i.	Dodawanie turysty do lotu (wybór istniejących w systemie turystów, uwaga na liczbę miejsc) 
ii.	Usuwanie turysty z lotu 
3.	Rezerwacja lotów 
Tutaj zostawiamy pełną dowolność - chodzi o stworzenie wygodnego i intuicyjnego interfejsu dla turystów, gdzie mogliby wyszukiwac i rezerwowac loty na podstawie zadanych kryteriow - najlepiej prosze wzorowac sie na funcjonalnosci rezerwacji na portalach linii lotniczych. Oczywiscie to tylko zadanie dla kandydatow, wiec nie chcemy obciazac Panstwa ogromem pracy, dlatego chodzi o prosta, podstawowa wyszukiwarke i uprosczona rezerwacje, choc jednoczesnie ten element zadania jest dla nas bardzo istotny, poniewaz ma pokazac jak radzicie sobie Panstwo z projektowaniem funkcjonalnosci, z tworzeniem intuicyjnych interfejsow i z wyszukiwaniem w duzej bazie danych.  
Struktura danych 
1. Turysta 
	a.	Imię 
	b.	Nazwisko 
	c.	Płeć 
	d.	Kraj 
	e.	Notatki 
	f.	Data urodzenia  
	g.	Lista lotów 
2.	Lot 	
	a.	Data i czas wylotu  
	b.	Data i czas przylotu  
	c.	Liczba miejsc 
	d.	Lista turystów 
	e.	Cena biletu 
 
