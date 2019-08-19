from bs4 import BeautifulSoup as bs
import os
import re


all = 1063
cpp_files = os.listdir('C&C++')
java_files = os.listdir('Java')
nums_file = [int(re.match(r'\d+', html_file)[0]) for html_file in cpp_files]
nums_file += [int(re.match(r'\d+', html_file)[0]) for html_file in java_files]
nums_file = set(nums_file)

html_file = os.path.join("E:/leetcode.html")
with open(html_file, encoding='utf-8') as rfile:
    html = rfile.read()
soup = bs(html, 'html.parser')
tds = soup.find_all('td', attrs={"value": "ac"})
have_been_implemented = []
for td in tds:
    have_been_implemented.append(int(td.find_next_sibling('td').text))

# tmp = [0] * all
# for num in nums_file:
#     tmp[num - 1] = 1
# nums_file = tmp

forgot = []
# for num in have_been_implemented:
#     if nums_file[num - 1] == 0:
#         forgot.append(num)
for num in have_been_implemented:
    if num not in nums_file:
        forgot.append(num)
print(forgot)
