import java.util.HashMap;
import java.util.Map;

public class ProductCatalog {
    /**intializing the hash map**/
    private Map<String, ProductSpecification> productSpecs = new HashMap<>();
    public ProductCatalog() {
        /**initializing the items and their key value pairs using a hash map**/
        ProductSpecification ps = new ProductSpecification("A001",1.50f,"bottled water");
        productSpecs.put(ps.getID(),ps);
        ProductSpecification ps2 = new ProductSpecification("A002",1.00f,"candy");
        productSpecs.put(ps2.getID(),ps2);
        ProductSpecification ps3 = new ProductSpecification("A003",2.50f,"chocolate");
        productSpecs.put(ps3.getID(),ps3);
        ProductSpecification ps4 = new ProductSpecification("A004",1.00f,"gum");
        productSpecs.put(ps4.getID(),ps4);
        ProductSpecification ps5 = new ProductSpecification("A005",2.50f,"soda");
        productSpecs.put(ps5.getID(),ps5);
        ProductSpecification ps6 = new ProductSpecification("A006",3.00f,"juice");
        productSpecs.put(ps6.getID(),ps6);
        ProductSpecification ps7 = new ProductSpecification("B001",2.50f,"popcorn");
        productSpecs.put(ps7.getID(),ps7);
        ProductSpecification ps8 = new ProductSpecification("B002",1.50f,"donut");
        productSpecs.put(ps8.getID(),ps8);
        ProductSpecification ps9 = new ProductSpecification("B003",2.00f,"pretzel");
        productSpecs.put(ps9.getID(),ps9);
        ProductSpecification ps10 = new ProductSpecification("A007",1.50f,"caramel");
        productSpecs.put(ps10.getID(),ps10);


    }
    // build productSpec (by reading from file)
    public ProductSpecification getSpecification(String id) {
        return(ProductSpecification) productSpecs.get(id);
    }
    /**This method Prints out each key value pair of the Hasp Map containing the items */
    public void viewProducts(){
        productSpecs.forEach((k,v)-> System.out.println(k + " " + v.getDescription() + " $" + v.getPrice()));

    }
    public ProductSpecification addProduct(String newProdID,String newProdName,float newPrice){
        ProductSpecification newProd = new ProductSpecification(newProdID,newPrice,newProdName);
        productSpecs.put(newProdID,newProd);
        return newProd;
    }
    /**This method removes the product passes in to the method**/

    public void removeProd(String removedItem) {
        productSpecs.remove(removedItem);

    }
    /**This method modifies the items already in the Hash Map if it exists by overriding it using the put method.**/
    public void modifyItem(String modItemCode,ProductCatalog obj,ProductSpecification psObj, float modPrice, String modName) {
        if (productSpecs.containsKey(modItemCode)){
            obj.getSpecification(modItemCode).setId(modItemCode);
            obj.getSpecification(modItemCode).setDescription(modName);
            obj.getSpecification(modItemCode).setPrice(modPrice);
            productSpecs.put(modItemCode,psObj);
        }else{
            System.out.println("!! cant modify an item thats not in the system or has been deleted");
        }

    }
}
