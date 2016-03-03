package com.heinzelmann.ricardo.near_optical_communication;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * A placeholder fragment containing a simple view.
 */
public class GeneratorActivityFragment extends Fragment {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    EditText edTxtString;
    ImageView imageView;
    Button buttonGenerate;

    public GeneratorActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_generator, container, false);

        edTxtString = (EditText)v.findViewById(R.id.edTxtString);
        imageView = (ImageView)v.findViewById(R.id.imageView);
        buttonGenerate = (Button)v.findViewById(R.id.buttonGenerate);
        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edTxtString.onEditorAction(EditorInfo.IME_ACTION_DONE);

                try {
                    Bitmap bitmap = encodeAsBitmap(edTxtString.getText().toString());
                    imageView.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    Log.e("ERROR", e.getLocalizedMessage());
                    Toast.makeText(getActivity().getApplicationContext(), "GENERATION FAILED", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return v;
    }

    private Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, WIDTH, HEIGHT, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
        return bitmap;
    }

}
