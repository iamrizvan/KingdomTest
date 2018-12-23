package work.rizvan.com.kingdomtest.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import work.rizvan.com.kingdomtest.Helper.Helper;
import work.rizvan.com.kingdomtest.R;
import work.rizvan.com.kingdomtest.network.CheckNetworkConnection;
import work.rizvan.com.kingdomtest.utils.CustomProgressDialog;

public class RegistrationActivity extends AppCompatActivity {

    EditText name, email, password;
    TextView login_text, submint_text;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().setTitle(Html.fromHtml("<small>"+"Register"+"</small>"));

        auth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name_edt);
        email = findViewById(R.id.email_edt);
        password = findViewById(R.id.password_edt);

        login_text = findViewById(R.id.login_text);
        submint_text = findViewById(R.id.submit_text);


        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                overridePendingTransition(R.anim.reverse_left_to_right, R.anim.reverse_right_to_left);
                finish();
            }
        });

        submint_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!CheckNetworkConnection.isConnectionAvailable(RegistrationActivity.this))
                {
                    Helper.showSnack(submint_text,"No internet connection.");
                    return;
                }

                String nameStr = name.getText().toString().trim();
                final String emailStr = email.getText().toString().trim();
                final String passwordStr = password.getText().toString().trim();

                if (TextUtils.isEmpty(nameStr))
                {
                    Helper.showSnack(submint_text,"Please enter name.");
                    return;
                }
                if (TextUtils.isEmpty(emailStr))
                {
                    Helper.showSnack(submint_text,"Please enter email.");
                    return;
                }
                if (TextUtils.isEmpty(passwordStr))
                {
                    Helper.showSnack(submint_text,"Please enter password");
                    return;
                }

                CustomProgressDialog.showDialog(RegistrationActivity.this,"Signing up..");

                auth = FirebaseAuth.getInstance();
                auth.createUserWithEmailAndPassword(emailStr, passwordStr)
                        .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {
                                    CustomProgressDialog.hideDialog();
                                    Helper.showSnack(submint_text, "Registration Failed.");
                                }
                                else if (task.isSuccessful())
                                {
                                    CustomProgressDialog.hideDialog();
                                    Helper.showSnack(submint_text, "Registration Success.");
                                    email.setText("");
                                    name.setText("");
                                    password.setText("");
                                    startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                                    overridePendingTransition(R.anim.reverse_left_to_right, R.anim.reverse_right_to_left);
                                    finish();
                                }
                            }
                        });
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}
