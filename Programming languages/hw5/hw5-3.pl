#!usr/bin/perl

print "Please enter the number of seconds since Jan 1, 1970: ";
$time = <STDIN>;
chomp($time);

($sec,$min,$hour,$mday,$mon,$year,$wday,$yday,$isdst) = gmtime($time);

if($wday == 0 || $wday == 6){
    print "Relax my friend.\n";
}
else{
    print "Get to work!!!!\n"
}
