#!/usr/bin/perl
@files = <*.txt>;

#Answer to problem 1
foreach $myfile (@files){
    if(-f $myfile and -r $myfile){
	open $file, $myfile;
	$firstLine = <$file>;
	close $file;
	print $firstLine;
    }
}
print "\n";

#Answer to problem 3
%hashTable = ();
foreach $myfile (@files){
    if(-f $myfile and -r $myfile){
        open $file, $myfile;
        while (<$file>){
	    chomp;
	    @words = split(' ');
	    foreach $word (@words){
		$word =~ s/[^A-Za-z0-9-]//g;
		$word = lc($word);
		$hashTable{$word} += 1;
	    }
	}
        close $file;
    }
}
foreach $word (%hashTable){
    if($hashTable{$word} > 5000){
	print "'$word' appears more than 5000 times!\n";
    }
}
#Answer to problem 2
print "\nThe word 'forsooth' appears $hashTable{'forsooth'} times!\n";

#Answer to problem 4
$i = 1;
print "\nTime for the ten most common words in the Shakespeare corpus!\n";
foreach $word (sort{$hashTable{$b} <=> $hashTable{$a}} keys %hashTable){
    if($i <= 10){
	print "Coming in at number $i is '$word' with $hashTable{$word} occurrences!\n";
	$i++;
    }
}
