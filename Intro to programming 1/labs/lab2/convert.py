#File:        convert.py
#Written by:  Joseph Wenzel
#Date:        5/19/13
#Lab Section: 12
#UMBC email:  jwenzel1@umbc.edu
#Description: This program converts degrees Celsius to degrees Farenheit

def main():

    degC = input('What is the temperature in degrees celsius?  ')

    degF = (9.0/5.0) * degC + 32

    print ('The temperature is %.2f degrees celsius.'%degF)

main
