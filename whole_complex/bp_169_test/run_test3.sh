#153 is the first residue of the second chain
#-rg 0, do not use radius of gyration for elongated structures
#cst is a distance constraint file, see bp file and cst for formatting

for i in *.bp
do
		~possu/src/git/Rosetta/main/source/bin/remodel.static.linuxgccrelease -s joint_renum.pdb -remodel:blueprint $i -two_chain_tree 153 \
-num_trajectory 5 -save_top 5  -no_jumps -overwrite -rg 0 -out:prefix test3 -jd2:no_output -enzdes:cstfile cst \
-core_cutoff 20 -boundary_cutoff 40 -remodel:use_pose_relax             
done


#-remodel:use_cart_relax -remodel:quick_and_dirty 