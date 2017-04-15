use strict;

print "Please enter the expression to use: ";
my $expression = <STDIN>;
chomp($expression);

print "Please enter the name of a file: ";
my $filename = <STDIN>;
chomp($filename);

open(my $fileHandle, '<', $filename)
    or die "unable to open file $filename.";

while(my $line = <$fileHandle>){
    chomp($line);
    if($line =~ /($expression){5}/){
	print "$line\n";
    }
}
