import argparse

parser = argparse.ArgumentParser(description='Say hello')
parser.add_argument('name', help='Name to greet')
args = parser.parse_args()
print('Hello, ' + args.name + '!')

"""
==出现 partially initialized module 'argparse' has no attribute 'ArgumentParser' (most likely due to a circular import)

原理：文件名和导入包名称重复了

==执行
python .\tiny-python-example\argparse_example.py anta

返回：Hello, anta!

==执行
python .\tiny-python-example\argparse_example.py -h

返回：
···
usage: argparse_example.py [-h] name

Say hello

positional arguments:
  name        Name to greet

options:
  -h, --help  show this help message and exit
···
"""
up https://github.com/kyclark/tiny_python_projects/blob/master/01_hello/hello05_argparse_option.py