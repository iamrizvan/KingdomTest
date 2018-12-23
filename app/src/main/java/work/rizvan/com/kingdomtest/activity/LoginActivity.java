package work.rizvan.com.kingdomtest.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import work.rizvan.com.kingdomtest.Helper.Helper;
import work.rizvan.com.kingdomtest.R;
import work.rizvan.com.kingdomtest.network.CheckNetworkConnection;
import work.rizvan.com.kingdomtest.utils.CustomProgressDialog;
import work.rizvan.com.kingdomtest.utils.UserUtils;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    TextView registerText, loginText;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle(Html.fromHtml("<small>"+"Login"+"</small>"));

        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email_edt);
        password = findViewById(R.id.password_edt);

        registerText = findViewById(R.id.register_text);
        loginText = findViewById(R.id.login_text);

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                overridePendingTransition(R.anim.reverse_left_to_right, R.anim.reverse_right_to_left);
                finish();
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!CheckNetworkConnection.isConnectionAvailable(LoginActivity.this))
                {
                    Helper.showSnack(loginText,"No internet connection.");
                    return;
                }

                String emailStr = email.getText().toString().trim();
                String passwordStr = password.getText().toString().trim();

                if (TextUtils.isEmpty(emailStr))
                {
                    Helper.showSnack(loginText,"Please enter email.");
                    return;
                }
                if (TextUtils.isEmpty(passwordStr))
                {
                    Helper.showSnack(loginText,"Please enter password.");
                    return;
                }


                CustomProgressDialog.showDialog(LoginActivity.this,"Signing in..");
                auth.signInWithEmailAndPassword(emailStr, passwordStr)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful())
                                {
                                        CustomProgressDialog.hideDialog();
                                        Helper.showSnack(loginText,"User not found.");
                                                                   }
                                else
                                {
                                    UserUtils.saveLoginStatus(LoginActivity.this,"1");
                                    CustomProgressDialog.hideDialog();
                                    email.setText("");
                                    password.setText("");
                                    startActivity(new Intent(LoginActivity.this, ToDoActivity.class));
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
