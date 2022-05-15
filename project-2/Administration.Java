package org.hyperledger.fabric.covid.tracker;

import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.shim.ChaincodeStub;

@Contract()
@Default
public final class DoseAdministration implements ContractInterface {

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


    // 1. Add fist dose 
    @Transaction()
    public Beneficiary administratorFirstDose(
        final Context ctx,
        final String name,
        final int age,
        final String gender,
        final String govId
    ) {
        // Should take the Bene details
        ChaincodeStub stub = ctx.getStub();

        //TODO: Check if this beneficiary already exists

        Beneficiary bene = new Beneficiary(name, age, gender, govId);
        // TODO: Check if dose count is not more that two.
        bene.Dose += 1;
        beneState = genson.serialize(bene);
        stub.putStringState(govId, beneState);

        return bene;

    }

    // 2. Get vaccination status 
    @Transaction()
    public Beneficiary getVaccineStaus(final Context ctx, final String govId) {
        // TODO: Chaincode stub

        //TODO: getString state

        //TODO: error check if Bene exists
        
        // TODO: Deserialize the string and return
    } 

    // 3. Update vaccination status
    @Transaction()
    public Beneficiary administratorSecondDose(final Context ctx, final String govId) {
        ChaincodeStub stub = ctx.getStub();

        String beneState = stub.getStringState(govId);

        // TODO: Error check if state is empty -- throw an exception
        
        Beneficiary bene = genson.deserialize(beneState, Beneficiary.class);

        //TODO: Change the dose count
        
        if (bene.Dose > 2 ) {
            // TODO: throw an exception and exit
        }

        bene.Dose += 1; // 2

        Beneficiary newBene = new Beneficiary(
            bene.getName(),
            bene.getAge(),
            bene.getGender(),
            bene.getGovId(),
            bene.Dose
        );

        String newBeneState = genson.serialize(newBene);
        stub.pubStringState(govId, newBeneState);

        return newBene;
    }
}