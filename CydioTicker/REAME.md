CydioTicker applicaiton is designed to read files from a CUSIP feed to show the final prices for each item.

The input includes CUSIP ids in the format of alpha numeric of exactly 8 characters.  Followed by the prices for the day in cronological order.

The input can be from standard in or from a given set of file names on the command line.

Data from standard in is process and the results are written to standard out.

Data from a file is read from each file given and written to a file with the same name, but '.out' appended.

Output is in the format of CUSIP: price, for each CUSIP in the file.  Like:

12345678: 19.95
...
