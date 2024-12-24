#!/bin/bash

file=IntCalculator.java

if [ ! -f "$file" ]; then
    echo -e "Error: File '$file' not found.\nTest failed."
    exit 1
fi

num_right=0
total=0
extra_credit=0
ec_correct=0
line="________________________________________________________________________"
compiler=
interpreter=
language=
if [ "${file##*.}" = "py" ]; then
    if [ ! -z "$PYTHON_PATH" ]; then
        interpreter=$(which python.exe)
    else
        interpreter=$(which python3.2)
    fi
    command="$interpreter $file"
    echo -e "Testing $file\n"
elif [ "${file##*.}" = "java" ]; then
    language="java"
    command="java ${file%.java}"
    echo -n "Compiling $file..."
    javac *.java
    echo -e "done\n"
fi

run_test_args() {
    (( ++total ))
    echo -n "Running test $total..."
    expected=$2
    received=$( $command "$1" 2>&1 | tr -d '\r' )
    if [ "$expected" = "$received" ]; then
        echo "success"
        (( ++num_right ))
    else
        echo -e "failure\n\nExpected$line\n$expected\nReceived$line\n$received\n"
    fi
}

run_test_args_ec() {
    (( ++extra_credit ))
    echo -n "Running extra credit test $extra_credit..."
    expected=$2
    received=$( $command "$1" 2>&1 | tr -d '\r' )
    if [ "$expected" = "$received" ]; then
        (( ++ec_correct))
        echo "success"
    else
        echo -e "failure\n\nExpected$line\n$expected\nReceived$line\n$received\n"
    fi
}

run_test_args "5" "Postfix expression: 5"$'\n'"Evaluation:         5"
run_test_args "-5" "Postfix expression: 5 ~"$'\n'"Evaluation:         -5"
run_test_args "-5 + 5" "Postfix expression: 5 ~ 5 +"$'\n'"Evaluation:         0"
run_test_args "--5 - --5 + 5" "Postfix expression: 5 ~ ~ 5 ~ ~ - 5 +"$'\n'"Evaluation:         5"
run_test_args "-5+5-5" "Postfix expression: 5 ~ 5 + 5 -"$'\n'"Evaluation:         -5"
run_test_args "-5^3" "Postfix expression: 5 ~ 3 ^"$'\n'"Evaluation:         -125"
run_test_args "-(5^3)" "Postfix expression: 5 3 ^ ~"$'\n'"Evaluation:         -125"
run_test_args "5 x 6 / 7" "Postfix expression: 5 6 x 7 /"$'\n'"Evaluation:         4"
run_test_args "(12 - 6) / 2" "Postfix expression: 12 6 - 2 /"$'\n'"Evaluation:         3"
run_test_args "(12 - 12) / (1 x 2)" "Postfix expression: 12 12 - 1 2 x /"$'\n'"Evaluation:         0"
run_test_args "(2 x (15 - 13))^(5 % 3)" "Postfix expression: 2 15 13 - x 5 3 % ^"$'\n'"Evaluation:         16"
run_test_args "1 x (2 x (3 x (4 + 5) % 6) x 7) / 8 - 9^2" "Postfix expression: 1 2 3 4 5 + x 6 % x 7 x x 8 / 9 2 ^ -"$'\n'"Evaluation:         -76"
run_test_args "(124 / 2) / (5 - 5)" "Postfix expression: 124 2 / 5 5 - /"$'\n'"Error:              Cannot evaluate expression, division by zero."
run_test_args "(124 / 2) % (5 - 5)" "Postfix expression: 124 2 / 5 5 - %"$'\n'"Error:              Cannot evaluate expression, division by zero."
run_test_args "(3 + -3)^(-4 + 4)" "Postfix expression: 3 3 ~ + 4 ~ 4 + ^"$'\n'"Error:              Cannot evaluate expression, 0^0 is undefined."
run_test_args "((17 - 1) x 4)^2 / 64" "Postfix expression: 17 1 - 4 x 2 ^ 64 /"$'\n'"Evaluation:         64"
run_test_args "(2   +  4)  ^ 3" "Postfix expression: 2 4 + 3 ^"$'\n'"Evaluation:         216"
run_test_args "(-3 + 3) + (-4 + 5)^3 x 8" "Postfix expression: 3 ~ 3 + 4 ~ 5 + 3 ^ 8 x +"$'\n'"Evaluation:         8"
run_test_args "1 + 2 x 3 + 4 + 5 x 6 / 7" "Postfix expression: 1 2 3 x + 4 + 5 6 x 7 / +"$'\n'"Evaluation:         15"
run_test_args "-123 + 21 x --32 - (4 + 5)^(1 + 1) / 4" "Postfix expression: 123 ~ 21 32 ~ ~ x + 4 5 + 1 1 + ^ 4 / -"$'\n'"Evaluation:         529"

run_test_args_ec "4^3^2" "Postfix expression: 4 3 2 ^ ^"$'\n'"Evaluation:         262144"
run_test_args_ec "(1+1)^(3+2)^(2x1)" "Postfix expression: 1 1 + 3 2 + 2 1 x ^ ^"$'\n'"Evaluation:         33554432"

echo -e "\nTotal tests run     : $total"
echo -e "Number correct      : $num_right"
echo -e "Extra credit correct: $ec_correct"
echo -n "Percent correct     : "
echo "scale=2; 100 * $num_right / $total + 5 * $ec_correct" | bc

if [ "$language" = "java" ]; then
   echo -e -n "\nRemoving class files..."
   rm -f *.class
   echo "done"
fi
