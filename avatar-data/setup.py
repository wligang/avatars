#!/usr/bin/env python
# encoding: utf-8
'''
@author: Ligang.Wang
@license: (C) Copyright 2018-2022, wlgdo.com Manager Corporation Limited.
@contact: wlgchun@163.com
@file: setup.py
@time: 2019/4/30 22:01
@desc:
'''

from setuptools import setup, find_packages

setup(
    name="avatar",
    version="0.1",
    packages=find_packages('avatar'),
    scripts=['avatar/blog.py'],

    # Project uses reStructuredText, so ensure that the docutils get
    # installed or upgraded on the target machine
    install_requires=['docutils>=0.3'],
    dependency_links=['beautifulsoup4==4.7.1'],

    package_data={
        # If any package contains *.txt or *.rst files, include them:
        '': ['*.txt', '*.rst'],
        # And include any *.msg files found in the 'hello' package, too:
        'hello': ['*.msg'],
    },

    # metadata for upload to PyPI
    author="Ligang.Wang",
    author_email="wlgchun@163.com",
    description="获取单个用户的CSDN画像数据",
    license="PSF",
    keywords="获取csdn的用户画像数据",
    url="https://github.com/wligang/avatars",
)
