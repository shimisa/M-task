# Sentence Matcher



The example in main.py demonstrates the sentence matcher with a sample document and search pattern. To use with your own text, modify the `doc` and `sentence` variables in `src/main.py`.

### How the Algorithm Works

The algorithm works in linear time and proceeds as follows:

1. Normalize all alphabetic characters to uppercase

2. Extract only letters from the altered sentence (since added punctuation/space should be      ignored), and convert them to uppercase.

3. Scan through the document with a sliding window, collecting sequences of letters.

4. At each step:

   * Compare the normalized, uppercased letter from the document with the next expected letter from the sentence.

   * If it matches, continue; if a full match is found, return the substring from doc.



#### Why It’s Linear Time

Let n be the length of doc, and m be the number of letters in sentence.

The algorithm makes a single pass over doc (O(n)), and each letter in sentence is consumed once (O(m)).

All character operations (normalization, case comparison) are constant time (O(1)).

Therefore, total time complexity is O(n + m).




### How to Run

1. Make sure you have Python 3.x installed on your system
3. Navigate to the project directory
4. Run the program using Python:
   ```
   python src/main.py
   ```