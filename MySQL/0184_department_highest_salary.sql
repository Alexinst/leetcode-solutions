/*
 * https://leetcode-cn.com/problems/department-highest-salary/

Employee 表包含所有员工信息，每个员工有其对应的 Id, salary 和 department Id。
	+----+-------+--------+--------------+
	| Id | Name  | Salary | DepartmentId |
	+----+-------+--------+--------------+
	| 1  | Joe   | 70000  | 1            |
	| 2  | Henry | 80000  | 2            |
	| 3  | Sam   | 60000  | 2            |
	| 4  | Max   | 90000  | 1            |
	+----+-------+--------+--------------+
	
Department 表包含公司所有部门的信息。
	+----+----------+
	| Id | Name     |
	+----+----------+
	| 1  | IT       |
	| 2  | Sales    |
	+----+----------+
	
编写一个 SQL 查询，找出每个部门工资最高的员工。例如，根据上述给定的表格，Max 在 IT 部门有最高工资，Henry 在 Sales 部门有最高工资。
	+------------+----------+--------+
	| Department | Employee | Salary |
	+------------+----------+--------+
	| IT         | Max      | 90000  |
	| Sales      | Henry    | 80000  |
	+------------+----------+--------+

----------------------------------------------------------------------------------------------------

The Employee table holds all employees. Every employee has an Id, a salary, and there is also a column for the department Id.
	+----+-------+--------+--------------+
	| Id | Name  | Salary | DepartmentId |
	+----+-------+--------+--------------+
	| 1  | Joe   | 70000  | 1            |
	| 2  | Jim   | 90000  | 1            |
	| 3  | Henry | 80000  | 2            |
	| 4  | Sam   | 60000  | 2            |
	| 5  | Max   | 90000  | 1            |
	+----+-------+--------+--------------+
	
The Department table holds all departments of the company.
	+----+----------+
	| Id | Name     |
	+----+----------+
	| 1  | IT       |
	| 2  | Sales    |
	+----+----------+

Write a SQL query to find employees who have the highest salary in each of the departments. For the above tables, your SQL query should return the following rows (order of rows does not matter).
	+------------+----------+--------+
	| Department | Employee | Salary |
	+------------+----------+--------+
	| IT         | Max      | 90000  |
	| IT         | Jim      | 90000  |
	| Sales      | Henry    | 80000  |
	+------------+----------+--------+

Explanation: Max and Jim both have the highest salary in the IT department and Henry has the highest salary in the Sales department.
*/

-- My Solution:
SELECT
	d.NAME AS Department,
	e.NAME AS Employee,
	e.Salary 
FROM
	Employee AS e
	INNER JOIN Department AS d ON e.DepartmentId = d.Id 
WHERE
	(e.DepartmentId, Salary) IN (SELECT DepartmentId, MAX(Salary) FROM Employee GROUP BY DepartmentId);

-- 用 INNER JOIN 而不是 LEFT JOIN：
-- 当 Department 表为空时，将会出错
