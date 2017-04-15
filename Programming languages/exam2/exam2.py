#!/usr/bin/python
import sys
import os
import re
from math import log

documentNames = {}
totalTermsInDoc = {}
docsWithTerm = {}
totalDocs = 0
programHomeDirectory = os.getcwd()
#expression = re.compile(r'\b(\w+)\b', re.IGNORECASE)
expression = re.compile(r'[^\s|\.|\!|\?|\-|,|"]+', re.IGNORECASE)

inputFile = sys.argv[1].rstrip()
fileHandle = open(inputFile, 'r')

for line in fileHandle:
    keyValue = []
    line = line.rstrip()
    
    if(line != "" and line[0] != "#"):
        keyValue.extend(line.split(":"))
        #begin counting every word in every document
        
        if(keyValue[0] == "filedir"):
            documentNames = {}
            totalTermsInDoc = {}
            docsWithTerm ={}
            totalDocs = 0
            os.chdir(keyValue[1])
            
            for play in os.listdir(os.getcwd()):
                if os.path.isfile(play):
                    documentNames[play] = {}
                    totalTermsInDoc[play] = 0
                    totalDocs += 1
                    openedPlay = open(play, 'r')
                    
                    for line in openedPlay:
                        line = line.rstrip()
                        parsedLine = [word.lower() for word in expression.findall(line)]
                        
                        for word in parsedLine:
                            if word in documentNames[play]:
                                documentNames[play][word] += 1
                            else:
                                documentNames[play][word] = 1
                    openedPlay.close()
                    
                    for word in documentNames[play]:
                        totalTermsInDoc[play] += documentNames[play][word]
            
            os.chdir(programHomeDirectory)
            #end counting every word in every document
        
        elif(keyValue[0] == "tfoutput"):
            fh = open(keyValue[1], 'w')
            
            for document in sorted(documentNames):
                for term in sorted(documentNames[document]):
                    tf = float(documentNames[document][term])/float(totalTermsInDoc[document])
                    fh.write("%s,%s,%.10f\n" %(document, term, tf))
            fh.close()

        elif(keyValue[0] == "idfoutput"):
            for document in sorted(documentNames):
                for word in sorted(documentNames[document]):
                    if word in docsWithTerm:
                        docsWithTerm[word] += 1
                    else:
                        docsWithTerm[word] = 1
            
            fh = open(keyValue[1], 'w')
            for word in sorted(docsWithTerm):
                idf = log(float(totalDocs)/float(docsWithTerm[word]))
                fh.write("%s,%.10f\n" %(word, idf))

            fh.close()

        elif(keyValue[0] == "tf-idfoutput"):
            fh = open(keyValue[1], 'w')
            for document in sorted(documentNames):
                for term in sorted(documentNames[document]):
                    tfidf = (float(documentNames[document][term])/float(totalTermsInDoc[document])) * log(float(totalDocs)/float(docsWithTerm[term]))
                    fh.write("%s,%s,%.10f\n" %(document,term,tfidf))
            
            fh.close()
fileHandle.close()
