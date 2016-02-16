// Copyright: Baihan Lin, Baker Lab, doerlbh@gmail.com
// Date: Nov 2015

import java.io.*;     // for File, FileNotFoundException
import java.util.*;   // for Scanner, List, Set, Collections

public class bp_creator {

	private static final String TEMPBP = "template.bp";
	private static final String OFILE = "WC-test2";
	private static final String OUTP = "WC-test2";
	private static final String PATH = "/Users/DoerLBH/Dropbox/Eclipse/workspace/bp_creator/";
	private static final String STNL1 = "BLOCK1";
	private static final String STNL2 = "BLOCK2";
	private static final String LOOP = "0 x L";
	private static final String HELX = "0 x H";
	private static final int LOOPSMALL = 3;
	private static final int LOOPBIG = 8;
	private static final int EXT = 2;
	private static List<String> templateList = new ArrayList<String>();

	public static void main(String[] args) throws IOException {	

		String templateFile = TEMPBP;

		String outFile = OFILE;

		File path = new File(PATH);
		String dir = path.getPath();
		File oPath = new File(PATH + OUTP);
		if (!oPath.exists()) {
			if (oPath.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}
		String oDir = oPath.getPath();

		Map<String, ArrayList<String>> resultList = new TreeMap<String, ArrayList<String>>();

		@SuppressWarnings("resource")
		Scanner templateIn = new Scanner(new File(dir, templateFile));
		while (templateIn.hasNextLine()) {
			templateList.add(templateIn.nextLine());
		}

		int absCount = 0;
		for (int uLen = LOOPSMALL; uLen <= LOOPBIG; uLen++) {
			for (int lLen = LOOPSMALL; lLen <= LOOPBIG; lLen++) {
				for (int uuE = 0; uuE <= EXT; uuE++) {
					for (int ulE = 0; ulE <= EXT; ulE++) {
						for (int luE = 0; luE <= EXT; luE++) {
							for (int llE = 0; llE <= EXT; llE++) {
								absCount++;
								resultList.put(outFile + "-18A19B-18B19A-D" + String.format("%04d", absCount) 
								+ "-ULen" + uLen + "-lLen" + lLen + "-UE" + uuE + "-" + ulE + "-LE" + luE 
								+ "-" + llE + ".bp", createBP(uLen, lLen, uuE, ulE, luE, llE));
							}
						}
					}
				}
			}
		}

		for (String fout : resultList.keySet()) {

			File result = new File(oDir, fout);
			result.createNewFile(); 
			PrintStream output = new PrintStream(result);

			for (int r = 0; r < resultList.get(fout).size(); r++) {
				output.print(resultList.get(fout).get(r));
				output.println();
			}

			output.close();
			System.out.println("Finished! " + fout);
		}

	}

	private static ArrayList<String> createBP(int uLen, int lLen, int uuE, int ulE, int luE, int llE) {
		ArrayList<String> outputList = new ArrayList<String>();

		for (int t = 0; t < templateList.size(); t++) {
			if (templateList.get(t).endsWith(STNL1)) {
				outputList = addLine(outputList, HELX, uuE);
				outputList = addLine(outputList, LOOP, uLen);
				outputList = addLine(outputList, HELX, ulE);
			} else if (templateList.get(t).endsWith(STNL2)) {
				outputList = addLine(outputList, HELX, luE);
				outputList = addLine(outputList, LOOP, lLen);
				outputList = addLine(outputList, HELX, llE);
			} else {
				outputList.add(templateList.get(t));
			}
		}
		return  outputList;

	}

	private static ArrayList<String> addLine(ArrayList<String> list, String add, int t) {
		if (t > 0) {
			for (int i = 0; i < t; i++) {
				list.add(add);
			}
		}
		return list;
	}

}
