#!/usr/bin/perl
use strict;
use Cwd;

print "Please enter a directory path to change to: ";
my $directory = <STDIN>;
chomp($directory);

if($directory =~ /^\s*$/){
    chdir();
    $directory = getcwd();
}
else{
    chdir($directory);
    $directory = getcwd();
}

opendir(DIRHANDLE, $directory);

my @files;

foreach my $file (readdir(DIRHANDLE)){
    push @files, $file;
}

foreach my $file (@files){
    (my $accessEpoch, my $modEpoch) = (stat($file))[8,9];
    my @accessDate = (localtime($accessEpoch))[5] + 1900;
    my @accessDateList = reverse ((localtime($accessEpoch))[0 .. 4]);
    $accessDateList[0]++;
    push (@accessDate, @accessDateList);
    

    print "$file $accessEpoch ";
    foreach my $element (@accessDate){
	if($element < 10){
	    print "0".$element;
	}
	else{
	    print $element;
	}
    }

    my @modDate = (localtime($modEpoch))[5] + 1900;
    my @modDateList = reverse ((localtime($modEpoch))[0 .. 4]);
    $modDateList[0]++;
    push (@modDate, @modDateList);
    
    print " $modEpoch ";
    foreach my $element (@modDate){
	if($element < 10){
	    print "0".$element;
	}
	else{
	    print $element;
	}
    }
    print "\n";
}
