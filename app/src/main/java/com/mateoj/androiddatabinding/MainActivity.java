package com.mateoj.androiddatabinding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mateoj.androiddatabinding.databinding.ViewCarItemBinding;
import com.mateoj.androiddatabinding.model.Car;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CarsAdapter mCarsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView mCarsRecyclerView = (RecyclerView) findViewById(R.id.carsRecycler);
        mCarsAdapter = new CarsAdapter();
        mCarsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCarsRecyclerView.setAdapter(mCarsAdapter);
        loadData();
    }

    private void loadData() {
        mCarsAdapter.setCars(CarsHelper.geListOfCars());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class CarsViewHolder extends RecyclerView.ViewHolder {
        private ViewCarItemBinding binding;

        public CarsViewHolder(View itemView) {
            super(itemView);
            binding = ViewCarItemBinding.bind(itemView);
        }

        public ViewCarItemBinding getBinding() {
            return binding;
        }
    }

    public static class CarsAdapter extends RecyclerView.Adapter<CarsViewHolder> {
        private List<Car> mCars;

        @Override
        public CarsViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_car_item, parent, false);
            return new CarsViewHolder(view);
        }

        public void setCars(List<Car> cars) {
            this.mCars = cars;
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(CarsViewHolder carsViewHolder, int i) {
            carsViewHolder.getBinding().setCar(mCars.get(i));
            carsViewHolder.getBinding().executePendingBindings();
        }

        @Override
        public int getItemCount() {
            return (mCars == null) ? 0 : mCars.size();
        }
    }
}
