use strict;

print "Please enter the name of a file: ";
my $filename = <STDIN>;
chomp($filename);

open(my $fileHandle, '<', $filename)
    or die "unable to open file $filename.";

my %words;

while(<$fileHandle>){
    chomp;
    my @wordsFromLine = split(' ');
    foreach my $word (@wordsFromLine){
	$words{$word}++;
    }
}

foreach my $word(keys(%words)){
    printf ("\"$word\": %d\n", $words{$word});
}
