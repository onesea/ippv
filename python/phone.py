#!/opt/bin/py
# -*- coding: utf-8 -*-

# & C:\Users\Max\AppData\Local\Programs\Python\Python39\python.exe -m pip install -U jinja2

from __future__ import print_function, absolute_import
from datetime import datetime, time, timedelta
#from jinja2 import Environment, PackageLoader, Template, select_autoescape

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

