use strict;

print "Please enter the name of a file: ";
my $filename = <STDIN>;
chomp($filename);

open(my $readFileHandle, '<', $filename)
    or die "Unable to open file $filename.";

open(my $writeFileHandle, '>', "Copy_$filename")
    or die "Unable to open a copy of $filename to write to.";

print "Please enter a search phrase: ";
my $searchPhrase = <STDIN>;
chomp($searchPhrase);

print "Please enter a replacement phrase: ";
my $replacementPhrase = <STDIN>;
chomp($replacementPhrase);

while(my $line = <$readFileHandle>){
    chomp($line);
    $line =~ s/$searchPhrase/$replacementPhrase/g;
    print $writeFileHandle $line . "\n";
}
