package com.example.jiamingzhao.tetrisx;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_in_page extends AppCompatActivity {

    private Button Sign_in_button;
    private Button Register_button;
    private EditText login_page_email;
    private EditText login_page_password;
    private FirebaseAuth firebaseauth2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in_page);

        Sign_in_button = (Button) findViewById(R.id.Sign_in_button);
        Register_button = (Button) findViewById(R.id.Register_button);
        login_page_email = (EditText) findViewById(R.id.login_page_email);
        login_page_password = (EditText) findViewById(R.id.login_page_password);
        firebaseauth2 = FirebaseAuth.getInstance();

    }

    private void checkregisterinformation(){
        String username2 = login_page_email.getText().toString().trim();
        String password2 = login_page_password.getText().toString().trim();

        if(TextUtils.isEmpty(username2)){
            Toast.makeText(this, "Please enter username",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password2)){
            Toast.makeText(this, "Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseauth2.signInWithEmailAndPassword(username2,password2)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),Tetris_game.class));
                        }else{
                            Toast.makeText(Login_in_page.this,"Invalid email or password"
                                    ,Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    //code to jump to the register page
    public void sendMessage(View view){
        Intent goto_register_page = new Intent(this, game_register_page.class);
        startActivity(goto_register_page);
    }

    public void sendMessage2(View view){
        checkregisterinformation();
    }

}
