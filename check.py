"""
Check if any code files haven't been recorded in README
and print their numbers out.
"""

import os
import re


all = 1126
cpp_files = os.listdir('C&C++')
java_files = os.listdir('Java')
nums_file = [re.match(r'\d+', filename)[0] for filename in cpp_files]
nums_file += [re.match(r'\d+', filename)[0] for filename in java_files]
nums_file = [int(nums) for nums in set(nums_file)]
print("numbers: %d" % len(nums_file))

tmp = [0] * all
for num in nums_file:
    tmp[num - 1] = 1
nums_file = tmp


def check(file):
    nums_readme = []
    with open(file, encoding='utf-8') as rfile:
        for line in rfile.readlines():
            if line[0] == '|':
                try:
                    num = re.findall(r'\d+', line)[0]
                    nums_readme.append(num)
                except IndexError:
                    pass

    nums_readme = [int(nums) for nums in set(nums_readme)]
    forgot = []
    for num in nums_readme:
        if nums_file[num - 1] == 0:
            forgot.append(num)
    print("%s:" % os.path.basename(file), sorted(forgot))


if __name__ == "__main__":
    root_dir = os.getcwd()
    readme_cn = os.path.join(root_dir, 'README.md')
    readme_en = os.path.join(root_dir, 'README.en.md')
    check(readme_cn)
    check(readme_en)
