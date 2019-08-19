import re
import os
from config import java_dir, cpp_dir, readme_cn, readme_en


files_cpp = os.listdir(cpp_dir)
files_java = os.listdir(java_dir)
paths_code = [cpp_dir + '/' + filename for filename in files_cpp]
paths_code += [java_dir + '/' + filename for filename in files_java]


def check(readme):
    paths_readme = []
    with open(readme, encoding='utf-8') as rfile:
        for line in rfile.readlines():
            if line[0] == "|":
                try:
                    path = re.findall(r'/[0-9A-Za-z_~/\-\+\.]*', line)[0]
                    paths_readme.append(path[1:])
                except IndexError:
                    pass

    errors = []
    for path in paths_readme:
        if path not in paths_code:
            errors.append(path)
    print(readme, ': ', errors)


if __name__ == "__main__":
    print('Path errors in README:')
    check(readme_cn)
    check(readme_en)
