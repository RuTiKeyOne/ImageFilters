package com.mobiledev.imagefilters.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.mobiledev.imagefilters.R;
import com.mobiledev.imagefilters.ViewModels.EditViewModel;
import com.mobiledev.imagefilters.databinding.ActivityEditImageBinding;
import java.io.FileNotFoundException;

public class EditImageActivity extends AppCompatActivity {

    private ActivityEditImageBinding editBinding;
    private EditViewModel editViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializationComponents();
        initializationComponentsView();
        setButtonsListener();
        displayImagePreviewFromTry();
    }


    private void initializationComponents(){
        editViewModel = new ViewModelProvider(this).get(EditViewModel.class);
    }

    private void initializationComponentsView(){
        editBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_image);
        editBinding.setIsLoading(true);
    }

    private void setButtonsListener(){
        setBackListener();
    }

    private void setBackListener(){
        editBinding.imageBack.setOnClickListener(v -> onBackPressed());
    }

    private void displayImagePreviewFromTry(){
        try {
            displayImagePreview();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void displayImagePreview() throws FileNotFoundException {
        Uri imageUri = getIntent().getParcelableExtra(MainActivity.KEY_IMAGE_URI);
        if(imageUri != null){
            Bitmap imageBitmap = editViewModel.prepareImageView(imageUri);
            editBinding.setIsLoading(false);
            editBinding.imagePreview.setImageBitmap(imageBitmap);
            editBinding.imagePreview.setVisibility(View.VISIBLE);
        }
    }

}