# Tequila
A simple SQL Parser using Java

## Description
There are 3 table to be used in the form of CSV (Comma Seprated Value).

The tables are **penulis**, **buku**, and **menulis**.

The SQL Parser's feature limited to:
1. Selecting attribute(s) from a table
2. Selecting attribute(s) from two table using outer join
3. Display the error from incorrect SQL statement

## How to run
1. Clone this repository
2. Install gradle
3. Run the command below
```
    # give access to runner
    chmod +x run.sh
    # run the program
    ./run.sh
```
4. Input SQL statement to parse

## Depedencies
OpenCSV 4.5
