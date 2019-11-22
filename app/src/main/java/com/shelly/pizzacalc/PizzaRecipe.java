package com.shelly.pizzacalc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class PizzaRecipe implements Serializable {

    //public static final long serialVersionUID = 42L;
    public static PizzaRecipe instance = null;
    public double flourInPercentage = 100;
    public double watterInPercentage = 60;
    public double yeastInPercentage = 0.06;
    public double saltInPercentage = 3;
    public double sugarInPercentage = 1.25;
    public double oliveOilInPercentage = 2;
    public double OneOzInGrams = 28.3495231;

    public double NumOfBalls = 4;
    public double BallWeight = 250;

    String tmp;
    public int ex = 0;

    private double oliveOilSpecificGravity = 0.97; // grams/cm^2

    public enum LiquidMeasureUnit {
        Milliliter,
        Grams
    }

    public LiquidMeasureUnit liquidMeasureUnit = LiquidMeasureUnit.Milliliter;

    public enum UnitOfMeasure {
        Grams,
        Ounce
    }

    public UnitOfMeasure unitOfMesure = UnitOfMeasure.Grams;

    enum YeastType{
        DryInstant,
        DryActive,
        Fresh
    }

    public YeastType yeastType = YeastType.DryInstant;

    private double TotalPercentage = flourInPercentage + watterInPercentage + yeastInPercentage + saltInPercentage; //163.06
    private boolean UseSugar = false;
    private boolean UseOliveOil = false;

    private double Flour,Watter,Salt,Sugar,OliveOil,Yeast;
    ;

    PizzaRecipe() {
    }

    public void Calculate (double TotalWeight) {

        Flour = TotalWeight * 100/TotalPercentage;

        Yeast = yeastInPercentage/100 * Flour;
        Watter = calculateWatter();
        Salt = saltInPercentage /100 * Flour;
        Sugar = sugarInPercentage / 100 * Flour;
        calculateOliveOil();
    }

    public static PizzaRecipe getInstance () {
        if (instance == null)
        {
            instance = new PizzaRecipe();
        }
        return instance;
    }

    public static PizzaRecipe getInstance(PizzaRecipe pizzaReciepe)
    {
        if (instance == null)
        {
            instance = pizzaReciepe;
        }

        return instance;
    }

    public static void OzToGrams () {

    }

    public static void GramsToOz () {

    }

    public String getFlour () {
        return String.valueOf(RoundDouble(ex,Flour))+" "+getWeightMeasureSimbole();
    }

    public String getWatter () {
        return String.valueOf(RoundDouble(ex,Watter))+" "+getLiquidMeassureSimbole();

    }

    public String getYeast () {
        if (unitOfMesure == UnitOfMeasure.Grams)
            return String.valueOf(RoundDouble(2,Yeast))+" "+getWeightMeasureSimbole();
        else
            return String.valueOf(RoundDouble(4,Yeast))+" "+getWeightMeasureSimbole();
    }

    public String getSalt () {
        return String.valueOf(RoundDouble(ex,Salt))+" "+getWeightMeasureSimbole();
    }

    public String getSugar () {
            return String.valueOf(RoundDouble(ex,Sugar))+" "+getWeightMeasureSimbole();

    }

    public String getOliveOil () {
        return String.valueOf((RoundDouble(ex,OliveOil))+" "+getLiquidMeassureSimbole());
    }

    public String getWeightMeasureSimbole() {

        if (unitOfMesure == UnitOfMeasure.Grams)
            return "g";
        else
            return "Oz";
    }

    public String getLiquidMeassureSimbole () {
        if (liquidMeasureUnit == LiquidMeasureUnit.Milliliter)
            return "mil";
        else if (unitOfMesure == UnitOfMeasure.Grams)
            return "g";
        else
            return "Oz";
    }

    public void changeLiquidMeasureUnit (LiquidMeasureUnit liqMeasureUnit) {
        liquidMeasureUnit = liqMeasureUnit;

    }

    public void changeWeightMeasureUnit (UnitOfMeasure unitOfMeasure) {
        if (this.unitOfMesure != unitOfMeasure) {
            if (unitOfMeasure == UnitOfMeasure.Ounce) {
                BallWeight = RoundDouble(2,BallWeight/OneOzInGrams);
            }
            else
                BallWeight = RoundDouble(0,BallWeight * OneOzInGrams);

            this.unitOfMesure = unitOfMeasure;
        }
    }

    public String getBallWeight () {
        return String.valueOf(RoundDouble(2,BallWeight))+" "+getWeightMeasureSimbole();
    }

    /*public static String savePizzaReciep (String dataDir)
    {
        try
        {
            FileOutputStream fileOutput = new FileOutputStream( dataDir + "/Receipt.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutput);

            objectOutputStream.writeObject(instance);

            return null;
        } catch (IOException ext)
        {
            return ext.getMessage();
        }
    }*/

    private double calculateWatter () {
        Watter = Flour * watterInPercentage / 100;

        if (unitOfMesure == UnitOfMeasure.Ounce && liquidMeasureUnit == LiquidMeasureUnit.Milliliter)
            Watter *= OneOzInGrams;

        return Watter;
    }

    private void calculateOliveOil () {
        if (liquidMeasureUnit == LiquidMeasureUnit.Milliliter)
            if (unitOfMesure == UnitOfMeasure.Grams)
                OliveOil = oliveOilInPercentage /100 * Flour;
            else
                OliveOil = oliveOilInPercentage /100 * Flour * OneOzInGrams;
        else
            OliveOil = oliveOilInPercentage /100 * Flour * oliveOilSpecificGravity;
    }

    private BigInteger RoundToInt (BigDecimal bigDecimal) {
        return bigDecimal.setScale(0,RoundingMode.HALF_UP).toBigInteger();
    }
    private double RoundDouble (int places, double doubleNumber) {

        BigDecimal tempBD = new BigDecimal(doubleNumber).setScale(places,RoundingMode.HALF_UP);
        return tempBD.doubleValue();
    }

    public void UseSuger (boolean bool) {
        UseSugar = bool;
    }
    public boolean UseSuger () {
        return UseSugar;
    }

    public void UseOliveOil (boolean bool) {
        UseOliveOil = bool;
    }

    public boolean UseOliveOil () {
        return UseOliveOil;
    }

    //    return;
    //}
}
