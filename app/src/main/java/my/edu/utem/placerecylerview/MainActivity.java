package my.edu.utem.placerecylerview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        CustomAdapter adapter = new CustomAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView;
        TextView descTextView;
        ImageView imageView;

        public CustomViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.custom_row, parent, false));
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descTextView = itemView.findViewById(R.id.descTextView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
    public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder>{

        public final String[] placeTitleArray;
        public final String[] placeDescArray;
        public final Drawable[] placeImageArray;

        public CustomAdapter(Context context){
            Resources resources = context.getResources();
            placeTitleArray = resources.getStringArray(R.array.title);
            placeDescArray = resources.getStringArray(R.array.description);
            TypedArray a = resources.obtainTypedArray(R.array.pictures);
            placeImageArray = new Drawable[a.length()];
            for (int i = 0; i< placeImageArray.length; i++){
                placeImageArray[i] = a.getDrawable(i);
            }
        }

        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new CustomViewHolder(LayoutInflater.from(viewGroup.getContext()), viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
            customViewHolder.titleTextView.setText(placeTitleArray[i]);
            customViewHolder.descTextView.setText(placeDescArray[i]);
            customViewHolder.imageView.setImageDrawable(placeImageArray[i]);
        }

        @Override
        public int getItemCount() {
            return placeTitleArray.length;
        }
    }
}
