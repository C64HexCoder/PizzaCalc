package com.shelly.pizzacalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class CalculateRecepie extends AppCompatActivity {
    PizzaRecipe pizzaReciepe = PizzaRecipe.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_recepie);

        TextView flour = findViewById(R.id.flourEd);
        TextView watter = findViewById(R.id.watterEd);
        TextView yeast = findViewById(R.id.yeastED);
        TextView salt = findViewById(R.id.saltED);
        TextView sugar = findViewById(R.id.sugarED);
        TextView oliveOil = findViewById(R.id.oliveOilED);
        TextView sugarTV = findViewById(R.id.sugarTV);
        TextView oliveOilTV = findViewById(R.id.oliveOilTV);
        TextView yeastTV = findViewById(R.id.calc_recipt_yeastTV);
        FloatingActionButton backFloatingButton = findViewById(R.id.backFloatingButton);

        double TotalWeight;
        TotalWeight = getIntent().getDoubleExtra("Total Weight",250);

        pizzaReciepe.Calculate(TotalWeight);


        flour.setText(pizzaReciepe.getFlour());
        watter.setText(pizzaReciepe.getWatter());
        yeast.setText(pizzaReciepe.getYeast());
        salt.setText(pizzaReciepe.getSalt());
        sugar.setText(pizzaReciepe.getSugar());
        oliveOil.setText(pizzaReciepe.getOliveOil());

        if (pizzaReciepe.yeastType == PizzaRecipe.YeastType.DryActive)
            yeastTV.setText(R.string.dryActive);
        else if (pizzaReciepe.yeastType == PizzaRecipe.YeastType.DryInstant)
            yeastTV.setText(R.string.dryInstant);
        else
            yeastTV.setText(R.string.freshYeast);

        if (pizzaReciepe.UseSuger()) {
            sugar.setVisibility(View.VISIBLE);
            sugarTV.setVisibility(View.VISIBLE);
        }
        else {
            sugar.setVisibility(View.GONE);
            sugarTV.setVisibility(View.GONE);
        }

        if (pizzaReciepe.UseOliveOil()) {
            oliveOil.setVisibility(View.VISIBLE);
            oliveOilTV.setVisibility(View.VISIBLE);
        }
        else {
            oliveOil.setVisibility(View.INVISIBLE);
            oliveOilTV.setVisibility(View.INVISIBLE);
        }

        backFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

    }
}
