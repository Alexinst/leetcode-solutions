/*
 * https://leetcode-cn.com/problems/consecutive-numbers/ 

编写一个 SQL 查询，查找所有至少连续出现三次的数字。
	+----+-----+
	| Id | Num |
	+----+-----+
	| 1  |  1  |
	| 2  |  1  |
	| 3  |  1  |
	| 4  |  2  |
	| 5  |  1  |
	| 6  |  2  |
	| 7  |  2  |
	+----+-----+
	
例如，给定上面的 Logs 表， 1 是唯一连续出现至少三次的数字。
	+-----------------+
	| ConsecutiveNums |
	+-----------------+
	| 1               |
	+-----------------+

----------------------------------------------------------------------------------------------------

Write a SQL query to find all numbers that appear at least three times consecutively.
	+----+-----+
	| Id | Num |
	+----+-----+
	| 1  |  1  |
	| 2  |  1  |
	| 3  |  1  |
	| 4  |  2  |
	| 5  |  1  |
	| 6  |  2  |
	| 7  |  2  |
	+----+-----+
	
For example, given the above Logs table, 1 is the only number that appears consecutively for at least three times.
	+-----------------+
	| ConsecutiveNums |
	+-----------------+
	| 1               |
	+-----------------+
*/


-- My Solution:
SELECT 
	DISTINCT num AS ConsecutiveNums 
FROM
	(
	SELECT
		num,
		@count := IF (num = @pre, @count + 1, 1) AS count,
		@pre := num AS pre 
	FROM
		LOGS,
		(SELECT @count := 0, @pre := NULL) AS var 
	) AS tmp 
WHERE
	count >= 3;


-- Reference1:
SELECT .
	DISTINCT Num AS ConsecutiveNums 
FROM
	(
	SELECT
		Num,
		CASE
			WHEN @prevRank = Num THEN @i := @i + 1 
			WHEN @prevRank := Num THEN @i := 1 
		END AS cc 
	FROM
		LOGS, (SELECT @prevRank := NULL,@i := 0) AS gg 
	) AS iss 
WHERE
	cc >= 3;
