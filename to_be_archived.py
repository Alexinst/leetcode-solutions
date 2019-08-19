import os
import re
from bs4 import BeautifulSoup as bs
from config import html_file, cpp_dir, java_dir, all


if html_file != "":
    with open(html_file, encoding='utf-8') as rfile:
        html = rfile.read()
    soup = bs(html, 'html.parser')
    tds = soup.find_all('td', attrs={"value": "ac"})
    have_been_implemented = []
    for td in tds:
        have_been_implemented.append(int(td.find_next_sibling('td').text))

    files_cpp = os.listdir(cpp_dir)
    files_java = os.listdir(java_dir)
    nums_code = [int(re.match(r'\d+', filename)[0]) for filename in files_cpp]
    nums_code += [int(re.match(r'\d+', filename)[0]) for filename in files_java]
    nums_code = set(nums_code)

    tmp = [0] * all
    for num in nums_code:
        tmp[num - 1] = 1
        nums_code = tmp

    forgot = []
    for num in have_been_implemented:
        if nums_code[num - 1] == 0:
            forgot.append(num)
    # for num in have_been_implemented:
    #     if num not in nums_code:
    #         forgot.append(num)
    print('Problems solved but not archived: ')
    print(forgot)
