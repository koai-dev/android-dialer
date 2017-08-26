package io.uuddlrlrba.dialpadview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.dialer.dialpadview.DialpadView;
import com.android.dialer.dialpadview.R;

public class DialpadFragment extends Fragment {
    private DialpadView dialpadView;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialpad_view, container, false);
        dialpadView = view.findViewById(R.id.dialpad_view);
        dialpadView.setShowVoicemailButton(false);
        return view;
    }
}
