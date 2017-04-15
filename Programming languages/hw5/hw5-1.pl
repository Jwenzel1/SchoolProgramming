#!/usr/bin/perl
use strict;
use Cwd;

print "Please enter a directory path to change to: ";
my $directory = <STDIN>;
chomp($directory);

print "Please enter a regular expression: ";
my $regEx = <STDIN>;
chomp($regEx);

if($directory =~ /^\s*$/){
    chdir();
    $directory = getcwd();
}
else{
    chdir($directory);
    $directory = getcwd();
}

if($!){
    warn "Unable to change into directory $directory.";
}
else{
    opendir(DIRHANDLE, $directory);

    my @files;
    foreach my $file (readdir(DIRHANDLE)){
	if($file =~ /$regEx/){
	    push @files, $file;
	}
    }
    
    foreach my $file (sort @files){
	print "$file\n";
    }
}
closedir(DIRHANDLE);
