 #!/usr/bin/perl
use strict;

print "Please enter a string: ";
my $string = <STDIN>;
chomp($string);

print "Please enter a substring: ";
my $subString = <STDIN>;
chomp($subString);
my $offset = 0;

my $result = index($string, $subString, $offset);

if($result != -1){
    while ($result != -1) {
    
	print "$result\n";
	$offset = $result + 1;
	$result = index($string, $subString, $offset);
    }
} 
else {
    print "The given substring was not found in the given string.\n" 
}
