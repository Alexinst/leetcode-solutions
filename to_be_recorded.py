"""
Check if any code files haven't been recorded in README
and print their numbers out.
"""

import os
import re
from config import cpp_dir, java_dir, readme_cn, readme_en, all


files_cpp = os.listdir(cpp_dir)
files_java = os.listdir(java_dir)
nums_code = [int(re.match(r'\d+', filename)[0]) for filename in files_cpp]
nums_code += [int(re.match(r'\d+', filename)[0]) for filename in files_java]
nums_code = set(nums_code)
print("Number of problems solved: %d\n" % len(nums_code))
print("Problems to be recorded: ")


def check(file):
    nums_readme = []
    with open(file, encoding='utf-8') as rfile:
        for line in rfile.readlines():
            if line[0] == '|':
                try:
                    num = re.findall(r'\d+', line)[0]
                    nums_readme.append(int(num))
                except IndexError:
                    pass

    nums_readme = sorted(nums_readme)
    tmp = [0] * all
    for num in nums_readme:
        tmp[num - 1] = 1
    nums_readme = tmp

    forgot = []
    for num in nums_code:
        if nums_readme[num - 1] == 0:
            forgot.append(num)
    print("%s: \t" % os.path.basename(file), len(forgot), forgot)


if __name__ == "__main__":
    check(readme_cn)
    check(readme_en)
