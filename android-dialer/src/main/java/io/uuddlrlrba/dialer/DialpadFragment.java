package io.uuddlrlrba.dialer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.dialer.dialpadview.DialpadView;
import com.android.dialer.dialpadview.R;
import com.google.i18n.phonenumbers.AsYouTypeFormatter;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

public class DialpadFragment extends Fragment {
    final static String REGION_CODE = "REGION_CODE";
    private final static String DEFAULT_REGION_CODE = "US";

    private DialpadView dialpadView;
    private EditText digits;
    private AsYouTypeFormatter formatter;
    private String input = "";
    private Callback callback;
    private String regionCode = DEFAULT_REGION_CODE;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        Bundle arguments = savedInstanceState;
        if (arguments == null) {
            arguments = getArguments();
        }
        if (arguments != null) {
            regionCode = arguments.getString(REGION_CODE, DEFAULT_REGION_CODE);
        }

        View view = inflater.inflate(R.layout.dialpad_fragment, container, false);
        dialpadView = view.findViewById(R.id.dialpad_view);
        dialpadView.setShowVoicemailButton(false);

        digits = dialpadView.getDigits();
        dialpadView.findViewById(R.id.zero).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                append('0');
            }
        });
        dialpadView.findViewById(R.id.zero).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                append('+');
                return true;
            }
        });
        dialpadView.findViewById(R.id.one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                append('1');
            }
        });
        dialpadView.findViewById(R.id.two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                append('2');
            }
        });
        dialpadView.findViewById(R.id.three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                append('3');
            }
        });
        dialpadView.findViewById(R.id.four).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                append('4');
            }
        });
        dialpadView.findViewById(R.id.four).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                append('4');
            }
        });
        dialpadView.findViewById(R.id.five).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                append('5');
            }
        });
        dialpadView.findViewById(R.id.six).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                append('6');
            }
        });
        dialpadView.findViewById(R.id.seven).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                append('7');
            }
        });
        dialpadView.findViewById(R.id.eight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                append('8');
            }
        });
        dialpadView.findViewById(R.id.nine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                append('9');
            }
        });
        dialpadView.findViewById(R.id.star).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                append('*');
            }
        });
        dialpadView.findViewById(R.id.pound).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                append('#');
            }
        });
        dialpadView.getDeleteButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                poll();
            }
        });
        dialpadView.getDeleteButton().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                clear();
                return true;
            }
        });

        formatter = PhoneNumberUtil.getInstance().getAsYouTypeFormatter(regionCode);

        view.findViewById(R.id.fab_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null) {
                    callback.ok(digits.getText().toString(), input);
                }
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(REGION_CODE, regionCode);
    }

    private void poll() {
        if (!input.isEmpty()) {
            input = input.substring(0, input.length() - 1);
            formatter = PhoneNumberUtil.getInstance().getAsYouTypeFormatter(regionCode);
            digits.setText("");
            for (char c : input.toCharArray()) {
                digits.setText(formatter.inputDigit(c));
            }
        }
    }

    private void clear() {
        formatter.clear();
        digits.setText("");
        input = "";
    }

    private void append(char c) {
        digits.setText(formatter.inputDigit(c));
        input += c;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            callback = (Callback) context;
        }
    }

    interface Callback {
        void ok(String formatted, String raw);
    }
}
