package com.example.jiamingzhao.tetrisx;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class game_register_page extends AppCompatActivity{

    private EditText Username_register_page;
    private EditText password_register_page;
    private FirebaseAuth FirebaseAuths;
    private Button register_button_register_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_register_page);

        FirebaseAuths = FirebaseAuth.getInstance();
        register_button_register_page =
                (Button) findViewById(R.id.register_button_register_page);
        Username_register_page = (EditText) findViewById(R.id.Username_register_page);
        password_register_page = (EditText) findViewById(R.id.password_register_page);

    }

    private void register_player(){
        String username = Username_register_page.getText().toString().trim();
        String password = password_register_page.getText().toString().trim();

        if(TextUtils.isEmpty(username)){
            Toast.makeText(this, "Please enter username",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuths.createUserWithEmailAndPassword(username,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(game_register_page.this,"Register successful!"
                                    ,Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(game_register_page.this,"Register failed..."
                                    ,Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void register_message(View view){
        register_player();
    }

}
