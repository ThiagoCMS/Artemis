package br.ufrpe.artemis.infra;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

import br.ufrpe.artemis.R;

public class Auxiliar {
    public static final Auxiliar instance = new Auxiliar();

    private Auxiliar(){}

    public static Bitmap comprimirImagem(Bitmap image){
        int maxSize = 1000;
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        image = Bitmap.createScaledBitmap(image, width, height, true);
        return image;
    }

    public static byte[] bitmapToByte(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    public static Bitmap byteToBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    public static Bitmap gerarBitmapPadrao(){
        int idResource = R.drawable.icon_woman_default;
        return BitmapFactory.decodeResource(ArtemisApp.getContext().getResources(), idResource, new BitmapFactory.Options());
    }


}