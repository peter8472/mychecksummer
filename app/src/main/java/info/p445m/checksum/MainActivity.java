package info.p445m.checksum;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.Button;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.crypto.CipherInputStream;
import java.security.MessageDigest;

//import javax.lang.model.util.ElementScanner;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_FILE = 3;
    private static final String TAG = "myocr";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Button getfile = findViewById(R.id.files_button);
    getfile.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*");

            // Optionally, specify a URI for the file that should appear in the
            // system file picker when it loads.
            // intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri);

            startActivityForResult(intent, PICK_FILE);
        }
    });

    Button lister = findViewById(R.id.lister_button);
    lister.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            File mydior = new File("/sorage/emulated/legacy/"); //getExternalFilesDir(null);
            mydior = new File("/mnt/shell/emulated/0/");
            mydior = new File("/storage/emulated/legacy/");
            String []files = mydior.list();
            if (files == null ) {
                Log.e(TAG, " filexs is null");

            } else if (files.length ==0 ) {
                Log.e(TAG, "no external files");
                Log.e(TAG, mydior.getAbsolutePath());
                mydior = getFilesDir();
                files = mydior.list();
                if(files.length ==0 ) {
                    Log.e(TAG, "no files");
                    Log.e(TAG, mydior.getAbsolutePath());
            
                }
            } else {
                for (String string : files) {
                    Log.e(TAG, string);
                    try {
                        File f = new File(string);
                        FileInputStream inStream = new FileInputStream(f);
                        MessageDigest md = MessageDigest.getInstance("SHA-256");

                        while ((i = inStream.read(ray)) > 0 ) {
                            myd.update(ray,0,i);
                            
                        }
   

                        java.io.StringWriter writer = new java.io.StringWriter();
                    
                        byte [] digest = myd.digest();
                        for (byte b : digest) {
                            writer.write(String.format("%02x",b));

                        } 

                        } catch (IOException f) {
                            Log.e(TAG, "io exception");

                                        
                                    
                        }
            Log.e(TAG, "workin");
            
        }
    });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openDirectory(Uri uriToLoad) {
        // Choose a directory using the system's file picker.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
    
        // Provide read access to files and sub-directories in the user-selected
        // directory.
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    
        // Optionally, specify a URI for the directory that should be opened in
        // the system file picker when it loads.
        // intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uriToLoad);
    
        startActivityForResult(intent, PICK_FILE);
    }

    
    
}
