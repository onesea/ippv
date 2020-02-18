#!/opt/bin/py
# -*- coding: utf-8 -*-

# & C:\Users\Max\AppData\Local\Programs\Python\Python39\python.exe -m pip install -U jinja2

from __future__ import print_function, absolute_import
from datetime import datetime, time, timedelta
#from jinja2 import Environment, PackageLoader, Template, select_autoescape

import sys
import phone

Tree = phone.PhoneTree(0,"root")

if __name__ == "__main__":
    global use_debug
    use_debug = '-debug' in sys.argv
    if use_debug:
      print("debug enabled")

    Tree.add("86","CHN")
    Tree.add("86755","sz")
    Tree.add("8675510","FT")
    print(Tree.parse("8618603000007"))
    print(Tree.parse("8675503000007"))
    print(Tree.parse("8675588900007"))
