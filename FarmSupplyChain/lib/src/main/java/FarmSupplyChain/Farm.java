package FarmSupplyChain;
import com.owlike.genson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import java.util.Objects;
 
@DataType()
public final class Farm {
 
	private String productId = "";
 
	private String productDescription = "";
 
	private String producerName = "";
 
	private String producerAddress = "";
	
	@Property()
	private String harvestDate = "";
	
	@Property()
	private String distributerName = "";
	
	@Property()
	private String distributerAddress = "";
	
	@Property()
	private String prodToDistDate = "";
	
	@Property()
	private String retailerName = "";
	
	@Property()
	private String retailerAddress = "";
	
	@Property()
	private String distToRetaDate = "";
 
	public String getProductId() {
		return productId;
	}
 
	public String getProductDescription() {
		return productDescription;
	}
	public String getProducerName() {
		return producerName;
	}
 
	public String getProducerAddress() {
		return producerAddress;
	}
	
	public String getHarvestDate() {
		return harvestDate;
	}
	
	public String getDistributerName() {
		return distributerName;
	}
	
	public String getDistributerAddress() {
		return distributerAddress;
	}
	
	public String getProdToDistDate() {
		return prodToDistDate;
	}
	
	public String getRetailerName() {
		return retailerName;
	}
	
	public String getRetailerAddress() {
		return retailerAddress;
	}
	
	public String getDistToRetaDate() {
		return distToRetaDate;
	}
	
 
	public Farm(@JsonProperty("productId") final String productId, @JsonProperty("productDescription") final String productDescription, @JsonProperty("producerName") final String producerName, @JsonProperty("producerAddress") final String producerAddress, 
	@JsonProperty("harvestDate") final String harvestDate) {
		this.productId = productId;
		this.productDescription = productDescription;
		this.producerName = producerName;
		this.producerAddress = producerAddress;
		this.harvestDate = harvestDate;
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
 
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
 
		Farm other = (Farm) obj;
 
		return Objects.deepEquals(new String[] { getProductId(), getProductDescription(), getProducerName(), getProducerAddress(), 
		getHarvestDate(), getDistributerName(), getDistributerAddress(), getProdToDistDate(), getRetailerName(), getRetailerAddress(),   getDistToRetaDate()},
				new String[] { other.getProductId(), other.getProductDescription(), other.getProducerName(), other.getProducerAddress(),
					other.getHarvestDate(), other.getDistributerName(), other.getDistributerAddress(), other.getProdToDistDate(), other.getRetailerName(), other.getRetailerAddress(), other.getDistToRetaDate()});
	}
 
	@Override
	public int hashCode() {
		return Objects.hash(getProductId(), getProductDescription(), getProducerName(), getProducerAddress(), 
		getHarvestDate(), getDistributerName(), getDistributerAddress(), getProdToDistDate(), getRetailerName(), getRetailerAddress(),   getDistToRetaDate());
	}
 
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " [productId=" + productId + ", productDescription=" + productDescription
				+ ", producerName=" + producerName + ", producerAddress=" + producerAddress + ", harvestDate=" + harvestDate + ", distributerName=" + distributerName + ", distributerAddress=" + distributerAddress + ", prodToDistDate=" + prodToDistDate + ", retailerName=" + retailerName + ", retailerAddress=" + retailerAddress + ", distToRetaDate=" + distToRetaDate + "]";
	}
 
}