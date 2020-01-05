#!/opt/bin/py
# -*- coding: utf-8 -*-

# & C:\Users\Max\AppData\Local\Programs\Python\Python39\python.exe -m pip install -U jinja2

from __future__ import print_function, absolute_import
from datetime import datetime, time, timedelta
#from jinja2 import Environment, PackageLoader, Template, select_autoescape

import sys
import math
import random

class PhoneTree:
  def __init__(self, type=0, iso=None):
    self.type = type
    self.iso = iso
    self.child = [None,None,None,None,None,None,None,None,None,None]

  def add(self, cc, iso):
    i = 0
    root = self
    for c in cc:
      i += 1
      index = ord(c) - ord('0')
      node = root.child[index]
      if node is None:
        root.child[index] = PhoneTree()
        node = root.child[index]
      if i < len(cc):
        root = node
      else:
        node.type = 1
        node.iso = iso

  def parse(self, phone):
    i = 0
    ln = 0
    ld = 0
    ac = 0
    iso = None

    root = self
    for c in phone:
      i += 1
      index = ord(c) - ord('0')
      node = root.child[index]
      if node is None:
        break
      if node.type == 1:
        if ln == 0:
          ln = i
        elif ld == 0:
          ld = i
        else:
          ac = i
        iso = node.iso
      root = node

    return (ln,ld,ac,iso)

Tree = PhoneTree(0,"root")

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