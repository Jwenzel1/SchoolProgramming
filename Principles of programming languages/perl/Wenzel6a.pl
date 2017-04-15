#!/usr/bin/perl
require HTML::LinkExtor;
print "Enter the website to get links from: ";
$website = <>;
chomp($website);
#$content = get($website);
$p = HTML::LinkExtor->new(\&callback);
sub callback{
    my($tag, %links) = @_;
    print "$tag @{[%links]}\n";
}
$p->parse_file(\$website);
