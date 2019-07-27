/*
 * https://leetcode-cn.com/problems/simplify-path/submissions/

以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。

在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 v(https://blog.csdn.net/u011327334/article/details/50355600)

请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。

示例 1：
	输入："/home/"
	输出："/home"
	解释：注意，最后一个目录名后面没有斜杠。

示例 2：
	输入："/../"
	输出："/"
	解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。

示例 3：
	输入："/home//foo/"
	输出："/home/foo"
	解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。

示例 4：
	输入："/a/./b/../../c/"
	输出："/c"

示例 5：
	输入："/a/../../b/../c//.//"
	输出："/c"

示例 6：
	输入："/a//b////c/d//././/.."
	输出："/a/b/c"

-----------------------------------------------------------------------------------------------------------------------

Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.

In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level. For more information, see: Absolute path vs relative path in Linux/Unix

Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.

Example 1:
	Input: "/home/"
	Output: "/home"
	Explanation: Note that there is no trailing slash after the last directory name.

Example 2:
	Input: "/../"
	Output: "/"
	Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.

Example 3:
	Input: "/home//foo/"
	Output: "/home/foo"
	Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.

Example 4:
	Input: "/a/./b/../../c/"
	Output: "/c"

Example 5:
	Input: "/a/../../b/../c//.//"
	Output: "/c"

Example 6:
	Input: "/a//b////c/d//././/.."
`	Output: "/a/b/c"
*/

class MySolution {
    public String simplifyPath(String path) {
        Stack<String> blocks = new Stack<>();
        int len = path.length();
        int i = 0, start = 0;
        while (i < path.length()) {
            // 舍去重复的 '/'
            while (i < len && path.charAt(i) == '/') {
                start = i;
                i++;
            }
            
            if (i + 1 < len && path.charAt(i + 1) == '/' || i == len - 1) {
                String block = path.substring(start, i + 1);
                
                // block 不会等于 "/", 所以 block 的值只有三种情况："/." "/.." "/xxxxx"
                if (block.equals("/..")) {
                     if (!blocks.isEmpty()) 
                         blocks.pop();
                }
                else if (!block.equals("/."))
                    blocks.push(block);
            }
            i++;
        }

        String simPath = toPath(blocks);
        if (simPath.equals(""))
            return "/";
        return simPath;
    }

    private String toPath(Stack<String> blocks) {
        StringBuilder path = new StringBuilder();
        for (String block : blocks)
            path.append(block);
        
        return path.toString();
    }
}

class Solution1 {
    public String simplifyPath(String path) {
        int len = path.length();
        LinkedList<String> s = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            if (path.charAt(i) != '/') {
                int nextIndex = path.indexOf('/', i);
                String filename = nextIndex == -1 ? path.substring(i) : path.substring(i, nextIndex);
                if (filename.equals("..")) {
                    if (!s.isEmpty())
                        s.poll();
                } else if (!filename.equals("."))
                    s.addFirst(filename);
                i = nextIndex;
                if (i == -1)
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!s.isEmpty()) {
            sb.append("/" + s.removeLast());
        }
        String res = sb.toString();
        return res.equals("") ? "/" : res;
    }
}
