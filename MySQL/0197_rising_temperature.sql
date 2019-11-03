/*
 * https://leetcode-cn.com/problems/rising-temperature/
 *
 * Reference: https://leetcode-cn.com/problems/rising-temperature/solution/shang-sheng-de-wen-du-by-leetcode/

给定一个 Weather 表，编写一个 SQL 查询，来查找与之前（昨天的）日期相比温度更高的所有日期的 Id。

+---------+------------------+------------------+
| Id(INT) | RecordDate(DATE) | Temperature(INT) |
+---------+------------------+------------------+
|       1 |       2015-01-01 |               10 |
|       2 |       2015-01-02 |               25 |
|       3 |       2015-01-03 |               20 |
|       4 |       2015-01-04 |               30 |
+---------+------------------+------------------+

例如，根据上述给定的 Weather 表格，返回如下 Id:

+----+
| Id |
+----+
|  2 |
|  4 |
+----+

----------------------------------------------------------------------------------------------------

Given a Weather table, write a SQL query to find all dates' Ids with higher temperature compared to its previous (yesterday's) dates.

+---------+------------------+------------------+
| Id(INT) | RecordDate(DATE) | Temperature(INT) |
+---------+------------------+------------------+
|       1 |       2015-01-01 |               10 |
|       2 |       2015-01-02 |               25 |
|       3 |       2015-01-03 |               20 |
|       4 |       2015-01-04 |               30 |
+---------+------------------+------------------+

For example, return the following Ids for the above Weather table:

+----+
| Id |
+----+
|  2 |
|  4 |
+----+
*/


-- Mine:

SELECT
	a.Id 
FROM
	Weather a INNER JOIN weather b 
	ON DATEDIFF(a.RecordDate, b.RecordDate) = 1 AND a.Temperature > b.Temperature;


-- Reference1:
SELECT
	ID 
FROM
	(
	SELECT
		w.id,
	  IF ( @tem < w.Temperature AND datediff( w.RecordDate, @dat )= 1, 1, 0 ) num,
		@tem := w.Temperature,
		@dat := w.RecordDate 
	FROM
		Weather w, ( SELECT @tem := 0, @dat := NULL ) var
	ORDER BY
		w.RecordDate 
	) t2 
WHERE
	num = 1;
