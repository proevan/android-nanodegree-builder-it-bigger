package com.proevan;

public class JokeData {

    private static final Joke JOKE_1 = new Joke("Q: What two words contain the most letters?\n" +
            "A: Post office.");
    private static final Joke JOKE_2 = new Joke("Q: What do you call a deer with no eye?\n" +
            "A: No idea.");
    private static final Joke JOKE_3 = new Joke("Justin Bieber");

    private static final Joke[] JOKES =
            {JOKE_1, JOKE_2, JOKE_3};

    static Joke getRandomJoke() {
        return JOKES[(int)(Math.random() * JOKES.length)];
    }
}
