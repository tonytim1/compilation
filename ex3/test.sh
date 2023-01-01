#!/bin/bash
rm output/o/*
for filename in ../Compilation_Tests/test_folder_proj3/input/test1/*.txt; do
    name=${filename##*/}
    java -jar COMPILER $filename "output/o/$name"
done
rename 's/test/output/' output/o/test*.txt