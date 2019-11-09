import re
import os
from config import (java_dir, cpp_dir, mysql_dir,
                    readme_alg_cn, readme_alg_en, readme_db_cn)


cpp_files = os.listdir(cpp_dir)
java_files = os.listdir(java_dir)
alg_file_paths = [cpp_dir + '/' + filename for filename in cpp_files]
alg_file_paths += [java_dir + '/' + filename for filename in java_files]

mysql_files = os.listdir(mysql_dir)
db_file_paths = [mysql_dir + '/' + filename for filename in mysql_files]


def alg_check(readme_file):
    """
    Check if there are any path errors in the readme file recording the algorithm problems.
    """    
    check(alg_file_paths, readme_file)


def db_check(readme_file):
    """
    Check if there are any path errors in the readme file recording the database problems.
    """      
    check(db_file_paths, readme_file)


def check(file_paths, readme_file):
    paths_readme = []
    with open(readme_file, encoding='utf-8') as rfile:
        for line in rfile.readlines():
            if line[0] == "|":
                try:
                    path = re.findall(r'/[0-9A-Za-z_~/\-\+\.]*', line)[0]
                    paths_readme.append(path[1:])
                except IndexError:
                    pass

    errors = []
    for path in paths_readme:
        if path not in file_paths:
            errors.append(path)
    print(readme_file, ': ', errors)


if __name__ == "__main__":
    print('Path errors in README:')
    alg_check(readme_alg_cn)
    alg_check(readme_alg_en)
    db_check(readme_db_cn)
