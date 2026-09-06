import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TextAnalysisTool {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== TEXT ANALYSIS TOOL =====");
        System.out.println("Enter a paragraph or lengthy text:");

        String text = scanner.nextLine().trim();

        while (text.isEmpty()) {
            System.out.println("Input cannot be empty. Please enter some text:");
            text = scanner.nextLine().trim();
        }

        String[] rawWords = text.split("\\s+");
        int characterCount = text.length();

        Map<Character, Integer> characterFrequency = new HashMap<>();

        for (char character : text.toLowerCase().toCharArray()) {
            characterFrequency.put(
                character,
                characterFrequency.getOrDefault(character, 0) + 1
            );
        }

        char mostCommonCharacter = text.toLowerCase().charAt(0);
        int highestCharacterFrequency = 0;

        for (Map.Entry<Character, Integer> entry : characterFrequency.entrySet()) {
            if (entry.getValue() > highestCharacterFrequency) {
                mostCommonCharacter = entry.getKey();
                highestCharacterFrequency = entry.getValue();
            }
        }

        Map<String, Integer> wordFrequency = new HashMap<>();
        Set<String> uniqueWords = new HashSet<>();

        for (String rawWord : rawWords) {
            String word = rawWord
                    .toLowerCase()
                    .replaceAll("^[^\\p{L}\\p{N}']+|[^\\p{L}\\p{N}']+$", "");

            if (!word.isEmpty()) {
                wordFrequency.put(
                    word,
                    wordFrequency.getOrDefault(word, 0) + 1
                );
                uniqueWords.add(word);
            }
        }

        System.out.println();
        System.out.println("===== ANALYSIS RESULTS =====");
        System.out.println("Total characters: " + characterCount);
        System.out.println("Total words: " + rawWords.length);
        System.out.println(
            "Most common character: '" + mostCommonCharacter
            + "' (" + highestCharacterFrequency + " occurrences)"
        );

        System.out.print("Enter a character to find its frequency: ");
        String characterInput = scanner.nextLine().trim();

        while (characterInput.length() != 1) {
            System.out.print(
                "Please enter exactly one character: "
            );
            characterInput = scanner.nextLine().trim();
        }

        char searchCharacter = Character.toLowerCase(characterInput.charAt(0));
        int selectedCharacterFrequency =
            characterFrequency.getOrDefault(searchCharacter, 0);

        System.out.println(
            "Frequency of '" + characterInput.charAt(0)
            + "': " + selectedCharacterFrequency
        );

        System.out.print("Enter a word to find its frequency: ");
        String searchWord = scanner.nextLine().trim();

        while (searchWord.isEmpty()) {
            System.out.print("Word cannot be empty. Enter a word: ");
            searchWord = scanner.nextLine().trim();
        }

        String normalizedSearchWord = searchWord
                .toLowerCase()
                .replaceAll(
                    "^[^\\p{L}\\p{N}']+|[^\\p{L}\\p{N}']+$",
                    ""
                );

        int selectedWordFrequency =
            wordFrequency.getOrDefault(normalizedSearchWord, 0);

        System.out.println(
            "Frequency of \"" + searchWord + "\": "
            + selectedWordFrequency
        );
        System.out.println("Number of unique words: " + uniqueWords.size());

        scanner.close();
    }
}
