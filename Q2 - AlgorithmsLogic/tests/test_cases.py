import unittest
from sentence_matcher import match_sentence

class SentenceMatcherTests(unittest.TestCase):

    def test_basic_match(self):
        doc = 'The quick, brown fox said: “Hello, world!”'
        sentence = 'THE,QUICK-BROWN FOX.SAID：＂HELLO WORLD＂'
        expected = 'The quick, brown fox said: “Hello, world!”'
        self.assertEqual(match_sentence(doc, sentence), expected)

    def test_different_quotes_and_punctuation(self):
        doc = 'She whispered: “Good night, stars.”'
        sentence = 'SHE!WHISPERED：＂GOOD NIGHT STARS＂'
        expected = 'She whispered: “Good night, stars.”'
        self.assertEqual(match_sentence(doc, sentence), expected)

    def test_full_width_and_extra_punctuation(self):
        doc = 'Welcome to the jungle, we’ve got fun and games.'
        sentence = 'WELCOME@TO.THE：JUNGLE，WE＇VE；GOT FUN+AND=GAMES'
        expected = 'Welcome to the jungle, we’ve got fun and games.'
        self.assertEqual(match_sentence(doc, sentence), expected)

    def test_match_at_end(self):
        doc = 'Something here. Then: “Find me if you can!”'
        sentence = 'FIND-ME IF YOU CAN'
        expected = 'Find me if you can'
        self.assertTrue(expected in match_sentence(doc, sentence))

    def test_no_match(self):
        doc = 'Nothing here should match this.'
        sentence = 'SOMETHING THAT DOES NOT EXIST'
        self.assertIsNone(match_sentence(doc, sentence))

if __name__ == '__main__':
    unittest.main()
