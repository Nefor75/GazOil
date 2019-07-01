package com.example.calculator;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    SoundPool sp;
    protected int soundKapla;
    protected int soundbed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        sp = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        soundbed = sp.load(this, R.raw.bed, 1);
        soundKapla = sp.load(this, R.raw.kaplya, 1);

    }

    protected int checkedRadioButtonId, convertInIntTag, convertInIntEditBenz, convertInIntEditSmes;
    protected double resultRound, resultRoundTag, result, resultCrossTag;
    protected String resultString, resultStringTag, gasolineStringTag, gasolineString, smesPlust1;
    protected EditText editBenz, editSmes;
    protected long exit;

    public void onClick(View view) {

        editSmes = findViewById(R.id.editSmes);
        editBenz = findViewById(R.id.editBenz);
        TextView resultOil = findViewById(R.id.result_oil);
        TextView resultGasoline = findViewById(R.id.result_gasoline);
        TextView resultProporciya = findViewById(R.id.result_proporciya);
        RadioGroup rG = findViewById(R.id.radiogroup);
        checkedRadioButtonId = rG.getCheckedRadioButtonId();
        RadioButton checkedButton = findViewById(checkedRadioButtonId);
        LinearLayout llbottom = findViewById(R.id.llbottom);

        if (view.getId() == R.id.расчет) {
            if (editBenz.length() == 0 && editBenz.length() == 0) {
                sp.play(soundbed, 1, 1, 0, 0, 1);
                llbottom.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "Недостаточно данных", Toast.LENGTH_SHORT).show();
            }

            else if (editBenz.length() != 0 && editSmes.length() == 0) {

                convertInIntEditBenz = Integer.parseInt(editBenz.getText().toString());
                convertInIntTag = Integer.parseInt(checkedButton.getTag().toString());

                    resultCrossTag = (double) convertInIntEditBenz / convertInIntTag;

                    if (Double.isNaN(resultCrossTag) || Double.isInfinite(resultCrossTag)|| convertInIntEditBenz == 0) {
                        sp.play(soundbed, 1, 1, 0, 0, 1);
                        llbottom.setVisibility(View.INVISIBLE);
                        Toast.makeText(this, "Недостаточно данных", Toast.LENGTH_SHORT).show();
                    } else {
                        resultRoundTag = Math.round(resultCrossTag * 10.0) / 10.0;
                        resultStringTag = resultRoundTag + "";
                        gasolineStringTag = editBenz.getText() + "";
                        sp.play(soundKapla, 1, 1, 0, 0, 1);

                        resultProporciya.setText(checkedButton.getText());
                        resultGasoline.setText(gasolineStringTag);
                        resultOil.setText(resultStringTag);
                        llbottom.setVisibility(View.VISIBLE);
                    }
            }
            else if (editSmes.length() != 0 && editBenz.length() != 0) {

                convertInIntEditSmes = Integer.parseInt(editSmes.getText().toString());
                convertInIntEditBenz = Integer.parseInt(editBenz.getText().toString());
                result = (double) convertInIntEditBenz / convertInIntEditSmes;
                    if (Double.isNaN(result) || Double.isInfinite(result) || convertInIntEditBenz == 0){
                        sp.play(soundbed, 1, 1, 0, 0, 1);
                        llbottom.setVisibility(View.INVISIBLE);
                        Toast.makeText(this, "Недостаточно данных", Toast.LENGTH_SHORT).show();
                    }else {
                        resultRound = Math.round(result * 10.0) / 10.0;
                        resultString = resultRound + "";
                        smesPlust1 = "1:" + editSmes.getText();
                        gasolineString = editBenz.getText() + "";
                        sp.play(soundKapla, 1, 1, 0, 0, 1);

                        resultProporciya.setText(smesPlust1);
                        resultGasoline.setText(gasolineString);
                        resultOil.setText(resultString);
                        llbottom.setVisibility(View.VISIBLE);
                    }
                }
            }
        }

    @Override
    public void onBackPressed(){
        if (exit + 3000 > System.currentTimeMillis()){
            super.onBackPressed();
        }
        else{
            Toast.makeText(getBaseContext(),"Для выхода нажмите еще раз", Toast.LENGTH_SHORT).show();
        }
        exit = System.currentTimeMillis();
    }
}

