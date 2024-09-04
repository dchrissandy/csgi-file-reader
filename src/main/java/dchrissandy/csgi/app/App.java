package dchrissandy.csgi.app;

public class App {
	public static void main(String[] args) {
		IndexFileSearch app = new IndexFileSearch();

		if (args.length > 0) {
			for (String fileName : args) {
				try {
					String fileContent = app.readFileContent(fileName);
					app.checkStartWithUppercase(fileContent, fileName);
					app.checkLengthLimitWords(fileContent, fileName);
				} catch (Exception e) {
					System.out.println("System can't read files properly, please check location of the file or its content");
				}
			}

			System.out.println("\n");
			System.out.println("Output the number of words that start with upper case letter in each file : ");
			app.printOutputUppercaseCounter();

			System.out.println("\n");
			System.out.println("List all the words that are longer than 5 characters long in each file : ");
			app.printOutputLimitLengthWords();
			
		} else {
			System.out.println("There's no parameter file name given, please give filenames as parameters to run the program");
		}

	}
}
