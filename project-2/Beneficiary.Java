package org.hyperledger.fabric.covid.tracker;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import com.owlike.genson.annotation.JsonProperty;

@DataType()
public final class Beneficiary {

    // TODO: finish property
    
    
    /*
        Beneficiary name
        Beneficiary age
        Beneficiary gender
        Beneficiary identity proof ID
        Vaccine reference ID
        Vaccine name
        Date of vaccination
        Dose number (first or second)
    */

    @Property()
    private final String name;

    @Property()
    private final int age;

    @Property()
    private final String gender;

    @Property()
    private final String govId;

    @Property()
    private final int Dose;

    //TODO: Finish all property declerations

    // define getter method
    public String getName() {
        return name;
    }

    // TODO: Finish getter method for all properties

    //Constructor for Bene
    public Beneficiary(
        @JsonProperty("name") final String name, 
        @JsonProperty("age") final int age,
        @JsonProperty("gender") final String gender,
        @JsonProperty("govId") final String govId) {
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.govId = govId;
            this.Dose = 0;
        }

    //TODO: equals

    //TODO: hashCode

    //TODO: toString

}