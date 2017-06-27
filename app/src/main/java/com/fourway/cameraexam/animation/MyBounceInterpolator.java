package com.fourway.cameraexam.animation;

import android.view.animation.Interpolator;

import static com.fourway.cameraexam.R.id.time;

/**
 * Created by 4 way on 27-06-2017.
 */

public class MyBounceInterpolator implements Interpolator {
    private double mAmplitude = 1;
    private double mFrequency = 10;

    public MyBounceInterpolator(double amplitude, double frequency) {
        mAmplitude = amplitude;
        mFrequency = frequency;
    }

    @Override
    public float getInterpolation(float input) {
        return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
                Math.cos(mFrequency * time) + 1);
    }
}
