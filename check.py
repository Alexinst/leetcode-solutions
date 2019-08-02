"""
Check if any code files haven't been recorded in README
and print their serial numbers out.
"""

import os
import re

# sns = serial numbers
cpp_files = os.listdir('C&C++')
java_files = os.listdir('Java')
sns_file = [re.match(r'\d+', filename)[0] for filename in cpp_files]
sns_file += [re.match(r'\d+', filename)[0] for filename in java_files]
sns_file = set(sns_file)

file = 'README.md'
sns_readme = []
with open(file, encoding='utf-8') as rfile:
    for line in rfile.readlines():
        if line[0] == '|':
            try:
                num = re.findall(r'\d+', line)[0]
                sns_readme.append(num)
            except IndexError:
                pass

sns_readme = set(sns_readme)
forgot = []
for serial_num in sns_file:
    if serial_num not in sns_readme:
        forgot.append(serial_num)
print(forgot)
