use strict;

print "Please enter the name of a file: ";
my $filename = <STDIN>;
chomp($filename);

open(my $fileHandle, '<', $filename)
    or die "unable to open file $filename.";

my @lines;
while(my $line = <$fileHandle>){
    push(@lines, $line);
}

my $lineNum = 1;
foreach my $line (reverse @lines){
    print $lineNum++ . ': ' . $line;
}
