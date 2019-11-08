/*
 * https://leetcode-cn.com/problems/exchange-seats/ 

小美是一所中学的信息科技老师，她有一张 seat 座位表，平时用来储存学生名字和与他们相对应的座位 id。

其中纵列的 id 是连续递增的

小美想改变相邻俩学生的座位。

你能不能帮她写一个 SQL query 来输出小美想要的结果呢？
 

示例：
	+---------+---------+
	|    id   | student |
	+---------+---------+
	|    1    | Abbot   |
	|    2    | Doris   |
	|    3    | Emerson |
	|    4    | Green   |
	|    5    | Jeames  |
	+---------+---------+
	
假如数据输入的是上表，则输出结果如下：
	+---------+---------+
	|    id   | student |
	+---------+---------+
	|    1    | Doris   |
	|    2    | Abbot   |
	|    3    | Green   |
	|    4    | Emerson |
	|    5    | Jeames  |
	+---------+---------+

注意：如果学生人数是奇数，则不需要改变最后一个同学的座位。

----------------------------------------------------------------------------------------------------

Mary is a teacher in a middle school and she has a table seat storing students' names and their corresponding seat ids.

The column id is continuous increment.

Mary wants to change seats for the adjacent students.

Can you write a SQL query to output the result for Mary?
 

	+---------+---------+
	|    id   | student |
	+---------+---------+
	|    1    | Abbot   |
	|    2    | Doris   |
	|    3    | Emerson |
	|    4    | Green   |
	|    5    | Jeames  |
	+---------+---------+
	
For the sample input, the output is:
	+---------+---------+
	|    id   | student |
	+---------+---------+
	|    1    | Doris   |
	|    2    | Abbot   |
	|    3    | Green   |
	|    4    | Emerson |
	|    5    | Jeames  |
	+---------+---------+

Note: If the number of students is odd, there is no need to change the last one's seat.
*/

-- Reference1:
SELECT
    IF (id % 2 = 0, id - 1, IF (id = cnt, id, id + 1)) AS id,
	student 
FROM
	seat,
	(SELECT count(*) AS cnt FROM seat) AS count 
ORDER BY
	id;


-- Reference2:
SELECT 
    ( CASE WHEN id % 2 = 0 
		   THEN id - 1 
           WHEN id = (SELECT max(id) FROM seat) 
           THEN id ELSE id + 1 
           END 
    ) AS id,
		student 
	FROM
		seat 
ORDER BY
	id;


-- Reference3:
SELECT
	a.id,
	ifnull(b.student, a.student) AS student 
FROM
	seat AS a LEFT JOIN seat AS b 
	ON (a.id % 2 = 1 && a.id = b.id - 1) || (a.id % 2 = 0 && a.id = b.id + 1) 
ORDER BY
	a.id;
