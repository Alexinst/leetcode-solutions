/*
 * https://leetcode-cn.com/problems/rank-scores/

编写一个 SQL 查询来实现分数排名。如果两个分数相同，则两个分数排名（Rank）相同。请注意，平分后的下一个名次应该是下一个连续的整数值。换句话说，名次之间不应该有“间隔”。

	+----+-------+
	| Id | Score |
	+----+-------+
	| 1  | 3.50  |
	| 2  | 3.65  |
	| 3  | 4.00  |
	| 4  | 3.85  |
	| 5  | 4.00  |
	| 6  | 3.65  |
	+----+-------+

例如，根据上述给定的 Scores 表，你的查询应该返回（按分数从高到低排列）：

	+-------+------+
	| Score | Rank |
	+-------+------+
	| 4.00  | 1    |
	| 4.00  | 1    |
	| 3.85  | 2    |
	| 3.65  | 3    |
	| 3.65  | 3    |
	| 3.50  | 4    |
	+-------+------+

----------------------------------------------------------------------------------------------------

Write a SQL query to rank scores. If there is a tie between two scores, both should have the same ranking. Note that after a tie, the next ranking number should be the next consecutive integer value. In other words, there should be no "holes" between ranks.

	+----+-------+
	| Id | Score |
	+----+-------+
	| 1  | 3.50  |
	| 2  | 3.65  |
	| 3  | 4.00  |
	| 4  | 3.85  |
	| 5  | 4.00  |
	| 6  | 3.65  |
	+----+-------+

For example, given the above Scores table, your query should generate the following report (order by highest score):

	+-------+------+
	| Score | Rank |
	+-------+------+
	| 4.00  | 1    |
	| 4.00  | 1    |
	| 3.85  | 2    |
	| 3.65  | 3    |
	| 3.65  | 3    |
	| 3.50  | 4    |
	+-------+------+
*/


-- My Solution:
SELECT
	Score,
	CAST( Rank AS UNSIGNED ) AS Rank 
FROM
	(
	SELECT
		Score,
		@Rank := IF (Score = @pre, @Rank, @Rank + 1) AS Rank,
		@pre := Score AS pre 
	FROM
		(SELECT Score FROM Scores ORDER BY Score DESC) AS SortedScores,
	  (SELECT @Rank := 0, @pre := - 1) AS var 
	) AS tmp;


-- Reference1:
SELECT
	S.Score,
	CAST( @RANK := @RANK + ( @PRE <> ( @PRE := S.Score )) AS SIGNED INTEGER ) AS Rank 
FROM
	( SELECT * FROM Scores ORDER BY Score DESC, Id ) S,
	( SELECT @RANK := 0, @PRE := - 1 ) T;
