package dchrissandy.csgi.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexFileSearch { 
	
	private static int MIN_LENGTH_CHAR_TO_DISPLAY = 5;	
	private static String SPACE_VALUE = " ";
	
	private Map<String, Integer> mapUppercaseCounter;
	private Map<String, List<String>> mapLimitLengthWords;
	
	public void printOutputUppercaseCounter() {
		if (mapUppercaseCounter != null) {
			for (Map.Entry<String, Integer> entry : mapUppercaseCounter.entrySet()) {
				System.out.println("File name: " + entry.getKey() + " , words start with uppercase: " + entry.getValue() + " words");
			}
		} else {
			System.out.println("There's no words start with uppercase, or file can't be read by system properly");
		}
	}
	
	public void printOutputLimitLengthWords () {
		if (mapLimitLengthWords != null) { 
			for (Map.Entry<String, List<String>> entry : mapLimitLengthWords.entrySet()) {
				System.out.println("File name: " + entry.getKey());
				System.out.println("Words list: ");
				
				for (String word: entry.getValue()) {
					System.out.print(word + " ");
				}
				
				System.out.println("\n");
			}
		} else {
			System.out.println("There's no words with more than limit characters, or file can't be read by system properly");
		}
	}
	
	public String readFileContent (String fileName) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		try {
			// first line read
			String singleLine = br.readLine();

			while (singleLine != null) {
			    sb.append(singleLine);
			    singleLine = br.readLine();
			}
			
		} finally {
		    br.close();
		}
		
		return sb.toString();
	}

	public void checkStartWithUppercase(String fileContent, String fileName) {	
		int counter = 0;
		
		String[] words = fileContent.split(SPACE_VALUE);
		for (String word : words) {
			if (Character.isUpperCase(word.charAt(0))) {
				counter++;
			}
		}
		
		if (mapUppercaseCounter == null) {
			mapUppercaseCounter = new HashMap<String, Integer>();
		}
		
		mapUppercaseCounter.put(fileName, counter);
	}
	
	public void checkLengthLimitWords(String fileContent, String fileName) {
		List<String> wordList = new ArrayList<String>();
		
		String[] words = fileContent.split(SPACE_VALUE);
		for (String word : words) {
			if (word.length() > MIN_LENGTH_CHAR_TO_DISPLAY) {
				wordList.add(word);
			}
		}
		
		if (mapLimitLengthWords == null) {
			mapLimitLengthWords = new HashMap<String, List<String>>();
		}
		
		mapLimitLengthWords.put(fileName, wordList);
	}
	
	
	// getter
	public Map<String, Integer> getMapUppercaseCounter() {
		return mapUppercaseCounter;
	}

	public Map<String, List<String>> getMapLimitLengthWords() {
		return mapLimitLengthWords;
	}

}
