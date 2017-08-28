package io.uuddlrlrba.dialpadview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Boris Maslakov.
 */
public class DialpadActivity extends AppCompatActivity implements DialpadFragment.Callback {
    private final static String EXTRA_RESULT_FORMATTED = "EXTRA_RESULT_FORMATTED";
    private final static String EXTRA_RESULT_RAW = "EXTRA_RESULT_RAW";
    private final static String REGION_CODE = "REGION_CODE";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Fragment fragment = new DialpadFragment();
            Bundle args = new Bundle();
            args.putString(DialpadFragment.REGION_CODE, getIntent().getStringExtra(REGION_CODE));
            fragment.setArguments(args);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment)
                    .commit();
        }
    }

    @Override
    public void ok(String formatted, String raw) {
        Intent result = new Intent();
        result.putExtra(EXTRA_RESULT_FORMATTED, formatted);
        result.putExtra(EXTRA_RESULT_RAW, raw);
        setResult(RESULT_OK, result);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
    }
}
