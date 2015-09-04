package com.mateoj.androiddatabinding.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by jose on 9/3/15.
 */
public class Car extends BaseObservable {
    private final ObservableField<String> year = new ObservableField<>();
    private final ObservableField<String> make = new ObservableField<>();
    private final ObservableField<String> model = new ObservableField<>();
    private String description;
    private String imageUrl;
    private String price;
    private String mileage;
    private OnCarChangeListener mOnChangeListener;

    public Car(String year, String make, String model, String mileage, String price,
               String description, String image)
    {
        this.year.set(year);
        this.make.set(make);
        this.model.set(model);
        this.description = description;
        this.mileage = mileage;
        this.price = price;
        this.imageUrl = image;
    }

    public void setOnChangeListener(OnCarChangeListener listener)
    {
        this.mOnChangeListener = listener;
    }

    public class BoundTextWatcher implements TextWatcher
    {
        ObservableField<String> field;

        public BoundTextWatcher(ObservableField<String> field)
        {
            this.field = field;
        }
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (!field.get().equals(editable.toString())) {
                field.set(editable.toString());
                if (mOnChangeListener != null)
                    mOnChangeListener.changed();
            }
        }
    }

    public TextWatcher makeWatcher = new BoundTextWatcher(make);

    public TextWatcher modelWatcher = new BoundTextWatcher(model);

    public TextWatcher yearWatcher = new BoundTextWatcher(year);

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getDisplayName() {
        return year.get() + " " + make.get() + " " + model.get();
    }

    @Bindable
    public String getYear() {
        return year.get();
    }

    public void setYear(String year) {
        this.year.set(year);
    }

    @Bindable
    public String getMake() {
        return make.get();
    }

    public void setMake(String make) {
        this.make .set(make);
    }

    @Bindable
    public String getModel() {
        return model.get();
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public interface OnCarChangeListener {
        void changed();
    }
}
