/*
 * https://leetcode-cn.com/problems/classes-more-than-5-students/

有一个courses 表 ，有: student (学生) 和 class (课程)。

请列出所有超过或等于5名学生的课。

例如,表:

+---------+------------+
| student | class      |
+---------+------------+
| A       | Math       |
| B       | English    |
| C       | Math       |
| D       | Biology    |
| E       | Math       |
| F       | Computer   |
| G       | Math       |
| H       | Math       |
| I       | Math       |
+---------+------------+

应该输出:

+---------+
| class   |
+---------+
| Math    |
+---------+

----------------------------------------------------------------------------------------------------

There is a table courses with columns: student and class

Please list out all classes which have more than or equal to 5 students.

For example, the table:

+---------+------------+
| student | class      |
+---------+------------+
| A       | Math       |
| B       | English    |
| C       | Math       |
| D       | Biology    |
| E       | Math       |
| F       | Computer   |
| G       | Math       |
| H       | Math       |
| I       | Math       |
+---------+------------+

Should output:

+---------+
| class   |
+---------+
| Math    |
+---------+
*/


-- My Solution:
SELECT
	class 
FROM
	courses 
GROUP BY
	class 
HAVING
	count( DISTINCT student ) >= 5;


