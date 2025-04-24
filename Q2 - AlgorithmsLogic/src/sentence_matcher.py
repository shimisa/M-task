import unicodedata

def normalize_char(c):
    return c.upper() if c.isalpha() else None

def match_sentence(doc, sentence):
    # Extract only normalized uppercase letters from altered sentence
    target_letters = [normalize_char(c) for c in sentence if normalize_char(c)]
    
    n = len(doc)
    m = len(target_letters)
    
    i = 0  # doc index
    j = 0  # target_letters index
    start = None

    while i < n:
        doc_c = normalize_char(doc[i])

        if doc_c is None:
            # Skip non-letter characters in doc
            i += 1
            continue

        if doc_c == target_letters[j]:
            if j == 0:
                start = i  # mark potential start
            j += 1
            if j == m:
                # Found full match
                end = i + 1
                return doc[start:end]
        else:
            if j > 0:
                # Restart search from next character
                j = 0
                start = None
                continue

        i += 1

    return None  # Not found
