use strict;

if(scalar @ARGV == 0){
    die "Usage: hw4-4.pl <arg1> <arg2> ... <argN>";
}

my $time = -1;
my $modFile;
foreach my $file (@ARGV){
    my $modTime = -M $file;
    if ($modTime > $time){
	$modFile = $file;
	$time = $modTime;
    }
}

$time = int $time;
print "Oldest file is $modFile which was last modified $time days ago.\n";
