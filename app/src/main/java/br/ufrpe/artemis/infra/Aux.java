package br.ufrpe.artemis.infra;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

import br.ufrpe.artemis.R;
import br.ufrpe.artemis.pessoa.dao.PessoaDao;
import br.ufrpe.artemis.pessoa.dominio.Pessoa;

public class Aux {

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
        byte[] byteImage = stream.toByteArray();
        return byteImage;
    }

    public static Bitmap byteToBitmap(byte[] image) {
        Bitmap b = BitmapFactory.decodeByteArray(image, 0, image.length);
        return b;

    }

    public static Bitmap gerarBitmapPadrao(){
        int idResource = R.drawable.icon_woman_default;
        Bitmap bitmap = BitmapFactory.decodeResource(ArtemisApp.getContext().getResources(), idResource, new BitmapFactory.Options());
        return bitmap;
    }


}
