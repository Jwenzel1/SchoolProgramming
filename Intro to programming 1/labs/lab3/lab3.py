#File: lab3.py
#Written by: Joseph Wenzel
#Date: 2/26/13
#Section: 12
#Email: jwenzel1@umbc.edu
#Description: Lab 3, Cipher. Encoding/Decoding a string.
#             Use this as example for our homework in a few weeks


def main():
    user_input = raw_input("Please input a string to encode")
    all_caps = user_input.upper()
    for letter in all_caps:
        if letter != ' ':
            ascii = ord(letter)
            encrypted_letter = ascii+13
            if encrypted_letter > ord('Z'):
               encrypted_letter= encrypted_letter-26
            print chr(encrypted_letter),
            
main()
