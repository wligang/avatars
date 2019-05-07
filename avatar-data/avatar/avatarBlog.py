#!/usr/bin/env python
# encoding: utf-8
'''
@author: Ligang.Wang
@license: (C) Copyright 2018-2022, wlgdo.com Manager Corporation Limited.
@contact: wlgchun@163.com
@file: blog.py
@time: 2019/5/1 21:56
@desc:
'''
import sys
import json
import time

import redis
import urllib
#import urllib.request
from bs4 import BeautifulSoup

print('This is a csdn blog data python application')


class Avatar(object):
    def __init__(self, username):
        super(Avatar, self).__init__()
        self.baseUrl = 'https://blog.csdn.net/%s/article/list/' % username;
        self.pool = redis.ConnectionPool(host='127.0.0.1', password='')
        self.redis = redis.Redis(connection_pool=self.pool)
        self.username = username

    def main(self):
        print('Welcome to my CSDN:[Eg:https://blog.csdn.net/LeegooWan]')

    def getPage(self, username):
        url = 'https://blog.csdn.net/' + username + '/article/list?orderby=ViewCount'
        html = urllib.urlopen(url).read().decode('utf-8')
        soup = BeautifulSoup(html, "lxml")
        self.getArticlesInPage(html)

    def getArticlesInPage(self, html):
        soup = BeautifulSoup(html, "lxml")
        asideProfile = soup.find(id='asideProfile').find(id='uid')
        avatarUrl = soup.find(class_='avatar_pic').get('src')
        fansNum = soup.find(id="fanBox").get('title')
        nikename = asideProfile.text
        accInfos = soup.find(class_="grade-box").find_all('dl')
        grade = accInfos[0].find("a").get("title")
        invitNum = accInfos[1].find('dd').get('title')
        scordNum = accInfos[2].find('dd').get('title')
        orderNum = accInfos[3].get('title')

        csdnUserInfo = {}
        csdnUserInfo['userName'] = self.username
        csdnUserInfo['avatarUrl'] = soup.find(class_='avatar_pic').get('src')
        csdnUserInfo['nickname'] = asideProfile.text
        csdnUserInfo['fansNum'] = soup.find(id="fanBox").get('title')
        csdnUserInfo['grade'] = accInfos[0].find("a").get("title")
        csdnUserInfo['interview'] = accInfos[1].find('dd').get('title')
        csdnUserInfo['integration'] = accInfos[2].find('dd').get('title')
        csdnUserInfo['ranking'] = accInfos[3].get('title')

        csdnUserInfo['articles'] = []
        articleList = soup.find(
            class_='article-list').find_all(class_='article-item-box')

        for articleIter in articleList:
            # 这里竟然反爬虫
            if articleIter.has_attr('style') and u'display: none;' == articleIter['style']:
                print('This is a csdn fan pachong setting')
                continue
            article = articleIter.find('a')
            href = article.get('href')
            title = article.get_text("|", strip=True)[2:]
            articleItem = {}
            articleItem['title'] = title
            articleItem['href'] = href

            interviews = articleIter.find_all(class_='read-num')
            i = 0
            for views in interviews:
                num = views.find(class_='num').text
                if i == 0:
                    articleItem['interview'] = num
                else:
                    articleItem['moment'] = num
                i += 1

            csdnUserInfo['articles'].append(articleItem)

        localtime = time.asctime(time.localtime(time.time()))
        csdnUserInfo['dataTime'] = localtime
        self.redis.set('userInfo_' + self.username, json.dumps(csdnUserInfo, indent=2))
        print('user`s info set redis successful')


if __name__ == '__main__':
    username = sys.argv[1]
    avatar = Avatar(username)
    avatar.getPage(avatar.username)
