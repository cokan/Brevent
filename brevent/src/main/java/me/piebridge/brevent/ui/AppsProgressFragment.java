package me.piebridge.brevent.ui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import me.piebridge.brevent.R;

/**
 * Created by thom on 2017/2/4.
 */
public class AppsProgressFragment extends DialogFragment {

    private static final String MAX = "max";
    private static final String PROGRESS = "progress";
    private static final String SIZE = "size";

    private View mView;
    private ProgressBar mProgress;
    private TextView mPercent;
    private TextView mNumber;

    public AppsProgressFragment() {
        setArguments(new Bundle());
        setCancelable(false);
        setStyle(STYLE_NO_TITLE, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_progress_apps, container);
            mProgress = mView.findViewById(R.id.progress);
            mPercent = mView.findViewById(R.id.progress_percent);
            mNumber = mView.findViewById(R.id.progress_number);
        }
        updateProgress();
        return mView;
    }

    @Override
    public void onStop() {
        mView = null;
        mProgress = null;
        mPercent = null;
        mNumber = null;
        super.onStop();
    }

    private void updateProgress() {
        Bundle arguments = getArguments();
        int progress = arguments.getInt(PROGRESS);
        int max = arguments.getInt(MAX);
        int size = arguments.getInt(SIZE);
        if (max > 0 && progress > 0) {
            mProgress.setIndeterminate(false);
            mProgress.setProgress(progress);
            mProgress.setMax(max);

            mPercent.setText(getString(R.string.percent, 100 * progress / max));
            mPercent.setVisibility(View.VISIBLE);

            mNumber.setText(getString(R.string.size, size));
            mNumber.setVisibility(View.VISIBLE);
        }
    }

    public void update(int progress, int max, int size) {
        Bundle arguments = getArguments();
        if (progress != arguments.getInt(PROGRESS)
                || max != arguments.getInt(MAX)
                || size != arguments.getInt(SIZE)) {
            arguments.putInt(PROGRESS, progress);
            arguments.putInt(MAX, max);
            arguments.putInt(SIZE, size);
            if (mProgress != null) {
                updateProgress();
            }
        }
    }

}
