#153 is the first residue of the second chain
#-rg 0, do not use radius of gyration for elongated structures
#cst is a distance constraint file, see bp file and cst for formatting
~possu/src/git/Rosetta/main/source/bin/remodel.static.linuxgccrelease -s joint_renum.pdb -remodel:blueprint test7-C001-1-1-18A19B_renum_0001-ext_ends_0_loop_len_4_Amin2-18B19A_renum_0001-ext_ends_-1_loop_len_4_Aplu.bp -two_chain_tree 153 \
-num_trajectory 5 -save_top 5  -no_jumps -overwrite -rg 0 -out:prefix test1 -jd2:no_output -enzdes:cstfile cst \
-core_cutoff 20 -boundary_cutoff 40 -remodel:use_pose_relax 
#-remodel:use_cart_relax -remodel:quick_and_dirty 