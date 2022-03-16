DROP TABLE if EXISTS albums;
CREATE TABLE albums (Album VARCHAR, Artist VARCHAR, Label VARCHAR, Year INT, Sales INT, PRIMARY KEY(Album, Artist));
INSERT INTO albums VALUES ('Greatest Hits','Queen','EMI Parlophone', 1981, 6600000),('Gold: Greatest Hits','ABBA','Polydor',1992,5580000),('Sgt. Pepperś Lonely Hearts Club Band','The Beatles', 'Parlophone',1967,5340000),('21','Adele','XL',2011,5200000),('(What''s the story) Morning Glory?','Oasis','Creation',1995,4940000),('Thriller','Michael Jackson','Epic',1982,4470000),('The Dark Side of the Moon','Pink FLoyd','Harvest',1973,4470000),('Brothers in Arms','Dire Straits','Vertigo',1985,4350000),('Bad','Michael Jackson','Epic',1987,4140000),('Greatest Hits ii','Queen','EMI/Parlophone',1991,3990000); 
SELECT * FROM albums ORDER BY Year DESC;
ALTER TABLE albums ADD chart_peak INT DEFAULT 1;
UPDATE albums SET chart_peak=2 WHERE Album ='The Dark Side of the Moon';
SELECT Artist,Album,Sales FROM albums WHERE chart_peak=1 ORDER BY Sales DESC;
SELECT Artist,Album,Sales,Year,chart_peak FROM albums WHERE Album LIKE '%Greatest%' ORDER BY Year ASC;
