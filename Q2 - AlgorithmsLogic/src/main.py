from sentence_matcher import match_sentence

def main():
    doc = 'The quick, brown fox said: “Hello, world!”'
    sentence = 'BROWN FOX.SAID：＂HELLO WORLD＂'

    result = match_sentence(doc, sentence)
    print(result)
    # Output: brown fox said: “Hello, world!”





if __name__ == "__main__":
    main()