import os
import re
import argparse
from bs4 import BeautifulSoup as bs
from config import (cpp_dir, java_dir, mysql_dir, max_num)


def check(html_file):
    if html_file != None and os.path.exists(html_file):
        with open(html_file, encoding='utf-8') as rfile:
            html = rfile.read()
        soup = bs(html, 'html.parser')
        tds = soup.find_all('td', attrs={"value": "ac"})
        solved = []
        for td in tds:
            solved.append(int(td.find_next_sibling('td').text))

        cpp_files = os.listdir(cpp_dir)
        java_files = os.listdir(java_dir)
        mysql_files = os.listdir(mysql_dir)

        # The list contains the number orders of all solved and archived problems.
        archived = [int(re.match(r'\d+', filename)[0]) for filename in cpp_files]
        archived += [int(re.match(r'\d+', filename)[0]) for filename in java_files]
        archived += [int(re.match(r'\d+', filename)[0] for filename in mysql_files)]
        archived = set(archived)

        tmp = [0] * max_num
        for no in archived:
            tmp[no - 1] = 1
        archived = tmp

        forgot = []
        for no in solved:
            if archived[no - 1] == 0:
                forgot.append(no)

        print('Problems solved but not archived: ')
        print(forgot)

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('-H', '--html', type=str,
                        help='the absolute path of html file')
    args = parser.parse_args()
    
    check(args.html)
    