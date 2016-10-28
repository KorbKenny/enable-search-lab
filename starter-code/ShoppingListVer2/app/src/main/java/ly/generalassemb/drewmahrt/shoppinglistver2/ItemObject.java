package ly.generalassemb.drewmahrt.shoppinglistver2;

/**
 * Created by KorbBookProReturns on 10/25/16.
 */

public class ItemObject {
    private String mName, mDescription, mPrice, mType;


    public ItemObject(String name, String description, String price, String type){
        mName = name;
        mDescription = description;
        mPrice = price;
        mType = type;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getPrice() {
        return mPrice;
    }

    public String getType() {
        return mType;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public void setType(String type) {
        mType = type;
    }
}
