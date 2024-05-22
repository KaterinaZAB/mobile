package ru.mirea.zubarevaes.mireaproject.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.mirea.zubarevaes.mireaproject.R;
import ru.mirea.zubarevaes.mireaproject.databinding.FragmentCameraBinding;
import ru.mirea.zubarevaes.mireaproject.databinding.FragmentProfileBinding;
public class ProfileFragment extends Fragment {
    private FragmentProfileBinding fragmentProfileBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = fragmentProfileBinding.getRoot();

        Button buttonSaveProfile = fragmentProfileBinding.buttonSaveProfile;
        EditText editName = fragmentProfileBinding.editName;
        EditText editFamily = fragmentProfileBinding.editFamily;
        EditText editNumber = fragmentProfileBinding.editNumber;
        EditText editCity = fragmentProfileBinding.editCity;

        SharedPreferences preferences = requireActivity().getSharedPreferences("Profile",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        buttonSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("NAME", editName.getText().toString());
                editor.putString("FAMILY", editFamily.getText().toString());
                editor.putString("NUMBER", editNumber.getText().toString());
                editor.putString("CITY", editCity.getText().toString());
                editor.apply();
                Toast.makeText(requireContext(), "Сохранено", Toast.LENGTH_SHORT).show();
            }
        });

        editName.setText(preferences.getString("NAME", ""));
        editFamily.setText(preferences.getString("FAMILY", ""));
        editNumber.setText(preferences.getString("NUMBER", ""));
        editCity.setText(preferences.getString("CITY", ""));
        return root;
    }
}