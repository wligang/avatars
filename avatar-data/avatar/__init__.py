#!/usr/bin/env python
# encoding: utf-8
'''
@author: Ligang.Wang
@license: (C) Copyright 2018-2022, wlgdo.com Manager Corporation Limited.
@contact: wlgchun@163.com
@file: __init__.py.py
@time: 2019/5/1 21:56
@desc:
'''
from avatar.blog import Avatar

if __name__ == '__main__':
    print('start begin the method...')
    avatar = Avatar('LeegooWang')
    avatar.getPage(avatar.username)
