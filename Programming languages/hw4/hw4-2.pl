use strict;

print "Please enter the first number to use: ";
my $N1 = <STDIN>;
chomp($N1);

print "Please enter a second number to use: ";
my $N2 = <STDIN>;
chomp($N2);

if($N1 > $N2 || $N2 - $N1 <= 98){
    die "INVALID INPUT. The first number must be less than the second number 
and the difference between those two numbers must be at least 99.
Rerun the program and enter different inputs.\n"
}

my $magicNum = int($N1 + rand($N2));
#print "$magicNum\n";
print "It's time to play GUESS THE MAGIC NUMBER!!!!
The number is between the two numbers you gave so get guessing!
You can also enter the word \"exit\" or \"quit\" or hit enter with 
no input to be a quitter.\n";

while(1){
    print "Enter your guess: ";
    my $guess = <STDIN>;
    
    if($guess == "\n" || chomp($guess) == "quit" || $guess == "exit"){
	exit(0);
    }
    elsif($guess > $magicNum){
	print "Too high\n";
    }
    elsif($guess < $magicNum){
	print "Too low\n";
    }
    else{
	print "Correct!!!\n";
	exit(0);
    }
}
