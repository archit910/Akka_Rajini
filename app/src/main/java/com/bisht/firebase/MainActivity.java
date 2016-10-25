package com.bisht.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;
    private ProgressDialog progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
       //FragmentManager fragmentManager = getSupportFragmentManager();
        //fragmentManager.beginTransaction().replace(R.id.content_frame,new BlankFragment()).commit();

        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            Log.e("hello","entered");
        }

        progressBar=new ProgressDialog(this);
        buttonRegister=(Button) findViewById(R.id.buttonSignup);

        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);

        textViewSignin=(TextView)findViewById(R.id.textViewSignin);
        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);



    }

    private void registerUser(){
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"pls mail",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"pls password",Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setMessage("Registering . . . ");
        progressBar.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();

                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));


                        } else {
                            Toast.makeText(MainActivity.this, " no success", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view==buttonRegister){
            registerUser();
        }
        if (view==textViewSignin){

            startActivity(new Intent(this, LoginActivity.class));

        }

    }


    public void alpha(View view)
    {   Intent chooser=null;

        if(view.getId()==R.id.shutdown)
        {
            Intent intent =new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));

            intent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
            String[] to={"messi040895@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL,to);
            intent.putExtra(Intent.EXTRA_SUBJECT,"shutdown");
            intent.putExtra(Intent.EXTRA_TEXT,"pc shutdownuvyviyvyviyv kro bhai ");
            intent.setType("message/rfc822");
            try {
                startActivity(intent);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
            //Intent.createChooser(intent,"Send Email");
            //startActivity(intent);
        }
        if(view.getId()==R.id.loggoff)
        {
            Intent intent =new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));

            intent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
            String[] to={"messi040895@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL,to);
            intent.putExtra(Intent.EXTRA_SUBJECT,"logoff");
            intent.putExtra(Intent.EXTRA_TEXT,"pc logoff ho jaa ");
            intent.setType("message/rfc822");
            try {
                startActivity(intent);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
            //Intent.createChooser(intent,"Send Email");
            //startActivity(intent);
        }
        if(view.getId()==R.id.sendEmail)
        {
            String url = "http://www.google.com/";
            Intent i = new Intent();
            i.setPackage("com.android.chrome");
            i.setAction(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);

            //Intent.createChooser(intent,"Send Email");
            //startActivity(intent);
        }

    }


}
