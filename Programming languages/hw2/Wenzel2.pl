#!/usr/bin/perl

#This takes in a string as a parameter. The string will be printed out when 
#prompting for input
sub getInput{
    print($_[0]);
    my $input = <STDIN>;
    return $input;
}

sub total{
    my $total = sum(@_);
    $runningTotal += $total;
    return ($total, $runningTotal, reverse @_);
}

sub sum{
    my $sum = 0;
    foreach my $num (@_){
	$sum += $num;
    }
    return $sum;
}

$userString = getInput("Please enter a string: ");
$userNumber = getInput("Please enter a number: ");
print("$userString" x $userNumber ** 2);
@results = total(4,3,2,1);
print "@results\n";
@results = total(8,7,6,5);
print "@results\n";
