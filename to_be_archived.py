import os
import re
import argparse
from bs4 import BeautifulSoup as bs
from config import cpp_dir, java_dir, num_all


def check(html_file):
    if html_file != None and os.path.exists(html_file):
        with open(html_file, encoding='utf-8') as rfile:
            html = rfile.read()
        soup = bs(html, 'html.parser')
        tds = soup.find_all('td', attrs={"value": "ac"})
        solved = []
        for td in tds:
            solved.append(int(td.find_next_sibling('td').text))

        files_cpp = os.listdir(cpp_dir)
        files_java = os.listdir(java_dir)
        nums_code = [int(re.match(r'\d+', filename)[0]) for filename in files_cpp]
        nums_code += [int(re.match(r'\d+', filename)[0]) for filename in files_java]
        nums_code = set(nums_code)

        tmp = [0] * num_all
        for num in nums_code:
            tmp[num - 1] = 1
            nums_code = tmp

        forgot = []
        for num in solved:
            if nums_code[num - 1] == 0:
                forgot.append(num)

        print('Problems solved but not archived: ')
        print(forgot)

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('-H', '--html', type=str,
                        help='the absolute path of html file')
    args = parser.parse_args()
    
    check(args.html)
    