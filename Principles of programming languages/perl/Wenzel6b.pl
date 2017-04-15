#!/usr/bin/perl
use Compress::Zlib;
use LWP::Simple;

print "Enter the first website: ";
$link1 = <>;
chomp($link1);

print "Enter the second website to compare to the first: ";
$link2 = <>;
chomp($link2);

$site1 = get($link1);
$site2 = get($link2);

$comS1 = compress($site1);
$comS2 = compress($site2);
$comCon = compress($site1.$site2);

if(length($comS1) > length($comS2))
{
    $max = length($comS1);
    $min = length($comS2);
}

else
{
    $max = length($comS2);
    $min = length($comS1);
}
$NCD = (length($comCon) - $min) / $max;
print "NCD of $link1 and $link2 is $NCD\n";
