import itertools
import random

try:
    input = raw_input
except NameError:
    pass

digits = '0123456789'
randomize = False

def main(n):
    candidates = list(itertools.permutations(digits, n))
    tries = 0
    if randomize:
        random.shuffle(candidates)
    while True:
        tries += 1
        guess = candidates[0]
        x = input('Try %d. Guess: %s. Type in bulls, cows: ' % (tries, ''.join(guess,)))
        x, y = x.replace(' ', '').split(',')
        bulls = int(x)
        if bulls == n:
            break
        cows = int(y)
        #print(bulls, cows)
        candidates = [candidate for candidate in candidates
                      if score(guess, candidate) == (bulls, cows)]
        if not candidates:
            raise ValueError('No more possibilities.')
        elif len(candidates) == 1:
            tries += 1
            print('Try %d. Found solution: %s.' % (
                tries, ''.join(candidates[0])))
            break
        #print([''.join(candidate) for candidate in candidates])
    #print('Found in %d tries.' % tries)

def score(guess, actual):
    cows = sum(int(digit in guess) for digit in actual)
    bulls = sum(int(guess[i] == actual[i]) for i in range(len(guess)))
    return bulls, cows - bulls

if __name__ == '__main__':
    print('Bulls and Cows solver')
    main(4)

""""
1234 0,1
2567 0,1
3689


1789  1689

7081
"""
