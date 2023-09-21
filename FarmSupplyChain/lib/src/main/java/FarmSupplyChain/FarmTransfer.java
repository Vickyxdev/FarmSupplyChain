package FarmSupplyChain;
 
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;
import com.owlike.genson.Genson;
 
 
@Contract(
        name = "FarmDetails",
        info = @Info(
                title = "FarmDetails contract",
                description = "A Sample Farm transfer chaincode example",
                version = "0.0.1-SNAPSHOT"))
 
 
@Default
public final class FarmTransfer implements ContractInterface {
 
	private final Genson genson = new Genson();
	private enum FarmDetailsErrors {
	        Farm_NOT_FOUND,
	        Farm_ALREADY_EXISTS
	    }
	
	
	/**
     * Add some initial properties to the ledger
     *
     * @param ctx the transaction context
     */
    @Transaction()
    public void initLedger(final Context ctx) {
    	
        ChaincodeStub stub= ctx.getStub();
        
        Farm Farm = new Farm("123", "Mango","Web3FarmCompany","xxxxxxxxxxx","11-9-2023");
        
        String FarmState = genson.serialize(Farm);
        
        stub.putStringState("1", FarmState);
    }
    
    
    /**
     * Add new Farm on the ledger.
     *
     * @param ctx the transaction context
     * @param id the key for the new Farm Asset
     * @param model the model of the new Farm Asset
     * @param ownername the owner of the new Farm
     * @param value the value of the new Farm asset
     * @return the created Farm
     */
	
    @Transaction()
    public Farm addNewFarm(final Context ctx, final String id, final String description,
            final String producerName, final String producerAddress, final String harvestdate) {
        
    	ChaincodeStub stub = ctx.getStub();
 
        String FarmState = stub.getStringState(id);
        
        if (!FarmState.isEmpty()) {
            String errorMessage = String.format("Farm Product %s already exists", id);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, FarmDetailsErrors.Farm_ALREADY_EXISTS.toString());
        }
        
        Farm Farm = new Farm(id, description, producerName, producerAddress, harvestdate);
        
        FarmState = genson.serialize(Farm);
        
        stub.putStringState(id, FarmState); 
        
        return Farm;
    }
 
 
    	/**
	     * Retrieves a Farm based upon Product Id from the ledger.
	     *
	     * @param ctx the transaction context
	     * @param id the key
	     * @return the Car found on the ledger if there was one
	     */
    	@Transaction()
	    public Farm queryFarmById(final Context ctx, final String id) {
	        ChaincodeStub stub = ctx.getStub();
	        String FarmState = stub.getStringState(id);
 
	        if (FarmState.isEmpty()) {
	            String errorMessage = String.format("Farm %s does not exist", id);
	            System.out.println(errorMessage);
	            throw new ChaincodeException(errorMessage, FarmDetailsErrors.Farm_NOT_FOUND.toString());
	        }
	        
	        Farm Farm = genson.deserialize(FarmState, Farm.class);
	        return Farm;
	    }
    	
        /**
   	     * Changes the owner of a Farm on the ledger.
   	     *
   	     * @param ctx the transaction context
   	     * @param id the key
   	     * @param newOwner the new owner
   	     * @return the updated Farm
   	     */
   	    @Transaction()
   	    public Farm changeFarmOwnership(final Context ctx, final String id, final String newFarmOwner) {
   	        ChaincodeStub stub = ctx.getStub();
 
   	        String FarmState = stub.getStringState(id);
 
   	        if (FarmState.isEmpty()) {
   	            String errorMessage = String.format("Farm %s does not exist", id);
   	            System.out.println(errorMessage);
   	            throw new ChaincodeException(errorMessage, FarmDetailsErrors.Farm_NOT_FOUND.toString());
   	        }
 
   	        Farm Farm = genson.deserialize(FarmState, Farm.class);
 
   	        Farm newFarm = new Farm(Farm.getProductId(), Farm.getProductDescription(), newFarmOwner, Farm.getProducerAddress(), Farm.getHarvestDate());
   	        
   	        String newFarmState = genson.serialize(newFarm);
   	        
   	        stub.putStringState(id, newFarmState);
 
   	        return newFarm;
   	    } 
}