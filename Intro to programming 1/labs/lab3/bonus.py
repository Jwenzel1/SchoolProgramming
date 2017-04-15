#File: lab3.py
#Written by: Joseph Wenzel
#Date: 2/26/13
#Section: 12
#Email: jwenzel1@umbc.edu
#Description: Lab 3, Cipher. Encoding/Decoding a string.
#             Use this as example for our homework in a few weeks

LETTERNUMBERS=26

def main():
    user_string = raw_input("Please input a string to encode: ")
    user_number = input("Enter a number to encode your words: ")
    all_caps = user_string.upper()
    for letter in all_caps:
        if letter != ' ':
            ascii = ord(letter)
            encrypted_letter = ascii+user_number%LETTERNUMBERS
            if encrypted_letter > ord('Z'):
               encrypted_letter= encrypted_letter-LETTERNUMBERS
            print chr(encrypted_letter),
            
main()
