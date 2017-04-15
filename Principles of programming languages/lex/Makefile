# You have to comment the proper lines below according to the
# scanner and parser generators available in your system

LEX = lex 
#LEX = flex 

# We assume that your C-compiler is called cc

CC = cc

all: JosephScanner

# 'make' will generate the scanner execuable

scanner: lex.yy.c
	$(CC) -o JosephScanner lex.yy.c -lfl

## this is the rule to generate the file lex.yy.c from our file scanner.l

lex.yy.c: JosephScanner.l
	$(LEX) JosephScanner.l

## 'make clean' removes the generated files

clean:	
	rm -f lex.yy.c scanner 
