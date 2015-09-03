package com.mateoj.androiddatabinding;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;

import com.mateoj.androiddatabinding.model.Car;

/**
 * Created by jose on 9/3/15.
 */
public class CarView extends CoordinatorLayout {
    private Car mCar;
    public CarView(Context context) {
        super(context);
    }

    public void setCar(Car car)
    {
        this.mCar = car;

    }
}
