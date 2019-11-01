/*
 * https://leetcode-cn.com/problems/combine-two-tables/
 *
 * Reference: https://leetcode-cn.com/problems/combine-two-tables/solution/zu-he-liang-ge-biao-by-leetcode/

表1: Person

+-------------+---------+
| 列名        | 类型    |
+-------------+---------+
| PersonId    | int     |
| FirstName   | varchar |
| LastName    | varchar |
+-------------+---------+
PersonId 是上表主键

表2: Address

+-------------+---------+
| 列名        | 类型    |
+-------------+---------+
| AddressId   | int     |
| PersonId    | int     |
| City        | varchar |
| State       | varchar |
+-------------+---------+
AddressId 是上表主键
 

编写一个 SQL 查询，满足条件：无论 person 是否有地址信息，都需要基于上述两表提供 person 的以下信息：
	FirstName, LastName, City, State

----------------------------------------------------------------------------------------------------

Table: Person

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| PersonId    | int     |
| FirstName   | varchar |
| LastName    | varchar |
+-------------+---------+
PersonId is the primary key column for this table.

Table: Address

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| AddressId   | int     |
| PersonId    | int     |
| City        | varchar |
| State       | varchar |
+-------------+---------+
AddressId is the primary key column for this table.

Write a SQL query for a report that provides the following information for each person in the Person table, regardless if there is an address for each of those people: FirstName, LastName, City, State
*/

-- mine:
SELECT 
    FirstName, LastName, City, State
FROM 
    Person LEFT JOIN Address
ON 
    Person.PersonId = Address.PersonId;

/*
数据库在通过连接两张或多张表来返回记录时，都会生成一张中间的临时表，然后再将这张临时表返回给用户。 在使用left jion时，on和where条件的区别如下：

1、on条件是在生成临时表时使用的条件，它不管on中的条件是否为真，都会返回左边表中的记录。

2、where条件是在临时表生成好后，再对临时表进行过滤的条件。这时已经没有left join的含义（必须返回左边表的记录）了，条件不为真的就全部过滤掉。
*/


-- Reference1:
SELECT
	p.FirstName,
	p.LastName,
	a.City,
	a.State 
FROM
	Person p LEFT JOIN ( SELECT DISTINCT PersonId, City, State FROM Address ) a 
ON 
	p.PersonId = a.PersonId;

