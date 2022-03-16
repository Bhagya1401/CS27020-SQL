--1
SELECT md.module_name AS modulename FROM modules as md INNER JOIN schememodules AS sm ON md.moduleid = sm.moduleid and sm.schemeid = 'G444';

--2

SELECT sch.schemeid AS schemeID, sch.scheme_name AS scheme_name ,b.year AS year , SUM(b.size) AS total_credits FROM blocks AS b INNER JOIN schemes AS sch ON b.schemeid = sch.schemeid GROUP by sch.schemeid,b.year; 

--3
SELECT sch.schemeid AS schemeID, sch.scheme_name AS scheme_name ,b.year AS year , b.block AS block, b.size AS size, SUM(md.credits) AS credits FROM(((schememodules AS sm INNER JOIN schemes AS sch ON sm.schemeid = sch.schemeid)INNER JOIN blocks as b on b.schemeid = sm.schemeid AND b.year = sm.year AND b.block = sm.block) Inner JOIN modules As md ON md.moduleid = sm.moduleid) GROUP BY sch.schemeid, b.year,b.block,b.size ORDER BY size DESC; 
