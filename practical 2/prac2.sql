-- 1
SELECT id, artist_id, name, sales FROM album WHERE sales IS NOT NULL AND year BETWEEN 1990 AND 2010 ORDER BY Sales DESC LIMIT 5;

--2
SELECT al.id, at.name, at.record_label_id, al.sales FROM artist AS at INNER JOIN album AS al ON at.id = al.artist_id AND al.sales IS NOT NULL ORDER BY al.sales DESC LIMIT 5;

--3
SELECT COUNT(al.id) AS total_albums,at.name AS artist_name ,SUM(al.sales) AS total_sales FROM artist AS at INNER JOIN album AS al ON at.id = al.artist_id AND al.sales IS NOT NULL AND al.year IS NOT NULL GROUP BY at.name ORDER BY total_sales DESC LIMIT 10;

--4

SELECT at.name AS artist_name ,al.name AS album_name ,al.year AS album_year,al.sales AS album_sales,rl.name AS record_label FROM (( artist AS at INNER JOIN album AS al ON at.id = al.artist_id ) INNER JOIN record_label AS rl ON at.record_label_id = rl.id) ;


--5 
--Using LIKE

SELECT at.name AS artist_name,al.name AS album_name,al.year AS album_year,al.sales AS album_sales,rl.name AS label_name FROM (( artist AS at INNER JOIN album AS al ON at.id = al.artist_id) INNER JOIN record_label AS rl ON at.record_label_id = rl.id AND rl.name LIKE '%Parlophone%');


--Using OR

SELECT at.name AS artist_name,al.name AS album_name,al.year AS album_year,al.sales AS album_sales,rl.name AS label_name FROM (( artist AS at INNER JOIN album AS al ON at.id = al.artist_id) INNER JOIN record_label AS rl ON at.record_label_id = rl.id) WHERE rl.name = 'Parlophone' OR rl.name = 'EMI/Parlophone';


--EXPLAIN ANALYZE

EXPLAIN ANALYZE SELECT at.name AS artist_name,al.name AS album_name,al.year AS album_year,al.sales AS album_sales,rl.name AS label_name FROM (( artist AS at INNER JOIN album AS al ON at.id = al.artist_id) INNER JOIN record_label AS rl ON at.record_label_id = rl.id AND rl.name LIKE '%Parlophone%');


EXPLAIN ANALYZE SELECT at.name AS artist_name,al.name AS album_name,al.year AS album_year,al.sales AS album_sales,rl.name AS label_name FROM (( artist AS at INNER JOIN album AS al ON at.id = al.artist_id) INNER JOIN record_label AS rl ON at.record_label_id = rl.id) WHERE rl.name = 'Parlophone' OR rl.name = 'EMI/Parlophone';



--6


SELECT rl.name AS label_name,COUNT(at.record_label_id) AS total_artist FROM artist AS at RIGHT JOIN record_label AS rl ON at.record_label_id = rl.id GROUP BY rl.name ORDER BY total_artist;
