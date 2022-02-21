insert into genres (naam) VALUES ('DollyWoodCrap'),('AndereFlutFilms');
insert into films(genreId, titel, voorraad, gereserveerd, prijs) VALUES
((select id from genres where naam='AndereFlutFilms'),'FlutFilm',7,1,20.62),
((select id from genres where naam='DollyWoodCrap'),'SuperHans en SpiderGrietje',9,2,1.12),
((select id from genres where naam='DollyWoodCrap'),'Cptn Pipo en de Zweven Dergen',2,0,3.2);
