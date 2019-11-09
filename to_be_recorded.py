"""
Check if any code files haven't been recorded in README
and print their numbers.
"""

import os
import re
from config import (cpp_dir, java_dir, mysql_dir, max_num,
                    readme_alg_cn, readme_alg_en, readme_db_cn)


cpp_files = os.listdir(cpp_dir)
java_files = os.listdir(java_dir)
mysql_files = os.listdir(mysql_dir)

# the number orders of algorithm problems solved.
alg_no = [int(re.match(r'\d+', filename)[0]) for filename in cpp_files if filename[-3:] == 'cpp']
alg_no += [int(re.match(r'\d+', filename)[0]) for filename in java_files if filename[-4:] == 'java']
alg_no = set(alg_no)

print("Number of the algorithm problems solved: %d" % len(alg_no))

# The number orders of database problems solved.
db_no = [int(re.match(r'\d+', filename)[0]) for filename in mysql_files if filename[-3:] == 'sql']
db_no = set(db_no)

print("Number of the database problems solved: %d\n" % len(db_no))


def alg_check(readme_file):
    """
    Check the readme file recording the algorithm problems.
    """
    check(alg_no, readme_file)


def db_check(readme_file):
    """
    Check the readme file recording the database problems.
    """
    check(db_no, readme_file)


def check(nos, readme_file):
    records = getRecords(readme_file)

    tmp = [0] * max_num
    for no in records:
        tmp[no - 1] = 1
    records = tmp

    forgot = []
    for no in nos:
        if records[no - 1] == 0:
            forgot.append(no)
    print("%s: \t" % os.path.basename(readme_file), len(forgot), sorted(forgot))


def getRecords(readme_file):
    records = []
    with open(readme_file, encoding='utf-8') as rfile:
        for line in rfile.readlines():
            if line[0] == '|':
                try:
                    num = re.findall(r'\d+', line)[0]
                    records.append(int(num))
                except IndexError:
                    pass

    return records

if __name__ == "__main__":
    alg_check(readme_alg_cn)
    alg_check(readme_alg_en)

    db_check(readme_db_cn)
