package com.udacity.gradle.builditbigger;

import junit.framework.TestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GetJokeServiceTest extends TestCase {

    private boolean mIsTestPassed = false;
    final CountDownLatch mCountDownLatch = new CountDownLatch(1);

    public void setUp() throws Exception {
        super.setUp();
        mIsTestPassed = false;
    }

    public void testGetJoke() throws Exception {
        // arrange
        GetJokeService getJokeService = new GetJokeService(JokeApiSingleton.getInstance());

        // act
        getJokeService.getJoke(new GetJokeService.Callback() {
            @Override
            public void onComplete(String jokeText) {
                if (isGettingJokeSuccessfully(jokeText)) {
                    mIsTestPassed = true;
                }
                mCountDownLatch.countDown();
            }
        });

        // assert
        mCountDownLatch.await(25, TimeUnit.SECONDS);
        assertTrue(mIsTestPassed);
    }

    private boolean isGettingJokeSuccessfully(String resultJokeText) {
        if (resultJokeText != null && resultJokeText.length() > 0) {
            return true;
        } else {
            return false;
        }
    }
}