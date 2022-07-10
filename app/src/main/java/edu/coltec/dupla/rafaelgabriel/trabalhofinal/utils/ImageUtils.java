package edu.coltec.dupla.rafaelgabriel.trabalhofinal.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import edu.coltec.dupla.rafaelgabriel.trabalhofinal.R;
import edu.coltec.dupla.rafaelgabriel.trabalhofinal.view.CadastroActivity;

public class ImageUtils {
    public ImageUtils(Context context){
        Bitmap foto1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.receita1);
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File file = new File(directory, "BoloSimples" + ".jpeg");
        if(!file.exists()){
            Log.d("path", file.toString());
            FileOutputStream fos = null;
            try{
                fos = new FileOutputStream(file);
                foto1.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
