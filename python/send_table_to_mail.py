# -*- coding: UTF-8 -*-

import time, datetime
import os, sys
import requests

'''
发送表格到指定邮件
'''

template = """
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></head>
<style type="text/css">
table {
    border-collapse: collapse;
    width: 100%%;
    text-align: center;
}

table, th, td {
    border: 1px solid #4D4D4D;
}
</style>
<body>
<table>
%s
</table>
<body>
</html>
"""
column_names = ["日期","pv","uv"]
mail_list = "sangzehui@sangzh.com"

def log(msg):
    msg = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()) + ' ' + msg
    os.system('echo ' + msg)

def main(date):
    data1 = column_names
    data2 = ["20200101", "100", "10"]
    data = data1.split('\t') + data2.split('\t')[1:]
    header = "<tr>%s</tr>" % reduce(lambda x,y: x+y, map(lambda x: "<th>%s</th>" % x, column_names))
    content = "<tr>%s</tr>" % reduce(lambda x,y: x+y, map(lambda x: "<td>%s</td>" % x, data))
    html = template % (header + content)
    print html
    cmd = """echo '%s' | mutt -e "my_hdr Content-Type: text/html" -s '标题' %s""" % (html, mail_list)
    r = os.system(cmd)
    if r:
        log("success")
    else:
        log("fail")
        os.exit(1)

if __name__ == '__main__':
    date = sys.argv[1]
    main(date)

