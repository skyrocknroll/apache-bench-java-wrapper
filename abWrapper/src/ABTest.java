import com.canvera.pvss.*;

public class ABTest {

	/**
	 * @param args
	 */
	static int incrementalValue = 100;

	public static void main(String[] args) {

		CreateCommandString cs = new CreateCommandString();
		Extractor ext = new Extractor();
		WriteData wd = new WriteData();
		cs.setNumberOfRequests("1000");
		//,"500kb.jpg","1mb.jpg","5mb.jpg","10mb.jpg","20mb.jpg","40mb.jpg"
		// "50kb.jpg","100kb.jpg","500kb.jpg","1mb.jpg","5mb.jpg","10mb.jpg","20mb.jpg","40mb.jpg"
		for (String fileToUpload : new String[] {"50kb.jpg","100kb.jpg","500kb.jpg","1mb.jpg"}) {
		//for (String fileToUpload : new String[] {"50kb.jpg"}) {
			cs.setFileToUpload(fileToUpload);
			cs.setFileToTest(fileToUpload);
			String basePath = cs.getPathToTest();
			wd.intialize(cs.getFileToUpload()+"-"+"data.tsv", "/srv/out/final/");
			for (int i = incrementalValue; i <= Integer.parseInt(cs.getNumberOfRequests()); i = i + incrementalValue) {
				cs.setConcurrentRequests(new Integer(i).toString());
				//cs.setPathToTest(basePath + cs.getFileToUpload().replaceAll(".jpg", "") + cs.getNumberOfRequests()
				//		+ "/" + cs.getConcurrentRequests() + "/");
				System.out.println(cs.getConcurrentRequests());
				cs.runTest(cs);
				ext.setFileToUpload(cs.getFileToUpload());
				ext.setConcurrentRequests(cs.getConcurrentRequests());
				ext.setNumberOfRequests(cs.getNumberOfRequests());
				ext.setPathToOutputFiles(cs.getOutputDirectory());
				wd.setLines(ext.extractusefulInfo());
				wd.parseAndWriteData();

				cs.setPathToTest(basePath);

			}
			wd.close();
		}
		// Extract the meaningful data from the ab Output files

	}

}
