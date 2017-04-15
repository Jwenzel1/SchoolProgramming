/* 

A scanner for Homework 3 in CMSC 331
*/

%{
#include <math.h> /* needed for calls to atoi() and atof() */
%}

DIGIT    [0-9]
ALPHA    [a-zA-Z]
ALPHANUM [a-zA-Z0-9]
CHARACTER <|>|"/"
PUNCTUATION "?"|"."|"!"
%%
"http://"({ALPHANUM}|{CHARACTER}|{PUNCTUATION})+"com" printf("URL: %s\n",yytext);

{DIGIT}+                  printf("Integer: %s (%d)\n", yytext, atoi(yytext));

{CHARACTER}"html"{CHARACTER}   printf("Open html Tag: %s\n",yytext);

{CHARACTER}"title"{CHARACTER}   printf("Open title Tag: %s\n",yytext);

{CHARACTER}"body"{CHARACTER}   printf("Open body Tag: %s\n",yytext);

{CHARACTER}"bold"{CHARACTER}   printf("Open bold Tag: %s\n",yytext);

{CHARACTER}"pre"{CHARACTER}   printf("Open pre Tag: %s\n",yytext);

{CHARACTER}"P"{CHARACTER}   printf("Open P Tag: %s\n",yytext);

{CHARACTER}{CHARACTER}"html"{CHARACTER}   printf("Closing html Tag: %s\n",yytext);

{CHARACTER}{CHARACTER}"title"{CHARACTER}   printf("Closing title Tag: %s\n",yytext);

{CHARACTER}{CHARACTER}"body"{CHARACTER}   printf("Closing body Tag: %s\n",yytext);

{CHARACTER}{CHARACTER}"bold"{CHARACTER}   printf("Closing bold Tag: %s\n",yytext);

{CHARACTER}{CHARACTER}"pre"{CHARACTER}   printf("Closing pre Tag: %s\n",yytext);

{CHARACTER}{CHARACTER}"P"{CHARACTER}   printf("Closing P Tag: %s\n",yytext);

{CHARACTER}+{ALPHA}+{CHARACTER}   printf("No rule for this Tag: %s\n",yytext);

{ALPHA}{ALPHANUM}*        printf("Identifier: %s\n",yytext);

[ \t\n]+                  /* skip space, tab and newline */

{PUNCTUATION}             printf("Punctuation: %s\n",yytext);

.                         printf("Unrecognized: %s\n",yytext);

%%

main(argc,argv)
  int argc; char **argv;
  {
    if (argc>1) yyin=fopen(argv[1],"r"); else yyin=stdin;
    yylex();
  }
