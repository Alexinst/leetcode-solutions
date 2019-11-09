/*
 * https://leetcode-cn.com/problems/nth-highest-salary/

编写一个 SQL 查询，获取 Employee 表中第 n 高的薪水（Salary）。
	+----+--------+
	| Id | Salary |
	+----+--------+
	| 1  | 100    |
	| 2  | 200    |
	| 3  | 300    |
	+----+--------+
	
例如上述 Employee 表，n = 2 时，应返回第二高的薪水 200。如果不存在第 n 高的薪水，那么查询应返回 null。
	+------------------------+
	| getNthHighestSalary(2) |
	+------------------------+
	| 200                    |
	+------------------------+

----------------------------------------------------------------------------------------------------

Write a SQL query to get the nth highest salary from the Employee table.
	+----+--------+
	| Id | Salary |
	+----+--------+
	| 1  | 100    |
	| 2  | 200    |
	| 3  | 300    |
	+----+--------+

For example, given the above Employee table, the nth highest salary where n = 2 is 200. If there is no nth highest salary, then the query should return null.
	+------------------------+
	| getNthHighestSalary(2) |
	+------------------------+
	| 200                    |
	+------------------------+
*/


-- My Solution:
CREATE FUNCTION getNthHighestSalary ( N INT ) RETURNS INT 
BEGIN 
	DECLARE os INT;
	
	IF N < 1 THEN
        return NULL
	ELSE
		SELECT N - 1 INTO os;
	END IF;
			
	RETURN ( # Write your MySQL query statement below.
		SELECT 
			IFNULL(
			  (SELECT DISTINCT Salary FROM Employee ORDER BY Salary DESC LIMIT 1 OFFSET os),
			  NULL
			) AS getNthHighestSalary
	);
END;


-- Reference1:
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
	DECLARE p INT DEFAULT N - 1;
	
	IF p < 0 THEN
			RETURN NULL;
	ELSE 
	    RETURN (SELECT 
			    (SELECT DISTINCT Salary 
					FROM Employee 
					ORDER BY Salary DESC LIMIT 1 OFFSET p) 
					AS getNthHighestSalary);
	END IF;
END
