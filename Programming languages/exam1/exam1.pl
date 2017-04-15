#!/usr/bin/perl
use warnings;
use strict;
use Cwd;

my $inputFile = $ARGV[0];
chomp($inputFile);

open(my $fh, '<', $inputFile) 
    or die "$inputFile is not a file.\n"; 

my %documentNames = ();
my %totalTermsInDoc = ();
my %docsWithTerm = ();
my $totalDocs = 0;
my $programHomeDirectory = getcwd();

foreach my $line (<$fh>){
    my @keyValue;
    if(substr($line, 0, 1) ne "#" && $line ne "\n"){
	$line =~ s/\n//g;
	push(@keyValue, split(':', $line));
	
	if($keyValue[0] eq "filedir"){
	    %documentNames = ();
	    %totalTermsInDoc = ();
	    %docsWithTerm = ();
	    $totalDocs = 0;
	    changeToDirectory($keyValue[0], $keyValue[1]);
	    opendir(DIRHANDLE, getcwd());
	    
	    #Begin counting how many of each word is in each Doc
	    foreach my $fileName (readdir(DIRHANDLE)){
		if(-f $fileName){
		    $totalDocs++;
		}
		open(my $lines, '<', $fileName) 
		    or die "Could not open $fileName.\n";
		foreach my $line (<$lines>){
		    while ($line =~ /(\w+)/ig){
			$documentNames{$fileName}{lc($1)}++;
		    }
		}
	    }
	    #End counting
	    
	    #Begin getting total terms in document
	    foreach my $document (keys %documentNames){
		foreach my $term (keys %{$documentNames{$document}}){
		    $totalTermsInDoc{$document} += $documentNames{$document}{$term};
		}
	    }
	    #End total terms
	    
	    closedir(DIRHANDLE);
	    chdir($programHomeDirectory);
	}

	elsif($keyValue[0] eq "tfoutput"){
	    open(my $fh, '>', $keyValue[1]) 
		or die "Couldn't open $keyValue[1]... Sorry\n";
	    foreach my $document (sort keys %documentNames){
		foreach my $term (sort keys %{$documentNames{$document}}){
		    my $tf = $documentNames{$document}{$term}/$totalTermsInDoc{$document};
		    printf $fh "%s,%s,%.10f\n",$document,$term,$tf;
		}
	    }
	    close $fh;
	}

	elsif($keyValue[0] eq "idfoutput"){
	    open(my $fh, '>', $keyValue[1]) 
		or die "Couldn't open $keyValue[1]... Sorry\n";
	    foreach my $document (sort keys %documentNames){
		foreach my $term (sort keys %{$documentNames{$document}}){
		    $docsWithTerm{$term}++;
		}
	    }
	    foreach my $term (sort keys %docsWithTerm){
		    my $idf = log($totalDocs/$docsWithTerm{$term});
		    printf $fh "%s,%.10f\n",$term,$idf;
	    }
	    close $fh;
	}
	
	elsif($keyValue[0] eq "tf-idfoutput"){
	    open(my $fh, '>', $keyValue[1]) 
		or die "Couldn't open $keyValue[1]... Sorry\n";
	    foreach my $document (sort keys %documentNames){
		foreach my $term (sort keys %{$documentNames{$document}}){
		    my $tfidf =$documentNames{$document}{$term}/$totalTermsInDoc{$document} * log($totalDocs/$docsWithTerm{$term});
		    printf $fh "%s,%s,%.10f\n",$document,$term,$tfidf;
		}
	    }
	    close $fh;
	}
    }
}

#this function takes in the directory to change to
#and returns a directory handle for use if the value was "filedir"
sub changeToDirectory{
    my $key = $_[0];
    my $value = $_[1];
    
    if($key eq "filedir"){
	chdir($value);
	$value = getcwd();
    }
}

