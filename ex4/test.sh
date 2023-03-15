#!/bin/bash

rm -rf bin/*
make

for path in input/TEST*.txt
do
    name=${path##*/}
    extension="${name##*.}"
    filename="${name%.*}"
    java -jar COMPILER input/$name output/1 > /dev/null
    spim -f output/MIPS.txt > output/MIPS_OUTPUT.txt
    output="expected_output/${filename}_EXPECTED_OUTPUT.txt"
    #cat $output
    echo $name
    diff --ignore-all-space output/MIPS_OUTPUT.txt $output
    echo "-------------------------------"
done



