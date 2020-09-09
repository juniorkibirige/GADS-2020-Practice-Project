package lan.tmsystem.gadsleaderboard.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import lan.tmsystem.gadsleaderboard.R;

public class CustomDialog extends Dialog implements View.OnClickListener {

    public Activity mActivity;
    public Dialog d;
    public Button yes;
    public ImageView errorIcon, successIcon;
    public TextView errorText, successText, confSubText, confSubExtension;
    public ImageButton cancel;

    public CustomDialog(Activity a) {
        super(a);
        this.mActivity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        cancel = findViewById(R.id.btnC);
        yes = findViewById(R.id.confSubBtn);
        errorIcon = findViewById(R.id.errorIcon);
        successIcon = findViewById(R.id.successIcon);
        errorText = findViewById(R.id.errorText);
        successText = findViewById(R.id.successText);
        confSubText = findViewById(R.id.confSubText);
        confSubExtension = findViewById(R.id.confSubExtension);
        yes.setVisibility(View.INVISIBLE);
        errorIcon.setVisibility(View.INVISIBLE);
        successIcon.setVisibility(View.INVISIBLE);
        errorText.setVisibility(View.INVISIBLE);
        successText.setVisibility(View.INVISIBLE);
        yes.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confSubBtn:
                mActivity.finish();
                break;
            case R.id.btnC:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
