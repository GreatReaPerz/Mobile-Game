package assignment.sidm.com.assignment01_ver2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View.OnClickListener;

public class Mainmenu extends Activity implements OnClickListener {

    //Define Button as an object
    private Button btn_start;
    private Button btn_credits;

    @Override // Annotation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Hide top bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        //This is using layouts! Not want
        setContentView(R.layout.mainmenu);
        //setContentView(new GameView(this));

        // Set Listener to button
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);

        btn_credits = (Button)findViewById(R.id.btn_credits);
        btn_credits.setOnClickListener(this);
    }

    // Invokde a callback on clicked event on a view
    public void onClick(View v){
        Intent intent = new Intent();

        if (v == btn_credits)
        {
            intent.setClass(this, CreditPage.class);
        }
        if (v == btn_start)
        {
            intent.setClass(this, GamePage.class);
        }
        startActivity(intent);
    }
}



