package com.mateoj.androiddatabinding.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mateoj.androiddatabinding.R;
import com.mateoj.androiddatabinding.databinding.DialogEditCarBinding;
import com.mateoj.androiddatabinding.databinding.ViewCarItemBinding;
import com.mateoj.androiddatabinding.model.Car;
import com.mateoj.androiddatabinding.model.CarsHelper;

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

    public class CarsAdapter extends RecyclerView.Adapter<CarsViewHolder> implements Car.OnCarChangeListener {
        private List<Car> mCars;
        @Override
        public CarsViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_car_item, parent, false);
            final CarsViewHolder viewHolder = new CarsViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = viewHolder.getAdapterPosition();
                    openEditDialog(pos);
                }
            });
            return viewHolder;
        }

        public Car getItem(int pos)
        {
            return (mCars == null) ? null : mCars.get(pos);
        }

        private void openEditDialog(int position)
        {
            EditDialogFragment fragment = new EditDialogFragment();
            Bundle args = new Bundle();
            args.putInt("carPos", position);
            fragment.setArguments(args);
            fragment.show(getSupportFragmentManager(), "dialog");
        }

        public void setCars(List<Car> cars) {
            this.mCars = cars;
            notifyDataSetChanged();
            for (Car car : cars)
            {
                car.setOnChangeListener(this);
            }
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

        @Override
        public void changed() {
            notifyDataSetChanged();
        }
    }

    @SuppressLint ("ValidFragment")
    public class EditDialogFragment extends DialogFragment {

        private int carPosition;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                carPosition = getArguments().getInt("carPos");
            }
            setStyle(DialogFragment.STYLE_NORMAL, R.style.Base_Theme_AppCompat_Light_Dialog);

        }

        @Nullable @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.dialog_edit_car, container, false);
            DialogEditCarBinding binding = DialogEditCarBinding.bind(view);
            binding.setCar(mCarsAdapter.getItem(carPosition));
            return view;
        }
    }
}
