#!/bin/bash

variation=("A" "B" "C")
depth=(0.05 0.25)
randomSeed=1
editStrategy=('15:5:5:5:25:25:20' '15:5:10:35:0:20:15' '20:10:20:0:30:10:10')

cd ..
cd emf-graph-gen
cd graph-gentool

for v in ${variation[@]}; do
    for d in ${depth[@]}; do
        for strat in ${editStrategy[@]}; do

            strategy=""

            if [ $strat = '15:5:5:5:25:25:20' ]
            then
                    strategy="balanced"
            fi

            if [ $strat = '15:5:10:35:0:20:15' ]
            then
                    strategy="dragdrop"
            fi

            if [ $strat = '20:10:20:0:30:10:10' ]
            then
                    strategy="crud"
            fi

            outname="${v}_${randomSeed}_${d}_${strategy}_IDS"
            echo $outname
            gradle run --args="../../examples/data/${outname} -c 3 -d 0.1 -f 0.75 -n 2 -r ${d} -s 250 -l 20 -x -u ${randomSeed} -o ${strat} -i"
            
            outname="${v}_${randomSeed}_${d}_${strategy}_NOIDS"
            echo $outname
            gradle run --args="../../examples/data/${outname} -c 3 -d 0.1 -f 0.75 -n 2 -r ${d} -s 250 -l 20 -x -u ${randomSeed} -o ${strat}"

            randomSeed=$((randomSeed+1))
        done
    done
done
