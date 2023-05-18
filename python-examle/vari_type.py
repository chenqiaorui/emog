"""
输入a，b，转换成正数
%d表示整数占位符，进行a+b

Author: Ricky
"""
a = int(input('a = '))
b = int(input('b = '))

print('%d + %d = %d' % (a, b, a + b)) # 10 + 3 = 13
print('%d / %d = %d' % (a, b, a / b)) # 10 / 3 = 3，本来输出浮点数的，但是被%d转化了
print('%d // %d = %d' % (a, b, a // b)) # 10 // 3 = 3