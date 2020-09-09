package lan.tmsystem.gadsleaderboard.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import lan.tmsystem.gadsleaderboard.R;
import lan.tmsystem.gadsleaderboard.dialogs.googleForm.APIClient;
import lan.tmsystem.gadsleaderboard.dialogs.googleForm.APIInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomDialog extends Dialog implements View.OnClickListener {

    public Activity mActivity;
    public Dialog d;
    public MaterialButton yes;
    public ImageView errorIcon, successIcon;
    public TextView errorText, successText, confSubText, confSubExtension;
    public ImageButton cancel;
    APIInterface mAPIInterface;

    String txtFirstName;
    String txtLastName;
    String txtEmail;
    String txtProjectLink;
    EditText txt_first;
    EditText txt_last;
    EditText txt_email;
    EditText txt_link;

    public CustomDialog(Activity a, EditText fN, EditText lN, EditText email, EditText project) {
        super(a);
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
        this.mActivity = a;
        this.txt_first = fN;
        this.txt_last = lN;
        this.txt_email = email;
        this.txt_link = project;
        this.txtFirstName = fN.getText().toString();
        this.txtLastName = lN.getText().toString();
        this.txtEmail = email.getText().toString();
        this.txtProjectLink = project.getText().toString();

        yes.setVisibility(View.VISIBLE);
        cancel.setVisibility(View.VISIBLE);
        confSubText.setVisibility(View.VISIBLE);
        confSubExtension.setVisibility(View.VISIBLE);
        errorIcon.setVisibility(View.INVISIBLE);
        successIcon.setVisibility(View.INVISIBLE);
        errorText.setVisibility(View.INVISIBLE);
        successText.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAPIInterface = APIClient.getClient().create(APIInterface.class);
        yes.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confSubBtn:
                Call<ResponseBody> call = mAPIInterface.doSubmitProject(txtEmail, txtFirstName, txtLastName, txtProjectLink);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        yes.setVisibility(View.INVISIBLE);
                        cancel.setVisibility(View.INVISIBLE);
                        confSubText.setVisibility(View.INVISIBLE);
                        confSubExtension.setVisibility(View.INVISIBLE);
                        errorIcon.setVisibility(View.INVISIBLE);
                        successIcon.setVisibility(View.VISIBLE);
                        errorText.setVisibility(View.INVISIBLE);
                        successText.setVisibility(View.VISIBLE);
                        txt_first.setText("");
                        txt_last.setText("");
                        txt_email.setText("");
                        txt_link.setText("");
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        yes.setVisibility(View.INVISIBLE);
                        cancel.setVisibility(View.INVISIBLE);
                        confSubText.setVisibility(View.INVISIBLE);
                        confSubExtension.setVisibility(View.INVISIBLE);
                        errorIcon.setVisibility(View.VISIBLE);
                        successIcon.setVisibility(View.INVISIBLE);
                        errorText.setVisibility(View.VISIBLE);
                        successText.setVisibility(View.INVISIBLE);
                        call.cancel();
                    }
                });
                break;
            case R.id.btnC:
                dismiss();
                break;
            default:
                break;
        }
    }
}
