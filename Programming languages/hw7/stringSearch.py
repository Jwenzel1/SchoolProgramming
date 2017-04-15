import re

string = "We the People of the United States, in Order to form a more perfect Union, establish Justice, insure domestic Tranquility, provide for the common defence, promote the general Welfare, and secure the Blessings of Liberty to ourselves and our Posterity, do ordain and establish this Constitution for the United States of America."

expression = re.compile("\w*ty", re.IGNORECASE)
ty = expression.findall(string)
print(ty)

expression = re.compile(r"\bp\w*\b", re.IGNORECASE)
p = expression.findall(string)
print(p)

expression = re.compile(r"\bthe\b", re.IGNORECASE)
the = expression.findall(string) 
print(the)
