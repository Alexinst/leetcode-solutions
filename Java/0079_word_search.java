/*
 * https://leetcode-cn.com/problems/word-search/
 *
 * Reference: https://leetcode-cn.com/problems/word-search/solution/zai-er-wei-ping-mian-shang-shi-yong-hui-su-fa-pyth/
 
给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

示例:
	board =
	[
	  ['A','B','C','E'],
	  ['S','F','C','S'],
	  ['A','D','E','E']
	]

	给定 word = "ABCCED", 返回 true.
	给定 word = "SEE", 返回 true.
	给定 word = "ABCB", 返回 false.

----------------------------------------------------------------------------------------------------

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:
	board =
	[
	  ['A','B','C','E'],
	  ['S','F','C','S'],
	  ['A','D','E','E']
	]

	Given word = "ABCCED", return true.
	Given word = "SEE", return true.
	Given word = "ABCB", return false.
*/


class MySolution {
    private int row, col;
    private boolean[][] marked;

    public boolean exist(char[][] board, String word) {
        row = board.length;
        col = board[0].length;
        if (row * col < word.length()) return false;

        marked = new boolean[row][col];
        char start = word.charAt(0);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == start &&
                        dfs(board, i, j, word.toCharArray(), 0))
                    return true;
            }
        }

        return false;
    }


    private boolean dfs(char[][] board, int i, int j, char[] word, int idx) {
        if (i < 0 || i >= row || j < 0 || j >= col ||
            idx >= word.length || marked[i][j])
            return false;

        if (board[i][j] == word[idx]) {
            marked[i][j] = true;

            if (idx == word.length - 1)
                return true;

            if (dfs(board, i, j - 1, word, idx + 1)) return true;
            if (dfs(board, i, j + 1, word, idx + 1)) return true;
            if (dfs(board, i - 1, j, word, idx + 1)) return true;
            if (dfs(board, i + 1, j, word, idx + 1)) return true;

            marked[i][j] = false;
        }

        return false;
    }
}


class Solution1 {
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0 || word.length() == 0) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        char[] chars = word.toCharArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0) && backtrack(board, i, j, 0, chars)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backtrack(char[][] board, int i, int j, int index, char[] word) {
        if (index == word.length) {
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[i].length) {
            return false;
        }
        if (board[i][j] == word[index]) {
            char temp = board[i][j];
            board[i][j] = '#';
            if (backtrack(board, i + 1, j, index + 1, word)
                    || backtrack(board, i - 1, j, index + 1, word)
                    || backtrack(board, i, j + 1, index + 1, word)
                    || backtrack(board, i, j - 1, index + 1, word)) {
                return true;
            }
            board[i][j] = temp;
        }

        return false;
    }
}

