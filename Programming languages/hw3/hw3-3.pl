use strict;

print "Please enter the name of a file: ";
my $filename = <STDIN>;
chomp($filename);

open(my $fileHandle, '<', $filename)
    or die "unable to open file $filename.";

print "Please enter the first search phrase: ";
my $firstPhrase = <STDIN>;
chomp($firstPhrase);

print "Please enter the second search phrase: ";
my $secondPhrase = <STDIN>;
chomp($secondPhrase);

while(my $line = <$fileHandle>){
    chomp($line);
    if($line =~ /$firstPhrase/ or $line =~ /$secondPhrase/){
	print "$line\n";
    }
}
