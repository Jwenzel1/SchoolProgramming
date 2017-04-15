use strict;

print "Please enter the name of a file: ";
my $filename = <STDIN>;
chomp($filename);

open(my $fileHandle, '<', $filename)
    or die "unable to open file $filename.";

while(my $line = <$fileHandle>){
    chomp($line);
    my $string = "File $line is ";
    if(-e $line){
	if(-r $line){
	    $string .= "Readable | ";
	} else {
	    $string .= "Not Readable | ";
	}
	if(-w $line){
	    $string .= "Writeable | ";
	} else {
	    $string .= "Not Writeable | ";
	}
	if(-x $line){
	    $string .= "Executable";
	} else {
	    $string .= "Not Executable";
	}
    } else {
	$string .= "Nonexistent";
    }
    print "$string\n";
}
