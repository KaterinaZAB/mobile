package ru.mirea.zubarevaes.practice4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ru.mirea.zubarevaes.practice4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding	= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonMirea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textViewMirea.setText("Текущая песня играет");
                binding.buttonMirea.setText("||");
            }
        });
        binding.buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textViewMirea.setText("Предыдущая песня играет");
            }
        });
        binding.buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textViewMirea.setText("Следующая песня играет");
            }
        });
    }



//    public class GalleryFragment extends Fragment {
//        private	FragmentGalleryBinding	binding;
//        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,	Bundle	savedInstanceState)	{
//            binding	=	FragmentGalleryBinding.inflate(inflater, container,	false);
//            View	root	=	binding.getRoot();
//            final	TextView	textView	=	binding.textGallery;
//            return	root;
//        }
//        @Override
//        public	void	onDestroyView()	{
//            super.onDestroyView();
//            binding	=	null;
//        }
//    }
}