package com.fourway.cameraexam;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CameraActivity extends AppCompatActivity {
    private static final String GALLERY_FRAGMENT_TAG = "gallery_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.container, Camera2BasicFragment.newInstance())
                    .commit();
        }
    }

    public void change(View view) {
       toggleList();
    }

    private void toggleList() {
        Fragment f = getFragmentManager().findFragmentByTag(GALLERY_FRAGMENT_TAG);
        if (f != null) {
            getFragmentManager().popBackStack();
        } else {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.animator.slide_up,
                            R.animator.slide_down,
                            R.animator.slide_up,
                            R.animator.slide_down)
                    .add(R.id.container, GalleryFragment
                                    .instantiate(this, GalleryFragment.class.getName()),
                            GALLERY_FRAGMENT_TAG
                    ).addToBackStack(null).commit();
        }
    }
}
