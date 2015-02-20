package com.pokedex.falcon.pokedex;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Abhi on 2/18/2015.
 */
public class Pokemon implements Parcelable{

    private String attack;
    private String resource_uri;
    private int nationalID;
    private String name;
    private String weight;
    private List<Type> types;

    public String getUri() {
        return resource_uri;
    }

    public void setNationalId (int nationalId) {
        this.nationalID = nationalId;
    }

    public int getNationalId() {
        return nationalID;
    }

    public String getAttack () {
        return attack;
    }

    public String getWeight() {
        return weight;
    }

    public List<Type> getTypes() {
        return types;
    }

    @Override
    public String toString() {
        return "#" + Integer.parseInt(resource_uri.substring(15).replaceAll("[\\D]", "")) + " " + name;
    }

    //*********** Parcelable code *****************//
    public Pokemon(Parcel in ) {
        readFromParcel( in );
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Pokemon createFromParcel(Parcel in ) {
            return new Pokemon(in);
        }

        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(attack);
        dest.writeString(resource_uri);
        dest.writeString(name);
        dest.writeString(weight);
        dest.writeList(types);
    }

    private void readFromParcel(Parcel in) {
        attack = in.readString();
        resource_uri = in.readString();
        name = in.readString();
        weight = in.readString();
        in.readList(types,null);
    }

    /*Comparator for sorting the list by Number*/
    public static Comparator<Pokemon> PokeNatIdComparator = new Comparator<Pokemon>() {

        public int compare(Pokemon p1, Pokemon p2) {
            int nationalIdNumber1 = Integer.parseInt(p1.getUri().substring(15).replaceAll("[\\D]", ""));
            int nationalIdNumber2 = Integer.parseInt(p2.getUri().substring(15).replaceAll("[\\D]", ""));

            //ascending order
            return nationalIdNumber1 - nationalIdNumber2;

            //descending order
            //return nationalIdNumber2 - nationalIdNumber1;
        }};

}