/*
 * https://leetcode-cn.com/problems/delete-duplicate-emails/

编写一个 SQL 查询，来删除 Person 表中所有重复的电子邮箱，重复的邮箱里只保留 Id 最小 的那个。

+----+------------------+
| Id | Email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
| 3  | john@example.com |
+----+------------------+
Id 是这个表的主键。

例如，在运行你的查询语句之后，上面的 Person 表应返回以下几行:

+----+------------------+
| Id | Email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
+----+------------------+


----------------------------------------------------------------------------------------------------


+----+------------------+
| Id | Email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
| 3  | john@example.com |
+----+------------------+
Id is the primary key column for this table.

For example, after running your query, the above Person table should have the following rows:

+----+------------------+
| Id | Email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
+----+------------------+

Note: Your output is the whole Person table after executing your sql. Use delete statement.
*/


-- My Solution:
DELETE FROM
	person 
WHERE
	id NOT IN (
	SELECT
		id 
	FROM
		(
		SELECT
			id,
			email,
			@isFirst := IF ( @pre = email, 1, 0 ) AS isFirst,
			@pre := email AS pre 
		FROM
			person, ( SELECT @pre := NULL, @isFirst := 0 ) var 
		ORDER BY
			email,
			id 
		) tmp 
	WHERE
		isFirst = 0 
	);
	

-- Reference1:
DELETE FROM
	person 
WHERE
	id NOT IN (
	SELECT
		id 
  FROM
	  (SELECT min(id) AS id FROM person GROUP BY email) AS p);  
